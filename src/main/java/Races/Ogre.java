package Races;

import GameCharacters.*;

public class Ogre extends Race {

	public Ogre() {
		setInitialHealth(300);
		setStrength(30);
		setIntelligence(10);
		setIfCanFly(false);
		setIfCanSwim(false);
		setIfCanWalkThroughTerraign(true);

	}

	public int getMaxHealth() {
		return 400;
	}

}
