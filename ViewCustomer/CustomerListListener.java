package ViewCustomer;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import View.GUI;

public class CustomerListListener implements ListSelectionListener
{
	/**
	 * The main GUI of the application.
	 */
	CustomerGUI frame;
	
	/**
	 * Constructs ListListener by attaching the GUI to frame.
	 * @param f is the GUI to be attached to the frame.
	 */
	public CustomerListListener(CustomerGUI f) 
	{
		frame = f;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) 
	{
		int index = frame.listArea.getSelectedIndex();
		String line = (String) frame.getList().get(index);
		
		if (index >= 1 && line.indexOf('*') < 0) // Will not select the first element, or any element containing '*'
		{
			String[] lineSplit = line.split(","); // Removes the delimiter, and put into array.
			
			String finalLine = lineSplit[0].substring(6); // will read past 'TOOL: ' and get just the name.
			frame.purchaseTextField.setText(finalLine);
		}
	}
}
