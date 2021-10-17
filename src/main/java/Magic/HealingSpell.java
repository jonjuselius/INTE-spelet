package Magic;

import GameCharacters.Character;

public class HealingSpell extends Spell {

    private int initialHeal;
    private int healOverTime;
    private int duration;


    public HealingSpell(String name, int manaCost, Element element) {
        super(name, manaCost, element);
        setInitialHeal(getManaCost() * 2);
    }

    public HealingSpell(String name, int manaCost) {
        super(name, manaCost);
        setInitialHeal(getManaCost() * 2);
    }

    public int getInitialHeal() {
        return initialHeal;
    }

    private void setInitialHeal(int initialHeal) {
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

    @Override
    public void powerProgression(Character character){
        int healFactor = 1 + character.getLevel() * 2 + character.getIntelligence();
        initialHeal = initialHeal * healFactor;
    }



}


