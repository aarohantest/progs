import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class ServerOps implements Runnable {

	DataInputStream in;
    PrintStream ps;
    Socket clientSocket;
    int connId;
    Server server;
	
	public ServerOps(Socket clientSocket, int connId, Server server) throws IOException {
		this.clientSocket = clientSocket;
		this.connId = connId;
		this.server = server;
		System.out.println( "Connection " + connId + " established with: " + clientSocket );
		
		in = new DataInputStream(this.clientSocket.getInputStream());
		ps = new PrintStream(this.clientSocket.getOutputStream());
	}
	
	@Override
	public void run(){
		
		String line;
		try {
			boolean serverStop = false;
			while (true) {
				line = in.readLine();
				System.out.println( "Received " + line + " from Connection " + this.connId + "." );
	            int choice = Integer.parseInt(line);
	            if (choice == -1) {
	            	serverStop = true;
	            	break;
	            }
	            if (choice == 0) break;
	            
	            switch (choice) {
					case 1: String input1 = in.readLine();
							String input2 = in.readLine();
							ps.println(input1 + "" + input2);
							break;
							
					case 2:int n1=Integer.parseInt(in.readLine());
								int n2=Integer.parseInt(in.readLine());
								int s=n1+n2;
								ps.println("" + s);
								break;
					case 3:	int n3=Integer.parseInt(in.readLine());
					int n4=Integer.parseInt(in.readLine());
					int sub=n3-n4;
					ps.println(sub);
					break;
					
					case 4:int n5=Integer.parseInt(in.readLine());
					int n6=Integer.parseInt(in.readLine());
					int mul=n5*n6;
					ps.println(mul);
					break;
					
					case 5:	int n7=Integer.parseInt(in.readLine());
					int n8=Integer.parseInt(in.readLine());
					float div=n7/n8;
					ps.println(div);
					break;
					
					case 6:int n9=Integer.parseInt(in.readLine());
					int f=1;
					for(int i=1;i<=n9;i++){
						 f=f*i;}
						ps.println(f);
						break;
						
					case 7: String str = in.readLine();
								String revstring="";
					 
								for(int i=str.length()-1;i>=0;--i){
									revstring +=str.charAt(i);
								}
					 
								
					 
								if(revstring.equalsIgnoreCase(str)){
									ps.println("The string is Palindrome");
								}
								else{
										ps.println("Not Palindrome");
								}
								break;
								
					case 8: String str1 = in.readLine();
					String str2 = in.readLine();
					boolean com = str1.equalsIgnoreCase(str2);
					if(com==true){
					ps.println("Equal");}
					else ps.println("Not Equal");
					
	            }
					
					
			}
			System.out.println( "Connection " + this.connId + " closed." );
	        in.close();
	        ps.close();
	        this.clientSocket.close();
	        
	        if (serverStop) this.server.stopServer();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
