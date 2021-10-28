package Map;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


class GameMapPositionTest {
    public static final int FAKE_RANDOM = 1;
    public static final GameMapGenerator MAP_GENERATOR = new GameMapGenerator(4, 4);
    public static final GameMap MAP = MAP_GENERATOR.generate(FAKE_RANDOM);
    public static final GameMapPosition MAP_POSITION = MAP.getMapTiles()[2][2];
    public static final GameMapPosition west = new GameMapPosition(0, 1);
    public static final GameMapPosition east = new GameMapPosition(1, 0);
    public static final GameMapPosition north = new GameMapPosition(1, 2);
    public static final GameMapPosition south = new GameMapPosition(2, 1);

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
        MAP_POSITION.setTerrain(Terrain.GRASS);
        assertEquals(Terrain.GRASS, MAP_POSITION.getTerrain());
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
        MAP_POSITION.setNeighbors(west, east, north, south);
        assertThat(MAP_POSITION.getWestNeighbor(), equalTo(west));
    }

    @Test
    void eastNeighborSetCorrectly() {
        MAP_POSITION.setNeighbors(west, east, north, south);
        assertThat(MAP_POSITION.getEastNeighbor(), equalTo(east));
    }

    @Test
    void northNeighborSetCorrectly() {
        MAP_POSITION.setNeighbors(west, east, north, south);
        assertThat(MAP_POSITION.getNorthNeighbor(), equalTo(north));
    }

    @Test
    void southNeighBorSetCorrectly() {
        MAP_POSITION.setNeighbors(west, east, north, south);
        assertThat(MAP_POSITION.getSouthNeighbor(), equalTo(south));
    }

    @Test
    void positionInTheWestEndTheMapHasNoWestNeighbor() {
        GameMapPosition posInTheWestEnd = MAP.getMapTiles()[0][0];
        assertThat(posInTheWestEnd.getWestNeighbor(), sameInstance(null));
    }

    @Test
    void positionInTheEastEndTheMapHasNoEastNeighbor() {
        GameMapPosition posInTheEastEnd = MAP.getMapTiles()[3][0];
        assertThat(posInTheEastEnd.getEastNeighbor(), sameInstance(null));
    }

    @Test
    void positionInTheNorthEndTheMapHasNoNorthNeighbor() {
        GameMapPosition posInTheNorthEnd = MAP.getMapTiles()[0][3];
        assertThat(posInTheNorthEnd.getNorthNeighbor(), sameInstance(null));
    }

    @Test
    void positionInTheSouthEndTheMapHasNoSouthNeighbor() {
        GameMapPosition posInTheSouthEnd = MAP.getMapTiles()[0][0];
        assertThat(posInTheSouthEnd.getSouthNeighbor(), sameInstance(null));
    }


}