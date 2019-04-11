package ViewCustomer;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;

import ClientCustomer.CustomerClient;

/**
 * The main GUI for the customer.
 * 
 * @author Daryl, Ilyas, Will
 *
 */
public class CustomerGUI extends JFrame
{
	/**
	 * The listener for the login window
	 */
	LoginListener logListen;
	
	/**
	 * Login window.
	 */
	private LoginDialog login;
	
	/**
	 * The selction field for the customer.
	 */
	JTextField purchaseTextField;
	
	/**
	 * Home button.
	 */
	JButton home;
	
	/**
	 * Buy button.
	 */
	JButton buy;
	
	/**
	 * Quit button.
	 */
	JButton quit;
	
	/**
	 * The search bar for typing in text.
	 */
	TextField searchbar;
	
	/**
	 * Search button.
	 */
	JButton search;
	
	/**
	 * The client attached to the GUI application.
	 */
	CustomerClient custClient;
	
	/**
	 * Elements of the main list that goes in listArea.
	 */
	private DefaultListModel<String> list;
	
	/**
	 * Adds a scroll bar to the main list area.
	 */
	private JScrollPane listScroll;
	
	/**
	 * The main listener (for buttons) of the Customer GUI.
	 */
	private CustomerListener custListener;
	
	/**
	 * Central list area of application.
	 */
	JList<String> listArea;
	
	/**
	 * The edge style of the panel.
	 */
	private Border panelEdge = BorderFactory.createEtchedBorder();
	
	/**
	 * Constructs the customer GUI given a customer client, c.
	 * @param c the CustomerClient to to be assigned to the GUI.
	 */
	public CustomerGUI(CustomerClient c)
	{
		super("Customer Shopping Page");
		custClient = c;
		custListener = new CustomerListener(this);
	
		setLogin(new LoginDialog(this)); //problem here
		logListen = new LoginListener(getLogin(), this);
		
		setLayout(new BorderLayout()); // N, E, S, W, and Center
		setDefaultCloseOperation(this.EXIT_ON_CLOSE); // Allows termination when exit is pressed.
	}
	
	/**
	 * Retrieves the list of type DefaultListModel.
	 * @return list of type DefaultListModel<String>.
	 */
	public DefaultListModel<String> getList() 
	{
		return list;
	}

	/**
	 * Sets the list of type DefaultListModel to list.
	 * @param list the list of type DefaultListModel<String> to be assigned to list.
	 */
	public void setList(DefaultListModel<String> list) 
	{
		this.list = list;
	}
	
	/**
	 * Builds the Center area of the GUI borderlayout.
	 */
	private void buildCenter()
	{
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(0, 150, 0)); // Sets the center to blue
		centerPanel.setBorder(panelEdge);
		CustomerListListener customerList = new CustomerListListener(this);
		
		setList(new DefaultListModel<String>());
		list.addElement("Welcome to the toolshop!");
		listArea = new JList<String>(list);
		
		String width = "123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"; // controls the width
		listArea.setPrototypeCellValue(width);
		listArea.setFont(new Font("Times New Roman", Font.BOLD, 12)); // sets font to Times New Roman (BOLD) size 12
		listArea.setVisibleRowCount(20); // controls the height
		
		listScroll = new JScrollPane(listArea);
		centerPanel.add(listScroll);
		
		listArea.addListSelectionListener(customerList);
		
		add("Center", centerPanel);
	}
	
	/**
	 * Builds the North area of the GUI borderlayout.
	 */
	private void buildNorth()
	{
		JPanel northPanel = new JPanel();
		
		home = new JButton("Home");
		buy = new JButton("Buy");
		search = new JButton("Search");
		quit = new JButton("Quit");
		searchbar = new TextField("Search by name...");
		
		northPanel.add(home);
		northPanel.add(searchbar);
		northPanel.add(search);
		northPanel.add(buy);
		northPanel.add(quit);
		
		add("North", northPanel);
		
		home.addActionListener(custListener);
		buy.addActionListener(custListener);
		search.addActionListener(custListener);
		quit.addActionListener(custListener);
	}
	
	/**
	 * Builds the South area of the GUI borderlayout.
	 */
	private void buildSouth()
	{
		JPanel southPanel = new JPanel();
		
		purchaseTextField = new JTextField(20);
		purchaseTextField.setEditable(false);
		//purchaseTextField.setText("Teestinginigd");
		
		southPanel.add(purchaseTextField);
		add("South", southPanel);
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
	
	/**
	 * Builds all areas of the borderlayout GUI (North, Center, South), as well as the initial login window.
	 */
	public void buildAll()
	{
		buildNorth();
		buildCenter();
		buildSouth();
		
		pack();
		setVisible(true);
		getLogin().setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); // Does nothing when 'X' is pressed.
		getLogin().setVisible(true);
	}

	/**
	 * Gets the login dialog.
	 * @return login of type LoginDialog.
	 */
	public LoginDialog getLogin() {
		return login;
	}

	/**
	 * Sets the login dialog.
	 * @param login is of type LoginDialog to be set to login.
	 */
	public void setLogin(LoginDialog login) {
		this.login = login;
	}

}
