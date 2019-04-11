package ViewCustomer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * This class is a dialog that holds the login user interface.
 * @author Daryl, Ilyas, Will
 *
 */
public class LoginDialog extends JDialog
{
	/**
	 * The username textfield.
	 */
	JTextField tfUser;
	
	/**
	 * The password textfield.
	 */
	JPasswordField tfPass;
	
	/**
	 * The label beside the username textfield.
	 */
	private JLabel lbUser;
	
	/**
	 * The label beside the password textfield.
	 */
	private JLabel lbPass;
	
	/**
	 * The login button.
	 */
	JButton login;
	
	/**
	 * The quit button.
	 */
	JButton quit;
	
	/**
	 * The listener for the LoginDialog, to detect when buttons are pressed.
	 */
	private LoginListener loginListener;
	
	/**
	 * Constructs the login dialog given the main CustomerGUI application, designed in 
	 * the way that the dialog must be dealt with first before being able to access the 
	 * CustomerGUI.
	 * @param parent the CustomerGUI to be sent to the LoginListener.
	 */
	public LoginDialog(CustomerGUI parent)
	{
		super(parent, "Login", true);
		loginListener = new LoginListener(this, parent);
		
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();
		
		cs.fill = GridBagConstraints.HORIZONTAL;
		
		lbUser = new JLabel("Username: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbUser, cs);
 
        tfUser = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(tfUser, cs);
 
        lbPass = new JLabel("Password: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbPass, cs);
        
        tfPass = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(tfPass, cs);
        panel.setBorder(new LineBorder(Color.GRAY));
        
        login = new JButton("Login");
        quit = new JButton("Quit");
        login.addActionListener(loginListener);
        quit.addActionListener(loginListener);
        
        JPanel buttonArea = new JPanel();
        buttonArea.add(login);
        buttonArea.add(quit);
        
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(buttonArea, BorderLayout.PAGE_END);
        
        pack();
	}
}
