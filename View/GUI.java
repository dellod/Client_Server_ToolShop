
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

//package View;
/**
 * MAR 30 ####### -have to add listeners for some of the buttons to add functionality
 * 			 	  -possibly add selection pane at the south panel to see choice
 * @author Daryl
 *
 */
public class GUI extends JFrame
{
	private ArrayList<JButton> buttons;
	/*
	 * INDEX	Button
	 * 0		List
	 * 1		Search
	 * 2		Check
	 * 3		Decrease
	 * 4		Print
	 * 5		Quit
	 */
	
	private JList<String> listArea;
	private DefaultListModel<String> list;
	private JScrollPane listScroll;
	
	private MyListener listener;
	
	private Border panelEdge = BorderFactory.createEtchedBorder(); //eteched border
	
	public GUI()
	{
		super("Tool Shop Application");
		buttons = new ArrayList<JButton>();
		//listener = MyListener(this);
		setLayout(new BorderLayout());
		setSize(500, 500);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE); // allows termination when exit is pressed.
	}
	
	public ArrayList<JButton> getButtons()
	{
		return buttons;
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
		buttons.add(new JButton("List"));
		buttons.add(new JButton("Search"));
		buttons.add(new JButton("Check"));
		buttons.add(new JButton("Decrease"));
		buttons.add(new JButton("Print"));
		buttons.add(new JButton("Quit"));
		/*
		for(JButton b: buttons)
		{
			b.addActionListener(listener); // NEED TO ADD THE ACTIONS
		}
		*/
		for(JButton b: buttons)
		{
			southPanel.add(b);
		}
			
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
	
	public static void main(String[] args)
	{
		GUI test = new GUI();
		test.buildAll();
	}
}
