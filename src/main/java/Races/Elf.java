package Races;
import GameCharacters.*;

public class Elf extends Race {

	public Elf() {
		
		setInitialHealth(200);
		setStrength(10);
		setIntelligence(40);

		setIfCanFly(true);
		setIfCanSwim(false);
		setIfCanWalkThroughTerraign(true);
	}
	
	public int getMaxHealth() {
		return 300;
	}

}