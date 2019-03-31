package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	public MainListener(GUI f)
	{
		frame = f;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == frame.lists)
		{
			frame.listWindow();
			System.out.println("list has been pressed");
		}
		else if(e.getSource() == frame.search)
		{
			System.out.println("search has been pressed");
		}
		else if(e.getSource() == frame.check)
		{
			System.out.println("check has been pressed");
		}
		else if(e.getSource() == frame.decrease)
		{
			System.out.println("decrease has been pressed");
		}
		else if(e.getSource() == frame.order)
		{
			System.out.println("order has been pressed");
		}
		else if(e.getSource() == frame.quit) // Quit is pressed
		{
			System.exit(1);
		}
		
		
	}
	
	
}
