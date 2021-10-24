package Map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapGeneratorTest {
    private static final int FAKE_RANDOM = 1;
    private static final MapGenerator MAP_GENERATOR = new MapGenerator(4, 4);
    private static final Map MAP = MAP_GENERATOR.generate(FAKE_RANDOM);
    private static final MapPosition MAP_POSITION = MAP.getMapTiles()[2][2];

    @Test
    void constructorSetsMap() {
        Map map = new Map(5, 3);
        MapGenerator mg = new MapGenerator(5, 3);
        assertEquals(map.getWidth(), mg.generate(FAKE_RANDOM).getWidth());
        assertEquals(map.getHeight(), mg.generate(FAKE_RANDOM).getHeight());
    }

    @Test
    void firstTileBecomesGrass() {
        MapGenerator mg = new MapGenerator(4, 3);
        Map map = mg.generate(FAKE_RANDOM);
        assertEquals(Terrain.GRASS, map.getMapTiles()[0][0].getTerrain());
    }


    @Test
    void realRandomizedMapContainsAllTerrains() {
        fail("Not yet implemented");
    }

    @Test
    void correctCoordinatesOfWestNeighbor() {
        MapPosition westNeighbor = MAP_POSITION.getNeighbors()[0];
        assertEquals(westNeighbor.getXPos(), MAP.getMapTiles()[westNeighbor.getXPos()][westNeighbor.getYPos()].getXPos());
        assertEquals(westNeighbor.getYPos(), MAP.getMapTiles()[westNeighbor.getXPos()][westNeighbor.getYPos()].getYPos());
    }

    @Test
    void correctCoordinatesOfEastNeighBor() {
        MapPosition eastNeighbor = MAP_POSITION.getNeighbors()[1];
        assertEquals(eastNeighbor.getXPos(), MAP.getMapTiles()[eastNeighbor.getXPos()][eastNeighbor.getYPos()].getXPos());
        assertEquals(eastNeighbor.getYPos(), MAP.getMapTiles()[eastNeighbor.getXPos()][eastNeighbor.getYPos()].getYPos());
    }

    @Test
    void correctCoordinatesOfNorthNeighbor() {
        MapPosition northNeighbor = MAP_POSITION.getNeighbors()[2];
        assertEquals(northNeighbor.getXPos(), MAP.getMapTiles()[northNeighbor.getXPos()][northNeighbor.getYPos()].getXPos());
        assertEquals(northNeighbor.getYPos(), MAP.getMapTiles()[northNeighbor.getXPos()][northNeighbor.getYPos()].getYPos());
    }

    @Test
    void correctCoordinatesOfSouthNeighbor() {
        MapPosition southNeighbor = MAP_POSITION.getNeighbors()[3];
        assertEquals(southNeighbor.getXPos(), MAP.getMapTiles()[southNeighbor.getXPos()][southNeighbor.getYPos()].getXPos());
        assertEquals(southNeighbor.getYPos(), MAP.getMapTiles()[southNeighbor.getXPos()][southNeighbor.getYPos()].getYPos());
    }


}