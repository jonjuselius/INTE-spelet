package Magic;

public class Healing extends Spell {

    private int initialHeal;
    private int healOverTime;


    public Healing(String name, int manaCost, Element element) {
        super(name, manaCost, element);
    }

    public Healing(String name, int manaCost) {
        super(name, manaCost);
    }
}
