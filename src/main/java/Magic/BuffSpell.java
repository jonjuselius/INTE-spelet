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

        switch (this.getElement()){
            case PHYSICAL -> {
                physicalBuffSpell(spellCaster, target);
            }
        }
    }

    public void physicalBuffSpell(Character spellCaster, Character target){
        increaseStrength(target);
        buffDuration(spellCaster);
        target.setStrength(target.getRace().getStrength());
    }

    private void increaseStrength(Character target){
        int strengthFactor = (int) Math.round(spellStrength * 1.5);
        target.setStrength(target.getStrength() * strengthFactor);
    }

    private void buffDuration(Character spellcaster){
        int durationInSeconds = spellcaster.getMagicSkill() * 2;
        try {
            Thread.sleep(durationInSeconds * 1000);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }


}
