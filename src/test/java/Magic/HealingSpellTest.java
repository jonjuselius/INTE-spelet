package Magic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealingSpellTest {

    @Test
    void elementalHealingConstructor(){
        HealingSpell hs = new HealingSpell("Cauterize", 30, Element.FIRE);

        assertEquals("Cauterize", hs.getName());
        assertEquals(30, hs.getManaCost());
        assertEquals(Element.FIRE, hs.getElement());
    }

    @Test
    void physicalHealingConstructor(){
        HealingSpell hs = new HealingSpell("Band-aid", 5);

        assertEquals("Band-aid", hs.getName());
        assertEquals(5, hs.getManaCost());
        assertEquals(Element.PHYSICAL, hs.getElement());
    }

    @Test
    void defaultInitialHeal() {
        HealingSpell hs = new HealingSpell("Band-aid", 5);

        assertEquals(10, hs.getInitialHeal());
    }

    @Test
    void defaultHealOverTime() {
    }


    @Test
    void defaultDuration() {
    }


}