package Magic;

import GameCharacters.Character;

public class DebuffSpell extends Spell{

    private final int spellStrength;

    public DebuffSpell(String name, int manaCost, Element element, int spellStrength) {
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
        if(getManaCost() > spellCaster.getRemainingMana()) {
            throw new IllegalStateException("Not enough mana");
        }else{
            spellCaster.useMana(getManaCost());

            switch (this.getElement()) {
                case PHYSICAL -> physicalDebuffSpell(spellCaster, target, duration);
            }
        }
    }

    private void physicalDebuffSpell(Character spellCaster, Character target, long duration){
        decreaseStrength(spellCaster, target);
        buffDuration(duration);
        target.setStrength(spellCaster.getRace().getStrength());
    }

    private void decreaseStrength(Character spellCaster,Character target){
        target.setStrength(target.getStrength() * calculateSpellStrength(spellCaster));
    }

    private int calculateSpellStrength(Character spellCaster){
        return (int) Math.round((double)(spellCaster.getMagicSkill() + spellStrength) / 100);
    }

    private long calculateDuration(Character spellCaster){
        return (spellCaster.getMagicSkill() * 2L) * 1000;
    }

    private void buffDuration(long duration){
        try {
            Thread.sleep(duration);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
}
