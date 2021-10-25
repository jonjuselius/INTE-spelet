package Map;

import java.util.HashMap;

public class GameMapGenerator {
    private GameMap map;

    public GameMapGenerator(int width, int height) {
        this.map = new GameMap(width, height);
    }

    public GameMap generate(int fakeRandom) {
        //The value of how the probability increases for a tile being of the same terrain as its neighbors
        final int PROBABILITY_INCREASER = 50;

        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                GameMapPosition tile = new GameMapPosition(x, y);
                map.putTileOnMap(tile, x, y);

                findNeighbors(tile, map);

                //Check the terrain of the south and west neighbor & increase the probability of this tile getting the same terrain
                Terrain terrain = getTerrainOfNeighbors(tile);
                tile.setTerrain(terrainGenerator(terrain, PROBABILITY_INCREASER, fakeRandom));
            }
        }
        return map;
    }

    private Terrain getTerrainOfNeighbors(GameMapPosition tile) {
        GameMapPosition southNeighbor = tile.getSouthNeighbor();
        GameMapPosition westNeighbor = tile.getWestNeighbor();
        GameMapPosition[][] tiles = map.getMapTiles();

        if (southNeighbor != null) {
            return southNeighbor.getTerrain();
        } else if (westNeighbor != null) {
            return westNeighbor.getTerrain();
        }

        //If the tile doesn't have any neighbors yet, return grass as standard
        return Terrain.GRASS;
    }

    private Terrain terrainGenerator(Terrain terrain, int probability, int fakeRandom) {
        HashMap<Terrain, Integer> terrains = new HashMap<>();
        terrains.put(Terrain.GRASS, 1);
        terrains.put(Terrain.LAVA, 1);
        terrains.put(Terrain.WATER, 1);
        
        //Increase probability for a certain terrain
        terrains.replace(terrain, probability);

        return calculateProbability(terrains, fakeRandom);
    }

    private Terrain calculateProbability(HashMap<Terrain, Integer> terrains, int fakeRandom) {
        //Assign random probability values for the terrains
        for (HashMap.Entry<Terrain, Integer> entry : terrains.entrySet()) {
            Terrain key = entry.getKey();
            Integer value = entry.getValue();
            //In reality, the random would return an int between 1 & 100
            terrains.replace(key, (value * fakeRandom));
        }

        //The terrain with the highest probability to be returned
        Terrain terrainWithHighestProbability = null;

        int highestProbability = 0;
        for (HashMap.Entry<Terrain, Integer> entry : terrains.entrySet()) {
            Terrain reviewTerrain = entry.getKey();
            Integer reviewProbability = entry.getValue();

            if (reviewProbability > highestProbability) {
                highestProbability = reviewProbability;
                terrainWithHighestProbability = reviewTerrain;
            }
        }
        return terrainWithHighestProbability;
    }

    private void findNeighbors(GameMapPosition tile, GameMap map) {
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
        if (xPos + 1 > map.getWidth() - 1) {
            eastNeighbor = null;
        } else {
            eastNeighbor = new GameMapPosition(xPos + 1, yPos);
        }

        //Set north neighbor
        if (yPos + 1 > map.getHeight() - 1) {
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

