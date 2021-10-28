package Map;

import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;

class GameMapTest {

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
    void putTileHasCorrectCoordinates() {
        GameMap map = new GameMap(2, 3);
        GameMapPosition tile = new GameMapPosition(1, 2);
        int xPos = tile.getXPos();
        int yPos = tile.getYPos();
        map.putTileOnMap(tile, xPos, yPos);
        assertEquals(xPos, map.getMapTiles()[xPos][yPos].getXPos());
        assertEquals(yPos, map.getMapTiles()[xPos][yPos].getYPos());
    }

    @Test
    void mapTilesPutCorrectly() {
        GameMapPosition[][] mapTiles = new GameMapPosition[2][2];
        mapTiles[0][0] = new GameMapPosition(0,0);
        mapTiles[0][1] = new GameMapPosition(0,1);
        mapTiles[1][0] = new GameMapPosition(1,0);
        mapTiles[1][1] = new GameMapPosition(1,1);
        GameMap map = new GameMap(2, 2);
        map.putTileOnMap(mapTiles[0][0], 0, 0);
        map.putTileOnMap(mapTiles[0][1], 0, 1);
        map.putTileOnMap(mapTiles[1][0], 1, 0);
        map.putTileOnMap(mapTiles[1][1], 1, 1);
        assertThat(map.getMapTiles(), equalTo(mapTiles));

    }

    @Test
    void putTileHasCorrectTerrain() {
        GameMap map = new GameMap(2, 2);
        GameMapPosition tile = new GameMapPosition(1, 1);
        tile.setTerrain(Terrain.GRASS);
        int xPos = tile.getXPos();
        int yPos = tile.getYPos();
        map.putTileOnMap(tile, xPos, yPos);
        assertThat(map.getMapTiles()[xPos][yPos].getTerrain(), sameInstance(tile.getTerrain()));
    }

    @Test
    void generatedRandomPosIsInsideMapBounds() {
        GameMap map = new GameMap(50, 20);
        GameMapPosition generatedPos = map.generateRandomPos(new Random(), new Random());
        assertThat(generatedPos.getXPos(), lessThan(map.getWidth()));
        assertThat(generatedPos.getYPos(), lessThan(map.getHeight()));
    }
}