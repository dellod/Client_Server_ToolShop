package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class SearchListener implements ActionListener
{
	JButton id;
	
	JButton name;
	
	String i;
	
	String n;
	
	Client controller;
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == id)
		{
			controller.searchID(i);
		}
		else if(e.getSource() == name)
		{
			contoller.searchName(n);
		}
	}

}