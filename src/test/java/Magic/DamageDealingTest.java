package Magic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DamageDealingTest {

    @Test
    void elementalDamageConstructor(){
        DamageDealingSpell dd = new DamageDealingSpell("Fireball", 50, Element.FIRE);

        assertEquals("Fireball", dd.getName());
        assertEquals(50, dd.getManaCost());
        assertEquals(Element.FIRE, dd.getElement());
    }

    @Test
    void physicalDamageSpellConstructor(){
        DamageDealingSpell dd = new DamageDealingSpell("Stonefist", 25);

        assertEquals("Stonefist", dd.getName());
        assertEquals(25, dd.getManaCost());
    }



}