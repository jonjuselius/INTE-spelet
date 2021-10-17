package GameCharacters;

import Magic.SpellCollection;

public abstract class Character {

    private String name;
    private boolean isAlive;
    private Race race;
    //private Job job;
    protected int level;
    private SpellCollection spellCollection;


    //hitPoints = hälsa
    private int maxMana;
    private int maxHealth;
    private int remainingHealth;
    private int remainingMana;
    private int strength;
    private int intelligence;



    public Character(String name, Race race, boolean isAlive) {
        this.name = name;
        this.race = race;
        isAlive = true;
        setStrength(10);
        setIntelligence(10);
        setMaxMana(200);
        remainingMana = maxMana;
    }

    public String getName() {
        return name;
    }

    public int getLevel(){
        return level;
    }

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

    public int getMaxHealth() {
        return maxHealth;
    }

    protected void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
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
}
