package Model;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * This class provides the details of the order line.
 * 
 * @author Daryl, Ilyas, Will
 * @since Feb 5, 2019
 */
public class Order implements Serializable
{
	/**
	 * The ID of the order that is to be randomly generated.
	 */
	private int orderId;
	
	/**
	 * The date that the order was created on.
	 */
	private Date date; 
	
	/**
	 * The order line of the item that is to be ordered.
	 */
	private OrderLine orderL;
	
	/**
	 * Constructs Order from ord, which is to be assigned to orderL while the 
	 * ID and Date of the order are generated/retrieved.
	 * @param ord
	 */
	public Order(OrderLine ord)
	{
		generateId();
		generateDate();
		orderL = ord;
	}
	
	/**
	 * Gets variable orderId from class Order.
	 * @return orderId of type int.
	 */
	public int getOrderId()
	{
		return orderId;
	}
	
	/**
	 * Retrieves the date that order is created for variable date.
	 */
	public void generateDate()
	{
		Date ndate = new Date();
		date = ndate;
	}
	
	/**
	 * Generates random 5-digit ID number for variable orderId.
	 */
	public void generateId()
	{
		Random rand = new Random();
		orderId = rand.nextInt(99999) + 10000; //generates random 5 digit number
	}
	
	/**
	 * Prints the order specifics out with the order ID, date, item description,
	 * amount ordered, and the supplier ID.
	 */
	public String toString()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		return ("ORDER ID: " + orderId + ";" + "DATE:" + dateFormat.format(date) + ";" + "ITEM: " + orderL.getItem().getName() + ";" +
					"AMOUNT: " + orderL.getQuantity() + ";" + "SUPPLIER ID: " + orderL.getItem().getSupplierId());
	}
}
