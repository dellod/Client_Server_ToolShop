import java.util.*;
import java.io.*;

/**
 * This is the front-end of the program (main application) that the user 
 * interacts with for certain manipulation of the back-end.
 * 
 * @author Daryl Dang
 * @since Feb 5, 2019
 */
public class main 
{
	/**
	 * Displays the options available for the user to choose from (static method).
	 */
	public static void printOptions()
	{
		System.out.println("\n(1) List all tools.");
		System.out.println("(2) Search for tool by tool name.");
		System.out.println("(3) Search for tool by tool ID.");
		System.out.println("(4) Check item quantity.");
		System.out.println("(5) Decrease item quantity.");
		System.out.println("(6) Print orders.");
		System.out.println("(7) Quit program.");
	}
	
	public static void main(String[] args)
	{
		Shop store = new Shop(); // Main store.
		store.setOrders(new ArrayList<Order>());
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
			store.setCollection(temp1);
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
			store.setSuppliers(temp2);
			System.out.println("suppliers.txt successfully loaded.");
			
			in.close();
			sn.close();
		}
		catch(IOException e)
		{
			System.out.println("File not found");
		}
	
		while(true)
		{
			int choice;
			printOptions();
			System.out.println("\nPlease enter choice number: ");
			Scanner scan = new Scanner(System.in);
			String input = scan.nextLine();
			choice  = Integer.parseInt(input);
			
			switch(choice)
			{
				case 1: // List all tools.
					for(int j = 0; j < store.getCollection().getInventory().size(); j++)
					{
						store.getCollection().listItem(j);
					}
					break;
					
				case 2: // Search for tool by tool name.
					System.out.println("Please enter tool name: ");
					String toolName = scan.nextLine();
					int n2 = store.getCollection().retrieveIndex(store.getCollection().searchToolName(toolName));
					if(n2 < 0)
					{
						break;
					}
					System.out.println("Item found! Item: " + store.getCollection().getInventory().get(n2).toStringItem());
					break;
					
				case 3: // Search for tool by tool ID.
					System.out.println("Please enter tool ID: ");
					String in3 = scan.nextLine();
					int id3 = Integer.parseInt(in3);
					int n3 = store.getCollection().retrieveIndex(store.getCollection().searchToolId(id3));
					if(n3 < 0)
					{
						break;
					}
					System.out.println("Item found! Item: " + store.getCollection().getInventory().get(n3).toStringItem());
					break;
					
				case 4: // Check item quantity.
					System.out.println("Please enter tool ID to check quantity: ");
					String in4 = scan.nextLine();
					int id4 = Integer.parseInt(in4);
					System.out.println("...Checking quantity...");
					int n4 = store.getCollection().retrieveIndex(store.getCollection().searchToolId(id4));
					if(n4 < 0)
					{
						break;
					}
					System.out.println("Quantity: " + store.getCollection().getInventory().get(n4).getStock());
					break;
					
				case 5: // Decrease item quantity.
					System.out.println("Type the ID of the item you would like to reduce quantity of: ");
					String in5 = scan.nextLine();
					int id5 = Integer.parseInt(in5);
					int n5 = store.getCollection().retrieveIndex(store.getCollection().searchToolId(id5));
					if(n5 < 0)
					{
						break;
					}
					System.out.println("How much of the quantity would you like to reduce? ");
					in5 = scan.nextLine();
					int reduce = Integer.parseInt(in5);
					store.getCollection().getInventory().get(n5).decreaseStock(reduce);
					System.out.println("Quantity reduced! NEW quantity: " + store.getCollection().getInventory().get(n5).getStock());
					if(store.getCollection().getInventory().get(n5).stockCheck())
					{
						System.out.println("Stock is below 40! Automatically ordering more...");
						OrderLine tempO = new OrderLine(store.getCollection().getInventory().get(n5));
						store.getOrders().add(new Order(tempO));
					}
					break;
					
				case 6: // Print orders.
					if(store.getOrders().size() == 0)
					{
						System.out.println("No current orders.");
						break;
					}
					for(int j = 0; j < store.getOrders().size(); j++)
					{
						store.getOrders().get(j).printOrder();
					}
					break;
					
				case 7: // Quit program.
					System.out.println("Program terminated.");
					System.exit(1);
			}
		
		}
		
	}
}
