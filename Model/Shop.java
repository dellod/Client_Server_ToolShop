package Model;
import java.util.ArrayList;

/**
 * This class is a general shops, with required characteristics.
 * Has the main collection (inventory) of items, orders list, and
 * suppliers list.
 * 
 * @author Daryl, Ilyas, Will
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
	 * Constructs shop with null values for collection and suppliers, and default constructor. (DEFAULT CONSTRUCTOR) 
	 */
	public Shop()
	{
		orders = null; 
		collection = null;
		suppliers = null;
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
	
}

