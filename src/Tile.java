/*
 * Tiles used to make the map
 */
public class Tile {
	private String type;
	private Obstacle ob;
	private Enemy enemy;
	private Item item;
	
	//constructor
	public Tile()
	{
		
	}
	
	//get and set methods
	public void setObstacle(String s)
	{
		ob = new Obstacle();
		type = s;
	}
	
	public void setEnemy(String s)
	{
		enemy = new Enemy();
		type = s;
	}
	
	public void setItem(String n, int d, int u)
	{
		item = new Item(n, d, u);
		type = n;
	}
	
	public void setType(String s)
	{
		type = s;
	}
	
	public String getType()
	{
		return type;
	}
	
	//to string
	public String toString()
	{
		return type;
	}
}
