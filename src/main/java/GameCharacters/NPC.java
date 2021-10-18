package GameCharacters;

import GameCharacters.Character;
import Jobs.Job;
import Races.*;


public class NPC extends Character {


    private int level;

    public NPC(String name, Race race,Job job, boolean isAlive) {
        super( name, race, job, isAlive);
        this.level = level;
    }
}
