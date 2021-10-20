
package Races;

import GameCharacters.*;

public abstract class Race {

	private boolean canWalkThroughTerraign;
	private boolean canSwim;
	private boolean canFly;
	protected int maxHealth;
	private int strength;
	private int intelligence;

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

	protected void setStrength(int strength) {
		this.strength = strength;

	}

	public int getStrength() {
		return strength;
	}

	protected void setIntelligence(int intelligence) {
		this.intelligence = intelligence;

	}

	public int getIntelligence() {
		return intelligence;
	}

	public abstract int getMaxHealth();
	
}