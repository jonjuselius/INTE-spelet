package Map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MapGenerator {

    public Map generateMap(int width, int height) {
        Map map = new Map(width, height);
        generateRandomMap(map);
        return map;
    }

    private void generateRandomMap(Map map) {
        final int PROBABILITY_INCREASER = 50;

        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                MapTile tile = new MapTile(new Position(x, y));
                map.setTile(tile);

                //Set neighbors
                setNeighbors(map, tile);

                //Check the terrain of the above and below neighbor & increase the probability of this tile getting the same terrain
                Map.MapTile.Terrain terrain = checkTerrainOfNeighbors(map, tile);
                //TODO: Ändras tile:n på map:en nu, eller bara det lokala objektet i metoden?
                tile.setTerrain(randomTerrainGenerator(terrain, PROBABILITY_INCREASER));
                //map.getTiles().get(tile.getPosition().getxPos()).get(tile.getPosition().getyPos()).setTerrain(randomTerrainGenerator(terrain, PROBABILITY_INCREASER));
            }
        }
    }

    private Map.MapTile.Terrain checkTerrainOfNeighbors(Map map, MapTile tile) {
        //TODO: Är det ens någon idé att kolla den övre grannen? Den kommer väl aldrig att finnas i detta skede?
        Position upNeighbor = tile.getNeighbors()[2];
        Position downNeighbor = tile.getNeighbors()[3];

        if (upNeighbor != null) {
            return map.getTiles().get(upNeighbor.getxPos()).get(upNeighbor.getyPos()).getTerrain();
        }

        if (downNeighbor != null) {
            return map.getTiles().get(upNeighbor.getxPos()).get(upNeighbor.getyPos()).getTerrain();
        }

        //If the tile doesn't have any neighbors yet, return grass
        return MapTile.Terrain.GRASS;
    }

    private Map.MapTile.Terrain randomTerrainGenerator(Map.MapTile.Terrain terrain, int probability) {
        HashMap<Map.MapTile.Terrain, Integer> terrains = new HashMap<>();
        terrains.put(MapTile.Terrain.GRASS, 1);
        terrains.put(MapTile.Terrain.LAVA, 1);
        terrains.put(MapTile.Terrain.WATER, 1);
        
        //Increase probability for a certain terrain
        terrains.replace(terrain, probability);

        //Random
        Random random = new Random();

        //Assign random probability values for the terrains
        for (HashMap.Entry<Map.MapTile.Terrain, Integer> entry : terrains.entrySet()) {
            Map.MapTile.Terrain key = entry.getKey();
            Integer value = entry.getValue();

            terrains.replace(key, (value * random.nextInt(101)));
        }

        //The terrain to be returned
        Map.MapTile.Terrain chosenTerrain = null;

        int highest = 0;
        for (HashMap.Entry<Map.MapTile.Terrain, Integer> entry : terrains.entrySet()) {
            Map.MapTile.Terrain key = entry.getKey();
            Integer value = entry.getValue();

            if (value > highest) {
                highest = value;
                chosenTerrain = key;
            }
        }
        return chosenTerrain;
    }

    private void setNeighbors(Map map, MapTile tile) {
        int xPos = tile.getPosition().getxPos();
        int yPos = tile.getPosition().getyPos();

        Position leftNeighbor;
        Position rightNeighbor;
        Position upNeighbor;
        Position downNeighbor;

        //Set left neighbor
        if (xPos - 1 < map.getWidth() -1 ) {
            leftNeighbor = null;
        } else {
            leftNeighbor = new Position(xPos - 1, yPos);
        }

        //Set right neighbor
        if (xPos + 1 > map.getWidth() - 1) {
            rightNeighbor = null;
        } else {
            rightNeighbor = new Position(xPos + 1, yPos);
        }

        //Set neighbor above
        if (yPos + 1 > map.getHeight() - 1) {
            upNeighbor = null;
        } else {
            upNeighbor = new Position(xPos, yPos + 1);
        }

        //Set neighbor below
        if (yPos - 1 < map.getHeight() - 1) {
            downNeighbor = null;
        } else {
            downNeighbor = new Position(xPos, yPos - 1);
        }

        tile.setNeighbors(leftNeighbor, rightNeighbor, upNeighbor, downNeighbor);
        }
    }

