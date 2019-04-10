package ServerController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import Model.Item;
import Model.Order;
import Model.OrderLine;
import Model.Shop;

public class ViewController implements Runnable {

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
	public ViewController(Socket s,Shop shop) {
		theSocket=s;
		theShop=shop;
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
						//System.out.println("3333333");
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
						//System.out.println("444444");
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
						try
						{
							objectOut.writeObject(theShop.getOrders());
							objectOut.flush();
							objectOut.reset();
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
