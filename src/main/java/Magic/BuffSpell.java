package Magic;

public class BuffSpell extends Spell {


    public BuffSpell(String name, int manaCost, Element element) {
        super(name, manaCost, element);
    }

    public BuffSpell(String name, int manaCost) {
        super(name, manaCost);
    }
}