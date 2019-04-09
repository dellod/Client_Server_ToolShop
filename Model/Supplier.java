package Model;

import java.io.Serializable;

/**
 * This class provides the details of a supplier.
 * 
 * @author Daryl, Ilyas, Will
 * @since Feb 5, 2019
 *
 */
public class Supplier implements Serializable
{
	/**
	 * The ID number of the supplier.
	 */
	private int supplierId; //ie. 8001
	
	/**
	 * The company name of the supplier.
	 */
	private String companyName; //ie. Pong Works
	
	/**
	 * The address of the company supplier.
	 */
	private String address; //ie. 788 30th st., SE
	
	/**
	 * The contact name of the supplier.
	 */
	private String contactName; //ie. Calgary;Fred
	
	/**
	 * Constructs object Supplier by breaking down the specific format of the string and assigning
	 * tokens of the string to class parameters.
	 * @param s is the string that is to be broken down by constructor.
	 */
	public Supplier(String s)
	{
		int index = 0;
		int tokenIndex = 1;
		String temp = "";
		
		while(index < s.length()) // THIS WORKs BUT ONLY FOR .txt FILE
		{
			if(s.charAt(index) != ';' /*|| tokenIndex != 5*/)
			{
				temp += s.charAt(index);
			}
			else
			{
				switch(tokenIndex)
				{
					case 1: // supplierId
						supplierId = Integer.parseInt(temp);
						temp = "";
						tokenIndex++;
						break;
					case 2: // companyName
						companyName = temp;
						temp = "";
						tokenIndex++;
						break;
					case 3: // address
						address = temp;
						temp = "";
						tokenIndex++;
						break;
				}
			}
			index++;
		}
		contactName = temp; // contactName
	}
	/**
	 * Gets variable supplierId from class Supplier.
	 * @return supplierdId of type int.
	 */
	public int getSupplierId()
	{
		return supplierId;
	}
	
	/**
	 * Gets variable companyName from class Supplier.
	 * @return companyName of type String.
	 */
	public String getCompanyName()
	{
		return companyName;
	}
	
	/**
	 * Gets variable address from class Supplier.
	 * @return address of type String.
	 */
	public String getAddress()
	{
		return this.address;
	}
	
	/**
	 * Gets variable contactName of type String.
	 * @return contactName of type String.
	 */
	public String getContact()
	{
		return contactName;
	}
	
	/**
	 * Converts variables and content of object Supplier to string format.
	 * @return string that holds the content of object Supplier.
	 */
	public String toString()
	{
		return supplierId + ";" + companyName + ";" + address + ";" + contactName;
	}
}
