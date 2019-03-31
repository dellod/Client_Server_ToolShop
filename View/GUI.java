
package View;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

/**
 * MAR 30 ####### -possibly add selection pane at the south panel to see choice
 * @author Daryl
 *
 */
public class GUI extends JFrame
{
	/**
	 * Represents the list button, and will display all the tools or suppliers.
	 */
	JButton lists;
	
	Object[] optionsList = {"Tools", "Suppliers"};
	
	JButton search;
	
	JButton check;
	
	JButton decrease;
	
	JButton order;
	
	JButton quit;
	
	private JList<String> listArea;
	private DefaultListModel<String> list;
	private JScrollPane listScroll;
	
	private MainListener listener;
	
	private Border panelEdge = BorderFactory.createEtchedBorder(); //eteched border
	
	public GUI()
	{
		super("Tool Shop Application");
		listener = new MainListener(this);
		setLayout(new BorderLayout());
		setSize(500, 500);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE); // allows termination when exit is pressed.
	}
	
	private void buildNorth() // Title area.
	{
		JLabel label = new JLabel("Toolshop Manager", SwingConstants.CENTER);
		label.setFont(new Font("Times New Roman", Font.BOLD, 25));
		add("North", label);
	}
	
	private void buildCenter() // List area.
	{
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(0, 0, 150)); //sets the center to blue
		centerPanel.setBorder(panelEdge);
		
		list = new DefaultListModel<String>();
		listArea = new JList<String>(list);
		
		String width = "123456789012345678901234567890123456789012345678901234567890"; // controls the width
		listArea.setPrototypeCellValue(width);
		listArea.setFont(new Font("Times New Roman", Font.BOLD, 12)); // sets font to Times New Roman (BOLD) size 12
		listArea.setVisibleRowCount(20); // controls the height
		
		//listArea.addListSelectionListener(new ListListener()); // when selected you see the text at the bottom
		
		listScroll = new JScrollPane(listArea);
		list.addElement("Select options below");
		centerPanel.add(listScroll);
		
		add("Center", centerPanel);
	}
	
	private void buildSouth() // Button area.
	{
		JPanel southPanel = new JPanel();
		lists = (new JButton("List"));
		search = (new JButton("Search"));
		check = (new JButton("Check"));
		decrease = (new JButton("Decrease"));
		order = (new JButton("Order"));
		quit = (new JButton("Quit"));
		
		lists.addActionListener(listener);
		search.addActionListener(listener);
		check.addActionListener(listener);
		decrease.addActionListener(listener);
		order.addActionListener(listener);
		quit.addActionListener(listener);
		
		southPanel.add(lists);
		southPanel.add(search);
		southPanel.add(check);
		southPanel.add(decrease);
		southPanel.add(order);
		southPanel.add(quit);
			
		add("South", southPanel);
	}
	
	private void buildEast()
	{
		
	}
	
	private void buildWest()
	{
		
	}
	
	public void buildAll()
	{
		buildNorth();
		buildSouth();
		buildCenter();
		buildEast();
		buildWest();
		
		setVisible(true);
	}
	
	public void listWindow()
	{
		
		
		int n = JOptionPane.showOptionDialog(this, "Which would you like to list?", "List", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionsList, optionsList[1]);
	}
	
	public static void main(String[] args)
	{
		GUI test = new GUI();
		test.buildAll();
	}
}
