package Magic;

import GameCharacters.*;
import GameCharacters.Character;

public class BuffSpell extends Spell {

    private int spellStrength;


    public BuffSpell(String name, int manaCost, Element element, int spellStrength) {
        super(name, manaCost, element);
        this.spellStrength = spellStrength;
    }

    public int getSpellStrength(){
        return spellStrength;
    }

    @Override
    public void cast(Character spellCaster, Character target){

        long duration = calculateDuration(spellCaster);

        cast(spellCaster, target, duration);

    }

    public void cast(Character spellCaster, Character target, long duration){

        switch (this.getElement()){
            case PHYSICAL -> physicalBuffSpell(spellCaster, target, duration);
            case FIRE -> fireBuffSpell(spellCaster, target, duration);
        }
    }

    private long calculateDuration(Character spellCaster){
        return (spellCaster.getMagicSkill() * 2L) * 1000;
    }

    private int calculateSpellStrength(Character spellCaster){
        return (int) Math.round(spellStrength * 0.1 + (spellCaster.getMagicSkill() * 0.1));
    }

    private void buffDuration(long duration){
        try {
            Thread.sleep(duration);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

    private void physicalBuffSpell(Character spellCaster, Character target, long duration){
        increaseStrength(spellCaster, target);
        buffDuration(duration);
        target.setStrength(target.getRace().getStrength());
    }

    private void increaseStrength(Character spellCaster, Character target){
        target.setStrength(target.getStrength() * calculateSpellStrength(spellCaster));
    }

    private void fireBuffSpell(Character spellCaster, Character target, long duration){
        increaseIntelligence(spellCaster, target);
        buffDuration(duration);
        target.setIntelligence(target.getRace().getIntelligence());
    }

    private void increaseIntelligence(Character spellCaster, Character target){
        target.setIntelligence(target.getIntelligence() * calculateSpellStrength(spellCaster));
    }





}
