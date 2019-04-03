package ClientController;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import View.GUI;
import Model.Item;
import Model.Order;
import Model.Supplier;
public class Client {
	private Socket theSocket;
	private ObjectInputStream objectIn;
	private PrintWriter writeOut;
	
	public Client(String serverName, int port) {
		try {
			theSocket=new Socket(serverName,port);
			objectIn = new ObjectInputStream(theSocket.getInputStream());
			
		writeOut=new PrintWriter(theSocket.getOutputStream());
		}catch(IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public void communicate()
	{
		Object record;
		ArrayList<Supplier> s = new ArrayList<Supplier>();
		ArrayList<Item> i = new ArrayList<Item>();
		
		while(true)
		{
			try
			{
				while(true)
				{
					System.out.println("about to print");
					record = objectIn.readObject();
					
					if(record instanceof ArrayList)
					{
						ArrayList r = (ArrayList) record;
						
						if(r.isEmpty())
						{
							System.out.println("Empty");
						}
						else if(r.get(0) instanceof Supplier)
						{
							for(Supplier t : (ArrayList<Supplier>) r)
							{
								System.out.println(t);
							}
						}
						else if(r.get(0) instanceof Item)
						{
							for(Item t : (ArrayList<Item>) r)
							{
								System.out.println(t);
							}
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
						System.out.println("item reading");
						System.out.println((Item) record);
					}
					else if(record instanceof Integer)
					{
						System.out.println("Stock is: " + (Integer) record);
					}
					else
					{
						System.out.println("not correct");
					}
					
		
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
	
	public void sendString(String s) {
		writeOut.println(s);
		writeOut.flush();
		
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
	public static void main(String[] args) {
		Client c=new Client("localhost", 8099);
		GUI test = new GUI(c);
		test.buildAll();
		c.communicate();
		
	}

}