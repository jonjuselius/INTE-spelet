package Map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapGeneratorTest {
    private final int FAKE_RANDOM = 1;
    MapGenerator testMg = new MapGenerator(4, 4);
    Map testMap = testMg.generate(FAKE_RANDOM);
    MapPosition testPos = testMap.getMapTiles()[2][2];

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
        MapPosition westNeighbor = testPos.getNeighbors()[0];
        assertEquals(westNeighbor.getXPos(), testMap.getMapTiles()[westNeighbor.getXPos()][westNeighbor.getYPos()].getXPos());
        assertEquals(westNeighbor.getYPos(), testMap.getMapTiles()[westNeighbor.getXPos()][westNeighbor.getYPos()].getYPos());
    }

    @Test
    void correctCoordinatesOfEastNeighBor() {
        MapPosition eastNeighbor = testPos.getNeighbors()[1];
        assertEquals(eastNeighbor.getXPos(), testMap.getMapTiles()[eastNeighbor.getXPos()][eastNeighbor.getYPos()].getXPos());
        assertEquals(eastNeighbor.getYPos(), testMap.getMapTiles()[eastNeighbor.getXPos()][eastNeighbor.getYPos()].getYPos());
    }

    @Test
    void correctCoordinatesOfNorthNeighbor() {
        MapPosition northNeighbor = testPos.getNeighbors()[2];
        assertEquals(northNeighbor.getXPos(), testMap.getMapTiles()[northNeighbor.getXPos()][northNeighbor.getYPos()].getXPos());
        assertEquals(northNeighbor.getYPos(), testMap.getMapTiles()[northNeighbor.getXPos()][northNeighbor.getYPos()].getYPos());
    }

    @Test
    void correctCoordinatesOfSouthNeighbor() {
        MapPosition southNeighbor = testPos.getNeighbors()[3];
        assertEquals(southNeighbor.getXPos(), testMap.getMapTiles()[southNeighbor.getXPos()][southNeighbor.getYPos()].getXPos());
        assertEquals(southNeighbor.getYPos(), testMap.getMapTiles()[southNeighbor.getXPos()][southNeighbor.getYPos()].getYPos());
    }


}