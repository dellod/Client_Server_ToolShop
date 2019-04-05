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
	 * The main list area at the center of the application layout.
	 */
	JList<String> listArea;
	
	/**
	 * Elements of the main list that goes in listArea.
	 */
	DefaultListModel<String> list;
	
	/**
	 * Adds a scroll bar to the main list area.
	 */
	private JScrollPane listScroll;
	
	/**
	 * Listener that listens to buttons being clicked on the main application GUI.
	 */
	private MainListener listener;
	
	/**
	 * The edge style of the panel.
	 */
	private Border panelEdge = BorderFactory.createEtchedBorder(); //eteched border
	
	/**
	 * Constructs GUI with a client to connect to and setting the listener.
	 * @param c client to construct GUI with.
	 */
	public GUI(Client c)
	{
		super("Tool Shop Application");
		client = c;
		listener = new MainListener(this);
		setLayout(new BorderLayout()); // N, E, S, W, and Center
		setDefaultCloseOperation(this.EXIT_ON_CLOSE); // Allows termination when exit is pressed.
	}
	
	/**
	 * Retrieves the list.
	 * @return list of type DefaultListModel<String>.
	 */
	public DefaultListModel<String> getList()
	{
		return list;
	}
	
	/**
	 * Builds the North quadrant of the application layout.
	 */
	private void buildNorth() // Title area.
	{
		JLabel label = new JLabel("Toolshop Manager", SwingConstants.CENTER);
		label.setFont(new Font("Times New Roman", Font.BOLD, 25));
		add("North", label);
	}
	
	/**
	 * Builds the Center quadrant of the application layout.
	 */
	private void buildCenter() // List area.
	{
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(0, 0, 150)); // Sets the center to blue
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
	
	/**
	 * Builds the South quadrant of the application layout.
	 */
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
		
		// Add buttons to the south panel and selected text viewer to southPanel.
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
	
	/**
	 * Builds all quadrants and packs it so that it fits in the window while allowing it to be visible.
	 */
	public void buildAll()
	{
		buildNorth();
		buildSouth();
		buildCenter();
		
		pack();
		setVisible(true);
	}
	
	/**
	 * Displays the dialog corresponding to the "List" button.
	 * @return a string that indicates which dialog has been pressed (tools or suppliers).
	 */
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
	
	/**
	 * Displays the dialog corresponding to the "Search" button.
	 */
	public void searchWindow()
	{
		SearchListener searchListen = new SearchListener(this);
		
		searchW = new JDialog();
		searchW.setTitle("Search");
		JTabbedPane tabbedPane = new JTabbedPane(); // Creates a tabbed dialog.
		
		JPanel page1 = new JPanel();
		page1.add(new Label("Enter name "));
		nameIn = new TextField(10);
		page1.add(nameIn);
		searchN = new JButton("Search Name"); 
		page1.add(searchN); 
	   
		JPanel page2 = new JPanel();
		page2.add(new Label("Enter ID "));
		idIn = new TextField(10);
		page2.add(idIn);
		searchI = new JButton("Search ID"); 
		page2.add(searchI);

		// Adds SearchListener to these buttons.
		searchN.addActionListener(searchListen);
		searchI.addActionListener(searchListen);
		
		tabbedPane.addTab("Name", null, page1, "Search by name");
		tabbedPane.addTab("ID", null, page2, "Search by id");
		
		searchW.add(tabbedPane);
		
		searchW.setLocationRelativeTo(this); // Centers in respect to parent window.
		searchW.pack();
		searchW.setVisible(true);
	}
	
	/**
	 * Displays the dialog corresponding to the "Check" button.
	 * @return the string of ID entered for stock checking.
	 */
	public String checkWindow()
	{
		String id = JOptionPane.showInputDialog(this, "Enter ID of tool to check its stock ");
		return id;
	}
	
	/**
	 * Displays the dialog corresponding to the "Decrease" button.
	 * @return String that identifies if the decrease button was pressed (decrease).
	 */
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
	
	/**
	 * Creates a normal plain message dialog.
	 * @param message the main message of the dialog.
	 * @param title the title of the dialog.
	 */
	public void createMessageDialog(String message, String title)
	{
		JOptionPane.showMessageDialog(this, message, title, JOptionPane.PLAIN_MESSAGE);
	}
	
	/**
	 * Creates an error message dialog.
	 * @param message the main message for the error dialog.
	 * @param title the title of error dialog.
	 */
	public void createErrorDialog(String message, String title)
	{
		JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
	}
}
