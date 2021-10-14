package Races;
public class NPC extends Character {


    private int level;

    public NPC(String name, Race race, boolean isAlive, int level) {
        super(name, race, isAlive);
        this.level = level;
    }
}
