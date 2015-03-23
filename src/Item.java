//items the player can find along the way in the map
public class Item {

	private String name;
	private int damage;
	private int durability;
	
	
	public Item(String n, int d, int u){
		name = n;
		damage = d;
		durability = u;
		
	}
	
	//get methods
	public String getName(){
		return name;
	}
	
	public int getDamage(){
		return damage;
	}
	
	public int getDur(){
		return durability;
	}
	
	//set methods
	//When used the durability of an item goes down
	//If it reaches 0 it is destroyed
	public void setDur(int x){
		durability-=x;
	}
	
	
	
	
	
	
}
