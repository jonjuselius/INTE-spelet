package Map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapGeneratorTest {
    private final int FAKE_RANDOM = 1;
    MapGenerator defaultMg = new MapGenerator(4, 4);
    Map defaultMap = defaultMg.generate(FAKE_RANDOM);
    MapTile defaultTile = defaultMap.getMapTiles()[2][2];

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
        Position westNeighbor = defaultTile.getNeighbors()[0];
        assertEquals(westNeighbor.getXPos(), defaultMap.getMapTiles()[westNeighbor.getXPos()][westNeighbor.getYPos()].getPosition().getXPos());
        assertEquals(westNeighbor.getYPos(), defaultMap.getMapTiles()[westNeighbor.getXPos()][westNeighbor.getYPos()].getPosition().getYPos());
    }

    @Test
    void correctCoordinatesOfEastNeighBor() {
        Position eastNeighbor = defaultTile.getNeighbors()[1];
        assertEquals(eastNeighbor.getXPos(), defaultMap.getMapTiles()[eastNeighbor.getXPos()][eastNeighbor.getYPos()].getPosition().getXPos());
        assertEquals(eastNeighbor.getYPos(), defaultMap.getMapTiles()[eastNeighbor.getXPos()][eastNeighbor.getYPos()].getPosition().getYPos());
    }

    @Test
    void correctCoordinatesOfNorthNeighbor() {
        Position northNeighbor = defaultTile.getNeighbors()[2];
        assertEquals(northNeighbor.getXPos(), defaultMap.getMapTiles()[northNeighbor.getXPos()][northNeighbor.getYPos()].getPosition().getXPos());
        assertEquals(northNeighbor.getYPos(), defaultMap.getMapTiles()[northNeighbor.getXPos()][northNeighbor.getYPos()].getPosition().getYPos());
    }

    @Test
    void correctCoordinatesOfSouthNeighbor() {
        Position southNeighbor = defaultTile.getNeighbors()[3];
        assertEquals(southNeighbor.getXPos(), defaultMap.getMapTiles()[southNeighbor.getXPos()][southNeighbor.getYPos()].getPosition().getXPos());
        assertEquals(southNeighbor.getYPos(), defaultMap.getMapTiles()[southNeighbor.getXPos()][southNeighbor.getYPos()].getPosition().getYPos());
    }


}