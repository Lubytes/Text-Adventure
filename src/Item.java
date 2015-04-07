//items the player can find along the way in the map
public class Item {

	private String name;
	private int damage;
	private int durability;
	
	//Randomly chooses an item to add to inventory
	public Item(){
		
		int a = (int) (Math.random()*5)+1;

		 if(a == 1){
			 name = "Water bottle";
			 damage = 0;
			 durability = 2;
		  }
		  else if(a == 2){
			name = "Stick";
			damage = 4;
			durability = 4;
		  }
		  else if(a == 3){
			name = "Rock";
			damage = 6;
			durability = 4;
		  }
		  else if(a == 4){
			name = "Wreckage";
			damage = 8;
			durability = 3;
		  }
		  else {
			name = "Baseball Bat";
			damage = 10;
			durability = 2;
		  }  
		
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
	//When used, the durability of an item goes down
	//If it reaches 0 it is destroyed
	public void decDur(){
		durability--;
	}
	
	
	
	
	
	
}
