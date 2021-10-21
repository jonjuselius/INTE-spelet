package Map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapPositionTest {
    private final int FAKE_RANDOM = 1;
    private MapPosition defaultTile = new MapPosition(3, 4);

    @Test
    void constructorSetsXPos() {
        MapPosition position = new MapPosition(1, 2);
        assertEquals(1, position.getXPos());
    }

    @Test
    void constructorSetsYPos() {
        MapPosition position = new MapPosition(2, 3);
        assertEquals(3, position.getYPos());
    }

    @Test
    void constructorThrowsIAENegativeXPOS() {
        assertThrows(IllegalArgumentException.class, () -> {
            new MapPosition(-1, 3);
        });
    }

    @Test
    void constructorThrowIAENegativeYPos() {
        assertThrows(IllegalArgumentException.class, () -> {
            new MapPosition(3, -1);
        });
    }

    @Test
    void constructorSetsTerrainToGrass() {
        defaultTile.setTerrain(Terrain.GRASS);
        assertEquals(Terrain.GRASS, defaultTile.getTerrain());
    }

    @Test
    void constructorSetsTerrainToWater() {
        defaultTile.setTerrain(Terrain.WATER);
        assertEquals(Terrain.WATER, defaultTile.getTerrain());
    }

    @Test
    void constructorsetsTerrainToLava() {
        defaultTile.setTerrain(Terrain.LAVA);
        assertEquals(Terrain.LAVA, defaultTile.getTerrain());
    }


    @Test
    void neighborsSetCorrectly() {
        MapPosition west = new MapPosition(0, 1);
        MapPosition east = new MapPosition(1, 0);
        MapPosition north = new MapPosition(1, 2);
        MapPosition south = new MapPosition(2, 1);
        defaultTile.setNeighbors(west, east, north, south);
        assertEquals(west, defaultTile.getNeighbors()[0]);
        assertEquals(east, defaultTile.getNeighbors()[1]);
        assertEquals(north, defaultTile.getNeighbors()[2]);
        assertEquals(south, defaultTile.getNeighbors()[3]);
    }


}