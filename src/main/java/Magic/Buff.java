package Magic;

public class Buff extends Spell {


    public Buff(String name, int manaCost, Element element) {
        super(name, manaCost, element);
    }

    public Buff(String name, int manaCost) {
        super(name, manaCost);
    }
}
