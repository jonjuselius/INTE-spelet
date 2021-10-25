package Map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameMapPositionTest {
    private static final int FAKE_RANDOM = 1;
    private static final GameMapGenerator MAP_GENERATOR = new GameMapGenerator(4, 4);
    private static final GameMap MAP = MAP_GENERATOR.generate(FAKE_RANDOM);
    private static final GameMapPosition MAP_POSITION = MAP.getMapTiles()[2][2];

    @Test
    void constructorSetsXPos() {
        GameMapPosition position = new GameMapPosition(1, 2);
        assertEquals(1, position.getXPos());
    }

    @Test
    void constructorSetsYPos() {
        GameMapPosition position = new GameMapPosition(2, 3);
        assertEquals(3, position.getYPos());
    }

    @Test
    void constructorThrowsIAENegativeXPOS() {
        assertThrows(IllegalArgumentException.class, () -> {
            new GameMapPosition(-1, 3);
        });
    }

    @Test
    void constructorThrowIAENegativeYPos() {
        assertThrows(IllegalArgumentException.class, () -> {
            new GameMapPosition(3, -1);
        });
    }

//    @Test
//    void constructorSetsTerrain() {
//        Map map = new Map(2, 2);
//        MapPosition position = new MapPosition(0, 0, Terrain.LAVA, map);
//        assertEquals(Terrain.LAVA, position.getTerrain());
//    }

//    @Test
//    void constructorSetsMap() {
//        MapPosition position = new MapPosition(0, 0, MAP);
//        assertEquals(MAP, position.getMap());
//    }

    @Test
    void setTerrainSetsTerrain() {
        MAP_POSITION.setTerrain(Terrain.GRASS);
        assertEquals(Terrain.GRASS, MAP_POSITION.getTerrain());
    }

//    @Test
//    void constructorSetsTerrainToWater() {
//        MAP_POSITION.setTerrain(Terrain.WATER);
//        assertEquals(Terrain.WATER, MAP_POSITION.getTerrain());
//    }
//
//    @Test
//    void constructorSetsTerrainToLava() {
//        MAP_POSITION.setTerrain(Terrain.LAVA);
//        assertEquals(Terrain.LAVA, MAP_POSITION.getTerrain());
//    }


    @Test
    void neighborsSetCorrectly() {
        GameMapPosition west = new GameMapPosition(0, 1);
        GameMapPosition east = new GameMapPosition(1, 0);
        GameMapPosition north = new GameMapPosition(1, 2);
        GameMapPosition south = new GameMapPosition(2, 1);
        MAP_POSITION.setNeighbors(west, east, north, south);
        assertEquals(west, MAP_POSITION.getWestNeighbor());
        assertEquals(east, MAP_POSITION.getEastNeighbor());
        assertEquals(north, MAP_POSITION.getNorthNeighbor());
        assertEquals(south, MAP_POSITION.getSouthNeighbor());
    }


}