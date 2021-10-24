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
            case PHYSICAL -> {
                physicalBuffSpell(spellCaster, target, duration);
            }
        }
    }

    private long calculateDuration(Character spellcaster){
        return (spellcaster.getMagicSkill() * 2L) * 1000;
    }

    private void buffDuration(long duration){
        try {
            Thread.sleep(duration);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

    private void physicalBuffSpell(Character spellCaster, Character target, long duration){
        increaseStrength(target);
        buffDuration(duration);
        target.setStrength(target.getRace().getStrength());
    }

    private void increaseStrength(Character target){
        int strengthFactor = (int) Math.round(spellStrength * 0.3);
        target.setStrength(target.getStrength() * strengthFactor);
    }





}
