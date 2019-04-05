package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the listener for the central application (starting window), which listens 
 * for button events from this main panel.
 * 
 * @author Daryl, Ilyas, Will
 *
 */
class MainListener implements ActionListener
{
	/**
	 * The GUI of the application.
	 */
	private GUI frame;
	
	/**
	 * Constructs MainListener by attaching the GUI to frame.
	 * @param f is the GUI to be attached to the frame.
	 */
	public MainListener(GUI f)
	{
		frame = f;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == frame.lists) // "List" pressed.
		{
			String list = frame.listWindow();
			if(list == "tools")
			{
				frame.client.listTools();
			}
			else if(list == "suppliers")
			{
				frame.client.listSuppliers();
			}
			else
			{
				System.out.println("Something went wrong in list window."); // Error checking.
			}
			System.out.println("'List' has been pressed.");
		}
		else if(e.getSource() == frame.search) // "Search" pressed.
		{
			frame.searchWindow();
			System.out.println("'Search' has been pressed.");
		}
		else if(e.getSource() == frame.check) // "Check" pressed.
		{
			String id = frame.checkWindow();
			frame.client.check(id);
			System.out.println("'Check' has been pressed.");
		}
		else if(e.getSource() == frame.decrease) // "Decrease" pressed.
		{
			String dec = frame.decreaseWindow();
			if(dec == "decrease")
			{
				frame.client.decrease(frame.id.getText(), frame.decreaseAmount.getText());
			}
			else
			{
				System.out.println("Something went wrong in decrease window."); 
			}
			System.out.println("'Decrease' has been pressed.");
		}
		else if(e.getSource() == frame.order)
		{
			frame.client.order();
			System.out.println("'Order' has been pressed.");
		}
		else if(e.getSource() == frame.quit) // "Quit" is pressed
		{
			System.exit(1);
		}
	}
	
	
}
