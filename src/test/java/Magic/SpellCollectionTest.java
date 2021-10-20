package Magic;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class SpellCollectionTest {

    SpellLoader sl = new SpellLoader();
    ArrayList<Spell> spells = sl.loadSpells("src/resources/SpellDataTest/SpellDataTest");

    @Test
    void testAddSpell() {

    }

}
