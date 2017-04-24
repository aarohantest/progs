package remoteComm;

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
