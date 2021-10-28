package Magic;

import GameCharacters.Character;

import java.util.Objects;

public class DebuffSpell extends Spell{

    private final int spellStrength;

    //Spell that negatively impacts a targets stats
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
                case FIRE -> fireDebuffSpell(spellCaster, target, duration);
                case LIGHTNING -> lightningDebuffSpell(spellCaster, target, duration);
                case WATER -> waterDebuffSpell(spellCaster, target, duration);
                case WIND -> windDebuffSpell(spellCaster, target, duration);
                default -> physicalDebuffSpell(spellCaster, target, duration);
            }
        }
    }

    private void physicalDebuffSpell(Character spellCaster, Character target, long duration){
        decreaseStrength(spellCaster, target);
        buffDuration(duration);
        target.setStrength(target.getRace().getStrength());
    }

    private void decreaseStrength(Character spellCaster,Character target){
        target.setStrength(
                (int) Math.round(target.getStrength() * (1.0 - calculateSpellStrength(spellCaster)))
        );
    }

    private void fireDebuffSpell(Character spellCaster, Character target, long duration){
        decreaseIntelligence(spellCaster, target);
        buffDuration(duration);
        target.setIntelligence(target.getRace().getIntelligence());
    }

    private void decreaseIntelligence(Character spellCaster, Character target){
        target.setIntelligence(
                (int) Math.round(target.getIntelligence() * (1.0 - calculateSpellStrength(spellCaster)))
        );
    }

    private void lightningDebuffSpell(Character spellCaster, Character target, long duration){
        decreaseMagicSkill(spellCaster, target);
        buffDuration(duration);
        target.setMagicSkill(target.getJob().getMagic());
    }

    private void decreaseMagicSkill(Character spellCaster, Character target){
        target.setMagicSkill(
                (int) Math.round(target.getMagicSkill() * (1.0 - calculateSpellStrength(spellCaster)))
        );
    }

    private void waterDebuffSpell(Character spellCaster, Character target, long duration){
        decreaseHealingSkill(spellCaster, target);
        buffDuration(duration);
        target.setHealingSkill(target.getJob().getHealing());
    }

    private void decreaseHealingSkill(Character spellCaster, Character target){
        target.setHealingSkill(
                (int) Math.round(target.getHealingSkill() * (1.0 - calculateSpellStrength(spellCaster)))
        );
    }

    private void windDebuffSpell(Character spellCaster, Character target, long duration){
        decreaseMaxMana(spellCaster, target);
        buffDuration(duration);
        target.setMaxMana(target.getJob().getMaxMana());
    }

    private void decreaseMaxMana(Character spellCaster, Character target){
        target.setMaxMana(
                (int) Math.round(target.getMaxMana() * (1.0 - calculateSpellStrength(spellCaster)))
        );
    }

    private double calculateSpellStrength(Character spellCaster){
        return ((double)(spellCaster.getMagicSkill() + spellStrength) / 100);
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

    @Override
    public boolean equals(Object o) {
        if (o instanceof DebuffSpell) {
            return super.equals(o) && spellStrength == ((DebuffSpell) o).getSpellStrength();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), spellStrength);
    }

}
