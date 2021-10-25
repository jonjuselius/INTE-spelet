package Races;

import GameCharacters.*;

public abstract class Race {

	private boolean canWalkThroughTerraign;
	private boolean canSwim;
	private boolean canFly;
	public int maxHealth;
	public int strength;
	public int intelligence;

	public Race() {

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

	public abstract int getStrength();
	public abstract int getIntelligence();
	public abstract int getMaxHealth();

}