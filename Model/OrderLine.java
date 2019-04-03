package Model;

import java.io.Serializable;

/**
 * This class provides the item with its details to be ordered for the shop.
 * 
 * @author Daryl Dang
 * @since Feb 5, 2019
 *
 */
public class OrderLine  implements Serializable
{
	/**
	 * Item that is to be ordered.
	 */
	private Item item;
	
	/**
	 * Quantity required for the item that is to be ordered.
	 */
	private int quantityRequired;
	
	/**
	 * The supplier of the item to be ordered.
	 */
	private Supplier itemSupplier;
	
	
	/**
	 * Constructs OrderLine with given item and its supplier, while the quanityRequired is generated.
	 * @param item is the item that is to be ordered.
	 */
	public OrderLine(Item item)
	{
		this.item = item;
		itemSupplier = this.item.getSupplier();
		quantityOrder();
	}
	
	/**
	 * Gets the variable item from class OrderLine.
	 * @return item of type Item.
	 */
	public Item getItem()
	{
		return item;
	}
	
	/**
	 * Gets the variable quantityRequired from class OrderLine.
	 * @return quantityRequired of type int.
	 */
	public int getQuantity()
	{
		return this.quantityRequired;
	}
	
	/**
	 * Gets the variable itemSupplier from class OrderLine.
	 * @return itemSupplier of type Supplier.
	 */
	public Supplier getItemSupplier()
	{
		return this.itemSupplier;
	}
	
	/**
	 * Generates the amount to be ordered (quantityRequired) by the current stock 
	 * subtracted from 50.
	 */
	public void quantityOrder()
	{
		quantityRequired = 50 - item.getStock();
	}

}
