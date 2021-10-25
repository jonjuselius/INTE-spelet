package Map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameMapTest {
    private static final int FAKE_RANDOM = 1;
    private static final GameMapGenerator MAP_GENERATOR = new GameMapGenerator(4, 4);
    private static final GameMap MAP = MAP_GENERATOR.generate(FAKE_RANDOM);
    private static final GameMapPosition MAP_POSITION = MAP.getMapTiles()[2][2];

    @Test
    void constructorSetsWidth() {
        GameMap map = new GameMap(5, 6);
        assertEquals(5, map.getWidth());
    }

    @Test
    void constructorSetsHeight() {
        GameMap map = new GameMap(5, 6);
        assertEquals(6, map.getHeight());
    }

    @Test
    void constructorThrowsIAEWhenNegativeWidth() {
        assertThrows(IllegalArgumentException.class, () ->
            new GameMap(-1, 5));
    }

    @Test
    void constructorThrowsIAEWhenNegativeHeight() {
        assertThrows(IllegalArgumentException.class, () ->
            new GameMap(5, -1));
    }

    @Test
    void constructorSetsTiles() {
        GameMapPosition[][] mapTiles = new GameMapPosition[3][5];
        GameMap map = new GameMap(3, 5);
        assertEquals(mapTiles.length, map.getMapTiles().length);
    }

    @Test
    void throwsIAEWhenTilePutOutsideOfBounds() {
        //Add utanför kartans storlek
        //Kolla både x och y?
        GameMap map = new GameMap(3, 6);
        GameMapPosition tile = new GameMapPosition(3, 7);
        assertThrows(IllegalArgumentException.class, () ->
            map.putTileOnMap(tile, tile.getXPos(), tile.getYPos())
        );
    }

    @Test
    void tilePutWithinBounds() {
        GameMap map = new GameMap(3, 6);
        GameMapPosition tile = new GameMapPosition(2, 3);
        int xPos = tile.getXPos();
        int yPos = tile.getYPos();
        map.putTileOnMap(tile, xPos, yPos);
        assertEquals(tile, map.getMapTiles()[xPos][yPos]);
    }

    @Test
    void tileIsInCorrectPosition() {
        GameMap map = new GameMap(2, 3);
        GameMapPosition tile = new GameMapPosition(1, 2);
        int xPos = tile.getXPos();
        int yPos = tile.getYPos();
        map.putTileOnMap(tile, xPos, yPos);
        assertEquals(xPos, map.getMapTiles()[xPos][yPos].getXPos());
        assertEquals(yPos, map.getMapTiles()[xPos][yPos].getYPos());
    }




}