package Map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapTileTest {
    private final int FAKE_RANDOM = 1;
    private Position defaultPos = new Position(3, 4);
    private MapTile defaultTile = new MapTile(defaultPos);

    @Test
    void constructorSetsPosition() {
        assertEquals(defaultPos, defaultTile.getPosition());
    }

    @Test
    void setTerrainToGrass() {
        defaultTile.setTerrain(Terrain.GRASS);
        assertEquals(Terrain.GRASS, defaultTile.getTerrain());
    }

    @Test
    void setTerrainToWater() {
        defaultTile.setTerrain(Terrain.WATER);
        assertEquals(Terrain.WATER, defaultTile.getTerrain());
    }

    @Test
    void setTerrainToLava() {
        defaultTile.setTerrain(Terrain.LAVA);
        assertEquals(Terrain.LAVA, defaultTile.getTerrain());
    }


    @Test
    void neighborsSetCorrectly() {
        Position west = new Position(0, 1);
        Position east = new Position(1, 0);
        Position north = new Position(1, 2);
        Position south = new Position(2, 1);
        defaultTile.setNeighbors(west, east, north, south);
        assertEquals(west, defaultTile.getNeighbors()[0]);
        assertEquals(east, defaultTile.getNeighbors()[1]);
        assertEquals(north, defaultTile.getNeighbors()[2]);
        assertEquals(south, defaultTile.getNeighbors()[3]);
    }


}