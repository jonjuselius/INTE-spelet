package GameCharacters;

import GameCharacters.Character;

public class Player extends Character {

    private int level;

    public Player(String name, Race race, boolean isAlive) {

        super(name, race, isAlive);
        setLevel(1);

    }

    protected void setLevel(int level) {
        this.level = level;
    }


}
