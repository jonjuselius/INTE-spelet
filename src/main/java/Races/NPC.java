package Races;
public class NPC extends Character {


    private int level;

    public NPC(String name, Race race, boolean isAlive, int level, int health) {
        super(name, race, isAlive, health);
        this.level = level;
    }
}
