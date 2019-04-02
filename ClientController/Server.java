package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	private ObjectOutputStream objectOut;
	private BufferedReader socketIn;
	private ServerSocket serverSocket;
	private Socket theSocket;
	private ExecutorService pool;
	//private Shop theShop;
	public Server(int port) {
		try {
		serverSocket=new ServerSocket(port);
		}catch(IOException e) {
			System.err.println(e.getMessage());
		}
		pool= Executors.newCachedThreadPool();
		System.out.println("Server is now working!");
	}
	public void communicate() {
		String input;
		while(true) {
			try {
			theSocket=serverSocket.accept();
			socketIn=new BufferedReader(new InputStreamReader(theSocket.getInputStream()));
			while(true) {
			
			
			input=socketIn.readLine();
			System.out.println(input);
			switch(input){
				case "1":
							System.out.println("11111");
							break;
				case "2":
					System.out.println("2222");	
							break;
				case "3":
					System.out.println("33333");	
							break;
				case "4":
					System.out.println("44444");	
							break;
				case "5":
					System.out.println("5555");	
							break;
				case "6":
					System.out.println("6666");	
							break;
				case "7":
					System.out.println("777");	
							break;
			}				
			
			}
			}
			
			catch(IOException e) {
				System.out.println(e.getMessage());
			}
			
			
			
			
			}
		}
		
	
	public static void main (String args[]) {
		Server s= new Server(8099);
		s.communicate();
	}
}