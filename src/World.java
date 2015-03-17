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
	
	//makes the map
	public void buildMap() throws IOException
	{
		//file reader from http://docs.oracle.com/javase/tutorial/essential/io/scanfor.html
		Scanner s = null;
		try {
			s = new Scanner(new BufferedReader(new FileReader(fileName)));
			
			int row = 0; //keeps track of row #
			int col = 0; //keeps track of col #
			while (s.hasNextLine()) {
				//System.out.println(s.next());
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
					} else{
						//anything else is gonna be empty
						tile.setType("Emtpy Tile");
					}
					currentLine.add(tile); //now add the tile
				}
				
				row++; //increments row
				
				
			}
			System.out.println(map);
			//put these in array list
			System.out.println(map.get(1).get(4)); //2D array lists are weird
		} finally {
			if (s != null) {
				s.close();
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main (String [] args)throws IOException{

		World w = new World();
		w.buildMap();


	}
}
