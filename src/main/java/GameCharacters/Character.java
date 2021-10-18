package GameCharacters;

import Inventory.*;
import Jobs.Job;
import Magic.SpellCollection;
import Races.*;


public abstract class Character {

    private String name;
    private boolean isAlive;
    private Race race;
	private Job job;
    protected int level;
	private int health;

    private SpellCollection spellCollection;
    private Inventory inventory;


    //hitPoints = hälsa
    private int maxMana;
    private int maxHealth;
    private int remainingHealth;
    private int remainingMana;
    private int strength;
    private int intelligence;


//TODO ändra maxhealth och maxmana
    public Character(String name, Race race,Job job, boolean isAlive) {
        this.name = name;
        this.race = race;
		this.job = job;
        isAlive = true;
        setStrength(10);
        setIntelligence(10);
        setMaxMana(200);
        setHealth(300);
        remainingMana = maxMana;
        remainingHealth = maxHealth;
        this.inventory = inventory;

    }

    public String getName() {
        return name;
    }
    
	public void increaseHealth(int hp) {
		if (getHealth() + hp > race.getMaxHealth()) {
			setHealth(race.getMaxHealth());
			return;
		}
		
		throw new IllegalStateException();
	}//increase

    public int getLevel(){
        return level;
    }
    
    //Lagt health i character
    
    public int getHealth(){
        return health;
    }
    
	protected void setHealth(int health) {
		this.health = health;
	}//

    public boolean isAlive() {
        return isAlive;
    }

    public int getStrength() {
        return strength;
    }

    protected void setStrength(int strength) {
        this.strength = strength;
    }

    public int getIntelligence() {
        return intelligence;
    }

    protected void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getMaxMana() {
        return maxMana;
    }

    protected void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    private void setMaxMana(){
        //Calculate job/race/etc.
    }

    public int getRemainingHealth() {
        return remainingHealth;
    }

    public void setRemainingHealth(int remainingHealth) {
        this.remainingHealth = remainingHealth;
    }

    public int getRemainingMana() {
        return remainingMana;
    }

    public void setRemainingMana(int remainingMana) {
        this.remainingMana = remainingMana;
    }

    public void takeDamage(int damage){
        remainingHealth = remainingHealth - damage;
        if (remainingHealth <= 0){
            isAlive = false;
        }
    }

    public void useMana(int manaCost){
        remainingMana = remainingMana - manaCost;
    }

    public void getHealed(int healPoints){
        int healTotal = remainingHealth + healPoints;
        remainingHealth = Math.min(healTotal, maxHealth);
    }
    
    public Inventory getInventory() {
        return inventory;
    }
}
