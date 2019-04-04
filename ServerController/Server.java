package ServerController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Model.Item;
import Model.Order;
import Model.OrderLine;
import Model.Shop;
import Model.Supplier;

public class Server 
{
	private ObjectOutputStream objectOut;
	private BufferedReader socketIn;
	private ServerSocket serverSocket;
	private Socket theSocket;
	private ExecutorService pool;
	private Shop theShop;
	private PrintWriter writeClient; // send strings to client
	
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
		
		/********************READING TXT FILES********************/
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
	 * Checks to see if a string is numeric in the sense of an Integer.
	 * @param s string to be checked to be Integer.
	 * @return true if Integer, false otherwise.
	 */
	public boolean isInteger(String s)
	{
		try
		{
			Integer.parseInt(s);
			return true;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}


	public void sendString(String s)
	{
		try {
			objectOut.writeObject(s);
			objectOut.flush();
		}catch (IOException e){
			System.out.println(e.getMessage());
		}
	}
	
	public void communicate() 
	{
		String input; // From client
		
		while(true) 
		{
			try 
			{
				theSocket = serverSocket.accept();
				socketIn = new BufferedReader(new InputStreamReader(theSocket.getInputStream()));
				objectOut = new ObjectOutputStream(theSocket.getOutputStream());
				while(true) 
				{
					input = socketIn.readLine();
					System.out.println(input);
					
					switch(input)
					{
						case "1": // list supplier
							sendString("1");
							try
							{
								objectOut.writeObject(theShop.getSuppliers());
								objectOut.flush();
							}
							catch(IOException e)
							{
								System.err.println("Error writing object");
								e.printStackTrace();
							}
							
							break;
						
						case "2": // list tools
							sendString("2");
							try
							{
								objectOut.writeObject(theShop.getCollection().getInventory());
								objectOut.flush();
							}
							catch(IOException e)
							{
								System.err.println("Error writing object");
								e.printStackTrace();
							}
							
							break;
							
						case "3": // search name
							sendString("3");
							String name = socketIn.readLine(); // need to get name from client
							sendString(name);
							//System.out.println("3333333");
							try
							{
								objectOut.writeObject(theShop.getCollection().searchToolName(name));
								objectOut.flush();
							}
							catch(IOException e)
							{
								System.err.println("Error writing object");
								e.printStackTrace();
							}
							
							break;
							
						case "4": // search id
							sendString("4");
							String id4 = socketIn.readLine();
							sendString(id4);
							//System.out.println("444444");
							if(!isInteger(id4))
							{
								System.out.println("Not a valid ID!");
								objectOut.writeObject(null);
								objectOut.flush();
								break;
							}
							Item l = theShop.getCollection().searchToolId(Integer.parseInt(id4));
							
							try
							{
								objectOut.writeObject(l);
								objectOut.flush();
							}
							catch(IOException e)
							{
								System.err.println("Error writing object");
								e.printStackTrace();
							}
							
							break;
							
						case "5": // check
							sendString("5");
							String id5 = socketIn.readLine();
							sendString(id5);
							if(!isInteger(id5) || (theShop.getCollection().searchToolId(Integer.parseInt(id5)) == null))
							{
								System.out.println("Not a valid ID!");
								objectOut.writeObject(-1);
								objectOut.flush();
								break;
							}
							int n5 = theShop.getCollection().retrieveIndex(theShop.getCollection().searchToolId(Integer.parseInt(id5)));
							
							try
							{
								objectOut.writeObject(theShop.getCollection().getInventory().get(n5).getStock());
								objectOut.flush();
							}
							catch(IOException e)
							{
								System.err.println("Error writing object");
								e.printStackTrace();
							}
							
							break;
							
						case "6": // decrease
							sendString("6");
							String id6 = socketIn.readLine();
							sendString(id6);
							String r6 = socketIn.readLine();
							sendString(r6);
							
							if(!isInteger(id6) || !isInteger(r6) || (theShop.getCollection().searchToolId(Integer.parseInt(id6)) == null))
							{
								System.out.println("Not valid");
								sendString("");
								objectOut.writeObject(-1);
								objectOut.flush();
								break;
							}
							
							int n6 = theShop.getCollection().retrieveIndex(theShop.getCollection().searchToolId(Integer.parseInt(id6)));
							int reduce = Integer.parseInt(r6);
							theShop.getCollection().getInventory().get(n6).decreaseStock(reduce);
							
							if(theShop.getCollection().getInventory().get(n6).stockCheck())
							{
								sendString("Stock is below 40! Automatically ordering more...");
								OrderLine tempO = new OrderLine(theShop.getCollection().getInventory().get(n6));
								theShop.getOrders().add(new Order(tempO));
							}
							else
							{
								sendString("");
							}
							
							try
							{
								objectOut.writeObject(theShop.getCollection().getInventory().get(n6).getStock());
								objectOut.flush();
							}
							catch(IOException e)
							{
								System.err.println("Error writing object");
								e.printStackTrace();
							}
							
							//System.out.println("6666");	
							break;
							
						case "7": // orders
							sendString("7");
							try
							{
								objectOut.writeObject(theShop.getOrders());
								objectOut.flush();
							}
							catch(IOException e)
							{
								System.err.println("Error writing object");
								e.printStackTrace();
							}
							System.out.println("777");
							
							break;
					}				
			
				}
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