package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import ClientController.Client;

public class SearchListener implements ActionListener
{
	GUI frame;
	
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