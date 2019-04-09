package Model;

import java.io.Serializable;

/**
 * This class provides the details and specifications of the items (tools).
 * 
 * @author Daryl, Ilyas, Will
 * @since Feb 5, 2019
 */
public class Item implements Serializable
{
	/**
	 * 4-digit ID number of the item.
	 */
	private int id;
	
	/**
	 * The name of the item.
	 */
	private String name;
	
	/**
	 * The amount of item in stock.
	 */
	private int stock;
	
	/**
	 * The price of the item.
	 */
	private double price;
	
	/**
	 * The supplier ID number of the item.
	 */
	private int supplierId; 
	
	/**
	 * The supplier of the item.
	 */
	private Supplier supplier;
	
	/**
	 * Constructs Item by breaking down the specific format of the string and assigning
	 * tokens of the string to class parameters.
	 * @param s is the string that is to be broken down by constructor.
	 */
	public Item(String s)
	{
		int index = 0;
		int tokenIndex = 1;
		String temp = "";
		
		while(index < s.length())
		{
			if(s.charAt(index) != ';')
			{
				temp += s.charAt(index); 
			}
			else // Once ';' is found, the token is decides which variable it is to be assigned to.
			{
				switch(tokenIndex)
				{
					case 1: // ID
						id = Integer.parseInt(temp);
						temp = "";
						tokenIndex++;
						break;
					case 2: // name
						name = temp;
						temp = "";
						tokenIndex++;
						break;
					case 3:// stock
						stock = Integer.parseInt(temp);
						temp = "";
						tokenIndex++;
						break;
					case 4:// price
						price = Double.parseDouble(temp);
						temp = "";
						tokenIndex++;
						break;
				}
			}
			index++;
		}
		supplierId = Integer.parseInt(temp); // supplierID
	}
	
	/**
	 * Sets supplier to anySupplier
	 * @param anySupplier of type Supplier to be set to variable supplier.
	 */
	public void setSupplier(Supplier anySupplier)
	{
		supplier = anySupplier;
	}
	
	/**
	 * Gets variable stock from class Item.
	 * @return stock of type int.
	 */
	public int getStock()
	{
		return stock;
	}
	
	/**
	 * Gets variable id from class Item.
	 * @return id of type int.
	 */
	public int getId()
	{
		return id;
	}
	
	/**
	 * Gets variable name from class Item.
	 * @return name of type String.
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Gets variable supplier from class Item.
	 * @return supplier of type Supplier.
	 */
	public Supplier getSupplier()
	{
		return supplier;
	}
	
	/**
	 * Gets variable supplierId from class Item.
	 * @return supplierId of type int.
	 */
	public int getSupplierId()
	{
		return supplierId;
	}
	
	/**
	 * Converts the content and variables of object Item to string.
	 * @return a string that contains information on the object Item.
	 */
	public String toString()
	{
		return id + ";" + name  + ";" + stock + ";" + price + ";" +  supplierId;
	}
	
	/**
	 * Checks the stock of the item and returns a boolean value based on 
	 * if the stock need to be replaced.
	 * @return true if the stock < 40 (be replaced), otherwise false.
	 */
	public boolean stockCheck()
	{
		if(stock < 40)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	/**
	 * Decreases the stock amount of item (simulating sale).
	 * @param sold is the amount to decrease the stock by.
	 */
	public void decreaseStock(int sold)
	{
		stock = stock - sold;
	}
}
