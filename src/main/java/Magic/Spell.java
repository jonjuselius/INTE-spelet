package Magic;

import GameCharacters.*;
import GameCharacters.Character;

import java.util.Objects;

public abstract class Spell {

    private String name;
    private int manaCost;
    private Element element;

    public Spell(String name, int manaCost, Element element) {
        this.name = name;
        this.manaCost = manaCost;
        this.element = element;
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Spell spell = (Spell) o;
        return manaCost == spell.manaCost && Objects.equals(name, spell.name) && element == spell.element;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, manaCost, element);
    }
}
