package Races;
public class Player extends Character {

    private int level;

    public Player(String name, Race race, boolean isAlive, int health) {

        super(name, race, isAlive, health);
        setLevel(1);

    }

    protected void setLevel(int level) {
        this.level = level;
    }


}
