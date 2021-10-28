package Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class GameMapPositionTest {
    public static final int FAKE_RANDOM = 1;
    public static GameMapGenerator mapGenerator;
    public static GameMap map;
    public static GameMapPosition mapPosition;
    public static GameMapPosition west;
    public static GameMapPosition east;
    public static GameMapPosition north;
    public static GameMapPosition south;

    @BeforeAll
    static void setup() {
        mapGenerator = new GameMapGenerator(4, 4);
        map = mapGenerator.generate(FAKE_RANDOM);
        mapPosition = map.getMapTiles()[2][2];
        west = new GameMapPosition(0, 1);
        east = new GameMapPosition(1, 0);
        north = new GameMapPosition(1, 2);
        south = new GameMapPosition(2, 1);
    }


    @Test
    void constructorSetsXPos() {
        GameMapPosition position = new GameMapPosition(1, 2);
        assertEquals(1, position.getXPos());
    }

    @Test
    void constructorSetsYPos() {
        GameMapPosition position = new GameMapPosition(2, 3);
        assertEquals(3, position.getYPos());
    }

    @Test
    void constructorThrowsIAENegativeXPOS() {
        assertThrows(IllegalArgumentException.class, () ->
            new GameMapPosition(-1, 3));
    }

    @Test
    void constructorThrowIAENegativeYPos() {
        assertThrows(IllegalArgumentException.class, () ->
            new GameMapPosition(3, -1));
    }

    @Test
    void setTerrainSetsTerrain() {
        mapPosition.setTerrain(Terrain.GRASS);
        assertEquals(Terrain.GRASS, mapPosition.getTerrain());
    }

    @Test
    void mapTilesPutCorrectly() {
        GameMapPosition[] neighbors = new GameMapPosition[4];
        neighbors[0] = west;
        neighbors[1] = east;
        neighbors[2] = north;
        neighbors[3] = south;
        GameMapPosition positionWithNeighbors = new GameMapPosition(0, 0);
        positionWithNeighbors.setNeighbors(west, east, north, south);
        assertThat(positionWithNeighbors.getNeighbors(), is(neighbors));
    }

    @Test
    void westNeighborSetCorrectly() {
        mapPosition.setNeighbors(west, east, north, south);
        assertThat(mapPosition.getWestNeighbor(), equalTo(west));
    }

    @Test
    void eastNeighborSetCorrectly() {
        mapPosition.setNeighbors(west, east, north, south);
        assertThat(mapPosition.getEastNeighbor(), equalTo(east));
    }

    @Test
    void northNeighborSetCorrectly() {
        mapPosition.setNeighbors(west, east, north, south);
        assertThat(mapPosition.getNorthNeighbor(), equalTo(north));
    }

    @Test
    void southNeighBorSetCorrectly() {
        mapPosition.setNeighbors(west, east, north, south);
        assertThat(mapPosition.getSouthNeighbor(), equalTo(south));
    }

    @Test
    void positionInTheWestEndTheMapHasNoWestNeighbor() {
        GameMapPosition posInTheWestEnd = map.getMapTiles()[0][0];
        assertThat(posInTheWestEnd.getWestNeighbor(), sameInstance(null));
    }

    @Test
    void positionInTheEastEndTheMapHasNoEastNeighbor() {
        GameMapPosition posInTheEastEnd = map.getMapTiles()[3][0];
        assertThat(posInTheEastEnd.getEastNeighbor(), sameInstance(null));
    }

    @Test
    void positionInTheNorthEndTheMapHasNoNorthNeighbor() {
        GameMapPosition posInTheNorthEnd = map.getMapTiles()[0][3];
        assertThat(posInTheNorthEnd.getNorthNeighbor(), sameInstance(null));
    }

    @Test
    void positionInTheSouthEndTheMapHasNoSouthNeighbor() {
        GameMapPosition posInTheSouthEnd = map.getMapTiles()[0][0];
        assertThat(posInTheSouthEnd.getSouthNeighbor(), sameInstance(null));
    }
}