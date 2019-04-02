import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	private ObjectOutputStream objectOut;
	private ServerSocket serverSocket;
	private Socket theSocket;
	private ExecutorService pool;
	
	public Server(int port) {
		try {
		serverSocket=new ServerSocket(port);
		}catch(IOException e) {
			System.err.println(e.getMessage());
		}
		pool= Executors.newCachedThreadPool();
	}
	public void communicate() {
		boolean running=true;
		while(running) {
			
		}
		
	}
	public static void main (String args[]) {
		Server s= new Server(8099);
	}
}
