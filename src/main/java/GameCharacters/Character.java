package GameCharacters;

import Magic.SpellCollection;

public abstract class Character {

    private String name;
    private boolean isAlive;
    private Race race;
    //private Job job;
    private int level;
    private SpellCollection spellCollection;


    //hitPoints = hälsa
    private int maxMana;
    private int maxHitpoints;
    private int remainingHitPoints;
    private int remainingMana;
    private int strength;
    private int intelligence;



    public Character(String name, Race race, boolean isAlive) {
        this.name = name;
        this.race = race;
        isAlive = true;
        setStrength(10);
        setIntelligence(10);
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

    public int getMaxHitpoints() {
        return maxHitpoints;
    }

    protected void setMaxHitpoints(int maxHitpoints) {
        this.maxHitpoints = maxHitpoints;
    }

    public int getRemainingHitPoints() {
        return remainingHitPoints;
    }

    public void setRemainingHitPoints(int remainingHitPoints) {
        this.remainingHitPoints = remainingHitPoints;
    }

    public int getRemainingMana() {
        return remainingMana;
    }

    protected void setRemainingMana(int remainingMana) {
        this.remainingMana = remainingMana;
    }
}
