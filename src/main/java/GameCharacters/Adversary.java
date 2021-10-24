package GameCharacters;

import Jobs.Job;
import Map.*;
import Races.*;

public class Adversary extends Character {


    public Adversary(String name, Race race, Job job, boolean isAlive, int level, MapPosition position) {
        super( name,  race, job,  isAlive, position);
        this.level = level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}