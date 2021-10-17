package Races;

public class Elf extends Race {

	public Elf() {

		setIfCanFly(true);
		setIfCanSwim(false);
		setIfCanWalkThroughTerraign(true);
	}
	
	public int getMaxHealth() {
		return 200;
	}

}