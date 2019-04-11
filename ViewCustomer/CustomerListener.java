package ViewCustomer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerListener implements ActionListener
{
	private CustomerGUI frame;
	
	public CustomerListener(CustomerGUI f)
	{
		frame = f;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == frame.home) // "Home" pressed.
		{
			frame.custClient.listToolsCustomer();
			System.out.println("'home' has been pressed.");
		}
		else if(e.getSource() == frame.search) // "Search" pressed.
		{	
			frame.custClient.searchCustomer(frame.searchbar.getText());
			System.out.println("'search' has been pressed");
		}
		else if(e.getSource() == frame.buy) // "Add to Cart" pressed.
		{
			String s = frame.purchaseTextField.getText();
			frame.custClient.buyCustomer(s);
			System.out.println("'buy' has been pressed.");
		}
		else if(e.getSource() == frame.quit) // "Quit" is pressed
		{
			System.exit(1);
		}
	}

}
