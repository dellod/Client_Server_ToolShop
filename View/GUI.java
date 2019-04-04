package View;

import javax.swing.*;
import javax.swing.border.Border;

import ClientController.Client;

import java.awt.*;

/**
 * This class is the main graphical user interface.
 * Where the user can make changes and view contents of a Toolshop.
 * 
 * @author Daryl, Ilyas, Will
 *
 */
public class GUI extends JFrame
{
	JTextField selectedTextField;
	
	/**
	 * Represents the list button, and will display all the tools or suppliers.
	 */
	JButton lists;
		
		/**
		 * The buttons that belong int the list window.
		 */
		Object[] optionsList = {"Tools", "Suppliers"};
	
	/**
	 * Represents the search button, and will display the tool that is searched for if found.
	 */
	JButton search;
	
		/**
		 * This is the search dialog that pops up from the search button being pressed.
		 */
		JDialog searchW;
		
		/**
		 * The text input for the name tab.
		 */
		TextField nameIn; // have to add this to the whole class so you can use .getText when button is pressed.
		
		/**
		 * The text input for the id tab.
		 */
		TextField idIn;
		
		/**
		 * The search button for the name tab.
		 */
		JButton searchN;
		
		/**
		 * The search button for the id tab.
		 */
		JButton searchI;
		
	/**
	 * Represents the check button, and will display the quantity of the tool being searched for.
	 */
	JButton check;
	
	/**
	 * Represents the decrease button, and will decrease the quantity specified for the specific tool.
	 */
	JButton decrease;
	
		/**
		 * The ID text input for the decrease dialog.
		 */
		JTextField id;
		
		/**
		 * The amount to decrease text for the decrease dialog.
		 */
		JTextField decreaseAmount;
	
	/**
	 * Represents the order button, and will display the current order list.
	 */
	JButton order;
	
	/**
	 * Represents the quit button, and will terminate the GUI as well as the current running client.
	 */
	JButton quit;
	
	/**
	 * Is the client that is attached to the GUIs.
	 */
	Client client;
	
	/**
	 * 
	 */
	JList<String> listArea;
	DefaultListModel<String> list;
	private JScrollPane listScroll;
	private MainListener listener;
	private Border panelEdge = BorderFactory.createEtchedBorder(); //eteched border
	
	public GUI(Client c)
	{
		super("Tool Shop Application");
		client = c;
		listener = new MainListener(this);
		setLayout(new BorderLayout());
		setSize(500, 500);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE); // allows termination when exit is pressed.
	}
	
	public DefaultListModel<String> getList()
	{
		return list;
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
		ListListener listListen = new ListListener(this);
		
		list = new DefaultListModel<String>();
		listArea = new JList<String>(list);
		
		String width = "123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"; // controls the width
		listArea.setPrototypeCellValue(width);
		listArea.setFont(new Font("Times New Roman", Font.BOLD, 12)); // sets font to Times New Roman (BOLD) size 12
		listArea.setVisibleRowCount(20); // controls the height
		
		//listArea.addListSelectionListener(new ListListener()); // when selected you see the text at the bottom
		
		listScroll = new JScrollPane(listArea);
		list.addElement("Select options below");
		centerPanel.add(listScroll);
		
		listArea.addListSelectionListener(listListen);
		
		add("Center", centerPanel);
	}
	
	private void buildSouth() // Button area.
	{
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new BorderLayout());
		JPanel buttons = new JPanel();
		
		selectedTextField = new JTextField(20);
		lists = (new JButton("List"));
		search = (new JButton("Search"));
		check = (new JButton("Check"));
		decrease = (new JButton("Decrease"));
		order = (new JButton("Orders"));
		quit = (new JButton("Quit"));
		
		// Add action to each main menu button.
		lists.addActionListener(listener);
		search.addActionListener(listener);
		check.addActionListener(listener);
		decrease.addActionListener(listener);
		order.addActionListener(listener);
		quit.addActionListener(listener);
		
		// Add buttons to the south panel.
		//southPanel.add
		southPanel.add("North", selectedTextField);
		buttons.add(lists);
		buttons.add(search);
		buttons.add(check);
		buttons.add(decrease);
		buttons.add(order);
		buttons.add(quit);
		southPanel.add("South", buttons);
		add("South", southPanel);
	}
	
	public void buildAll()
	{
		buildNorth();
		buildSouth();
		buildCenter();
		
		pack();
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
	}
	
	public void searchWindow()
	{
		SearchListener searchListen = new SearchListener(this);
		
		searchW = new JDialog();
		searchW.setTitle("Search");
		JTabbedPane tabbedPane = new JTabbedPane();
		
		JPanel page1 = new JPanel();
		page1.add(new Label("Enter name "));
		nameIn = new TextField(10);
		page1.add(nameIn);
		searchN = new JButton("Search Name"); // will have to make this accessible to the whole class.
		page1.add(searchN); 
	   
		JPanel page2 = new JPanel();
		page2.add(new Label("Enter ID "));
		idIn = new TextField(10);
		page2.add(idIn);
		searchI = new JButton("Search ID"); // will have to make this accessible to the whole class.
		page2.add(searchI);

		
		// not sure about this part
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
		String id = JOptionPane.showInputDialog(this, "Enter ID of tool to check its stock ");
		 
		return id;
	}
	
	public String decreaseWindow()
	{
		id = new JTextField();
		decreaseAmount = new JTextField();
		
		JComponent[] inputs = new JComponent[] {new JLabel("Tool ID: "), id, new JLabel("Decrease Amount: "), decreaseAmount};
		Object[] options = {"Decrease" , "Cancel"};
		int result = JOptionPane.showOptionDialog(this,  inputs, "Decrease tool quantity",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[1]);
		
		if(result == JOptionPane.YES_NO_OPTION)
		{
			return "decrease";
		}
		return "";
	}
	
	public void createMessageDialog(String message, String title)
	{
		JOptionPane.showMessageDialog(this, message, title, JOptionPane.PLAIN_MESSAGE);
	}
	
	public void createErrorDialog(String message, String title)
	{
		JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
	}
}
