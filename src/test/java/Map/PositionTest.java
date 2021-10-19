package Map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void constructorSetsXPos() {
        Position position = new Position(1, 2);
        assertEquals(1, position.getXPos());
    }

    @Test
    void constructorSetsYPos() {
        Position position = new Position(2, 3);
        assertEquals(3, position.getYPos());
    }

    @Test
    void constructorThrowsIAENegativeXPOS() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Position(-1, 3);
        });
    }

    @Test
    void constructorThrowIAENegativeYPos() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Position(3, -1);
        });
    }
}