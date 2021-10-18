package Races;

import GameCharacters.*;


public class Ogre extends Race {

	public Ogre() {
		setIfCanFly(false);
		setIfCanSwim(false);
		setIfCanWalkThroughTerraign(true);

	}

	public int getMaxHealth() {
		return 400;
	}

}
