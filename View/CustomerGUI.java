package View;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;

public class CustomerGUI extends JFrame
{
	JButton home;
	
	JButton checkout;
	
	JButton addToCart;
	
	TextField searchbar;
	
	JButton search;
	
	/**
	 * Elements of the main list that goes in listArea.
	 */
	DefaultListModel<String> list;
	
	/**
	 * Adds a scroll bar to the main list area.
	 */
	private JScrollPane listScroll;
	
	
	JList<String> listArea;
	
	private Border panelEdge = BorderFactory.createEtchedBorder();
	
	public CustomerGUI()
	{
		super("Customer Shopping Page");
		
		
		
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
		searchbar = new TextField("Search by name or id...");
		
		northPanel.add(home);
		northPanel.add(searchbar);
		northPanel.add(search);
		northPanel.add(addToCart);
		northPanel.add(checkout);
		
		add("North", northPanel);
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
		CustomerGUI test = new CustomerGUI();
		test.buildAll();
	}
}
