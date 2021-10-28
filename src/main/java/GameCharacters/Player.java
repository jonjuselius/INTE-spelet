package GameCharacters;

import Jobs.Job;
import Map.GameMapPosition;
import Map.Terrain;
import Races.*;

public class Player extends Character {

//    private int level;

    public Player(String name, Race race,Job job, boolean isAlive, GameMapPosition mapPosition) {
        super(name, race, job, isAlive, mapPosition);
        setLevel(1);
        setPlayerPos(0, 0);
    }

    private void setPlayerPos(int xPos, int yPos) {

    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public GameMapPosition moveNorth() {
        GameMapPosition currentPos = this.getPosition();
        GameMapPosition northNeighbor = currentPos.getNorthNeighbor();
        if (northNeighbor == null) {
            throw new IllegalArgumentException("Can't move out of the map");
        }

        return tryToMoveHere(this, currentPos, northNeighbor);
    }

    public GameMapPosition moveSouth() {
        GameMapPosition currentPos = this.getPosition();
        GameMapPosition southNeighbor = currentPos.getSouthNeighbor();
        if (southNeighbor == null) {
            throw new IllegalArgumentException("Can't move out of the map");
        }

        return tryToMoveHere(this, currentPos, southNeighbor);
    }

    public GameMapPosition moveWest() {
        GameMapPosition currentPos = this.getPosition();
        GameMapPosition westNeighbor = currentPos.getWestNeighbor();
        if (westNeighbor == null) {
            throw new IllegalArgumentException("Can't move out of the map");
        }

        return tryToMoveHere(this, currentPos, westNeighbor);
    }

    public GameMapPosition moveEast() {
        GameMapPosition currentPos = this.getPosition();
        GameMapPosition eastNeighbor = currentPos.getEastNeighbor();
        if (eastNeighbor == null) {
            throw new IllegalArgumentException("Can't move out of the map");
        }

        return tryToMoveHere(this, currentPos, eastNeighbor);
    }

    private GameMapPosition tryToMoveHere(Player player, GameMapPosition currentPos, GameMapPosition positionToMoveTo) {
        if (!player.isAlive()) {
            throw new IllegalArgumentException("Can't move dead player");
        }

        if (positionToMoveTo.getTerrain() == Terrain.LAVA) {
            if (player.getIfCanFly()) {
                this.setPosition(positionToMoveTo);
                return positionToMoveTo;
            } else {
                player.die();
                return positionToMoveTo;
            }
        }

        if (positionToMoveTo.getTerrain() == Terrain.GRASS) {
            this.setPosition(positionToMoveTo);
            return positionToMoveTo;
        }

        if (positionToMoveTo.getTerrain() == Terrain.WATER) {
            if (player.getIfCanSwim() || player.getIfCanFly()) {
                this.setPosition(positionToMoveTo);
                return positionToMoveTo;
            }
        }
        return currentPos;
    }


    
    


}