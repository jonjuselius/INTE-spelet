package Races;
public abstract class Character {

    private String name;
    private boolean isAlive;
    private Race race;
    private Job job;
    private int level;

    private int health;
    private int hitPoints;
    private int mana;
    private int strength;
    private int intelligence;
    private int wisdom;
    private int charisma;
    



    public Character(String name, Race race, boolean isAlive, int health) {
        this.name = name;
        this.race = race;
        isAlive = true;
        
        setStrength(10);
        setIntelligence(10);
        setWisdom(10);
        setCharisma(10);
    }

    public String getName() {
        return name;
    }

    public void increaseHealth(int hp) {
    	if(getHealth() + hp > race.getMaxHealth()) {
    		setHealth(race.getMaxHealth());
    		return;
    	}
    	throw new IllegalStateException();    	
    }
    
    public int getLevel(){
        return level;
    }

    public boolean isAlive() {
        return isAlive;
    }
    
    public int getHealth() {
        return health;
    }

    protected void setHealth(int health) {
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }

    protected void setStrength(int strength) {
        this.strength = strength;
    }
    

    public int getintelligence() {
        return intelligence;
    }

    protected void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    protected void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public int getCharisma() {
        return charisma;
    }

    protected void setCharisma(int charisma) {
        this.charisma = charisma;
    }
    
//    public void move() {
//    	/* 
//    	 * If this character is in water 
//    	 * 	...and can swim -> move at speed s1
//    	 *  ...and cannot swim -> isAlive = false;
//    	 * 
//    	 * */
//    }
}
