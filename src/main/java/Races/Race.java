package Races;

import GameCharacters.*;

import java.util.*;

public abstract class Race {

	private boolean canWalkThroughTerraign;
	private boolean canSwim;
	private boolean canFly;
	public int maxHealth;
	public int strength;
	public int intelligence;

	public Race() {

	}

	public boolean getIfCanFly() {
		return canFly;
	}

	protected void setIfCanFly(boolean canFly) {
		this.canFly = canFly;
	}

	public boolean getIfCanSwim() {
		return canSwim;
	}

	protected void setIfCanSwim(boolean canSwim) {
		this.canSwim = canSwim;
	}

	public boolean getIfCanWalkThroughTerraign() {
		return canWalkThroughTerraign;
	}

	protected void setIfCanWalkThroughTerraign(boolean canWalkThroughTerraign) {
		this.canWalkThroughTerraign = canWalkThroughTerraign;
	}

	public abstract int getStrength();
	public abstract int getIntelligence();
	public abstract int getMaxHealth();
	
	public static List<Race> getAllRaces() {
		List<Race> allRaces = new ArrayList<>();
		allRaces.add(new Human());
		allRaces.add(new Ogre());
		allRaces.add(new Elf());
		return allRaces;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Race race = (Race) o;
		return canWalkThroughTerraign == race.canWalkThroughTerraign && canSwim == race.canSwim && canFly == race.canFly && maxHealth == race.maxHealth && strength == race.strength && intelligence == race.intelligence;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(canWalkThroughTerraign, canSwim, canFly, maxHealth, strength, intelligence);
	}
}