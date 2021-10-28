package Magic;

import java.util.ArrayList;
import java.util.HashMap;


public class SpellCollection {

    private HashMap<String, Spell> spellCollection;

    public SpellCollection() {
        this.spellCollection = new HashMap<>();
    }

    public HashMap<String, Spell> getSpellCollection() {
        return spellCollection;
    }

    public void addSpell(Spell spell){
        spellCollection.put(spell.getName(), spell);
    }




}
