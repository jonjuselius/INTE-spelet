package Magic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SpellLoaderTest {


    FileWriter fr = new FileWriter("src/resources/SpellDataTest/FaultySpellDataTest");
    PrintWriter pr = new PrintWriter(fr);

    public SpellLoaderTest() throws IOException {
    }

    @Test
    void wrongNumberOfArgumentsDDS() {
        pr.write("spells:\n");
        pr.write("DamageDealingSpell,Snowball,5,water");
        pr.close();
        assertThrows(IllegalStateException.class, () -> {
           new SpellLoader().loadSpells("src/resources/SpellDataTest/FaultySpellDataTest");
        });

    }

    @Test
    void wrongNumberOfArgumentsHS() {
        pr.write("spells:\n");
        pr.write("HealingSpell,Snowball,5,water");
        pr.close();
        assertThrows(IllegalStateException.class, () -> {
            new SpellLoader().loadSpells("src/resources/SpellDataTest/FaultySpellDataTest");
        });

    }

    //Tests the Loader from the txt-file SpellDataTest.txt which reads:
    //Spells:
    //DamageDealingSpell,Fireball,25,Fire,50
    //HealingSpell,Band-Aid,10,physical,15

    ArrayList<Spell> testSpells = new SpellLoader().loadSpells("src/resources/SpellDataTest/SpellDataTest");

    @Test
    void correctlyLoadingDamageDealingSpellName() {
        Spell spell = new DamageDealingSpell("Fireball", 25, Element.FIRE, 50);
        Spell actualSpell = testSpells.get(0);
        assertEquals(spell.getName(), actualSpell.getName());
    }

    @Test
    void correctlyLoadingDamageDealingSpellManaCost() {
        Spell spell = new DamageDealingSpell("Fireball", 25, Element.FIRE, 50);
        Spell actualSpell = testSpells.get(0);
        assertEquals(spell.getManaCost(), actualSpell.getManaCost());
    }

    @Test
    void correctlyLoadingDamageDealingSpellElement() {
        Spell spell = new DamageDealingSpell("Fireball", 25, Element.FIRE, 50);
        Spell actualSpell = testSpells.get(0);
        assertEquals(spell.getElement(), actualSpell.getElement());
    }

    @Test
    void correctlyLoadingDamageDealingSpellBaseDamage() {
        DamageDealingSpell spell = new DamageDealingSpell("Fireball", 25, Element.FIRE, 50);
        Spell actualSpell = testSpells.get(0);
        assertEquals(spell.getBaseDamage(), ((DamageDealingSpell)actualSpell).getBaseDamage());
    }

    @Test
    void correctlyLoadingHealingSpellName() {
        HealingSpell spell = new HealingSpell("Band-Aid", 10, Element.PHYSICAL, 15);
        Spell actualSpell = testSpells.get(1);
        assertEquals(spell.getName(), actualSpell.getName());
    }

    @Test
    void correctlyLoadingHealingSpellManaCost() {
        HealingSpell spell = new HealingSpell("Band-Aid", 10, Element.PHYSICAL, 15);
        Spell actualSpell = testSpells.get(1);
        assertEquals(spell.getManaCost(), actualSpell.getManaCost());
    }

    @Test
    void correctlyLoadingHealingSpellElement() {
        HealingSpell spell = new HealingSpell("Band-Aid", 10, Element.PHYSICAL, 15);
        Spell actualSpell = testSpells.get(1);
        assertEquals(spell.getElement(), actualSpell.getElement());
    }

    @Test
    void correctlyLoadingHealingSpellBaseHeal() {
        HealingSpell spell = new HealingSpell("Band-Aid", 10, Element.PHYSICAL, 15);
        Spell actualSpell = testSpells.get(1);
        assertEquals(spell.getBaseHeal(), ((HealingSpell)actualSpell).getBaseHeal());
    }

}
