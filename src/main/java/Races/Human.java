package Races;
import GameCharacters.*;


public class Human extends Race {

	public Human() {
		setIfCanFly(false);
		setIfCanSwim(true);
		setIfCanWalkThroughTerraign(true);

	}
	public int getMaxHealth() {
		return 100;
	}

}