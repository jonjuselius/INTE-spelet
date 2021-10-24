package GameCharacters;

import Jobs.Job;
import Map.Map;
import Map.MapPosition;
import Races.*;

public class Player extends Character {

//    private int level;

    public Player(String name, Race race,Job job, boolean isAlive, MapPosition mapPosition) {
        super(name, race, job, isAlive, mapPosition);
        setLevel(1);
        setPlayerPos(0, 0);
    }

    private void setPlayerPos(int xPos, int yPos) {

    }

    public MapPosition moveNorth() {
        return null;
    }

    public MapPosition moveSouth() {
        return null;
    }

    public MapPosition moveWest() {
        return null;
    }

    public MapPosition moveEast() {
        return null;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    
    public int getLevel() {
    	return level;
    }
    
    


}