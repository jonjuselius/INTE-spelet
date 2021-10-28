package Map;

import java.util.HashMap;
import java.util.Map;

public class GameMapGenerator {
    private GameMap gameMap;
    private HashMap<Terrain, Integer> probabilityOfTerrains = new HashMap<>();

    public GameMapGenerator(int width, int height) {
        this.gameMap = new GameMap(width, height);
    }

    public GameMap generate(int fakeRandom) {
        for (int x = 0; x < gameMap.getWidth(); x++) {
            for (int y = 0; y < gameMap.getHeight(); y++) {
                GameMapPosition tile = new GameMapPosition(x, y);
                gameMap.putTileOnMap(tile, x, y);

                findNeighbors(tile, gameMap);

                //Check the terrain of the south and west neighbor
                // & increase the probability of this tile getting the same terrain
                Terrain terrain = getTerrainOfNeighbors(tile);
                tile.setTerrain(terrainGenerator(terrain, fakeRandom));
            }
        }
        return gameMap;
    }

    private Terrain getTerrainOfNeighbors(GameMapPosition tile) {
        GameMapPosition southNeighbor = tile.getNeighbors()[3];
        GameMapPosition westNeighbor = tile.getNeighbors()[0];
        GameMapPosition[][] tiles = gameMap.getMapTiles();

        if (null != southNeighbor) {
            return tiles[southNeighbor.getXPos()][southNeighbor.getYPos()].getTerrain();
        } else if (null != westNeighbor) {
            return tiles[westNeighbor.getXPos()][westNeighbor.getYPos()].getTerrain();
        }

        //If the tile doesn't have any neighbors yet, return grass as standard
        return Terrain.GRASS;
    }

    private Terrain terrainGenerator(Terrain terrain, int fakeRandom) {
        //The value of how the probability increases for a tile being of the same terrain as its neighbors
        final int probabilityIncreaser = 20;
        probabilityOfTerrains.put(Terrain.GRASS, 1);
        probabilityOfTerrains.put(Terrain.LAVA, 1);
        probabilityOfTerrains.put(Terrain.WATER, 1);
        
        //Increase probability for a certain terrain
        probabilityOfTerrains.replace(terrain, probabilityIncreaser);

        Map<Terrain, Integer> terrainProbabilitiesRandomized = probabilityOfTerrains;

        //Assign random probability values for the probabilityOfTerrain
        for (HashMap.Entry<Terrain, Integer> entry : terrainProbabilitiesRandomized.entrySet()) {
            Terrain key = entry.getKey();
            Integer value = entry.getValue();
            //In reality, the random would return an int between 1 & 100
            terrainProbabilitiesRandomized.replace(key, (value * fakeRandom));
        }

        //The terrain to be returned
        Terrain chosenTerrain = null;

        int highest = 0;
        for (HashMap.Entry<Terrain, Integer> entry : terrainProbabilitiesRandomized.entrySet()) {
            Terrain key = entry.getKey();
            Integer value = entry.getValue();

            if (value > highest) {
                highest = value;
                chosenTerrain = key;
            }
        }
        return chosenTerrain;
    }

    private void findNeighbors(GameMapPosition tile, GameMap gameMap) {
        int xPos = tile.getXPos();
        int yPos = tile.getYPos();

        GameMapPosition westNeighbor;
        GameMapPosition eastNeighbor;
        GameMapPosition northNeighbor;
        GameMapPosition southNeighbor;

        //Set west neighbor
        if (0 > xPos - 1) {
            westNeighbor = null;
        } else {
            westNeighbor = new GameMapPosition(xPos - 1, yPos);
        }

        //Set east neighbor
        if (xPos + 1 > gameMap.getWidth() - 1) {
            eastNeighbor = null;
        } else {
            eastNeighbor = new GameMapPosition(xPos + 1, yPos);
        }

        //Set north neighbor
        if (yPos + 1 > gameMap.getHeight() - 1) {
            northNeighbor = null;
        } else {
            northNeighbor = new GameMapPosition(xPos, yPos + 1);
        }

        //Set south neighbor
        if (0 > yPos - 1) {
            southNeighbor = null;
        } else {
            southNeighbor = new GameMapPosition(xPos, yPos - 1);
        }

        tile.setNeighbors(westNeighbor, eastNeighbor, northNeighbor, southNeighbor);
        }

        //get the map of probabilites for the last set tile in map
    public HashMap<Terrain, Integer> getProbabilityOfTerrains() {
        HashMap<Terrain, Integer> probabilityOfTerrainsCopy = probabilityOfTerrains;
        return probabilityOfTerrainsCopy;
    }
}

