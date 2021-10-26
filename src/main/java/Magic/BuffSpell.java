package Magic;

import GameCharacters.*;
import GameCharacters.Character;

public class BuffSpell extends Spell {

    private final int spellStrength;

    //Spell that positively changes stats on a player
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

        cast(spellCaster, duration);

    }

    //
    public void cast(Character spellCaster, long duration){
        if(getManaCost() > spellCaster.getRemainingMana()) {
            throw new IllegalStateException("Not enough mana");
        }else{
            spellCaster.useMana(getManaCost());

            switch (this.getElement()) {
                case PHYSICAL -> physicalBuffSpell(spellCaster, duration);
                case FIRE -> fireBuffSpell(spellCaster, duration);
                case LIGHTNING -> lightningBuffSpell(spellCaster, duration);

            }
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

    private void physicalBuffSpell(Character spellCaster, long duration){
        increaseStrength(spellCaster);
        buffDuration(duration);
        spellCaster.setStrength(spellCaster.getRace().getStrength());
    }

    private void increaseStrength(Character spellCaster){
        spellCaster.setStrength(spellCaster.getStrength() * calculateSpellStrength(spellCaster));
    }

    private void fireBuffSpell(Character spellCaster, long duration){
        increaseIntelligence(spellCaster);
        buffDuration(duration);
        spellCaster.setIntelligence(spellCaster.getRace().getIntelligence());
    }

    private void increaseIntelligence(Character spellCaster){
        spellCaster.setIntelligence(spellCaster.getIntelligence() * calculateSpellStrength(spellCaster));
    }

    private void lightningBuffSpell(Character spellCaster, long duration){
        increaseMagicSkill(spellCaster);
        buffDuration(duration);
        spellCaster.setMagicSkill(spellCaster.getJob().getMagic());
    }

    private void increaseMagicSkill(Character spellCaster){
        spellCaster.setMagicSkill(spellCaster.getIntelligence() * calculateSpellStrength(spellCaster));
    }







}
