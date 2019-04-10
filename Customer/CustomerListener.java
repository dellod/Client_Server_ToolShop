package Customer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

/**
 * This is the listener for the central application (starting window), which listens 
 * for button events from this main panel.
 * 
 * @author Daryl, Ilyas, Will
 *
 */
 class CustomerListener implements ActionListener
{
	/**
	 * The GUI of the application.
	 */
	private CustomerGUI frame;
	
	/**
	 * Constructs MainListener by attaching the GUI to frame.
	 * @param f is the GUI to be attached to the frame.
	 */
	public CustomerListener(CustomerGUI f)
	{
		frame = f;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == frame.home) // "home" pressed.
		{
			frame.client.listTools();

			System.out.println("'home' has been pressed.");
		}
		else if(e.getSource() == frame.checkout) // "checkout" pressed.
		{
			System.out.println("'checkout' has been pressed.");
		}
		else if(e.getSource() == frame.addToCart) // "addToCart" pressed.
		{

			System.out.println("'add to cart' has been pressed.");
		}
		else if(e.getSource() == frame.search) // "Decrease" pressed.
		{	
			
			frame.client.searchName(frame.searchbar.getText());
		}
		
		else if(e.getSource() == frame.quit) // "Quit" is pressed
		{
			System.exit(1);
		}
	}
	
	
}