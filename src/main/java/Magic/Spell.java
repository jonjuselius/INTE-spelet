public abstract class Spell {

    private int manaCost;

    public Spell(int manaCost) {
        this.manaCost = manaCost;
    }

    abstract void powerProgression();

}
