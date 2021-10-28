package Magic;

import GameCharacters.Character;

import java.util.Objects;

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
                //case PHYSICAL -> physicalBuffSpell(spellCaster, duration);
                case FIRE -> fireBuffSpell(spellCaster, duration);
                case LIGHTNING -> lightningBuffSpell(spellCaster, duration);
                case WATER -> waterBuffSpell(spellCaster, duration);
                case WIND -> windBuffSpell(spellCaster, duration);
                default -> physicalBuffSpell(spellCaster,duration);
            }
        }
    }

    private long calculateDuration(Character spellCaster){
        return (spellCaster.getMagicSkill() * 2L) * 1000;
    }

    private double calculateSpellStrength(Character spellCaster){
        return ((double)(spellCaster.getMagicSkill() + spellStrength) / 100);
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
        spellCaster.setStrength(
                (int) Math.round(spellCaster.getStrength() * (1.0 + calculateSpellStrength(spellCaster)))
        );
    }

    private void fireBuffSpell(Character spellCaster, long duration){
        increaseIntelligence(spellCaster);
        buffDuration(duration);
        spellCaster.setIntelligence(spellCaster.getRace().getIntelligence());
    }

    private void increaseIntelligence(Character spellCaster){
        spellCaster.setIntelligence(
                (int) Math.round(spellCaster.getIntelligence() * (1.0 + calculateSpellStrength(spellCaster)))
        );
    }

    private void lightningBuffSpell(Character spellCaster, long duration){
        increaseMagicSkill(spellCaster);
        buffDuration(duration);
        spellCaster.setMagicSkill(spellCaster.getJob().getMagic());
    }

    private void increaseMagicSkill(Character spellCaster){
        spellCaster.setMagicSkill(
                (int) Math.round(spellCaster.getMagicSkill() * (1.0 + calculateSpellStrength(spellCaster)))
        );
    }

    private void waterBuffSpell(Character spellCaster, long duration){
        increaseHealingSkill(spellCaster);
        buffDuration(duration);
        spellCaster.setHealingSkill(spellCaster.getJob().getHealing());
    }

    private void increaseHealingSkill(Character spellCaster){
        spellCaster.setHealingSkill(
                (int) Math.round(spellCaster.getHealingSkill() * (1.0 + calculateSpellStrength(spellCaster)))
        );
    }

    private void windBuffSpell(Character spellCaster, long duration){
        increaseMaxMana(spellCaster);
        buffDuration(duration);
        spellCaster.setMaxMana(spellCaster.getJob().getMaxMana());
    }

    private void increaseMaxMana(Character spellCaster){
        spellCaster.setMaxMana(
                (int) Math.round(spellCaster.getMaxMana() * (1.0 + calculateSpellStrength(spellCaster)))
        );
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof HealingSpell) {
            return super.equals(o) && spellStrength == ((HealingSpell) o).getBaseHeal();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), spellStrength);
    }
}
