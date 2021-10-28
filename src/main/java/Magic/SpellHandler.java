package Magic;

import java.util.HashMap;

public class SpellHandler {

    HashMap<String, Spell> spells;

    public SpellHandler() {
        spells = new SpellLoader().loadSpells("src/resources/SpellData.txt");
    }

}
