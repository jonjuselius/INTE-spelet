package Magic;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class SpellLoaderTest {

    private static ArrayList<Spell> testSpells;

    public SpellLoaderTest() throws IOException {
    }


    @Test
    void throwsFileNotFoundExceptionWithIncorrectPathName() throws FileNotFoundException {
        String badPath = "no/such/file/exists";
        new SpellLoader().spellFileReader(badPath);
    }

    //Reading from File that has the following format:
    //
    //"Spells:
    //DamageDealingSpell,Fireball,25,Fire,50
    //HealingSpell,Band-Aid,10,physical,15
    //BuffSpell,Strength,20,physical,20
    //DebuffSpell,Weaken,20,physical,25"
    @Test
    void spellFileReaderReturningCorrectLines() {
        ArrayList<String> expectedListOfLines = new ArrayList<>();
        expectedListOfLines.add("DamageDealingSpell,Fireball,25,Fire,50");
        expectedListOfLines.add("HealingSpell,Band-Aid,10,physical,15");
        expectedListOfLines.add("BuffSpell,Strength,20,physical,20");
        expectedListOfLines.add("DebuffSpell,Weaken,20,physical,25");
        ArrayList<String> actualLines = new SpellLoader().spellFileReader("src/resources/SpellDataTest/SpellDataTest");
        assertEquals(expectedListOfLines, actualLines);
    }

    @Test
    void spellFileReaderReturningEmptyListWhenWrongHeader(){
        //reading from file with header: "List of Spells"
        ArrayList<String> list = new SpellLoader().spellFileReader("src/resources/SpellDataTest/SpellDataTestWrongHeader");
        assertTrue(list.isEmpty());
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
    void throwIllegalStateExceptionWhenUsingIncorrectSpellType(){
        assertThrows(IllegalStateException.class, () -> {
            new SpellLoader().constructSpell("DamageHealingSepll,What,20,wind,100");
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
        Spell actualSpell = new SpellLoader().constructSpell("HealingSpell,Band-Aid,10,physical,10");
        assertEquals(spell, actualSpell);
    }

    @Test
    void correctlyConstructingBuffSpell() {
        Spell spell = new BuffSpell("Rally", 15, Element.PHYSICAL, 20);
        Spell actualSpell = new SpellLoader().constructSpell("BuffSpell,Rally,15,physical,20");
        assertEquals(spell, actualSpell);
    }

    @Test
    void correctlyConstructingDebuffSpell() {
        Spell spell = new DebuffSpell("Weaken", 15, Element.PHYSICAL, 25);
        Spell actualSpell = new SpellLoader().constructSpell("DebuffSpell,Weaken,15,physical,25");
        assertEquals(spell, actualSpell);
    }


    //Reading from File that has the following format:
    //
    //"Spells:
    //DamageDealingSpell,Fireball,25,Fire,50
    //HealingSpell,Band-Aid,10,physical,15
    //BuffSpell,Strength,20,physical,20
    //DebuffSpell,Weaken,20,physical,25"
    @Test
    void correctlyLoadSpellHashMapFromFile() {
        HashMap<String, Spell> expectedHashMap = new HashMap<>();
        Spell spell1 = new DamageDealingSpell("Fireball",25,Element.FIRE,50);
        Spell spell2 = new HealingSpell("Band-Aid", 10, Element.PHYSICAL,15);
        Spell spell3 = new BuffSpell("Strength", 20,Element.PHYSICAL,20);
        Spell spell4 = new DebuffSpell("Weaken", 20,Element.PHYSICAL, 25);
        expectedHashMap.put(spell1.getName(), spell1);
        expectedHashMap.put(spell2.getName(), spell2);
        expectedHashMap.put(spell3.getName(), spell3);
        expectedHashMap.put(spell4.getName(), spell4);
        HashMap<String, Spell> actualHashMap = new SpellLoader().loadSpells("src/resources/SpellDataTest/SpellDataTest");
        assertEquals(expectedHashMap, actualHashMap);
    }

    @Test
    void loadEmptyHashMapIfFileHasWrongHeader() {
        HashMap<String, Spell> spells = new SpellLoader().loadSpells("src/resources/SpellDataTest/SpellDataTestWrongHeader");
        assertTrue(spells.isEmpty());
    }

    @Test
    void loadEmptyHashMapIfFileIsEmpty() {
        HashMap<String, Spell> spells = new SpellLoader().loadSpells("src/resources/SpellDataTest/SpellDataTestEmpty");
        assertTrue(spells.isEmpty());
    }

    @Test
    void loadEmptyHashMapIfFileCannotBeFound() {
        HashMap<String, Spell> spells = new SpellLoader().loadSpells("no/such/path/exists");
        assertTrue(spells.isEmpty());
    }
}
