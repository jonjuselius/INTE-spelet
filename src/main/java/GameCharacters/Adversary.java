package GameCharacters;

import GameCharacters.Character;
import Jobs.Job;
import Races.*;

public class Adversary extends Character {


    public Adversary(String name, Race race,Job job, boolean isAlive, int level) {
        super( name,  race, job,  isAlive);
        this.level = level;
    }

    protected void setLevel(int level) {
        this.level = level;
    }
}