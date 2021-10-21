package GameCharacters;

import Jobs.Job;
import Map.*;
import Races.*;

public class NPC extends Character {

	public NPC(String name, Race race, Job job, boolean isAlive, int level, Map map, MapPosition position) {
		super(name, race, job, isAlive, map, position);

		this.level = level;
	}

	public void setLevel(int level) {

		this.level = level;
	}
	
    public int getLevel() {
    	return level;
    }

}