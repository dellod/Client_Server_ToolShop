package Model;
import java.util.ArrayList;

/**
 * This class is a general shops, with required characteristics.
 * Has the main collection (inventory) of items, orders list, and
 * suppliers list.
 * 
 * @author Daryl Dang
 * @since Feb 5, 2019
 */
public class Shop
{
	/**
	 * The orders that are currently active for the store.
	 */
	private ArrayList<Order> orders;
	
	/**
	 * The main collection of items of the shop.
	 */
	private Inventory collection;
	
	/**
	 * The main suppliers list that provide items.
	 */
	private ArrayList<Supplier> suppliers;

	/**
	 *
	 */
	private Database database;
	
	/**
	 * Constructs shop with null values for collection and suppliers, and reads information from text,
	 * and default constructor. (DEFAULT CONSTRUCTOR) 
	 */
	public Shop()
	{
		orders = new ArrayList<Order>(); 
		collection = null;
		suppliers = null;
		database = new Database();
		readText(this);
	}
	
	/**
	 * Gets the variable orders from class Shop.
	 * @return orders of type ArrayList<Order>.
	 */
	public ArrayList<Order> getOrders()
	{
		return orders;
	}

	/**
	 * Gets the variable collection from class Shop.
	 * @return collection of type Inventory.
	 */
	public Inventory getCollection()
	{
		return collection;
	}
	
	/**
	 * Gets the variable suppliers from class Shop.
	 * @return suppliers of type ArrayList<Supplier>.
	 */
	public ArrayList<Supplier> getSuppliers()
	{
		return suppliers;
	}
	
	/**
	 * Sets the suppliers variable to s.
	 * @param s is  of type ArrayList<Supplier> that is to be set equal to suppliers.
	 */
	public void setSuppliers(ArrayList<Supplier> s)
	{
		suppliers = s;
	}
	
	/**
	 * Sets the collection variable to i.
	 * @param i is of type ArrayList<Item> that is to be set equal to collection through one of 
	 * Inventory's constructor.
	 */
	public void setCollection(ArrayList<Item> i)
	{
		collection = new Inventory(i);
	}
	
	/**
	 * Sets the orders variable to o.
	 * @param o is of type of ArrayList<Order> that is to be set equal to orders.
	 */
	public void setOrders(ArrayList<Order> o)
	{
		orders = o;
	}
	/**
	 * Adds o to the the list orders.
	 * @param o of type Order to be added to the end of the list of orders.
	 */
	public void addOrder(Order o)
	{
		orders.add(o);
	}

	public Database getDatabase(){
		return database;
	}
	/**
	 * Read all the information form text file
	 * @param theShop the shop that will store the information
	 */
	public void readText(Shop theShop) {

		try
		{

			ArrayList<Item> temp1 = database.getItemList();
			theShop.setCollection(temp1);
			System.out.println("items successfully loaded from database");
			
			ArrayList<Supplier> temp2 = database.getSupplierList();
			theShop.setSuppliers(temp2);
			System.out.println("suppliers successfully loaded from database");

		}
		catch(Exception e)
		{
			System.out.println("File not found");
		}
	}
	

	

	
	
}
