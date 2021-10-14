package Map;

import java.util.ArrayList;

public class MapGenerator {

    public Map generateMap(int width, int height) {
        Map map = new Map(width, height);
        fillMapWithGrass(map);
        generateTileNeighbors(map);

        return map;
    }

    private void fillMapWithGrass(Map map) {
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                map.setTile(new MapTile(x, y, MapTile.Terrain.GRASS));
            }
        }
    }

    private void generateTileNeighbors(Map map) {
        //ArrayList<ArrayList<MapTile>> wholeList = map.getTiles();
        for (ArrayList<MapTile> tiles: map.getTiles()) {
            for (MapTile current : tiles) {
                int xPos = current.getXPos();
                int yPos = current.getYPos();
                MapTile leftNeighbor;
                MapTile rightNeighbor;
                MapTile upNeighbor;
                MapTile downNeighbor;

                if (xPos - 1 < map.getWidth() -1 ) {
                    leftNeighbor = null;
                } else {
                    leftNeighbor = map.getTiles().get(xPos - 1).get(yPos);
                }

                if (xPos + 1 > map.getWidth() - 1) {
                    rightNeighbor = null;
                } else {
                    rightNeighbor = map.getTiles().get(xPos + 1).get(yPos);
                }

                if (yPos + 1 > map.getHeight() - 1) {
                    upNeighbor = null;
                } else {
                    upNeighbor = map.getTiles().get(xPos).get(yPos + 1);
                }

                if (yPos - 1 < map.getHeight() - 1) {
                    downNeighbor = null;
                } else {
                    downNeighbor = map.getTiles().get(xPos).get(yPos - 1);
                }
                
                current.setNeighbors(leftNeighbor, rightNeighbor, upNeighbor, downNeighbor);
            }
            }

    }

//    private void generateTerrain(Map map, Map.MapTile.Terrain, double chanceOfTile) {
//        Set<MapTile> patchSpots = new HashSet<>();
//
//        for (int x = 0; x < map.getTiles().length; x++) {
//            for (int y = 0; y < map.getTiles()[0].length; y++) {
//                double randomValue = Math.random();
//                if (randomValue < chanceOfTile) {
//                    patchSpots.add(new MapTile(x, y, MapTile.Terrain.GRASS));
//                    map.setTile(x, y, Tile.copyOf(tile));
//                }
//            }
//        }
//
//        growPatches(map, patchSpots, tile);
//
//    }
//
//    private void growPatches(Map gameMap, Set<MapTile> patchSpots, Tile tile) {
//        System.out.println("Number of patches" + patchSpots.size());
//        patchSpots.forEach(spot -> {
//            int size = 0;
//            boolean stillGenerating;
//
//            do {
//                size++;
//                stillGenerating = false;
//
//                for (int x = spot.gridX() - size; x <= spot.gridX() + size; x++) {
//                    for (int y = spot.gridY() - size; y <= spot.gridY() + size; y++) {
//                        boolean isNotOuterLoopTile = (x > spot.gridX() - size && x < spot.gridX() + size) && y > spot.gridY() - size && y < spot.gridY() + size);
//                        if (!map.gridWithingBounds(x, y))
//                        }
//                    }
//                }
//
//            } while (stillGenerating && size < 5);
//
//        });
//
//    }

}
