package Model;

/**
 * Class that allows to create an object with user info (password and username)
 * @author Ilyas Ganiyev, Daryl Dang, Will Huang
 * @since April 8, 2019
 * @version 1.0
 */
public class UserData 
{
	/**
	 * Username of a user
	 */
	private String username;

	/**
	 * Password of a user
	 */
	private String password;

	/**
	 * UserData class constructor. It receives the username and password as string and assigned accordingly
	 * @param u username
	 * @param p password
	 */
	public UserData(String u, String p)
	{
		username = u;
		password = p;
	}

	/**
	 * The method checks if the provided username and password are legitimate or not
 	 * @param user username
	 * @param pass password
	 * @return true if password AND username matches, return false if it does not match
	 */
	public boolean authenticate(String user, String pass)
	{
		if(user.equals(username) && pass.equals(password)) 
		{
			return true;
		}
		return false;
	}
}
