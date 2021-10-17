package Magic;

import GameCharacters.*;
import GameCharacters.Character;

public class DamageDealingSpell extends Spell {

    private int initialDamage;
    private int damageOverTime;
    private int duration;

    public DamageDealingSpell(String name, int manaCost, Element element) {
        super(name, manaCost, element);
        setInitialDamage(10);
        setDamageOverTime(0);
        setDuration(0);
    }

    public DamageDealingSpell(String name, int manaCost) {
        super(name, manaCost);
        setInitialDamage(10);
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

    private void setDamageOverTime(int damageOverTime) {
        this.damageOverTime = damageOverTime;
    }

    public int getDuration(){
        return duration;
    }

    private void setDuration(int duration){
        this.duration = duration;
    }

    //Lägg till något om vapnet?
    @Override
    public void powerProgression(Character character){
        int powerFactor = 1 + character.getLevel() * 2 + character.getIntelligence() * 2;
        initialDamage = initialDamage * powerFactor;

    }
//Rough draft, lägg till fler variabler
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
}
