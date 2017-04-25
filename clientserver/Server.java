import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author gaurav
 */
public class Server {
	public static final int PORT = 6969;
	public static final String HOST = "localhost";
	
	ServerSocket serverSocket;
	Socket clientSocket;
	static int connId = 0;

	/**
	 * @param args
	 * @throws IOException 
	 */
		
	public static void main(String[] args) throws IOException {
		Server server = new Server();
		server.startServer();
	}

	private void startServer() throws IOException {
		serverSocket = new ServerSocket(PORT);
		System.out.println( "Server is started and is waiting for connections." );
		
		// Whenever a connection is received, start a new thread to process the connection
		// and wait for the next connection.
		
		while (true) {
			clientSocket = serverSocket.accept();
			connId++;
			ServerOps conn = new ServerOps(clientSocket, connId, this);
			new Thread(conn).start();
		}
	}
	
	public void stopServer() {
		System.out.println( "Server stoping" );
		System.exit(0);
	}
}
