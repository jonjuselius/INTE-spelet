package Races;
import Jobs.*;
public class NPC extends Character {


    private int level;

    public NPC(String name, Race race, Job job, boolean isAlive, int level, int health) {
        super(name, race, job, isAlive, health);
        this.level = level;
    }
    
	public int getLevel() {
		return level;
	}
	
    protected void setLevel(int level) {
        this.level = level;
    }
}
