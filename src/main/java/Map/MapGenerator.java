package Map;

import java.util.HashMap;

public class MapGenerator {
    private Map map;

    public MapGenerator(int width, int height) {
        this.map = new Map(width, height);
    }

    public Map generate(int fakeRandom) {
        //The value of how the probability increases for a tile being of the same terrain as its neighbors
        final int PROBABILITY_INCREASER = 50;

        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                MapPosition tile = new MapPosition(x, y);
                map.put(tile, x, y);

                setNeighbors(tile);

                //Check the terrain of the south and west neighbor & increase the probability of this tile getting the same terrain
                Terrain terrain = checkTerrainOfNeighbors(tile);
                tile.setTerrain(terrainGenerator(terrain, PROBABILITY_INCREASER, fakeRandom));
            }
        }
        return map;
    }

    private Terrain checkTerrainOfNeighbors(MapPosition tile) {
        MapPosition southNeighbor = tile.getNeighbors()[3];
        MapPosition westNeighbor = tile.getNeighbors()[0];
        MapPosition[][] tiles = map.getMapTiles();

        if (southNeighbor != null) {
            return tiles[southNeighbor.getXPos()][southNeighbor.getYPos()].getTerrain();
            //return map.getTiles().get(southNeighbor.getXPos()).get(southNeighbor.getYPos()).getTerrain();
        } else if (westNeighbor != null) {
            return tiles[westNeighbor.getXPos()][westNeighbor.getYPos()].getTerrain();
            //return map.getTiles().get(westNeighbor.getXPos()).get(westNeighbor.getYPos()).getTerrain();
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

        //Assign random probability values for the terrains
        for (HashMap.Entry<Terrain, Integer> entry : terrains.entrySet()) {
            Terrain key = entry.getKey();
            Integer value = entry.getValue();
            //In reality, the random would return an int between 1 & 100
            terrains.replace(key, (value * fakeRandom));
        }

        //The terrain to be returned
        Terrain chosenTerrain = null;

        int highest = 0;
        for (HashMap.Entry<Terrain, Integer> entry : terrains.entrySet()) {
            Terrain key = entry.getKey();
            Integer value = entry.getValue();

            if (value > highest) {
                highest = value;
                chosenTerrain = key;
            }
        }
        return chosenTerrain;
    }

    private void setNeighbors(MapPosition tile) {
        int xPos = tile.getXPos();
        int yPos = tile.getYPos();

        MapPosition westNeighbor;
        MapPosition eastNeighbor;
        MapPosition northNeighbor;
        MapPosition southNeighbor;

        //Set west neighbor
        if (xPos - 1 < 0 ) {
            westNeighbor = null;
        } else {
            westNeighbor = new MapPosition(xPos - 1, yPos);
        }

        //Set east neighbor
        if (xPos + 1 > map.getWidth() - 1) {
            eastNeighbor = null;
        } else {
            eastNeighbor = new MapPosition(xPos + 1, yPos);
        }

        //Set north neighbor
        if (yPos + 1 > map.getHeight() - 1) {
            northNeighbor = null;
        } else {
            northNeighbor = new MapPosition(xPos, yPos + 1);
        }

        //Set south neighbor
        if (yPos - 1 < 0) {
            southNeighbor = null;
        } else {
            southNeighbor = new MapPosition(xPos, yPos - 1);
        }

        tile.setNeighbors(westNeighbor, eastNeighbor, northNeighbor, southNeighbor);
        }


    }

