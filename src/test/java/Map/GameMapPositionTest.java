package Map;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
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

    public static class NoNeighborMatcher extends TypeSafeMatcher<GameMapPosition> {
        private GameMapPosition noNeighbor = null;

        @Override
        public boolean matchesSafely(GameMapPosition neighbor) {
            if (neighbor == noNeighbor) {
                return true;
            }
            return false;
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("This position was not expected to have a neighbor, but it had.");
        }

        public static Matcher<GameMapPosition> hasNoNeighborInThisDirection() {
            return new NoNeighborMatcher();
        }
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
        MAP_POSITION.setTerrain(Terrain.GRASS);
        assertEquals(Terrain.GRASS, MAP_POSITION.getTerrain());
    }

    @Test
    void neighborsSetCorrectly() {
        assertEquals(west, MAP_POSITION.getWestNeighbor());
        assertEquals(east, MAP_POSITION.getEastNeighbor());
        assertEquals(north, MAP_POSITION.getNorthNeighbor());
        assertEquals(south, MAP_POSITION.getSouthNeighbor());
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
    void positionInTheWestEndOfTheMapHasNoWestNeighbor() {

    }

    @Test
    void positionInTheEastEndOfTheMapHasNoEastNeighbor() {

    }

    @Test
    void positionInTheNorthEndOfTheMapHasNoNorthNeighbor() {

    }

    @Test
    void positionInTheSouthEndOfTheMapHasNoSouthNeighbor() {

    }


}