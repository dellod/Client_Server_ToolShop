package ServerController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
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
	
	public Server(int port) 
	{
		try {
		serverSocket=new ServerSocket(port);
		}catch(IOException e) {
			System.err.println(e.getMessage());
		}
		pool= Executors.newCachedThreadPool();
		System.out.println("Server is now working!");
		
		
		
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
			while(true)
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
			while(true)
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
	public void communicate() {
		String input;
		while(true) {
			try {
				theSocket=serverSocket.accept();
				socketIn=new BufferedReader(new InputStreamReader(theSocket.getInputStream()));
				objectOut = new ObjectOutputStream(theSocket.getOutputStream());
				
				while(true) {
					
					input=socketIn.readLine();
					System.out.println(input);
					switch(input){
						case "1": // list supplier
							ArrayList<Supplier> s = theShop.getSuppliers(); // returns arraylist suppliers
							try
							{
								objectOut.writeObject(s);
								objectOut.flush();
							}
							catch(IOException e)
							{
								System.err.println("Error writing object");
							}
							break;
						case "2": // list tools
							ArrayList<Item> i = theShop.getCollection().getInventory(); // returns arraylist tools
							try
							{
								objectOut.writeObject(i);
								objectOut.flush();
							}
							catch(IOException e)
							{
								System.err.println("Error writing object");
							}
							
				
							break;
						case "3": // search name
							String name = socketIn.readLine();
							Item t = theShop.getCollection().searchToolName(name); // does not error check yet
							System.out.println("3333333");
							try
							{
								objectOut.writeObject(t);
								objectOut.flush();
							}
							catch(IOException e)
							{
								System.err.println("Error writing object");
							}
							break;
						case "4": // search id

							String id4 = socketIn.readLine();
							System.out.println("444444");
							Item l = theShop.getCollection().searchToolId(Integer.parseInt(id4));
							
							try
							{
								objectOut.writeObject(l);
								objectOut.flush();
							}
							catch(IOException e)
							{
								System.err.println("Error writing object");
							}
							break;
						case "5": // check
							String id5 = socketIn.readLine();
							int n5 = theShop.getCollection().retrieveIndex(theShop.getCollection().searchToolId(Integer.parseInt(id5)));
							try
							{
								objectOut.writeObject(theShop.getCollection().getInventory().get(n5).getStock());
								objectOut.flush();
							}
							catch(IOException e)
							{
								System.err.println("Error writing object");
							}
							break;
						case "6": // decrease
							String in6 = socketIn.readLine();
							int id6 = Integer.parseInt(in6);
							int n6 = theShop.getCollection().retrieveIndex(theShop.getCollection().searchToolId(id6));
							int reduce = Integer.parseInt(socketIn.readLine());
							theShop.getCollection().getInventory().get(n6).decreaseStock(reduce);
							
							if(theShop.getCollection().getInventory().get(n6).stockCheck())
							{
								System.out.println("Stock is below 40! Automatically ordering more...");
								OrderLine tempO = new OrderLine(theShop.getCollection().getInventory().get(n6));
								theShop.getOrders().add(new Order(tempO));
							}
							
							try
							{
								objectOut.writeObject(theShop.getCollection().getInventory().get(n6).getStock());
								objectOut.flush();
							}
							catch(IOException e)
							{
								System.err.println("Error writing object");
							}
							
							System.out.println("6666");	
							break;
						case "7": // orders
							try
							{
								objectOut.writeObject(theShop.getOrders());
								objectOut.flush();
							}
							catch(IOException e)
							{
								System.err.println("Error writing object");
							}
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