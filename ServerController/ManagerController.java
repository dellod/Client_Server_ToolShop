package ServerController;

import Model.Item;
import Model.OrderLine;
import Model.Shop;
import Model.UserData;
import Model.Order;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ManagerController implements Runnable 
{
	/**
	 * List of user data for log-in information.
	 */
	private ArrayList<UserData> users;
	
	/**
	 * The object output stream to send serialized objects to the client.
	 */
	private ObjectOutputStream objectOut;
	/**
	 * The input reader for string information.
	 */
	private BufferedReader socketIn;
	/**
	 * Socket that connects the client and server.
	 */
	private Socket theSocket;
	/**
	 * The main shop of the server, that the clients will have and be able
	 * to access.
	 */
	private Shop theShop;
	/**
	 * Constructs the viewController by setting the socket that connect to each client
	 * @param shop the main shop we use in this application 
	 * @param s is the socket connect server to a specified client
	 */
	public ManagerController(Socket s,Shop shop) 
	{
		theSocket = s;
		theShop = shop;
		
		//ADD pre-defined users to the database
		users = new ArrayList<UserData>();
		users.add(new UserData("daryl", "dang"));
		users.add(new UserData("will", "huang"));
		users.add(new UserData("ilyas", "ganiyev"));
	}
	/**
	 * Sends strings to the client by writing to Object stream.
	 * @param s String to be sent to the client.
	 */
	public void sendString(String s)
	{
		try {
			objectOut.writeObject(s);
			objectOut.flush();
		}catch (IOException e){
			System.out.println(e.getMessage());
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
	/**
	 * Receives and communicates specified instructions/information from the client 
	 * side to change or gather data from the Model.
	 */
	@Override
	public void run() {
		String input;
		try 
		{
			
			socketIn = new BufferedReader(new InputStreamReader(theSocket.getInputStream()));
			objectOut = new ObjectOutputStream(theSocket.getOutputStream());
			
			while(true) 
			{
				input = socketIn.readLine();
				System.out.println(input);
				
				switch(input)
				{
					/*****MAIN CLIENT*****/
					case "1": // list supplier
						sendString("1");
						try
						{
							objectOut.writeObject(theShop.getSuppliers());
							objectOut.flush();
							objectOut.reset();
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
							theShop.setCollection(theShop.getCollection().getInventory());
							objectOut.writeObject(theShop.getCollection().getInventory());
							objectOut.flush();
							objectOut.reset();
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
						try
						{
							objectOut.writeObject(theShop.getCollection().searchToolName(name));
							objectOut.flush();
							objectOut.reset();
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
						if(!isInteger(id4))
						{
							System.out.println("Not a valid ID!");
							objectOut.writeObject(null);
							objectOut.flush();
							objectOut.reset();
							break;
						}
						Item l = theShop.getCollection().searchToolId(Integer.parseInt(id4));
						
						try
						{
							objectOut.writeObject(l);
							objectOut.flush();
							objectOut.reset();
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
							objectOut.reset();
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
						if(reduce < 0 || theShop.getCollection().getInventory().get(n6).getStock() - reduce < 0){
							sendString("We do not have that many elements, sorry");
							try
							{
								objectOut.writeObject(theShop.getCollection().getInventory().get(n6).getStock());
								objectOut.flush();
								objectOut.reset();
							}
							catch(IOException e)
							{
								System.err.println("Error writing object");
								e.printStackTrace();
							}

							break;
						}
						theShop.getCollection().getInventory().get(n6).decreaseStock(reduce);
						System.out.println("Go into database_decrease");
						theShop.getDatabase().decreaseQuantity(Integer.parseInt(id6), reduce);
						int is = 0;
						if(theShop.getCollection().getInventory().get(n6).stockCheck())
						{
							sendString("Stock is below 40! Automatically ordering more...");
							OrderLine tempO = new OrderLine(theShop.getCollection().getInventory().get(n6));
							theShop.getDatabase().setQuantity(Integer.parseInt(id6), tempO.getQuantity() + theShop.getCollection().getInventory().get(n6).getStock());
							theShop.getCollection().getInventory().get(n6).setStock(tempO.getQuantity());

							is = tempO.getQuantity();
							theShop.getOrders().add(new Order(tempO));

						}
						else
						{
							sendString("");
						}
						
						try
						{
							objectOut.writeObject(theShop.getCollection().getInventory().get(n6).getStock() - is);
							objectOut.flush();
							objectOut.reset();
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
						try{

							objectOut.writeObject(theShop.getOrders());
							objectOut.flush();
							objectOut.reset();
							theShop.getOrders().clear();
						}
						catch(IOException e)
						{
							System.err.println("Error writing object");
							e.printStackTrace();
						}
						System.out.println("777");
						
						break;
						
					/*****CUSTOMER CLIENT*****/
					case "8": //home for customer.
						sendString("8");
						System.out.println("Home button selected from customer");
						try
						{
							theShop.setCollection(theShop.getCollection().getInventory());
							objectOut.writeObject(theShop.getCollection().getInventory());
							objectOut.flush();
							objectOut.reset();
						}
						catch(IOException e)
						{
							System.err.println("Error writing object");
							e.printStackTrace();
						}
						break;
					case "9": //search for customer
						sendString("9");
						String token = socketIn.readLine(); // gets name/id from customer client
						System.out.println("Searching for: " + token);
						System.out.println("Search button selected from customer");
						
						try
						{
							Item i9 = theShop.getCollection().searchToolName(token); // will try to search by tool name first 
							
							if(i9 == null)
							{
								i9 = theShop.getCollection().searchToolId(Integer.parseInt(token));
								if(i9 == null)
								{
									System.out.println("Item cannot be found for customer.");
									objectOut.writeObject(null);
								}
								else
								{
									objectOut.writeObject(i9);
								}
							}
							else
							{
								objectOut.writeObject(i9);
							}
							objectOut.flush();
							objectOut.reset();
						}
						catch(IOException e)
						{
							System.err.println("Error writing object");
							e.printStackTrace();
						}
						
						break;
					case "10":
						sendString("10");
						String buy = socketIn.readLine(); // get name of selected item.
						System.out.println("Buying: " + buy);
						int id10 = theShop.getCollection().searchToolName(buy).getId();
						
						if(theShop.getCollection().searchToolId(id10).getStock() == 0)
						{
							sendString("There is no more stock of this tool!");
							break;
						}
						theShop.getCollection().searchToolId(id10).decreaseStock(1);
						sendString("Purchase succesful! You now have 1 '" + buy + "' and the new stock is " + String.valueOf(String.valueOf(theShop.getCollection().searchToolId(id10).getStock()) + "!"));
						break;
					case "11":
						sendString("11");
						String user = socketIn.readLine();
						String pass = socketIn.readLine();
						boolean exit = false;
						for(UserData u: users)
						{
							if(u.authenticate(user, pass))
							{
								System.out.println("Login approved");
								objectOut.writeObject(new Boolean(true));
								exit = true;
							}
						}
						if(!exit)
						{
							System.out.println("Login not approved");
							objectOut.writeObject(new Boolean(false));
						}
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
