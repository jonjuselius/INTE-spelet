package GameCharacters;

import GameCharacters.Character;
import Jobs.Job;
import Races.*;

public class NPC extends Character {

	public NPC(String name, Race race, Job job, boolean isAlive, int level) {
		super(name, race, job, isAlive);

		this.level = level;
	}

	public void setLevel(int level) {

		this.level = level;
	}
	
    public int getLevel() {
    	return level;
    }

}