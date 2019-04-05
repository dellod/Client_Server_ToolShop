package ClientController;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import View.GUI;
import Model.Item;
import Model.Order;
import Model.Supplier;

/**
 * This is the client of the application.
 * This communicates from the user to the server to extract and change information
 * in the model. Also, utilizes and constructs the GUI to create an accessible
 * interface for the user.
 * 
 * @author Daryl, Ilyas, Will
 *
 */
public class Client 
{
	/**
	 * Main GUI application of current running client.
	 */
	GUI app;
	
	/**
	 * Main communication socket between client and server.
	 */
	private Socket theSocket;
	
	/**
	 * Main object input stream, to receive serialized objects.
	 */
	private ObjectInputStream objectIn;
	
	/**
	 * Writer that writes strings to the socket of the server.
	 */
	private PrintWriter writeServer;
	
	/**
	 * Constructs client with a name for the server, and a specified port to link
	 * the server.
	 * @param serverName name of the socket.
	 * @param port specified port to connect the client to the server.
	 */
	public Client(String serverName, int port) 
	{
		app = new GUI(this);
		try 
		{
			theSocket = new Socket(serverName,port);
			objectIn = new ObjectInputStream(theSocket.getInputStream());
			writeServer = new PrintWriter(theSocket.getOutputStream());
		}
		catch(IOException e) 
		{
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Receives and communicates specified information from the server to create
	 * specific actions/events on the GUI.
	 */
	public void communicate()
	{
		while(true)
		{
			try
			{
				while(true)
				{
					String input = (String)objectIn.readObject();
					System.out.println("From socket " + input);
		
					switch(input)
					{
						case "1": // List suppliers
							ArrayList<Supplier> recordSupp = (ArrayList<Supplier>)objectIn.readObject();
							app.getList().addElement("*********************** SUPPLIERS LIST ***********************");
							if(recordSupp.isEmpty())
							{
								app.getList().addElement("No suppliers to print!");
							}
							else
							{
								for(Supplier t : recordSupp)
								{
									app.getList().addElement(t.toString());
								}
							}
							app.getList().addElement("**************************************************************");
							break;
							
						case "2": // List tools
							ArrayList<Item> recordTool = new ArrayList<Item>((ArrayList<Item>)objectIn.readObject());
							app.getList().addElement("************************* TOOLS LIST *************************");
							if(recordTool.isEmpty())
							{
								app.getList().addElement("No tools to print!");
							}
							else
							{
								for(Item t : recordTool)
								{
									app.getList().addElement(t.toString());
								}
							}
							app.getList().addElement("**************************************************************");
							break;
							
						case "3": // Search name
							String name3 = (String)objectIn.readObject();
							Item recordItem3 = (Item)objectIn.readObject();
							if(recordItem3 == null)
							{
								System.out.println("NO ITEM");
								app.createMessageDialog("Search of " + name3 + " has NOT been found\n", "Item not found");
								break;
							}
							app.createMessageDialog("Search of " + name3 + " has been found!\n" + recordItem3.toString(), "Item found!");
							break;
							
						case "4": // Search ID
							String id4 = (String) objectIn.readObject();
							Item recordItem4 = (Item)objectIn.readObject();
							if(recordItem4 == null)
							{
								app.createMessageDialog("Search of " + id4 + " has NOT been found\n", "Item not found");
								break;
							}
							app.createMessageDialog("Search of " + id4 + " has been found!\n" + recordItem4.toString(), "Item found!");
							break;
							
						case "5": // Check stock
							String id5 = (String) objectIn.readObject();
							int recordItem5 = (Integer)objectIn.readObject();
							if(recordItem5 < 0)
							{
								app.createMessageDialog("Search of " + id5 + " has NOT been found, unable to check stock.\n", "Stock check");
								break;
							}
							app.createMessageDialog("Search of " + id5 + " has been found and the stock is: " + recordItem5 + "\n" , "Stock check");
							break;
							
						case "6": // Decrease stock
							String id6 = (String)objectIn.readObject();
							String reduced = (String)objectIn.readObject();
							String mes = (String)objectIn.readObject();
							int recordItem6 = (Integer)objectIn.readObject();
							if(recordItem6 < 0) // STOCK could be negative.
							{
								app.createMessageDialog(id6 + " could not be decreased.\n", "Decrease Quantity");
								break;
							}
							app.createMessageDialog("Item "+ id6 + "'s stock has been reduced by " + reduced + " (" + mes + ").\n The new stock is " + recordItem6 +"!\n", "Decrease Quantity");
							break;
						case "7":
							ArrayList<Order> recordOrd = (ArrayList<Order>)objectIn.readObject();
							app.getList().addElement("************************* ORDERS LIST ************************");
							if(recordOrd.isEmpty())
							{
								app.getList().addElement("No orders to print!");
							}
							else
							{
								for(Order t : recordOrd)
								{
									app.getList().addElement(t.toString());
								}
							}
							app.getList().addElement("**************************************************************");
							break;	
					}
				}
			}
			catch(EOFException e)
			{
				e.printStackTrace();
			}
			catch(ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Sends string towards the server.
	 * @param s String to be sent towards the server.
	 */
	public void sendString(String s) 
	{
		writeServer.println(s);
		writeServer.flush();
	}
	
	/**
	 * Retrieves list of suppliers (Case 1).
	 */
	public void listSuppliers() 
	{
		sendString("1");
		System.out.println("List suppliers.");
	}
	/**
	 * Retrieves list of tools (Case 2).
	 */
	public void listTools() 
	{
		sendString("2");
		System.out.println("List tools.");
	}
	
	/**
	 * Retrieves the item being searched by name (Case 3).
	 * @param name String to be searched in tools list by name.
	 */
	public void searchName(String name) 
	{
		sendString("3");
		sendString(name);
		System.out.println("Searching name: " + name);
	}
	
	/**
	 * Retrieves the item being searched by ID (Case 4).
	 * @param ID String to be searched in tools list by ID.
	 */
	public void searchID(String ID) 
	{
		sendString("4");
		sendString(ID);
		System.out.println("Searching id: " + ID);
	}
	
	/**
	 * Retrieves the stock of item being searched by ID (Case 5).
	 * @param ID String to be searched for, for its stock count.
	 */
	public void check(String ID) {
		sendString("5");
		sendString(ID);
		System.out.println("Searching id for checking stock: " + ID);
	}
	
	/**
	 * Decreases the stock of item being searched by ID (Case 6).
	 * @param ID String to be searched for to decrease stock.
	 * @param amount the amount to decrease the stock by.
	 */
	public void decrease(String ID, String amount) 
	{
		sendString("6");
		sendString(ID);
		sendString(amount);
		System.out.println("Going to decrease item id: " + ID + " by this amount: " + amount);
	}
	
	/**
	 * Prints the list of possible orders for the tool shop.
	 */
	public void order() 
	{
		
		sendString("7");
		System.out.println("printing orders...");		
	}
	
	public static void main(String[] args) 
	{
		Client c = new Client("localhost", 8099);
		c.app.buildAll(); // Builds GUI
		c.communicate();
	}

}