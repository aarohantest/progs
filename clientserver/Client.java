import java.util.Scanner;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author gaurav
 */
public class Client {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		String host = "localhost";
		int port = 6969;
		
		Socket clientSocket = null;
		DataInputStream in = null;
		PrintStream ps = null;
		
		clientSocket = new Socket(host, port);
		in = new DataInputStream(clientSocket.getInputStream());
		ps = new PrintStream(clientSocket.getOutputStream());
		
		if (clientSocket == null || in == null || ps == null) {
			System.err.println( "Something is wrong. One variable is null." );
		    return;
		}
		
		try {
			while (true) {	
				System.out.println("1.String Concatination 2.Addition 3.Substractin 4.Multiplication 5.division 6.factorial 7.Palindrome 8.String comparision");
				System.out.print( "Enter your choice (0 to stop connection, -1 to stop server): " );
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
			   Scanner i=new Scanner(System.in);
				String input = br.readLine();
				ps.println(input);
				
				int choice = Integer.parseInt(input);
				if (choice == 0 || choice == -1)
					break;
				
				switch (choice) {
					case 1: System.out.println("Enter first string: ");
							String input1 = br.readLine();
							System.out.println("Enter second string: ");
							String input2 = br.readLine();
							ps.println(input1);
							ps.println(input2);
							break;
					case 2: System.out.println("Enter first no: ");
							int n1 = Integer.parseInt(br.readLine());
							System.out.println("Enter second no: ");
							int n2 = Integer.parseInt(br.readLine());
							ps.println(n1);
							ps.println(n2);
							break;
					case 3: System.out.println("Enter first no: ");
					Integer n3	= i.nextInt();
					System.out.println("Enter second no: ");
					Integer n4	= i.nextInt();
					ps.println(n3);
					ps.println(n4);
					break;
					case 4: System.out.println("Enter first no: ");
					Integer n5	= i.nextInt();
					System.out.println("Enter second no: ");
					Integer n6	= i.nextInt();
					ps.println(n5);
					ps.println(n6);
					break;
					case 5: System.out.println("Enter first no: ");
					Integer n7	= i.nextInt();
					System.out.println("Enter second no: ");
					Integer n8	= i.nextInt();
					ps.println(n7);
					ps.println(n8);
					break;
					case 6:System.out.println("Enter the no: ");
					Integer n9	= i.nextInt();
					ps.println(n9);
					break;
					case 7:System.out.println("Enter the string: ");
					String str = br.readLine();
					ps.println(str);
					break;
					case 8:System.out.println("Enter the string: ");
					String str1 = br.readLine();
					System.out.println("Enter the string: ");
					String str2 = br.readLine();
					ps.println(str1);
					ps.println(str2);
					break;
					
					default:System.out.println("Wrong Choice");
							break;
							
							
				}
				
				String resultLine = in.readLine();
				System.out.println("Server returns the answer as: " + resultLine);
			}
			
			in.close();
			ps.close();
			clientSocket.close();
			
		} catch (UnknownHostException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
