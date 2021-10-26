package Map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameMapGeneratorTest {
    private static final int FAKE_RANDOM = 1;
    private static final int DEFAULT_MAPWIDTH = 2;
    private static final int DEFAULT_MAPHEIGHT = 4;
    private static final GameMapGenerator MAP_GENERATOR = new GameMapGenerator(DEFAULT_MAPWIDTH, DEFAULT_MAPHEIGHT);
    private static final GameMap MAP = MAP_GENERATOR.generate(FAKE_RANDOM);
    private static final GameMapPosition MAP_POSITION = MAP.getMapTiles()[1][1];

    @Test
    void constructorSetsMapWidth() {
        GameMap map = new GameMap(DEFAULT_MAPWIDTH, DEFAULT_MAPHEIGHT);
        GameMapGenerator mg = new GameMapGenerator(DEFAULT_MAPWIDTH, DEFAULT_MAPHEIGHT);
        assertEquals(map.getWidth(), mg.generate(FAKE_RANDOM).getWidth());
    }

    @Test
    void constructorSetsMapHeight() {
        GameMap map = new GameMap(DEFAULT_MAPWIDTH, DEFAULT_MAPHEIGHT);
        GameMapGenerator mg = new GameMapGenerator(DEFAULT_MAPWIDTH, DEFAULT_MAPHEIGHT);
        assertEquals(map.getHeight(), mg.generate(FAKE_RANDOM).getHeight());
    }

//    @Test
//    void probabilityIncreasesForGrassIfTileHasNoNeighbors() {
//        GameMapGenerator mapGenerator = new GameMapGenerator(1, 1);
//        GameMap map = mapGenerator.generate(FAKE_RANDOM);
//        GameMapPosition pos = map.getMapTiles()[0][0];
//        assertEquals(Terrain.GRASS, mapGenerator.getTerrainOfNeighbors(pos));
//    }

    @Test
    void generateGrassArea() {
        fail("Not yet implemented");
        //TODO: Använd slumpen för att generera stor mängd
    }

    @Test
    void generateWaterArea() {
        //TODO: Använd slumpen för att generera stor mängd

    }

    @Test
    void generateLavaArea() {
        fail("Not yet implemented");
        //TODO: Använd slumpen för att generera stor mängd
    }

    @Test
    void realRandomizedMapContainsAllTerrains() {
        fail("Not yet implemented");
    }

    @Test
    void correctCoordinatesSetForWestNeighbor() {
        GameMapPosition westNeighbor = MAP_POSITION.getNeighbors()[0];
        assertEquals(westNeighbor.getXPos(), MAP.getMapTiles()[westNeighbor.getXPos()][westNeighbor.getYPos()].getXPos());
        assertEquals(westNeighbor.getYPos(), MAP.getMapTiles()[westNeighbor.getXPos()][westNeighbor.getYPos()].getYPos());
    }

    @Test
    void correctCoordinatesSetForEastNeighBor() {
        GameMapPosition mapPosition = MAP.getMapTiles()[0][0];
        GameMapPosition eastNeighbor = mapPosition.getNeighbors()[1];
        assertEquals(eastNeighbor.getXPos(), MAP.getMapTiles()[eastNeighbor.getXPos()][eastNeighbor.getYPos()].getXPos());
        assertEquals(eastNeighbor.getYPos(), MAP.getMapTiles()[eastNeighbor.getXPos()][eastNeighbor.getYPos()].getYPos());
    }

    @Test
    void correctCoordinatesSetForNorthNeighbor() {
        GameMapPosition northNeighbor = MAP_POSITION.getNeighbors()[2];
        assertEquals(northNeighbor.getXPos(), MAP.getMapTiles()[northNeighbor.getXPos()][northNeighbor.getYPos()].getXPos());
        assertEquals(northNeighbor.getYPos(), MAP.getMapTiles()[northNeighbor.getXPos()][northNeighbor.getYPos()].getYPos());
    }

    @Test
    void correctCoordinatesSetForSouthNeighbor() {
        GameMapPosition southNeighbor = MAP_POSITION.getNeighbors()[3];
        assertEquals(southNeighbor.getXPos(), MAP.getMapTiles()[southNeighbor.getXPos()][southNeighbor.getYPos()].getXPos());
        assertEquals(southNeighbor.getYPos(), MAP.getMapTiles()[southNeighbor.getXPos()][southNeighbor.getYPos()].getYPos());
    }


}