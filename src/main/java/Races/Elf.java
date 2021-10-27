package Races;

public class Elf extends Race {


	public Elf() {

		setMaxHealth(200);
		setStrength(10);
		setIntelligence(40);

		setIfCanFly(true);
		setIfCanSwim(false);
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