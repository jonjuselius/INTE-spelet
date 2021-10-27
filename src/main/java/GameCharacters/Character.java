package GameCharacters;

import Inventory.*;
import Item.Item;
import Jobs.Job;
import Magic.SpellCollection;
import Map.*;
import Races.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
	private GameMapPosition position;
	
	private Inventory inventory;
	private List<Item> equippedItems;
	private Wallet wallet;

	public Character(String name, Race race, Job job, boolean isAlive, GameMapPosition position) {
		this.name = name;
		this.race = race;
		this.job = job;
		this.isAlive = isAlive;
		this.position = position;
		this.inventory = new Inventory();
		this.wallet = new Wallet();
		this.equippedItems = new ArrayList<>();

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

	public GameMapPosition getPosition() {
		return position;
	}

	public void setPosition(GameMapPosition position) {
		this.position = position;
	}

	public void increaseHealth(int hp) {
		if (remainingHealth + hp < getMaxHealth()) {
			remainingHealth += hp;
			return;
		}

		remainingHealth= getMaxHealth();
		
	}// increase

	public int getLevel() {
		return level;
	}

	// Lagt health i character

	public boolean isAlive() {
		return isAlive;
	}

	public void die() {
		isAlive = false;
	}

	public int getMaxMana() {
		return maxMana;
	}

	public void setMaxMana(int maxMana) {
		this.maxMana = maxMana;
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

	public void setMagicSkill(int magicSkill) {
		this.magicSkill = magicSkill;
	}

	public int getHealingSkill() {
		return healingSkill;
	}

	public void setHealingSkill(int healingSkill) {
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

	public void healDependingOnYourOwnHealSkill(Character otherCharacter) {
		if (otherCharacter.getMaxHealth() > otherCharacter.getRemainingHealth()) {
			int newHealthOfOtherCharacter = otherCharacter.getRemainingHealth() + getHealingSkill() * getLevel();
			otherCharacter.setRemainingHealth(Math.min(newHealthOfOtherCharacter, otherCharacter.getMaxHealth()));
		}
	}

	public void dealDamageDependingOnYourSwordSkillAndStrength(int damage, Character otherCharacter) {

		int increasedDamage = getSwordSkill() * getLevel() + getStrength() + damage;
		int otherCharacterNewHealth = otherCharacter.getRemainingHealth() - increasedDamage;
		if (otherCharacter.getRemainingHealth() > 0 && otherCharacterNewHealth >0) {
			otherCharacter.setRemainingHealth(Math.min(otherCharacterNewHealth, otherCharacter.getMaxHealth()));

		} else {
			otherCharacter.setRemainingHealth(0);
			otherCharacter.die();
		}
	}


	public void loseMagicSkillFromLoss(int loss) {
		if (magicSkill > 0) {
			magicSkill = magicSkill - loss;
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

		if (checkIfLevelUp() == true && race.toString().contains("Elf")) {
			level++;
			strength += 3;
			increaseSkillWhenLevelingUp();
			if (level == 3 && magicSkill - job.getMagic() > 0) {
				setIfCanSwim(true);
			}

		}

		if (checkIfLevelUp() == true && race.toString().contains("Ogre")) {
			level++;
			intelligence += 3;
			increaseSkillWhenLevelingUp();
			if (level == 3 && magicSkill - job.getMagic() > 0) {
				setIfCanSwim(true);
			}
		}

		if (checkIfLevelUp() == true && race.toString().contains("Human")) {
			level++;
			strength += 3;
			increaseSkillWhenLevelingUp();
			if (level == 3 && magicSkill - job.getMagic() > 0) {
				setIfCanFly(true);
			}

		}
	}

	private boolean checkIfLevelUp() {
		int intelligenceAquired = intelligence - race.getIntelligence();
		int strengthAquired = strength - race.getStrength();

		boolean levelUp;
		if ((intelligenceAquired > 0) || (strengthAquired > 0)) {
			levelUp = true;
		} else {
			levelUp = false;
		}
		return levelUp;
	}

	private void increaseSkillWhenLevelingUp() {
		swordSkill += 2;
		healingSkill += 2;
		magicSkill += 2;

	}


	public void setLevel(int level) {
		this.level = level;
	}
	
	public Race getRace() {
		return race;
	}
	
	public Job getJob() {
		return job;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public List<Item> getItems() {
		return Collections.unmodifiableList(inventory.getItems());
	}
	
	public List<Item> getEquippedItems() {
		return Collections.unmodifiableList(equippedItems);
	}
	
	public Wallet getWallet() {
		return wallet;
	}
	
	public int getMoney() {
		return wallet.getAmount();
	}
	
	public void gainMoney(int money) {
		wallet.gain(money);
	}
	
	public void loseMoney(int money) {
		wallet.lose(money);
	}
	
	public boolean canAfford(Item item) {
		return wallet.getAmount() >= item.getValue();
	}
	
	public void gain(Item item) {
		inventory.add(item);
	}
	
	public void lose(Item item) {
		if (hasEquipped(item)) {
			unequip(item);
		}
		inventory.remove(item);
	}
	
	public boolean owns(Item item) {
		return inventory.contains(item);
	}
	
	public boolean canEquip(Item item) {
		return item.isEquippable();
	}
	
	public void equip(Item item) {
		if (!canEquip(item)) {
			throw new IllegalArgumentException("The item can't be equipped!");
		}
		equippedItems.add(item);
		item.setEquipped(true);
	}
	
	public void unequip(Item item) {
		if (!hasEquipped(item)) {
			throw new IllegalArgumentException("The item can't be unequipped!");
		}
		equippedItems.remove(item);
		item.setEquipped(false);
	}
	
	public boolean hasEquipped(Item item) {
		return equippedItems.contains(item);
	}
	
	public void give(Item item, Character recipient) {
		if (!item.canBeGiven(this, recipient)) {
			throw new IllegalArgumentException("The item can't be given!");
		}
		this.lose(item);
		recipient.gain(item);
	}
	
	public boolean canGive(Item item) {
		return owns(item) && !hasEquipped(item);
	}
	
	public boolean canReceive(Item item) {
		return !owns(item) && getInventory().hasAvailableSpace();
	}
	
	public void buy(Item item, Character other) {
		int price = item.getValue();
		Character buyer = this;
		Character seller = other;
		if (!item.canBeSold(seller, buyer)) {
			throw new IllegalArgumentException("The item can't be bought!");
		}
		seller.give(item, buyer);
		seller.gainMoney(price);
		buyer.loseMoney(price);
	}
	
	public void sell(Item item, Character other) {
		int price = item.getValue();
		Character buyer = other;
		Character seller = this;
		if (!item.canBeSold(seller, buyer)) {
			throw new IllegalArgumentException("The item can't be sold!");
		}
		seller.give(item, buyer);
		seller.gainMoney(price);
		buyer.loseMoney(price);
	}
	
	public boolean canBuy(Item item) {
		return canReceive(item) && canAfford(item);
	}
	
	public boolean canSell(Item item) {
		return canGive(item);
	}
	
	public boolean canUse(Item item) {
		return item.canBeUsedBy(this);
	}
	
	public boolean canEat(Item item) {
		return item.canBeEatenBy(this);
	}
	
	public void use(Item item) {
		if (!item.canBeUsedBy(this)) {
			throw new IllegalArgumentException("Item can't be used!");
		}
		if (item.isFood()) {
			destroy(item);
		} else {
			damage(item, 10);
		}
	}
	
	public void eat(Item item) {
		if (!item.isFood()) {
			throw new IllegalArgumentException("Item can't be eaten!");
		}
		use(item);
	}
	
	public void enhance(Item item) {
		if (!item.isEnhancable()) {
			throw new IllegalArgumentException("Item can't be enhanced!");
		}
		item.setEnhanced(true);
	}
	
	public void destroy(Item item) {
		if (!item.isDestroyable()) {
			throw new IllegalArgumentException();
		}
		item.becomeDestroyed();
	}
	
	public void recover(Item item) {
		if (!item.isRecoverable()) {
			throw new IllegalArgumentException();
		}
		item.becomeRecovered();
	}
	
	public void damage(Item item, int amount) {
		item.becomeDamaged(10);
	}
	
	public void restore(Item item, int amount) {
		item.becomeRestored(10);
	}

}

