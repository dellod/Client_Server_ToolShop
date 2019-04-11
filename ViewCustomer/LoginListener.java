package ViewCustomer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class listens for button pressed on the Login dialog.
 * @author Daryl, Ilyas, Will
 *
 */
public class LoginListener implements ActionListener
{
	/**
	 * The main Customer GUI of the application.
	 */
	CustomerGUI frame;
	
	/**
	 * The initial login dialog of the application.
	 */
	LoginDialog log;
	
	/**
	 * Constructs LoginListener given a LoginDialog and a CustomerGUI.
	 * @param l is the LoginDialog to be set to log.
	 * @param f is the CustomerGUI to be set to frame.
	 */
	public LoginListener(LoginDialog l, CustomerGUI f)
	{
		log = l;
		frame = f;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == log.login)
		{
			frame.custClient.loginInfo(log.tfUser.getText(), log.tfPass.getText());
			log.setVisible(false);
			System.out.println("Login button was pressed.");
		}
		else if(e.getSource() == log.quit)
		{
			System.exit(1);
		}
	}
}
