package ServerController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Model.Item;
import Model.Order;
import Model.Shop;
import Model.Supplier;

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
		{	//System.out.println(InetAddress.getLocalHost().getHostAddress());
			//socket.getRemoteSocketAddress().toString();
			serverSocket = new ServerSocket(port);
			//serverSocket = new ServerSocket(port);
			
		}
		catch(IOException e) 
		{
			e.printStackTrace();
		}
		pool= Executors.newCachedThreadPool();
		System.out.println("Server is now working!");
		
		theShop = new Shop();
		
		// Main store.
		//theShop.setOrders(new ArrayList<Order>());
		/********************READING TXT FILES********************/ // CHANGE TO DATABASE LATER.
		theShop = new Shop(); // Main store.
		theShop.setOrders(new ArrayList<Order>());
		Item i; // Temporary item used in reading file.txt.
		Supplier sup; // Temporary supplier used in reading file.txt.
		String s; //Temporary string used in reading file.txt.
		
		try
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("items.txt"))); // Links items.txt to BufferedReader
			BufferedReader sn = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("suppliers.txt"))); // Links suppliers.txt to BufferedReader
			// *** .txt files must be in the same folder as the class files.
			
			ArrayList<Item> temp1 = new ArrayList<Item>();
			while(true) // Reading in tools
			{
				s = in.readLine();
				if(s == null) // end of file
				{
					break;
				}
				i = new Item(s);
				temp1.add(i);
			}
			theShop.setCollection(temp1);
			System.out.println("items.txt successfully loaded.");
			
			ArrayList<Supplier> temp2 = new ArrayList<Supplier>();
			while(true) // Reading in suppliers
			{
				s = sn.readLine();
				if(s == null)
				{
					break;
				}
				sup = new Supplier(s);
				temp2.add(sup);
			}
			theShop.setSuppliers(temp2);
			System.out.println("suppliers.txt successfully loaded.");
			
			in.close();
			sn.close();
		}
		catch(IOException e)
		{
			System.out.println("File not found");
		}
		
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