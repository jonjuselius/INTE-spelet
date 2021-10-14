public abstract class Character {

    private String name;
    private boolean isAlive;
    private Race race;
    private Job job;
    private int level;

    //hitPoints = hälsa
    private int hitPoints;
    private int mana;
    private int strength;
    private int intelligence;
    private int wisdom;
    private int charisma;



    public Character(String name, Race race, boolean isAlive) {
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
}
