
public class Enemy {
  String enemyType;
  private int hp;
  private int attack;
  
  public Enemy() {
	  
	  int a = (int) (Math.random()*5)+1;
	  
	  if(a == 1){
		  enemyType = "Tortoise";
	      hp = 1;
	      attack = 1;
	  }
	  else if(a == 2){
		  enemyType = "Snake";
	      hp = 4;
	      attack = 2;
	  }
	  else if(a == 3){
		  enemyType = "Scorpion";
	      hp = 4;
	      attack = 6;
	  }
	  else if(a == 4){
		  enemyType = "Wolf";
	      hp = 20;
	      attack = 8;
	  }
	  else {
		enemyType = "Tiger";
		hp = 30;
		attack = 10;
	  }  
  }
	  
	  
  
  public void setAttack(int attack) {
        this.attack = attack;
  }
  
  //subtracts how much HP it takes as damamge
  public void setHP(int a) {
        hp -= a;
  }
  
  public int getHP() {
    return hp;
  }
  
  public int getAttack() {
    return attack;
  }
  
  public String getEnemyType() {
    return enemyType;
  }
  
  public String toString() {
       return "You've encountered a " + getEnemyType() + "! It has " + getHP() + " health and " + getAttack() 
       + " attack! Do you wish to fight, or do you wish to escape?";
  }

}
