/*
 * This is the person.
 * Put inventory here I guess?
 */
import java.util.ArrayList;
public class Person {
	private int hp;
	private int attack;
	private int parts;
	private boolean hasAttacked = false;
	
	private ArrayList<Item> inv = new ArrayList<Item>();
	
	
	public Person(){
		hp = 100;
		parts = 0;
	}
	
	public int getHP(){
		return hp;
	}
	
	public void cngHP(int a){
		hp += a;
	}
	
	public void addPart(){
		parts++;
	}
	
	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public boolean allParts(){
		if (parts == 8)
			return true;
		return false;
	}
	
	public boolean attacked(){
		return hasAttacked;
	}
	
	public void setAttacked(boolean a){
		hasAttacked = a;
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
	
	public void battle()
	{
		
	}
}
