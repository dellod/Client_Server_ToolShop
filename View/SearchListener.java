package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the listener corresponding to events in the 'Search'
 * dialog.
 * 
 * @author Daryl, Ilyas, Will
 *
 */
public class SearchListener implements ActionListener
{
	/**
	 * The GUI of the application.
	 */
	GUI frame;
	
	/**
	 * Constructs SearchListener by attaching the GUI to frame.
	 * @param f is the GUI to be attached to the frame.
	 */
	public SearchListener(GUI f)
	{
		frame = f;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == frame.searchI)
		{
			frame.client.searchID(frame.idIn.getText());
			frame.searchW.dispose();
		}
		else if(e.getSource() == frame.searchN)
		{
			frame.client.searchName(frame.nameIn.getText());
			frame.searchW.dispose();
		}
	}

}