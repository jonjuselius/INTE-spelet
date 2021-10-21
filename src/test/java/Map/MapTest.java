package Map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    @Test
    void constructorSetsWidth() {
        Map map = new Map(5, 6);
        assertEquals(5, map.getWidth());
    }

    @Test
    void constructorSetsHeight() {
        Map map = new Map(5, 6);
        assertEquals(6, map.getHeight());
    }

    @Test
    void constructorThrowsIAEWhenNegativeWidth() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Map(-1, 5);
        });
    }

    @Test
    void constructorThrowsIAEWhenNegativeHeight() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Map(5, -1);
        });
    }

    @Test
    void constructorSetsTiles() {
        MapPosition[][] mapTiles = new MapPosition[3][5];
        Map map = new Map(3, 5);
        assertEquals(mapTiles.length, map.getMapTiles().length);
    }

    @Test
    void throwsIAEWhenTilePutOutsideOfBounds() {
        //Add utanför kartans storlek
        //Kolla både x och y?
        Map map = new Map(3, 6);
        MapPosition tile = new MapPosition(3, 7);
        assertThrows(IllegalArgumentException.class, () -> {
            map.put(tile, tile.getXPos(), tile.getYPos());
        });
    }

    @Test
    void tilePutWithinBounds() {
        Map map = new Map(3, 6);
        MapPosition tile = new MapPosition(2, 3);
        int xPos = tile.getXPos();
        int yPos = tile.getYPos();
        map.put(tile, xPos, yPos);
        assertEquals(tile, map.getMapTiles()[xPos][yPos]);
    }

    @Test
    void tileIsInCorrectPosition() {
        Map map = new Map(2, 3);
        MapPosition tile = new MapPosition(1, 2);
        int xPos = tile.getXPos();
        int yPos = tile.getYPos();
        map.put(tile, xPos, yPos);
        assertEquals(xPos, map.getMapTiles()[xPos][yPos].getXPos());
        assertEquals(yPos, map.getMapTiles()[xPos][yPos].getYPos());
    }




}