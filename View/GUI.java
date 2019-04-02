
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
		
		// Add action to each main menu button.
		lists.addActionListener(listener);
		search.addActionListener(listener);
		check.addActionListener(listener);
		decrease.addActionListener(listener);
		order.addActionListener(listener);
		quit.addActionListener(listener);
		
		// Add buttons to the south panel.
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
	
	public String listWindow()
	{
		int n = JOptionPane.showOptionDialog(this, "Which would you like to list?", "List", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionsList, optionsList[1]);
		
		if(n == JOptionPane.YES_OPTION) // Tools
		{
			return "tools";
		}
		else if(n == JOptionPane.NO_OPTION)
		{
			return "suppliers";
		}
		else
		{
			return "";
		}
		//have to make this listen outside
	}
	
	public void searchWindow() // DO THIS LAST...
	{
		SearchListener searchListen = null;
		
		TextField nameIn; // have to add this to the whole class so you can use .getText when button is pressed.
		TextField idIn;
		
		JDialog searchW = new JDialog();
		searchW.setTitle("Search");
		JTabbedPane tabbedPane = new JTabbedPane();
		
		JPanel page1 = new JPanel();
		page1.add(new Label("Enter name "));
		nameIn = new TextField(10);
		page1.add(nameIn);
		JButton searchN = new JButton("Search ID"); // will have to make this accessible to the whole class.
		page1.add(searchN); 
	   
		JPanel page2 = new JPanel();
		page2.add(new Label("Enter ID "));
		idIn = new TextField(10);
		page2.add(idIn);
		JButton searchI = new JButton("Search Name"); // will have to make this accessible to the whole class.
		page2.add(searchI);

		
		// not sure about this part
		searchListen.name = searchN;
		searchListen.id = searchI;
		searchListen.n = nameIn.getText();
		searchListen.i = idIn.getText();
		searchN.addActionListener(searchListen);
		searchI.addActionListener(searchListen);
		
		
		tabbedPane.addTab("Name", null, page1, "Search by name");
		tabbedPane.addTab("ID", null, page2, "Search by id");
		
		searchW.add(tabbedPane);
		
		searchW.setLocationRelativeTo(this); // Centers in respect to parent window
		searchW.pack();
		searchW.setVisible(true);
	}
	
	public String checkWindow()
	{
		String id = JOptionPane.showInputDialog("Enter ID of tool to check its stock ");
		 
		// SOME CALL TO FIND said stock
			// if equal null , say not found
			// else return the stock number
		
		String stock = "";
		return stock;
	}
	
	public void decreaseWindow()
	{
		JTextField id = new JTextField();
		JTextField decreaseAmount = new JTextField();
		
		JComponent[] inputs = new JComponent[] {new JLabel("Tool ID: "), id, new JLabel("Decrease Amount: "), decreaseAmount};
		Object[] options = {"Decrease" , "Cancel"};
		int result = JOptionPane.showOptionDialog(null,  inputs, "Decrease tool quantity",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[1]);
		
		if(result == JOptionPane.YES_NO_OPTION)
		{
			System.out.println("Tool" + id + " is going to be decreased");
			// DECREASE THE AMOUNT REQUESTED BY SEARCHING THE TOOL ID AND TAKING AWAY FROM ITS STOCK
			// THEN DISPLAY THE NEW QUANTITY HERE
		}
	}
	
	public static void main(String[] args)
	{
		GUI test = new GUI();
		test.buildAll();
	}
}
