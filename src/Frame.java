/*
 * The GUI for the text adventure game
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
public class Frame extends JFrame implements ActionListener {
	
	//private JPanel panel; //the panel to hold stuff
	private JButton buttonUp; //up button
	private JButton buttonDown; //up button
	private JButton buttonRight; //up button
	private JButton buttonLeft; //up button
	private JButton buttonUse; //Use Item button
	private JButton buttonStart; //Start button
	private JTextArea labelMessage; //holds game text
	private JLabel labelHP; //displays the person's hp
	private int hp = 100; //holds the person's hit points. we should put this in the Person class.
	private String message = "This is the space where the game will say messages.\n" +
			" It will relay what is happening in the game.\n" +
			" We are going to need a lot of strings."; //text message from the game
	private String[] items = {"stick", "health", "thing", "item"}; //array to hold items
	private JComboBox itemList; //holds the items in a combo box
	
	//for background image
	private JLabel background;
	private Game game; //make a game
	
	public Frame() throws IOException
	{
		
		//panel = new JPanel();
		//text message
		labelMessage = new JTextArea(message);
		labelMessage.setSize(490, 100);
		labelMessage.setEditable(false);
		labelMessage.setOpaque(false);
		
		//player hit points
		labelHP = new JLabel("HP: " + hp);
		
		//populate item list
		itemList = new JComboBox(items);
		
		//buttons
		buttonUp = new JButton("Up");
		buttonRight = new JButton("Right");
		buttonLeft = new JButton("Left");
		buttonDown = new JButton("Down");
		buttonUse = new JButton("Use Item");
		buttonStart = new JButton("New Game");
		
		//setting a background image
		//got this here http://java-demos.blogspot.ca/2012/09/setting-background-image-in-jframe.html
		setLayout(new BorderLayout());
		background = new JLabel(new ImageIcon("img/bg.jpg"));
		background.setLayout(new FlowLayout());
		add(background);
		
		
		//add all this
		background.add(buttonStart);
		background.add(labelMessage);
		background.add(buttonUp);
		background.add(buttonRight);
		background.add(buttonLeft);
		background.add(buttonDown);
		
		//add a combo box to hold items for use
		//http://da2i.univ-lille1.fr/doc/tutorial-java/uiswing/components/combobox.html
		itemList.setSelectedIndex(0);
		background.add(itemList);
		background.add(buttonUse);
		background.add(labelHP);
		
		
		
		//add(panel);
		
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
		itemList.addActionListener(this);
	
		//start a new game
		game = new Game();
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
			int item = itemList.getSelectedIndex(); //get the selected item
			itemList.removeItemAt(item); //remove the used item
		}
		
	}
}
