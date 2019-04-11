package ViewCustomer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginListener implements ActionListener
{
	CustomerGUI frame;
	
	LoginDialog log;
	
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
