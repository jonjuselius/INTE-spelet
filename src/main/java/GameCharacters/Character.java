package GameCharacters;

import Inventory.*;
import Jobs.Job;
import Magic.SpellCollection;
import Map.*;
import Races.*;

public abstract class Character {

	private String name;
	private boolean isAlive;
	private Race race;
	private Job job;
	protected int level;

	private int strength;
	private int intelligence;
	private boolean canWalkThroughTerraign;
	private boolean canSwim;
	private boolean canFly;

	protected int magicSkill;
	protected int healingSkill;
	protected int swordSkill;
	protected int maxMana;

	private int remainingHealth;
	private int remainingMana;

	private SpellCollection spellCollection;
	private Inventory inventory;
	private Map map;
	private MapPosition position;

	public Character(String name, Race race, Job job, boolean isAlive, Map map, MapPosition position) {
		this.name = name;
		this.race = race;
		this.job = job;
		isAlive = true;
		this.inventory = inventory;
		this.map = map;
		this.position = position;

		setRemainingHealth(race.getMaxHealth());
		setStrength(race.getStrength());
		setIntelligence(race.getIntelligence());
		setIfCanFly(race.getIfCanFly());
		setIfCanSwim(race.getIfCanSwim());
		setIfCanWalkThroughTerraign(race.getIfCanWalkThroughTerraign());

		setMagicSkill(job.getMagic());
		setHealingSkill(job.getHealing());
		setSwordSkill(job.getSwordSkill());
		setRemainingMana(job.getMaxMana());
		setLevel(level);

	}

	public String getName() {
		return name;
	}

	public MapPosition getPosition() {
		return position;
	}

	public void increaseHealth(int hp) {
		if (remainingHealth + hp < race.getMaxHealth()) {
			remainingHealth += hp;
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

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
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

	public int getMagicSkill() {
		return magicSkill;
	}

	protected void setMagicSkill(int magicSkill) {
		this.magicSkill = magicSkill;
	}

	public int getHealingSkill() {
		return healingSkill;
	}

	protected void setHealingSkill(int healingSkill) {
		this.healingSkill = healingSkill;
	}

	public int getSwordSkill() {
		return swordSkill;
	}

	protected void setSwordSkill(int swordSkill) {
		this.swordSkill = swordSkill;
	}

	public int getRemainingHealth() {
		return remainingHealth;
	}

	public void setRemainingHealth(int remainingHealth) {
		this.remainingHealth = remainingHealth;//
	}

	public int getMaxHealth() {
		return race.getMaxHealth();
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
		if (race.getMaxHealth() > remainingHealth) {
			int newHealth = remainingHealth + getHealingSkill() * getLevel();
			remainingHealth = Math.min(newHealth, race.getMaxHealth());
		}
	}

	public void dealDamageDependingOnYourSwordSkillAndStrength(int damage, Character otherCharacter) {

		int increasedDamage = getSwordSkill() * getLevel() + getStrength() + damage;
		int otherCharacterNewHealth = otherCharacter.getRemainingHealth() - increasedDamage;
		if (otherCharacter.getRemainingHealth() > 0 && otherCharacterNewHealth >= 0) {
			otherCharacter.setRemainingHealth(Math.min(otherCharacterNewHealth, otherCharacter.getMaxHealth()));

		} else {
			otherCharacter.setRemainingHealth(0);
		}
	}

	public void increaseIntelligenceFromWinningASpell() {
		if (getIntelligence() < race.getIntelligence() + 15) {
			intelligence += 3;
			return;
		}
	}

	public void increaseStrengthFromWinningASpell() {
		if (getStrength() < race.getStrength() + 15) {
			strength += 3;
			return;
		}
	}// Level up if high attributes

	public void levelsUp() {

		if ( race.toString().contains("Elf") && level < 6 && ((intelligence - 40) % 3 == 0) && (intelligence - 40 > 0)) {
			level++;
			strength += 3;

		}

		if (race.toString().contains("Ogre") && level < 6 && ((strength - 30) % 3 == 0) && (strength - 30 > 0)) {
			level++;
			intelligence += 3;
		}

		if (race.toString().contains("Human") && level < 6 && ((intelligence - 20) % 3 == 0) && ((strength - 20 > 0)
				|| (intelligence - 20 > 0))) {
			level++;
			strength += 3;

		}
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public Job getJob() {
		return job;
	}

	public Race getRace() {
		return race;
	}
}
// Lagt health i character
