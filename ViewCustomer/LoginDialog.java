package ViewCustomer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class LoginDialog extends JDialog
{
	JTextField tfUser;
	JPasswordField tfPass;
	private JLabel lbUser;
	private JLabel lbPass;
	JButton login;
	JButton quit;
	private LoginListener loginListener;
	
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
        //setVisible(true); //problem here
	}
}
