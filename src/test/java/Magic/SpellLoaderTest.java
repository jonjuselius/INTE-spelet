package Magic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SpellLoaderTest {

    private static ArrayList<Spell> testSpells;

    public SpellLoaderTest() throws IOException {
    }

    @BeforeAll
    static void loadTestSpells() {
        testSpells = new SpellLoader().loadSpells("src/resources/SpellDataTest/SpellDataTest");
    }


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
