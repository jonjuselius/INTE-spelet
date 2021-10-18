package GameCharacters;

import GameCharacters.Character;
import Jobs.Job;
import Races.*;

public class Player extends Character {

    //private int level;

    public Player(String name, Race race,Job job, boolean isAlive) {

        super( name,  race, job,  isAlive);
        setLevel(1);

    }

    protected void setLevel(int level) {
        this.level = level;
    }


}
