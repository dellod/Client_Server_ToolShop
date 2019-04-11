package ClientCustomer;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.WindowConstants;

import ClientController.Client;
import Model.Item;
import Model.UserData;
import ViewCustomer.CustomerGUI;
/**
 * This is the client of the application for customer.
 * This communicates from the user to the server to extract and change information
 * in the model. Also, utilizes and constructs the GUI to create an accessible
 * interface for the user.
 * 
 * @author Daryl, Ilyas, Will
 *
 */

public class CustomerClient extends Client
{
	/**
	 * The array that stores users' user name/password.
	 */
	ArrayList<UserData> users;
	/**
	 * Main GUI application of current running client.
	 */
	CustomerGUI app;
	/**
	 * Constructs client with a name for the server, and a specified port to link
	 * the server.
	 * @param serverName name of the socket.
	 * @param port specified port to connect the client to the server.
	 */
	public CustomerClient(String serverName, int port) 
	{
		super(serverName, port);
		app = new CustomerGUI(this); //problem in constructor.
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
						case "8": // List tools
							app.getList().removeAllElements();
							app.getList().addElement("Welcome to the toolshop!");
							app.getList().addElement("TOOL: TESTING, PRICE: 99.99, STOCK: 321"); //testing w/o database, remove later.
							app.getList().addElement("*** Tools Available! ***");
							ArrayList<Item> recordTool = new ArrayList<Item>((ArrayList<Item>)objectIn.readObject());
							if(recordTool.isEmpty() || recordTool == null)
							{
								app.getList().addElement("No tools available!");
							}
							else
							{
								for(Item t : recordTool)
								{
									app.getList().addElement(t.customerString());
								}
							}
							app.getList().addElement("************************");
							break;
						case "9": // Search
							app.getList().removeAllElements();
							app.getList().addElement("Search for tool...");
							Item i9 = (Item)objectIn.readObject();
							if(i9 == null)
							{
								app.getList().addElement("*** Item was not found! Search again. ***");
								break;
							}
							app.getList().addElement(i9.customerString());
							break;
						case "10": // Buy
							System.out.println("buying 1....");
							String message = (String)objectIn.readObject();
							app.createMessageDialog(message, "Purchase Order");
							break;
						case "11": // Login stuff
							users = new ArrayList<UserData>();
							boolean status = (Boolean)objectIn.readObject();
							//System.out.println("test");
							if(status)
							{
								System.out.println("Login successful");
								app.getLogin().setVisible(false);
								app.createMessageDialog("Successfully logged in!", "Login");
								break;
							}
							System.out.println("Login not successful");
							app.createMessageDialog("Wrong username and/or password. Try Again.", "Login");
							app.getLogin().setVisible(true);
							break;
						default:
							System.out.println("Option doesn't exist for the Customer client");
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
	 * Retrieves list of tools.
	 */
	public void listToolsCustomer() 
	{
		sendString("8");
		System.out.println("List tools for Customer.");
	}
	/**
	 * Retrieves the item being searched by name .
	 * @param name String to be searched in tools list by name.
	 */
	public void searchCustomer(String token)
	{
		sendString("9");
		sendString(token);
		System.out.println("Search tool for Customer.");
	}
	/**
	 * Decreases the stock of item being searched.
	 * @param s the name of the tool to be bought.
	 */
	public void buyCustomer(String s)
	{
		sendString("10");
		sendString(s);
		System.out.println("Buying " + s + " for Customer.");
	}
	/**
	 * Pass users user name/password to the server to see if it match.
	 * @param user the user name
	 * @param pass the password
	 */
	public void loginInfo(String user, String pass)
	{
		sendString("11");
		sendString(user);
		sendString(pass);
		System.out.println("Retrieving user database.");
	}
	
	public static void main(String[] args)
	{
		CustomerClient c = new CustomerClient("10.13.190.36", 7000);
		
		c.app.buildAll();
		
		c.communicate();
	}
}