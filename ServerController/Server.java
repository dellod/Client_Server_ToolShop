package ServerController;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import Model.Shop;

/**
 * This is the main server of the application.
 * This communicates with the client to send/update information on both sides,
 * while also retrieving/updating information in the model.
 * 
 * @author Daryl, Ilyas, Will
 *
 */

public class Server
{
	/**
	 * The main server socket.
	 */
	private ServerSocket serverSocket;
	/**
	 * Pool for threads.
	 */
	private ExecutorService pool;
	/**
	 * The main shop of the server, that the clients will have and be able
	 * to access.
	 */
	private Shop theShop;
	/**
	 * Constructs Server by setting the serverSocet to a new ServerSocket
	 * via port number.
	 * @param port is the number to set the serverSocket to.
	 */
	public Server(int port) 
	{
		try 
		{
			serverSocket = new ServerSocket(port);
		}
		catch(IOException e) 
		{
			e.printStackTrace();
		}
		pool= Executors.newCachedThreadPool();
		System.out.println("Server is now working!");
		
		theShop = new Shop(); // Main store.
		//theShop.setOrders(new ArrayList<Order>());
		//theShop.readText(theShop);
		
	}
	/**
	 * Always waiting for a new client to be connected, then start the application in the ViewController
	 */
	public void communicate() 
	{
		while(true) 
		{
			try 
			{
				ViewController v=new ViewController(serverSocket.accept(),theShop);
				pool.execute(v);
				
				
			}
			catch(IOException e) 
			{
				System.out.println(e.getMessage());
			}
		}
	}
	public static void main (String args[]) 
	{
		Server s= new Server(8099);
		s.communicate();
	}

	
}