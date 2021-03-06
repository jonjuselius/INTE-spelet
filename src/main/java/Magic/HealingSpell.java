package Magic;

import GameCharacters.Character;
import Jobs.Healer;

import java.util.Objects;

public class HealingSpell extends Spell {

    private final int baseHeal;
    private int initialHeal;
    private int healOverTime;
    private int duration;


    public HealingSpell(String name, int manaCost, Element element, int baseHeal) {
        super(name, manaCost, element);
        this.baseHeal = baseHeal;
        setInitialHeal(baseHeal);
        setHealOverTime(0);
        setDuration(0);
    }

    public int getInitialHeal() {
        return initialHeal;
    }

    private void setInitialHeal(int initialHeal) {
        this.initialHeal = initialHeal;
    }

    public int getHealOverTime() {
        if(healOverTime == 0 || duration == 0){
            return 0;
        }
        return healOverTime / duration;
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
        int healFactor = 1 + character.getHealingSkill() * 2 + character.getIntelligence();
        if(character.getJob() instanceof Healer){
            healFactor = healFactor * 2;
        }
        initialHeal = initialHeal * healFactor;
    }

    @Override
    public void cast(Character spellCaster, Character target){
        if(spellCaster.getRemainingMana() < getManaCost()) {
            throw new IllegalStateException("Not enough mana");
        }else{
            spellCaster.useMana(getManaCost());
            powerProgression(spellCaster);
            spellCaster.getHealed(initialHeal);
        }
    }

    public int getBaseHeal() {
        return baseHeal;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof HealingSpell) {
            return super.equals(o) && baseHeal == ((HealingSpell) o).getBaseHeal();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), this.getManaCost(), this.getElement(), this.getBaseHeal());
    }

}


