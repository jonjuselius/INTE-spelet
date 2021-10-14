package Magic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DamageDealingTest {

    @Test
    void elementalConstructor(){
        DamageDealingSpell dd = new DamageDealingSpell("FireBall", 50, Element.FIRE);

        assertEquals("Fireball", dd.getName());
        assertEquals(50, dd.getManaCost());
        assertEquals(Element.FIRE, dd.getElement());
    }

}