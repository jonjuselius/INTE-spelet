package Magic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SpellLoaderTest {

    private static ArrayList<Spell> testSpells;

    public SpellLoaderTest() throws IOException {
    }

    @BeforeAll
    static void loadTestSpells() {
        testSpells = new SpellLoader().loadSpells("src/resources/SpellDataTest/SpellDataTest");
    }

    @Test
    void throwsFileNotFoundExceptionWithIncorrectPathName() throws FileNotFoundException {
        String badPath = "no/such/file/exists";
        new SpellLoader().spellFileReader(badPath);
    }

    @Test
    void illegalStateExceptionWhenWrongNumberOfArgumentsDDS() {
        String testSpellString = "DamageDealingSpell,Ice,20,Water";
        assertThrows(IllegalStateException.class, () -> {
            new SpellLoader().stringToSpellArguments(testSpellString);
        });
    }

    @Test
    void illegalStateExceptionWhenWrongNumberOfArgumentsHS() {
        String testSpellString = "HealingSpell,Holy,20,Physical";
        assertThrows(IllegalStateException.class, () -> {
            new SpellLoader().stringToSpellArguments(testSpellString);
        });
    }

    @Test
    void illegalStateExceptionWhenWrongNumberOfArgumentsBS() {
        String testSpellString = "BuffSpell,Strength,20";
        assertThrows(IllegalStateException.class, () -> {
            new SpellLoader().stringToSpellArguments(testSpellString);
        });
    }

    @Test
    void illegalStateExceptionWhenWrongNumberOfArgumentsDS() {
        String testSpellString = "DebuffSpell,Holy";
        assertThrows(IllegalStateException.class, () -> {
            new SpellLoader().stringToSpellArguments(testSpellString);
        });
    }

    @Test
    void correctlyConstructingDamageDealingSpell() {
        Spell spell = new DamageDealingSpell("Fireball", 25, Element.FIRE, 50);
        Spell actualSpell = new SpellLoader().constructSpell("DamageDealingSpell,Fireball,25,fire,50");
        assertEquals(spell, actualSpell);
    }

    @Test
    void correctlyConstructingHealingSpell() {
        Spell spell = new HealingSpell("Band-Aid", 10, Element.PHYSICAL, 10);
        Spell actualSpell = new SpellLoader().constructSpell("HealingSpell,10,physical,10");
        assertEquals(spell, actualSpell);
    }

}
