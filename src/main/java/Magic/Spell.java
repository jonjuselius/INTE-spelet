package Magic;

import GameCharacters.*;
import GameCharacters.Character;

public abstract class Spell {

    private String name;
    private int manaCost;
    private Element element;

    public Spell(String name, int manaCost, Element element) {
        this.name = name;
        this.manaCost = manaCost;
        this.element = element;
    }

    public Spell(String name, int manaCost) {
        this.name = name;
        this.manaCost = manaCost;
        this.element = Element.PHYSICAL;
    }

    public void powerProgression(Character character) {

    }

    public void cast(Character spellCaster, Character target){

    }

    public String getName() {
        return name;
    }

    public int getManaCost() {
        return manaCost;
    }

    public Element getElement(){
        return element;
    }
}
