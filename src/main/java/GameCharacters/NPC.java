package GameCharacters;

import Jobs.Job;
import Map.*;
import Races.*;

public class NPC extends Character {

	public NPC(String name, Race race, Job job, boolean isAlive, int level, MapPosition position) {
		super(name, race, job, isAlive, position);

		this.level = level;
	}

	public void setLevel(int level) {

		this.level = level;
	}
	
    public int getLevel() {
    	return level;
    }

}