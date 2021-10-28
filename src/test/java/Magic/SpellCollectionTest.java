package Magic;

import org.hamcrest.collection.IsMapContaining;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SpellCollectionTest {


    @Test
    public void spellCollectionConstructor(){
        SpellCollection sc = new SpellCollection();
        HashMap<String, Spell> spellCollection = new HashMap<>();
        assertEquals(sc.getSpellCollection(), spellCollection);
    }

    @Test
    public void addSpellToSpellCollection(){
        SpellCollection sc = new SpellCollection();
        sc.addSpell(new DebuffSpell("StrengthDebuff", 10, Element.PHYSICAL, 5));

        assertThat(sc.getSpellCollection(), IsMapContaining.hasKey("StrengthDebuff"));
    }

}