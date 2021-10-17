package GameCharacters;

public abstract class Race {

    private int health;

    private boolean canWalkThroughTerrain;
    private boolean canSwim;
    private boolean canFly;

    public Race() {
        setHealth(100);


        setIfCanFly(false);
        setIfCanSwim(false);
        setIfCanWalkThroughTerrain(false);
    }

    public int getHealth() {
        return health;
    }

    protected void setHealth(int health) {
        this.health = health;
    }

    public boolean getIfCanFly() {
        return canFly;
    }

    protected void setIfCanFly(boolean canFly) {
        this.canFly = canFly;
    }

    public boolean getIfCanSwim() {
        return canSwim;
    }

    protected void setIfCanSwim(boolean canSwim) {
        this.canSwim = canSwim;
    }

    public boolean getIfCanWalkThroughTerrain() {
        return canWalkThroughTerrain;
    }

    protected void setIfCanWalkThroughTerrain(boolean canWalkThroughTerrain) {
        this.canWalkThroughTerrain = canWalkThroughTerrain;
    }

}
