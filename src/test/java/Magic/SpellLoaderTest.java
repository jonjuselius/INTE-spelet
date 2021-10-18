package Magic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class SpellLoaderTest {

    //Tests the Loader from the txt-file SpellDataTest.txt which reads:
    //
    //Spells:
    //DamageDealingSpell,Fireball,25,Fire,50
    //HealingSpell,Band-Aid,10,physical,15

    ArrayList<Spell> testSpells = new SpellLoader().loadSpells("src/resources/SpellDataTest/SpellDataTest");

    @Test
    void testLoadedDamageDealingSpell() {
        Spell spell = testSpells.get(0);
        assertTrue(spell instanceof DamageDealingSpell);
        assertEquals("Fireball", spell.getName());
        assertEquals(25, spell.getManaCost());
        assertEquals(Element.FIRE, spell.getElement());
        assertEquals(50, ((DamageDealingSpell) spell).getBaseDamage());
    }

    @Test
    void testLoadedHealingSpell() {
        Spell spell = testSpells.get(1);
        assertTrue(spell instanceof HealingSpell);
        assertEquals("Band-Aid", spell.getName());
        assertEquals(10, spell.getManaCost());
        assertEquals(Element.PHYSICAL, spell.getElement());
        assertEquals(15, ((HealingSpell) spell).getBaseHeal());
    }

    @Test
    void testLoadedBuffSpell() {

    }

    @Test
    void testLoadedDebuffSpell() {

    }

    @Test
    void testMissingParameterInTxtFile() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            new SpellLoader().loadSpells("src/resources/SpellDataTest/FaultySpellDataTest1");
        });
    }

}
