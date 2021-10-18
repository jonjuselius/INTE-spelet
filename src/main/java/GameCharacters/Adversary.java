package GameCharacters;

import GameCharacters.Character;

public class Adversary extends Character {

    private int level;

    public Adversary(String name, Race race, boolean isAlive, int level) {
        super(name, race, isAlive);
        this.level = level;
    }
}
