package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ClientController.Client;
/**
 * MAR 30 #### -added actionlistener for main buttons 
 * 			   -stil have to add listeners for sub buttons.
 * @author Daryl
 *
 */
class MainListener implements ActionListener
{
	/**
	 * The GUI of the application.
	 */
	private GUI frame;
	
	/**
	 * Constructs a MyListener object with a given GUI.
	 * @param f is the main GUI of application to be assigned to the frame variable.
	 */
	
	//private Client controller;
	
	public MainListener(GUI f)
	{
		frame = f;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == frame.lists)
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
				System.out.println("Something went wrong in list window."); // take out later.
			}
			System.out.println("list has been pressed");
		}
		else if(e.getSource() == frame.search)
		{
			frame.searchWindow();
			System.out.println("search has been pressed");
		}
		else if(e.getSource() == frame.check)
		{
			String id = frame.checkWindow();
			frame.client.check(id);
			System.out.println("check has been pressed");
		}
		else if(e.getSource() == frame.decrease)
		{
			String dec = frame.decreaseWindow();
			if(dec == "decrease")
			{
				frame.client.decrease(frame.id.getText(), frame.decreaseAmount.getText());
			}
			else
			{
				System.out.println("Something went wrong in decrease window."); // take out later.
			}
			System.out.println("decrease has been pressed");
		}
		else if(e.getSource() == frame.order)
		{
			frame.client.order();
			System.out.println("order has been pressed");
		}
		else if(e.getSource() == frame.quit) // Quit is pressed
		{
			System.exit(1);
		}
		
		
	}
	
	
}
