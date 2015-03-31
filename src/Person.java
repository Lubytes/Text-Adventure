/*
 * This is the person.
 * Put inventory here I guess?
 */
import java.util.ArrayList;
public class Person {
	private int hp;
	private int attack;
	
	private ArrayList<Item> inv = new ArrayList<Item>();
	
	
	public Person(){
		hp = 100;
		attack = 50;
	}
	
	//Max inv space is currently 5
	public boolean addItem(Item a){
		
		boolean ret = false;
		
		if(inv.size()>=5)
			System.out.println("Not enough inventory space");
		else{
			inv.add(a);
			ret = true;
		}
		
		return ret;
		
	}
	
	
	public ArrayList<Item> getInventory() {
		return inv;
	}
	
	
	
}
