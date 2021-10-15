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

    public int getInitialHeal() {
        return initialHeal;
    }

    public void setInitialHeal(int initialHeal) {
        this.initialHeal = initialHeal;
    }

    public int getHealOverTime() {
        return healOverTime;
    }

    public void setHealOverTime(int healOverTime) {
        this.healOverTime = healOverTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

}


