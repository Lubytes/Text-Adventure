import java.io.IOException;

/*
 * This is the actual game.
 * All the stuff that gets done gets done here.
 * Use the GUI to run these methods.
 */
public class Game {
	private World world; //the world
	private Person person; //the player
	private int xPos; //the xposition
	private int yPos; //the y position
	private Tile current; //the current tile
	
	private String status; //this will hold status messages to print to the player in the game
	
	//constructor - run this when someone clicks "New Game"
	public Game() throws IOException { //use this IOException thingy because the map uses File IO stuff
		world = new World(); //sets default map
		world.buildMap(); //makes the map
		person = new Person(); //makes a person
		status = "Welcome to the game!"; //put a good welcome here I guess?
		
		yPos = world.getStartRow();
		xPos = world.getStartCol();
		
		current = world.getTile(yPos, xPos);
	}
	
	//set and get status messages
	public void setStatus(String s)
	{
		status = s;
	}
	
	public String getStatus()
	{
		return status;
	}
	
	//get and set x and y
	public void setPos(int x, int y)
	{
		xPos = x;
		yPos = y;
	}
	
	public int getXpos()
	{
		return xPos;
	}
	
	public int getYpos()
	{
		return yPos;
	}
	
	//move in the world
	public void moveUp()
	{
		if (yPos > 0)
		{
			yPos--;
			current = world.getTile(yPos, xPos); //sets the position
			status = current.getType(); //delete this
			System.out.println(xPos + ", " + yPos); //prints pos
		} else {
			System.out.println("Can't move, edge");
			status = "You can't move in that direction.";
		}
	}
	
	public void moveDown()
	{
		if (yPos < world.getMap().size()-1)
		{
			yPos++;
			current = world.getTile(yPos, xPos); //sets the position
			status = current.getType(); //delete this
			System.out.println(xPos + ", " + yPos); //prints pos
		} else {
			System.out.println("Can't move, edge");
			status = "You can't move in that direction.";
		}
	}
	
	public void moveRight()
	{
		if (xPos < world.getMap().get(yPos).size()-1)
		{
			xPos++;
			current = world.getTile(yPos, xPos); //sets the position
			status = current.getType(); //delete this
			System.out.println(xPos + ", " + yPos); //prints pos
		} else {
			System.out.println("Can't move, edge");
			status = "You can't move in that direction.";
		}
	}
	
	public void moveLeft()
	{
		if (xPos > 0)
		{
			xPos--;
			current = world.getTile(yPos, xPos); //sets the position
			status = current.getType(); //delete this
			System.out.println(xPos + ", " + yPos); //prints pos
		} else {
			System.out.println("Can't move, edge");
			status = "You can't move in that direction.";
		}
	}
	
	public void useItem(String name)
	{
		//uses an item from the person somehow?
		System.out.println(name); //TESTING
	}
	
	public ArrayList<Item> getInventoryOfPerson() {
		return person.getInventory();
	}
	
	
}
