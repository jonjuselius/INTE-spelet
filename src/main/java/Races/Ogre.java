package Races;

public class Ogre extends Race {

	public Ogre() {
		setMaxHealth(300);
		setStrength(30);
		setIntelligence(10);

		setIfCanFly(false);
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
