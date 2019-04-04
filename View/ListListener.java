package View;

import javax.swing.event.*;

public class ListListener implements ListSelectionListener
{
	GUI frame;
	
	public ListListener(GUI f) 
	{
		frame = f;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) 
	{
		int index = frame.listArea.getSelectedIndex();
		String line = (String) frame.list.get(index);
		
		if (index >= 1 && line.indexOf('*') < 0) 
		{
			//String line = (String) frame.list.get(index);
			String[] lineSplit = line.split(";");
			String finalLine = "";
			for(String s: lineSplit)
			{
				finalLine += s + "     ";
			}
			frame.selectedTextField.setText(finalLine);
		}
		
	}

}
