/*
 * The GUI for the text adventure game
 */
import javax.swing.*;
import java.awt.*;
public class Frame extends JFrame {
	
	//private JPanel panel; //the panel to hold stuff
	private JButton buttonUp; //up button
	private JButton buttonDown; //up button
	private JButton buttonRight; //up button
	private JButton buttonLeft; //up button
	private JButton buttonUse; //Use Item button
	private JTextArea labelMessage; //holds game text
	private String message = "This is the space where the game will say messages.\n" +
			" It will relay what is happening in the game.\n" +
			" We are going to need a lot of strings."; //text message from the game
	private String[] items = {"stick", "health", "thing", "item"}; //array to hold items
	private JComboBox itemList = new JComboBox(items); //holds the items in a combo box
	
	//for background image
	private JLabel background;
	
	public Frame()
	{
		//panel = new JPanel();
		//text message
		labelMessage = new JTextArea(message);
		labelMessage.setSize(490, 100);
		labelMessage.setEditable(false);
		labelMessage.setOpaque(false);
		
		//buttons
		buttonUp = new JButton("Up");
		buttonRight = new JButton("Right");
		buttonLeft = new JButton("Left");
		buttonDown = new JButton("Down");
		buttonUse = new JButton("Use Item");
		
		//setting a background image
		//got this here http://java-demos.blogspot.ca/2012/09/setting-background-image-in-jframe.html
		setLayout(new BorderLayout());
		background = new JLabel(new ImageIcon("img/bg.jpg"));
		background.setLayout(new FlowLayout());
		add(background);
		
		
		//add all this
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
		
		
		
		//add(panel);
		
		//labelMessage.setForeground(Color.WHITE);
		setTitle("Text Adventure Game");
		setSize(500,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args)
	{
		Frame myWindow = new Frame();
	}
}
