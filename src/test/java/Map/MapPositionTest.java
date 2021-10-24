package Map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapPositionTest {
    private static final int FAKE_RANDOM = 1;
    private static final MapGenerator MAP_GENERATOR = new MapGenerator(4, 4);
    private static final Map MAP = MAP_GENERATOR.generate(FAKE_RANDOM);
    private static final MapPosition MAP_POSITION = MAP.getMapTiles()[2][2];

    @Test
    void constructorSetsXPos() {
        MapPosition position = new MapPosition(1, 2, MAP);
        assertEquals(1, position.getXPos());
    }

    @Test
    void constructorSetsYPos() {
        MapPosition position = new MapPosition(2, 3, MAP);
        assertEquals(3, position.getYPos());
    }

    @Test
    void constructorThrowsIAENegativeXPOS() {
        assertThrows(IllegalArgumentException.class, () -> {
            new MapPosition(-1, 3, MAP);
        });
    }

    @Test
    void constructorThrowIAENegativeYPos() {
        assertThrows(IllegalArgumentException.class, () -> {
            new MapPosition(3, -1, MAP);
        });
    }

//    @Test
//    void constructorSetsTerrain() {
//        Map map = new Map(2, 2);
//        MapPosition position = new MapPosition(0, 0, Terrain.LAVA, map);
//        assertEquals(Terrain.LAVA, position.getTerrain());
//    }

    @Test
    void constructorSetsMap() {
        MapPosition position = new MapPosition(0, 0, MAP);
        assertEquals(MAP, position.getMap());
    }

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
        MapPosition west = new MapPosition(0, 1, MAP);
        MapPosition east = new MapPosition(1, 0, MAP);
        MapPosition north = new MapPosition(1, 2, MAP);
        MapPosition south = new MapPosition(2, 1, MAP);
        MAP_POSITION.setNeighbors(west, east, north, south);
        assertEquals(west, MAP_POSITION.getNeighbors()[0]);
        assertEquals(east, MAP_POSITION.getNeighbors()[1]);
        assertEquals(north, MAP_POSITION.getNeighbors()[2]);
        assertEquals(south, MAP_POSITION.getNeighbors()[3]);
    }


}