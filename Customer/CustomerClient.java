package Customer;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import ClientController.Client;
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
public class CustomerClient extends Client
{
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
		super(serverName,port);
		app = new CustomerGUI(this);
		
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
						
							
						case "2": // List tools
							ArrayList<Item> recordTool = new ArrayList<Item>((ArrayList<Item>)objectIn.readObject());
							app.list.addElement("************************* TOOLS LIST *************************");
							if(recordTool.isEmpty())
							{
								app.list.addElement("No tools to print!");
							}
							else
							{
								for(Item t : recordTool)
								{
									app.list.addElement(t.toString());
								}
							}
							app.list.addElement("**************************************************************");
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
	public static void main(String[] args) 
	{

		CustomerClient c = new CustomerClient("localhost", 8099);
		c.app.buildAll(); // Builds GUI
		c.communicate();
	}

}
