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
	private JPanel topPanel; //holds top stuff
	private JPanel mapPanel; //holds the directional buttons
	private JPanel itemPanel; //holds the items
	private JPanel infoPanel; //panel with text message and HP
	private JPanel newPanel; //holds new game button
	private JButton buttonUp; //up button
	private JButton buttonDown; //up button
	private JButton buttonRight; //up button
	private JButton buttonLeft; //up button
	private JButton buttonUse; //Use Item button
	private JButton buttonFist; //attack without a weapon
	private JButton buttonStart; //Start button
	private JTextArea labelMessage; //holds game text
	private JLabel labelHP; //displays the person's hp
	private int hp = 100;
	private Tile curr;
	private Enemy enemy;
	private boolean isInBattle = false;

	private String message = "This is the space where the game will say messages.\n" +
			" It will relay what is happening in the game.\n" +
			" We are going to need a lot of strings."; //text message from the game
	private ArrayList<Item> inventory; //holds the inventory
	private String[] items; //array to hold inventory names
	private JComboBox itemList; //holds the items in a combo box

	//for background image
	private JLabel background;
	private Game game; //make a game

	//boolean to disable the button
	private boolean btnleftClicked = true;
	private boolean btnrightClicked = true;
	private boolean btnupClicked = true;
	private boolean btndownClicked = true;

	public Frame() throws IOException
	{

		//panel = new JPanel();
		//text message
		labelMessage = new JTextArea(message);
		labelMessage.setPreferredSize(new Dimension(400, 100)); //sizes the textbox
		labelMessage.setEditable(false);
		labelMessage.setOpaque(false);

		//get the names of items from the ArrayList
		//and put them into the item string array


		//buttons
		buttonUp = new JButton("Up");
		buttonRight = new JButton("Right");
		buttonLeft = new JButton("Left");
		buttonDown = new JButton("Down");
		buttonUse = new JButton("Use Item");
		buttonFist = new JButton("Punch");
		buttonStart = new JButton("New Game");
		buttonFist = new JButton("Escape");

		//setting a background image
		//got this here http://java-demos.blogspot.ca/2012/09/setting-background-image-in-jframe.html
		//setLayout(new BorderLayout());
		background = new JLabel(new ImageIcon("img/bg.jpg"));

		//set up the panels
		topPanel = new JPanel();
		mapPanel = new JPanel();
		itemPanel = new JPanel();
		infoPanel = new JPanel();
		newPanel = new JPanel();

		//set the size and layouts for the panels and background
		background.setLayout(new BorderLayout(10,10));
		topPanel.setLayout(new GridLayout(2,1,0,0));
		infoPanel.setLayout(new FlowLayout());
		mapPanel.setLayout(new GridLayout(3,3,25,0)); //make it a 3x3 grid with empty spaces
		itemPanel.setLayout(new FlowLayout());
		topPanel.setOpaque(false);
		mapPanel.setOpaque(false);
		itemPanel.setOpaque(false);
		infoPanel.setOpaque(false);
		newPanel.setOpaque(false);




		//start a new game
		game = new Game();
		hp = game.getPerson().getHP();
		inventory = game.getInventoryOfPerson();
		//player hit points
		labelHP = new JLabel("HP: " + hp);


		//size the item list appropriately
		items = new String[inventory.size()];

		//populate item list
		itemList = new JComboBox(items);

		itemList.addActionListener(this);

		//add a combo box to hold items for use
		//http://da2i.univ-lille1.fr/doc/tutorial-java/uiswing/components/combobox.html

		//itemList.setSelectedIndex(0);
		itemPanel.add(itemList);
		itemPanel.add(buttonUse);
		itemPanel.add(buttonFist);
		infoPanel.add(labelHP);

		//add all this
		newPanel.add(buttonStart);
		newPanel.setPreferredSize(new Dimension(500, 50));
		newPanel.setSize(500, 50);
		infoPanel.add(labelMessage);
		topPanel.add(newPanel);
		topPanel.add(infoPanel);
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


		background.setSize(500,400);
		background.add(topPanel);

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
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		//add action listeners
		buttonUp.addActionListener(this);
		buttonRight.addActionListener(this);
		buttonLeft.addActionListener(this);
		buttonDown.addActionListener(this);
		buttonUse.addActionListener(this);
		buttonStart.addActionListener(this);
		buttonFist.addActionListener(this);

	}

	public void battleStart(){
		//disable movement
		isInBattle = true;
		buttonUp.setEnabled(false);
		buttonDown.setEnabled(false);
		buttonLeft.setEnabled(false);
		buttonRight.setEnabled(false);

		enemy = new Enemy();


	}

	public void battleCont(){
		if(enemy.getHP()>0)
			game.getPerson().cngHP(enemy.getAttack());



		if(game.getPerson().getHP()<=0){
			updateHP();//Game over message
		}
		if(enemy.getHP()<=0){
			buttonUp.setEnabled(true);
			buttonDown.setEnabled(true);
			buttonLeft.setEnabled(true);
			buttonRight.setEnabled(true);
			setEmpty(curr);
			isInBattle = false;
			//Set the tile so no more battles are here
		}
	}

	public void updateHP()
	{
		//get the hp
		hp = game.getPerson().getHP();
		labelHP.setText("HP: " + hp);
		System.out.println("updating the hp to " + hp);

		if(hp<=0) //no hp left
		{
			message = "You're dead!"; //print the status in the message
			labelMessage.setText(message); 
		}
	}

	//find an item
	public void findItem()
	{
		inventory.add(new Item()); //add a new item to inventory
		System.out.println(inventory);
		//size the item list appropriately
		items = new String[inventory.size()];

		//give a message
		message = "You found a " + inventory.get(inventory.size()-1).getName();
		labelMessage.setText(message);

		//puts the names of the inventory into the combo box
		for (int i = 0; i<game.getPerson().getInventory().size(); i++)
		{
			items[i] = game.getPerson().getInventory().get(i).getName();
		}

		//populate item list
		itemList.setModel(new DefaultComboBoxModel(items));
		setEmpty(curr);
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
				updateHP();
				message = game.getStatus();
				labelMessage.setText(message);
				//resets the item list
				inventory.removeAll(inventory); //empty the inventory
				String [] empty = new String[0];
				itemList.setModel(new DefaultComboBoxModel(empty));
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
			curr = game.getCurrent();
			if(curr.getType().equals("wild_enemy"))
				battleStart();
			updateHP();
			if(curr.getType().equals("item")) //is it an item tile?
			{
				findItem(); //you found an item!
			}

		}
		if (e.getSource() == buttonDown) //pressing the down button
		{
			game.moveDown(); //move down on the map
			message = game.getStatus();
			labelMessage.setText(message);
			System.out.println("Move Down");
			curr = game.getCurrent();
			if(curr.getType().equals("wild_enemy"))
				battleStart();
			updateHP();
			if(curr.getType().equals("item")) //is it an item tile?
			{
				findItem(); //you found an item!
			}
		}
		if (e.getSource() == buttonRight) //pressing the right button
		{
			game.moveRight();
			message = game.getStatus();
			labelMessage.setText(message);
			System.out.println("Move Right");
			curr = game.getCurrent();
			if(curr.getType().equals("wild_enemy"))
				battleStart();
			updateHP();
			if(curr.getType().equals("item")) //is it an item tile?
			{
				findItem(); //you found an item!
			}
		}
		if (e.getSource() == buttonLeft) //pressing the left button
		{
			game.moveLeft();
			message = game.getStatus();
			labelMessage.setText(message);
			System.out.println("Move Left");
			curr = game.getCurrent();
			if(curr.getType().equals("wild_enemy"))
				battleStart();
			updateHP();
			if(curr.getType().equals("item")) //is it an item tile?
			{
				findItem(); //you found an item!
			}

		}
		if (e.getSource() == buttonUse) //use an item in the list
		{
			if (itemList.getItemCount() > 0)
			{
				int item = itemList.getSelectedIndex(); //get the selected item
				String s = itemList.getSelectedItem().toString(); //probably need to change this
				if(curr.getType().equals("wild_enemy")){
					enemy.setHP(inventory.get(item).getDamage());
					battleCont();
				}
				itemList.removeItemAt(item); //remove the used item
				inventory.remove(item); //removes the item from inventory arraylist
				//game.useItem(s); //does whatever Game's useItem() will do

			} else {
				System.out.println("There's no items!");
			}

			updateHP();
		}
		if (e.getSource() == buttonFist) //the punch button
		{
			//do some punch stuff
			message = "Punching!";
			labelMessage.setText(message);
			if(curr.getType().equals("wild_enemy")){
				message = "Punching! Enemy health down by 2";
				labelMessage.setText(message);
				enemy.setHP(2);
				battleCont();
			}
			updateHP();
		}
		
		if (e.getSource() == buttonEscape) {// the escape button
		int random = (int)(Math.random()*3); // this generates a number from 0 to 2
			if (!isInBattle) {   // checks to see if the player is in a battle
				if (random == 2) {   // if the player is in the battle and if the random number is 2
			
				    message = "Fleeing from the enemy!";     // you are able to flee from the enemy.
			            labelMessage.setText(message);
			     	    updateHP();
			     	    setEmpty(curr);
				}
				else {
					message = "You cannot escape!";   // otherwise you can't escape
					labelMessage.setText(message);
				}
			}
			else {
		             message = "There is nothing to flee from!"; // there is no battle so you can't escape.
		             labelMessage.setText(message);
			}
			
		}

	}

	public void setEmpty(Tile a){
		a.setType("empty");
	}

	public void disable(){
		if(game.getPerson().getHP()<=0){
			if(btnleftClicked == false){
				buttonLeft.setEnabled(true);
			}
			if(btnrightClicked == false){
				buttonRight.setEnabled(true);
			}
			if(btnupClicked == false){
				buttonUp.setEnabled(true);
			}
			if(btndownClicked == false){
				buttonDown.setEnabled(true);
			}

		}
	}
}
