package Races;

import GameCharacters.*;

public class Human extends Race {

	public Human() {
		setMaxHealth(300);
		setStrength(20);
		setIntelligence(20);

		setIfCanFly(false);
		setIfCanSwim(true);
		setIfCanWalkThroughTerraign(true);

	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getMaxHealth() {
		return maxHealth;
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



}
