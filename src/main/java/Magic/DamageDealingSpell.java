package Magic;

import GameCharacters.*;
import GameCharacters.Character;

import java.util.Objects;

public class DamageDealingSpell extends Spell {

    private final int baseDamage;
    private int initialDamage;
    private int damageOverTime;
    private int duration;

    public DamageDealingSpell(String name, int manaCost, Element element, int baseDamage) {
        super(name, manaCost, element);
        this.baseDamage = baseDamage;
        setInitialDamage(baseDamage);
        setDamageOverTime(0);
        setDuration(0);
    }


    public int getInitialDamage() {
        return initialDamage;
    }

    private void setInitialDamage(int initialDamage){
        this.initialDamage = initialDamage;
    }

    public int getDamageOverTime() {
        if(damageOverTime == 0 || duration == 0){
            return 0;
        }else {
            return damageOverTime / duration;
        }
    }

    public void setDamageOverTime(int damageOverTime) {
        this.damageOverTime = damageOverTime;
    }

    public int getDuration(){
        return duration;
    }

    public void setDuration(int duration){
        this.duration = duration;
    }

    //Lägg till något om vapnet?
    @Override
    public void powerProgression(Character character){
        int powerFactor = 1 + character.getMagicSkill() * 2 + character.getIntelligence();
        initialDamage = initialDamage * powerFactor;

    }

    @Override
    public void cast(Character spellCaster, Character target){
        if(getManaCost() > spellCaster.getRemainingMana()){
            throw new IllegalStateException("Not enough mana");
        }else {
            powerProgression(spellCaster);
            spellCaster.useMana(getManaCost());
            target.takeDamage(initialDamage);
        }
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof DamageDealingSpell) {
            return super.equals(o) && baseDamage == ((DamageDealingSpell) o).getBaseDamage();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), this.getManaCost(), this.getElement(), this.getBaseDamage());
    }
}