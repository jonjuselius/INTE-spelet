package Magic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

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
    void

    //Tests the Loader from the txt-file SpellDataTest.txt which reads:
    //Spells:
    //DamageDealingSpell,Fireball,25,Fire,50
    //HealingSpell,Band-Aid,10,physical,15

    @Test
    void correctlyLoadingDamageDealingSpell() {
        Spell spell = new DamageDealingSpell("Fireball", 25, Element.FIRE, 50);
        Spell actualSpell = testSpells.get(0);
        assertEquals(spell, actualSpell);
    }

    @Test
    void correctlyLoadingHealingSpell() {
        HealingSpell spell = new HealingSpell("Band-Aid", 10, Element.PHYSICAL, 15);
        Spell actualSpell = testSpells.get(1);
        assertEquals(spell, actualSpell);
    }

}
