package Model;
import java.util.ArrayList;

/**
 * This class provides a list of items that are available in the inventory.
 * 
 * @author Daryl, Ilyas, Will
 * @since Feb 5, 2019
 */
public class Inventory 
{
	/**
	 * List of items which is of type ArrayList<Item>
	 */
	private ArrayList<Item> inventory;
	
	/**
	 * Constructs Inventory by setting inventory to items.
	 * @param items of type ArrayList<Item> to be set to inventory.
	 */
	public Inventory(ArrayList<Item> items)
	{
		inventory = items;
	}
	
	/**
	 * Get variable inventory from class Inventory.
	 * @return inventory of type ArrayList<Item>.
	 */
	public ArrayList<Item> getInventory()
	{
		return inventory;
	}
	

	/**
	 * Retrieves item at specified index as a string.
	 * @param index of the inventory.
	 * @return String at specified index of inventory.
	 */
	public String listItem(int index)
	{
		return inventory.get(index).toString(); 
	}

	/**
	 * Adds item i to the end of inventory list.
	 * @param i is the item that is to be added to the end of the inventory list.
	 */
	public void addItem(Item i)
	{
		inventory.add(i);
	}
	
	/**
	 * Deletes item by searching its ID in the inventory list.
	 * @param id is the item ID of the item that is going to be deleted.
	 */
	public Item deleteItem(int id)
	{
		int indexDel;
		Item delItem = searchToolId(id);
		indexDel = retrieveIndex(delItem);
		if(indexDel < 0)
		{
			return null; 
		}
		inventory.remove(indexDel);
		return delItem;
	}
	
	/**
	 * Deletes item by searching its name in the inventory list.
	 * @param name is the name of the item that is going to be deleted.
	 */
	public Item deleteItem(String name)
	{
		int indexDel;
		Item delItem = searchToolName(name);
		indexDel = retrieveIndex(delItem);
		if(indexDel < 0)
		{
			return null;
		}
		inventory.remove(indexDel);
		return delItem;
	}
	
	/**
	 * Searches inventory list by its name.
	 * @param name is the name of the item that is being searched for.
	 * @return the item by the corresponding name in the list.
	 */
	public Item searchToolName(String name) 
	{
		Item item = null;
		int i = 0;
		while(i < inventory.size())
		{
			if(inventory.get(i).getName().equals(name))
			{
				item = inventory.get(i);
				break;
			}
			i++;
		}
		return item;
	}
	
	/**
	 * Searches inventory list by its ID number.
	 * @param idNum is the id of the item that is being searched for.
	 * @return the item by the corresponding id in the list.
	 */
	public Item searchToolId(int idNum)
	{
		Item item = null;
		int i = 0;
		while(i < inventory.size())
		{
			if(inventory.get(i).getId() == idNum)
			{
				item = inventory.get(i);
				break;
			}
			i++;
		}
		return item;
	}
	
	/**
	 * Retrieves the index of the specified item (if it is there) in the inventory
	 * list.
	 * @param i is the item that its index is to be found in the inventory list.
	 * @return the index of the item in inventory list.
	 */
	public int retrieveIndex(Item i)
	{
		int index = inventory.indexOf(i);
		if(index < 0)
		{
			System.out.println("Item does not exist.");
		}
		return index;
	}
}
