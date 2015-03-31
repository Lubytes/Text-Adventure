/*
 * The GUI for the text adventure game
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
public class Frame extends JFrame implements ActionListener {
	
	//private JPanel panel; //the panel to hold stuff
	private JPanel topPanel; //panel with text message and HP
	private JPanel mapPanel; //holds the directional buttons
	private JPanel itemPanel; //holds the items
	private JButton buttonUp; //up button
	private JButton buttonDown; //up button
	private JButton buttonRight; //up button
	private JButton buttonLeft; //up button
	private JButton buttonUse; //Use Item button
	private JButton buttonStart; //Start button
	private JTextArea labelMessage; //holds game text
	private JLabel labelHP; //displays the person's hp
	private int hp = 100; //change this to getPerson().getHP() or something when it exists
	private String message = "This is the space where the game will say messages.\n" +
			" It will relay what is happening in the game.\n" +
			" We are going to need a lot of strings."; //text message from the game
	private ArrayList<Item> inventory; //holds the inventory
	private String[] items; //array to hold inventory names
	private JComboBox itemList; //holds the items in a combo box
	
	//for background image
	private JLabel background;
	private Game game; //make a game
	
	public Frame() throws IOException
	{
		
		//panel = new JPanel();
		//text message
		labelMessage = new JTextArea(message);
		labelMessage.setPreferredSize(new Dimension(400, 100)); //sizes the textbox
		labelMessage.setEditable(false);
		labelMessage.setOpaque(false);
		
		//player hit points
		labelHP = new JLabel("HP: " + hp);
		
		//get the names of items from the ArrayList
		//and put them into the item string array
		
		
		//buttons
		buttonUp = new JButton("Up");
		buttonRight = new JButton("Right");
		buttonLeft = new JButton("Left");
		buttonDown = new JButton("Down");
		buttonUse = new JButton("Use Item");
		buttonStart = new JButton("New Game");
		
		//setting a background image
		//got this here http://java-demos.blogspot.ca/2012/09/setting-background-image-in-jframe.html
		//setLayout(new BorderLayout());
		background = new JLabel(new ImageIcon("img/bg.jpg"));
        
		//set up the panels
		topPanel = new JPanel();
		mapPanel = new JPanel();
		itemPanel = new JPanel();
		
		//set the size and layouts for the panels and background
		background.setLayout(new BorderLayout(10,10));
		topPanel.setLayout(new FlowLayout());
		mapPanel.setLayout(new GridLayout(3,3,10,10)); //make it a 3x3 grid with empty spaces
		itemPanel.setLayout(new FlowLayout());
		background.setSize(500,400);
		background.add(topPanel);
		topPanel.setOpaque(false);
		mapPanel.setOpaque(false);
		itemPanel.setOpaque(false);
		
		
		
	
		//start a new game
		game = new Game();
		inventory = game.getInventoryOfPerson();
		
		//testing adding items to the combobox
		Item i1 = new Item("Stick", 2, 10);
		Item i2 = new Item("Health", 4, 10);
		game.getPerson().addItem(i1);
		game.getPerson().addItem(i2);
		//size the item list appropriately
		items = new String[inventory.size()];
		
		//puts the names of the inventory into the combo box
		for (int i = 0; i<game.getPerson().getInventory().size(); i++)
		{
			items[i] = game.getPerson().getInventory().get(i).getName();
		}
		
		//populate item list
		itemList = new JComboBox(items);

		itemList.addActionListener(this);
		
		//add a combo box to hold items for use
		//http://da2i.univ-lille1.fr/doc/tutorial-java/uiswing/components/combobox.html
		itemList.setSelectedIndex(0);
		itemPanel.add(itemList);
		itemPanel.add(buttonUse);
		topPanel.add(labelHP);
		
		//add all this
		itemPanel.add(buttonStart);
		topPanel.add(labelMessage);
		//empty label
		mapPanel.add(new JLabel(" "));
		mapPanel.add(buttonUp);
		//empty label
		mapPanel.add(new JLabel(" "));
		mapPanel.add(buttonLeft);
		//empty label
		mapPanel.add(new JLabel(" "));
		mapPanel.add(buttonRight);
		//empty label
		mapPanel.add(new JLabel(" "));
		mapPanel.add(buttonDown);
		
		
		
		
		//add panels
		background.add(topPanel, BorderLayout.NORTH);
		background.add(mapPanel, BorderLayout.CENTER);
		background.add(itemPanel, BorderLayout.SOUTH);
		add(background);
		
		//topPanel.setSize(500,200);
		//mapPanel.setSize(200,150);
		//itemPanel.setSize(500,50);
		
		//labelMessage.setForeground(Color.WHITE);
		setTitle("Text Adventure Game");
		setSize(500,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		//add action listeners
		buttonUp.addActionListener(this);
		buttonRight.addActionListener(this);
		buttonLeft.addActionListener(this);
		buttonDown.addActionListener(this);
		buttonUse.addActionListener(this);
		buttonStart.addActionListener(this);
		
	}
	
	public static void main(String[] args) throws IOException
	{
		Frame myWindow = new Frame();
	}

	//All the button pressing actions!
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonStart) //pressing the "New Game" button
		{
			//make a game
			try {
				game = new Game();
				message = game.getStatus();
				labelMessage.setText(message);
				//resets the item list
				itemList.setModel(new DefaultComboBoxModel(items));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource() == buttonUp) //pressing the up button
		{
			game.moveUp(); //move up on the map
			message = game.getStatus(); //print the status in the message
			labelMessage.setText(message);
			System.out.println("Move Up");
		}
		if (e.getSource() == buttonDown) //pressing the down button
		{
			game.moveDown(); //move down on the map
			message = game.getStatus();
			labelMessage.setText(message);
			System.out.println("Move Down");
		}
		if (e.getSource() == buttonRight) //pressing the right button
		{
			game.moveRight();
			message = game.getStatus();
			labelMessage.setText(message);
			System.out.println("Move Right");
		}
		if (e.getSource() == buttonLeft) //pressing the left button
		{
			game.moveLeft();
			message = game.getStatus();
			labelMessage.setText(message);
			System.out.println("Move Left");
		}
		if (e.getSource() == buttonUse) //use an item in the list
		{
			if (itemList.getItemCount() > 0)
			{
				int item = itemList.getSelectedIndex(); //get the selected item
				String s = itemList.getSelectedItem().toString(); //probably need to change this
				itemList.removeItemAt(item); //remove the used item
				inventory.remove(item); //removes the item from inventory arraylist
				game.useItem(s); //does whatever Game's useItem() will do
			} else {
				System.out.println("There's no items!");
			}
		}
		
	}
}
