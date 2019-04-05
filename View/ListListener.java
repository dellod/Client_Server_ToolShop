package View;

import javax.swing.event.*;

/**
 * This class listens for events corresponding to the main listArea
 * of the central application (starting window).
 * 
 * @author Daryl, Ilyas, Will
 *
 */
public class ListListener implements ListSelectionListener
{
	/**
	 * The main GUI of the application.
	 */
	GUI frame;
	
	/**
	 * Constructs ListListener by attaching the GUI to frame.
	 * @param f is the GUI to be attached to the frame.
	 */
	public ListListener(GUI f) 
	{
		frame = f;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) 
	{
		int index = frame.listArea.getSelectedIndex();
		String line = (String) frame.list.get(index);
		
		if (index >= 1 && line.indexOf('*') < 0) // Will not select the first element, or any element containing '*'
		{
			String[] lineSplit = line.split(";"); // Removes the delimiter, and put into array.
			String finalLine = "";
			for(String s: lineSplit)
			{
				finalLine += s + "     ";
			}
			frame.selectedTextField.setText(finalLine);
		}
	}

}
