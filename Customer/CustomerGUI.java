package Customer;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;

public class CustomerGUI extends JFrame
{
	JButton home;
	
	JButton checkout;
	
	JButton addToCart;
	
	JButton quit;
	
	TextField searchbar;
	
	JButton search;
	
	CustomerClient client;
	/**
	 * Elements of the main list that goes in listArea.
	 */
	DefaultListModel<String> list;
	
	/**
	 * Adds a scroll bar to the main list area.
	 */
	private JScrollPane listScroll;
	
	private CustomerListener listener;
	
	JList<String> listArea;
	
	
	private Border panelEdge = BorderFactory.createEtchedBorder();
	
	public CustomerGUI(CustomerClient c)
	{
		super("Customer Shopping Page");
		client=c;
		listener= new CustomerListener(this);
		
		setLayout(new BorderLayout()); // N, E, S, W, and Center
		setDefaultCloseOperation(this.EXIT_ON_CLOSE); // Allows termination when exit is pressed.
	}
	
	public void buildCenter()
	{
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(0, 150, 0)); // Sets the center to blue
		centerPanel.setBorder(panelEdge);
		//ListListener listListen = new ListListener(this);
		
		list = new DefaultListModel<String>();
		listArea = new JList<String>(list);
		
		String width = "123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"; // controls the width
		listArea.setPrototypeCellValue(width);
		listArea.setFont(new Font("Times New Roman", Font.BOLD, 12)); // sets font to Times New Roman (BOLD) size 12
		listArea.setVisibleRowCount(20); // controls the height
		
		//listArea.addListSelectionListener(new ListListener()); // when selected you see the text at the bottom
		
		listScroll = new JScrollPane(listArea);
		centerPanel.add(listScroll);
		
		//listArea.addListSelectionListener(listListen);
		
		add("Center", centerPanel);
	}
	
	public void buildNorth()
	{
		JPanel northPanel = new JPanel();
		
		home = new JButton("Home");
		checkout = new JButton("Checkout");
		addToCart = new JButton("Add to cart");
		search = new JButton("Search");
		quit = new JButton("Quit");
		searchbar = new TextField("Search by name...");
		
		northPanel.add(home);
		northPanel.add(searchbar);
		northPanel.add(search);
		northPanel.add(addToCart);
		northPanel.add(checkout);
		northPanel.add(quit);
		
		add("North", northPanel);
		
		home.addActionListener(listener);
		checkout.addActionListener(listener);
		addToCart.addActionListener(listener);
		search.addActionListener(listener);
		quit.addActionListener(listener);
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

	public void buildAll()
	{
		buildNorth();
		buildCenter();
		
		pack();
		setVisible(true);
	}
	
	public static void main(String[] args)
	{
		CustomerGUI test = new CustomerGUI(new CustomerClient("localhost",8099));
		test.buildAll();
	}
}
