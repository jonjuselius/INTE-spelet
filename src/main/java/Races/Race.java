package Races;
public abstract class Race {


	private boolean canWalkThroughTerraign;
	private boolean canSwim;
	private boolean canFly;
	protected int maxHealth;


	public Race() {


		setIfCanFly(false);
		setIfCanSwim(false);
		setIfCanWalkThroughTerraign(false);
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
	
	public int getMaxHealth() {
		return maxHealth;
	}

}