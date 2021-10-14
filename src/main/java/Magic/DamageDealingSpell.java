package Magic;

public class DamageDealingSpell extends Spell {

    private int initialDamage;
    private int damageOverTime;
    private int duration;

    public DamageDealingSpell(String name, int manaCost, Element element) {
        super(name, manaCost, element);
        setInitialDamage(10);
        setDamageOverTime(10);
        setDuration(10);
    }

    public DamageDealingSpell(String name, int manaCost) {
        super(name, manaCost);
    }

    public int getInitialDamage() {
        return initialDamage;
    }

    public int getDamageOverTime() {
        return damageOverTime / duration;
    }

    private void setDamageOverTime(int damageOverTime) {
        this.damageOverTime = damageOverTime;
    }

    private void setInitialDamage(int initialDamage){
        this.initialDamage = initialDamage;
    }

    private void setDuration(int duration){
        this.duration = duration;
    }

    @Override
    public void cast(){

    }
}
