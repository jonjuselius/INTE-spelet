package Map;

import java.util.HashMap;

public class GameMapGenerator {
    private GameMap gameMap;

    public GameMapGenerator(int width, int height) {
        this.gameMap = new GameMap(width, height);
    }

    public GameMap generate(int fakeRandom) {
        //The value of how the probability increases for a tile being of the same terrain as its neighbors
        final int probabilityIncreaser = 50;

        for (int x = 0; x < gameMap.getWidth(); x++) {
            for (int y = 0; y < gameMap.getHeight(); y++) {
                GameMapPosition tile = new GameMapPosition(x, y);
                gameMap.putTileOnMap(tile, x, y);

                findNeighbors(tile, gameMap);

                //Check the terrain of the south and west neighbor & increase the probability of this tile getting the same terrain
                Terrain terrain = getTerrainOfNeighbors(tile);
                tile.setTerrain(terrainGenerator(terrain, probabilityIncreaser, fakeRandom));
            }
        }
        return gameMap;
    }

    public Terrain getTerrainOfNeighbors(GameMapPosition tile) {
        GameMapPosition southNeighbor = tile.getNeighbors()[3];
        GameMapPosition westNeighbor = tile.getNeighbors()[0];
        GameMapPosition[][] tiles = gameMap.getMapTiles();

        if (southNeighbor != null) {
            return tiles[southNeighbor.getXPos()][southNeighbor.getYPos()].getTerrain();
        } else if (westNeighbor != null) {
            return tiles[westNeighbor.getXPos()][westNeighbor.getYPos()].getTerrain();
        }

        //If the tile doesn't have any neighbors yet, return grass as standard
        return Terrain.GRASS;
    }

    private Terrain terrainGenerator(Terrain terrain, int probability, int fakeRandom) {
        HashMap<Terrain, Integer> probabilityOfTerrain = new HashMap<>();
        probabilityOfTerrain.put(Terrain.GRASS, 1);
        probabilityOfTerrain.put(Terrain.LAVA, 1);
        probabilityOfTerrain.put(Terrain.WATER, 1);
        
        //Increase probability for a certain terrain
        probabilityOfTerrain.replace(terrain, probability);

        //Assign random probability values for the probabilityOfTerrain
        for (HashMap.Entry<Terrain, Integer> entry : probabilityOfTerrain.entrySet()) {
            Terrain key = entry.getKey();
            Integer value = entry.getValue();
            //In reality, the random would return an int between 1 & 100
            probabilityOfTerrain.replace(key, (value * fakeRandom));
        }

        //The terrain to be returned
        Terrain chosenTerrain = null;

        int highest = 0;
        for (HashMap.Entry<Terrain, Integer> entry : probabilityOfTerrain.entrySet()) {
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
        if (xPos - 1 < 0 ) {
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
        if (yPos - 1 < 0) {
            southNeighbor = null;
        } else {
            southNeighbor = new GameMapPosition(xPos, yPos - 1);
        }

        tile.setNeighbors(westNeighbor, eastNeighbor, northNeighbor, southNeighbor);
        }
    }

