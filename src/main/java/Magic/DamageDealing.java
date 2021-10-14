package Magic;

public class DamageDealing extends Spell {

    private int initialDamage;
    private int damageOverTime;
    private int duration;

    public DamageDealing(String name, int manaCost, Element element) {
        super(name, manaCost, element);
    }

    public DamageDealing(String name, int manaCost) {
        super(name, manaCost);
    }

    public int getInitialDamage() {
        return initialDamage;
    }

    public int getDamageOverTime() {
        return damageOverTime / duration;
    }

}
