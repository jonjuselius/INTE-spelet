package Magic;

public class HealingSpell extends Spell {

    private int initialHeal;
    private int healOverTime;
    private int duration;


    public HealingSpell(String name, int manaCost, Element element) {
        super(name, manaCost, element);
    }

    public HealingSpell(String name, int manaCost) {
        super(name, manaCost);
    }
}
