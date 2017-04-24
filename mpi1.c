#include<stdio.h>
#include<string.h>
#include<malloc.h>
#include<math.h>
#include<stdlib.h>
#include<mpi.h>

void mpi_call(int *argv, char **argc);

int main(int argv, char *argc)
{
	
	mpi_call(&argv, &argc);
	return 0;
}

void mpi_call(int *argv, char **argc)
{
	int n, ml, sl, sl1, i, j, k;
	int rank, size, tag=10;
	char *str, *rev;
	char *temp[n];
	MPI_Status status;
	 	
	str = (char *)malloc(sizeof(str));
	rev = (char *)malloc(sizeof(str));
	
	MPI_Init(argv, &argc);
	MPI_Comm_size(MPI_COMM_WORLD, &size);
	MPI_Comm_rank(MPI_COMM_WORLD, &rank);
	
	n = size-1;
	
	if(rank == 0)
	{
		printf("\nEnter a string: ");
		scanf("%s",str);
			
		ml = strlen(str);	//ml = length of main string
		sl = ml/n;		//sl = length of substrings
		j=0;
		
		/*The division into substrings is as follows:
		example: string is 123456789 and number of processes is 5(slaves=4)
		slave 1: 12
		slave 2: 34
		slave 3: 56
		slave 4: 789;
		sl has value of slaves 1, 2, 3 (i.e 2);
		sl1 has value of slave 4 (i.e. 3)*/
		
		
		//sending
		
		//send to all slave process except the last slave
		for(i=0; i<n-1; i++)
		{
			
			temp[i] = (char *)malloc(sizeof(char)*sl);
			k=0;
			while(k<sl)
			{
				temp[i][k] = str[j];
				k++, j++;
			}
			temp[i][k] = '\0';

			MPI_Send(temp[i], strlen(temp[i]), MPI_CHAR, i+1, tag, MPI_COMM_WORLD);
			printf("\nSending %s to slave %d %d", temp[i], i+1, sl);
		}
		
		
		//repeat the above steps for the last slave
		k = 0;
		temp[i] = (char *)malloc((ml-sl*i)*sizeof(char));
		
		for(j;j<ml;j++)
		{			
			temp[i][k] = str[j];
			k++;
		}
	
		temp[i][k] = '\0';
		sl1 = strlen(temp[i]);
		MPI_Send(temp[i], sl1, MPI_CHAR, i+1, tag, MPI_COMM_WORLD);
		printf("\nSending %s to slave %d", temp[i], i+1);	
		
		
		//receiving
		
		//receive from the last slave
		MPI_Recv(temp[n-1], sl1+1, MPI_CHAR, n, tag, MPI_COMM_WORLD, &status);
		j = 0;
		
		k = 0;	
		while(j<sl1)
		{
			rev[k] = temp[n-1][j];
			j++;
			k++;
		}
		
		
		//receive from all other slaves
		for(i=n-2; i>=0; i--)
		{
			MPI_Recv(temp[i], sl+1, MPI_CHAR, i+1, tag, MPI_COMM_WORLD, &status);
			j = 0;
			
			while(j<sl)
			{
				rev[k] = temp[i][j];
				j++;
				k++;
			}
		}
		
		
		rev[k] = '\0';
		printf("\n\nThe reversed string is '%s'", rev);
			
		
	}
	
	
	else if(rank > 0)
	{
		
			char *arr = NULL, c;
			int count;
						
			MPI_Probe(0, tag, MPI_COMM_WORLD, &status);
			MPI_Get_count(&status, MPI_CHAR, &count);
			
			arr = (char *)malloc(count*sizeof(char));
			
			MPI_Recv(arr,count,MPI_CHAR,0,tag,MPI_COMM_WORLD,&status);
			
			arr[count] = '\0';
			printf("\nRank %d received = %s", rank, arr);
			
			
			i=0, j=count-1;
			while(i<j)
			{
				c=arr[i];
				arr[i]=arr[j];
				arr[j]=c;
				i++, j--;
			}
			
			printf("\nRank %d sending = %s", rank, arr);
			MPI_Send(arr, count, MPI_CHAR, 0, tag, MPI_COMM_WORLD);
			
			
			
		
	}	
	
	MPI_Finalize();	
	fflush(NULL);
	printf("\n");
	
} 
	

