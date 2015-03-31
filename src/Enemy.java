
public class Enemy {
  String enemyType;
  private hp;
  private attack;
  
  public Enemy(String t, int hp, int attack) {
      this.type = t;
      this.hp = hp;
      this.attack = attack;
  }
  
  public void setAttack(int attack) {
        this.attack = attack;
  }
  
  public void setHP(int hp) {
        this.hp = hp;
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
