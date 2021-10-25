public abstract class Race {

	private int health;

	private boolean canWalkThroughTerraign;
	private boolean canSwim;
	private boolean canFly;

	public Race() {
		setHealth(100);


		setIfCanFly(false);
		setIfCanSwim(false);
		setIfCanWalkThroughTerraign(false);
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

	public boolean getIfCanWalkThroughTerraign() {
		return canWalkThroughTerraign;
	}

	protected void setIfCanWalkThroughTerraign(boolean canWalkThroughTerraign) {
		this.canWalkThroughTerraign = canWalkThroughTerraign;
	}

}