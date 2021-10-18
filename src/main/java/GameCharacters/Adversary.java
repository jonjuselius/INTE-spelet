package GameCharacters;

import GameCharacters.Character;
import Jobs.Job;
import Races.*;

public class Adversary extends Character {

    private int level;

    public Adversary(String name, Race race,Job job, boolean isAlive, int health) {
        super( name,  race, job,  isAlive,  health);
        this.level = level;
    }
}
