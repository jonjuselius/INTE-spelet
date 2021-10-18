package Races;
import GameCharacters.*;


public class Human extends Race {

	public Human() {
		setInitialHealth(100);
		setStrength(20);
		setIntelligence(20);
		setIfCanFly(false);
		setIfCanSwim(true);
		setIfCanWalkThroughTerraign(true);

	}
	public int getMaxHealth() {
		return 200;
	}

}