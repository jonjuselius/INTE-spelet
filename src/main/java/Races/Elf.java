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
}