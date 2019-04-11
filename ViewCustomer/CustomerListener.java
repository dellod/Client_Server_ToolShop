package ViewCustomer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The main listener of the Customer GUI.
 * Listens for the main button presses.
 *
 * @author Daryl, Ilyas, Will
 *
 */
public class CustomerListener implements ActionListener
{
	/**
	 * The GUI of the Customer application.
	 */
	private CustomerGUI frame;
	
	/**
	 * Constructs CustomerListener with a given CustomerGUI, f.
	 * @param f of type CustomerGUI to be set to the frame variable.
	 */
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
