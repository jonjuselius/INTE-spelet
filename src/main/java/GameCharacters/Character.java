package GameCharacters;

import Inventory.*;
import Jobs.Job;
import Magic.SpellCollection;
import Races.*;

public abstract class Character {

	private String name;
	private boolean isAlive;
	private Race race;
	private Job job;
	protected int level;
	private int health;

	private SpellCollection spellCollection;
	private Inventory inventory;

	// hitPoints = hälsa
	private int maxMana;
	private int maxHealth;
	private int remainingHealth;
	private int remainingMana;
	private int strength;
	private int intelligence;

	public Character(String name, Race race, Job job, boolean isAlive) {
		this.name = name;
		this.race = race;
		this.job = job;
		isAlive = true;
		this.inventory = inventory;

	}

	public String getName() {
		return name;
	}

	public void increaseHealth(int hp) {
		if (race.getHealth() + hp < race.getMaxHealth()) {
			race.setInitialHealth(race.getMaxHealth());
			return;
		}

		throw new IllegalStateException();

	}// increase

	public int getLevel() {
		return level;
	}

	// Lagt health i character

	public boolean isAlive() {
		return isAlive;
	}

	public int getStrength() {
		return strength;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public int getRemainingHealth() {
		return remainingHealth;
	}

	public void setRemainingHealth(int remainingHealth) {
		this.remainingHealth = race.getHealth();//
	}

	public int getRemainingMana() {
		return remainingMana;
	}

	public void setRemainingMana(int remainingMana) {
		this.remainingMana = remainingMana;
	}

	public void takeDamage(int damage) {
		remainingHealth = remainingHealth - damage;
		if (remainingHealth <= 0) {
			isAlive = false;
		}
	}

	public void useMana(int manaCost) {
		remainingMana = remainingMana - manaCost;
	}

	public void getHealed(int healPoints) {
		int healTotal = remainingHealth + healPoints;
		remainingHealth = Math.min(healTotal, race.getMaxHealth());
	}

	public void getHealedDependingOnYourOwnHealSkill() {
		if (race.getHealth() < remainingHealth) {
			int newHealth = remainingHealth + job.getHealing() * getLevel();
			remainingHealth = Math.min(newHealth, race.getMaxHealth());
		} else if(race.getHealth() > remainingHealth){
			int newHealth = race.getHealth() + job.getHealing() * getLevel();
			remainingHealth = Math.min(newHealth, race.getMaxHealth());
		}
	}

	public void takeDamageDependingOnYourSwordSkillAndStrength(int damage) {
		int milderDamage = job.getSwordSkill() *getLevel() + (race.getStrength()/10);
		if (race.getHealth() < remainingHealth) {
			int newHealth = remainingHealth - damage + milderDamage;
			remainingHealth = Math.min(newHealth, race.getMaxHealth());
		} else if(race.getHealth() > remainingHealth){
			int newHealth = race.getHealth() - damage + milderDamage;
			remainingHealth = Math.min(newHealth, race.getMaxHealth());
		}
	}


	public Inventory getInventory() {
        return inventory;
    }
}



	// Lagt health i character
	
