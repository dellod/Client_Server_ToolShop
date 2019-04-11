package Model;

public class UserData 
{
	private String username;
	private String password;
	
	public UserData(String u, String p)
	{
		username = u;
		password = p;
	}
	
	public boolean authenticate(String user, String pass)
	{
		if(user.equals(username) && pass.equals(password)) 
		{
			return true;
		}
		return false;
	}
}
