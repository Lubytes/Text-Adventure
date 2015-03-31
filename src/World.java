/*
 * Holds the World which holds a 2D arraylist of tiles
 * 2D ArrayLists are weird. To access an entry, instead of map[1][4] use map.get(1).get(4)
 * The maps get loaded from the maps folder. They are csv files that can be edited in exel or open office
 */
import java.io.*;
import java.util.*;
public class World {
	private Tile tile; //creates tiles
	private ArrayList<ArrayList<Tile>> map = new ArrayList<ArrayList<Tile>>(); //holds map
	private String fileName = "maps/map01.csv";
	private int row = 0; //keeps track of row #
	private int col = 0; //keeps track of col #
	private Enemy tiger = new Enemy("Tiger", 100, 42);
	
	//default constructor
	public World()
	{
		//change this to randomize
		fileName = "maps/map01.csv";
	}
	
	//constructor to pass a filename
	public World(String path)
	{
		//make sure the file exists first. 
		//Got this from here http://docs.oracle.com/javase/tutorial/essential/io/check.html
		File f = new File(path);
		if(f.exists()) 
		{ 
			fileName = path;
		} else {
			System.out.println("That file doesn't exist, using maps01.csv");
		}
	}
	
	//get the # of rows
	public int getRows()
	{
		return row;
	}
	
	//get the # of columns
	public int getCols()
	{
		return col;
	}
	
	//makes the map
	public void buildMap() throws IOException
	{
		//file reader from http://docs.oracle.com/javase/tutorial/essential/io/scanfor.html
		//this will scan your maps for layouts. split it up with commas.
		Scanner s = null;
		try {
			s = new Scanner(new BufferedReader(new FileReader(fileName)));
			
			while (s.hasNextLine()) {
				String line = s.nextLine(); //makes a string from each row
				String[] array = line.split(","); //splits the rows into string arrays
				ArrayList<Tile> currentLine = new ArrayList<Tile>(); //makes a row array list for the map
				map.add(currentLine); //adds the row
				
				for (col=0; col<array.length; col++) //for each space in the row
				{
					
					tile = new Tile();
					//based on the string type, we place the appropriate tile
					if (array[col].equals("water"))
					{
						//water tile
						tile.setObstacle("water");
					} else if (array[col].equals("enemy_tiger")) {
						//enemy tile
						tile.setEnemy("enemy_tiger");
					} else if (array[col].equals("start")) {
						//the starting tile
						tile.setType("start");
					} else if (array[col].equals("finish")) {
						//the finish tile
						tile.setType("finish");
					} else if (array[col].equals("wild_enemy")) {
						Random rand = new Random(); //random number
						int n = rand.nextInt(1); //a bigger number lowers the odds
						//random empty or enemy
						if (n == 0)
						{
							tile.setEnemy("enemy_tiger");
						} else {
							tile.setType("empty");
						}
					 } else if (array[col].equals("wild_item")) {
						Random rand = new Random(); //random number
						int n = rand.nextInt(1); //a bigger number lowers the odds
						//random empty or enemy
						if (n == 0)
						{
							tile.setEnemy("item");
						} else {
							tile.setType("empty");
						}
					} else{
						//anything else is gonna be empty
						tile.setType("empty");
					}
					currentLine.add(tile); //now add the tile
				}
				
				row++; //increments row
				
				
			}
			//System.out.println(map);
			//print this map so it looks good
			for (int j=0; j<map.size(); j++)
			{
				System.out.println(map.get(j));
			}
			//put these in array list
			System.out.println(map.indexOf(1));
			System.out.println(map.get(1).get(4)); //2D array lists are weird
		} finally {
			if (s != null) {
				s.close();
			}
		}
	}
	
	//get the map
	public ArrayList<ArrayList<Tile>> getMap()
	{
		return map;
	}
	
	//set the map
	public void setMap(String path)
	{
		//make sure the file exists first. 
		//Got this from here http://docs.oracle.com/javase/tutorial/essential/io/check.html
		File f = new File(path);
		if(f.exists()) 
		{ 
			fileName = path;
		} else {
			System.out.println("That file doesn't exist, using maps01.csv");
		}
	}
	
	//get the start tile row
	public int getStartRow()
	{
		int index = -1; //if it doesn't exist
		for (int i=0; i<row; i++) //iterate through rows
		{
			for (int j=0; j<col; j++) //check each tile in row
			{
				if (map.get(i).get(j).getType().equals("start")) //if that tile is a start type
				{
					index = i;
				}
			}
		}
		return index;
	}
	
	//get the start tile column
	public int getStartCol()
	{
		int index = -1; //if it doesn't exist
		for (int i=0; i<row; i++) //iterate rows
		{
			for (int j=0; j<col; j++) //iterate columns
			{
				if (map.get(i).get(j).getType().equals("start")) //if there's a start tile
				{
					index = j; //return the column
				}
			}
		}
		return index;
	}
	
	//get the finish tile row
	public int getFinishRow()
	{
		int index = -1; //if it doesn't exist
		for (int i=0; i<row; i++) //iterate through rows
		{
			for (int j=0; j<col; j++) //check each tile in row
			{
				if (map.get(i).get(j).getType().equals("finish")) //if that tile is a start type
				{
					index = i;
				}
			}
		}
		return index;
	}
	
	//get the finish tile column
	public int getFinishCol()
	{
		int index = -1; //if it doesn't exist
		for (int i=0; i<row; i++) //iterate rows
		{
			for (int j=0; j<col; j++) //iterate columns
			{
				if (map.get(i).get(j).getType().equals("finish")) //if there's a start tile
				{
					index = j; //return the column
				}
			}
		}
		return index;
	}
	
	//get a specific tile
	public Tile getTile(int r, int c)
	{
		if (r < map.size() && c < map.get(r).size())
		{
			return map.get(r).get(c);
		} else {
			return null;
		}
	}
	
		
	//test this map
	public static void main (String [] args)throws IOException{

		//how to build the map and find the start and finish tiles
		World w = new World();
		w.buildMap();
		int startRow = w.getStartRow();
		int startCol = w.getStartCol();
		System.out.println(startRow + ", " + startCol );
		int finishRow = w.getFinishRow();
		int finishCol = w.getFinishCol();
		System.out.println(finishRow + ", " + finishCol );
		System.out.println("\n" + w.getMap());
		System.out.println(w.getTile(3, 5));
		
		//how to look at what's in tiles
		System.out.println("You want to look around? Let's say you're on tile 3,5");
		System.out.println("Above: " + w.getTile(2, 5).getType());
		System.out.println("Right: " + w.getTile(3, 6).getType());
		System.out.println("Left: " + w.getTile(3, 4).getType());
		System.out.println("Below: " + w.getTile(4, 5).getType());
		
		//you can use addition and subtraction to go from tile to tile
		//you can use the tile type to do stuff
		for (int i=0; i<6; i++)
		{
			if (w.getTile(i, 5).getType().equals(tiger);
			{
				System.out.print(tiger);
			} else {
				System.out.println("Step");
			}
		}

	}
}
