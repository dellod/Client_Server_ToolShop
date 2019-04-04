package ClientController;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import View.GUI;
import Model.Item;
import Model.Order;
import Model.Supplier;

public class Client 
{
	GUI app;
	private Socket theSocket;
	private ObjectInputStream objectIn;
	private PrintWriter writeServer;
	private BufferedReader socketIn; // from server
	
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

	public void communicate()
	{
		ArrayList<Supplier> s = new ArrayList<Supplier>();
		ArrayList<Item> i = new ArrayList<Item>();
		
		while(true)
		{
			try
			{
				while(true)
				{
					String input = (String)objectIn.readObject();
					System.out.println("From socket " + input);
					
					//record = objectIn.readObject();
					
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
							if(recordItem6 < 0) // THIS IS ACTUALLY WRONG, THE STOCK COULD BE TURNED NEGATIVE FOR THE FIRST TIME. HAVE TO MAKE IT SENSE THIS SCENARIO
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
					/*
					if(record instanceof ArrayList)
					{
						ArrayList r = (ArrayList) record;
						
						if(r.isEmpty())
						{
							app.getList().addElement("Nothing to print!");
						}
						else if(r.get(0) instanceof Supplier)
						{
							app.getList().addElement("*********************** SUPPLIERS LIST ***********************");
							for(Supplier t : (ArrayList<Supplier>) r)
							{
								app.getList().addElement(t.toString());
							}
							app.getList().addElement("**************************************************************");
						}
						else if(r.get(0) instanceof Item)
						{
							app.getList().addElement("************************* TOOLS LIST *************************");
							for(Item t : (ArrayList<Item>) r)
							{
								app.getList().addElement(t.toString());
							}
							app.getList().addElement("**************************************************************");
						}
						else if(r.get(0) instanceof Order)
						{
							for(Order o: (ArrayList<Order>) r)
							{
									o.printOrder();
							}
						}
						
					}
					else if(record instanceof Item)
					{
						app.createMessageDialog(record.toString(), "Item found!");
						//System.out.println((Item) record);
					}
					else if(record instanceof Integer)
					{
						app.createMessageDialog("The stock for that item is " + record, "Stock count");
						//System.out.println("Stock is: " + (Integer) record);
					}
					else
					{
						app.createErrorDialog("Invalid input!", "Error");
					}*/
					
		
				}
			}
			catch(EOFException e)
			{
				System.err.println("Could not read file");
			}
			catch(ClassNotFoundException e)
			{
				e.getMessage();
			}
			catch(IOException e)
			{
				e.getMessage();
			}
		}
		
	}
	
	public void sendString(String s) 
	{
		writeServer.println(s);
		writeServer.flush();
	}
	public void listSuppliers() {
		sendString("1");
		System.out.println("list suppliers");
	}
	public void listTools() {
		sendString("2");
		System.out.println("list tools");
	}
	public void searchName(String name) {
		sendString("3");
		sendString(name);
		System.out.println("Searching name: " + name);
	}
	public void searchID(String ID) {
		sendString("4");
		sendString(ID);
		System.out.println("Searching id: " + ID);
	}
	public void check(String ID) {
		sendString("5");
		sendString(ID);
		System.out.println("Searching id for checking stock: " + ID);
	}
	public void decrease(String ID, String amount) 
	{
		sendString("6");
		sendString(ID);
		sendString(amount);
		System.out.println("Going to decrease item id: " + ID + " by this amount: " + amount);
	}
	public void order() {
		
		sendString("7");
		System.out.println("printing orders...");		
	}
	
	public static void main(String[] args) 
	{
		Client c = new Client("localhost", 8099);
		//GUI test = new GUI(c);
		c.app.buildAll();
		c.communicate();
		
	}

}