package Item;

import Exceptions.DamageException;
import Exceptions.RestoreException;
import GameCharacters.Character;
import Jobs.Job;
import Map.GameMapPosition;
import Races.Race;

import java.util.*;

public abstract class Item {
	public static final int MAX_CONDITION = 100;
	public static final int MIN_CONDITION = 0;
	private int weight;
	private int value;
	private Size size;
	private Type type;
	private int condition;
	private Set<Job> jobCertifications;
	private Set<Race> raceCertifications;
	private GameMapPosition mapPosition;
	private boolean owned;
	private boolean equipped;
	private boolean enhanced;
	
	public Item(int weight, int value, Job[] jobCertifications, Race[] raceCertifications, Size size, Type type, int condition) {
		if (condition < MIN_CONDITION || condition > MAX_CONDITION) {
			throw new IllegalArgumentException();
		}
		this.weight = weight;
		this.value = value;
		this.jobCertifications = new HashSet<>(Arrays.asList(jobCertifications));
		this.raceCertifications = new HashSet<>(Arrays.asList(raceCertifications));
		this.size = size;
		this.type = type;
		this.condition = condition;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public int getValue() {
		return value;
	}
	
	public int getCondition() {
		return condition;
	}
	
	public Type getType() {
		return type;
	}
	
	public GameMapPosition getMapPosition() {
		return mapPosition;
	}
	
	public void setMapPosition(GameMapPosition mapPosition) {
		this.mapPosition = mapPosition;
	}
	
	public Set<Job> getJobCertifications() {
		return Collections.unmodifiableSet(jobCertifications);
	}
	
	public Set<Race> getRaceCertifications() {
		return Collections.unmodifiableSet(raceCertifications);
	}
	
	public Size getSize() {
		return size;
	}
	
	public boolean canBeUsedBy(Character character) {
		Race race = character.getRace();
		Job job = character.getJob();
		return getRaceCertifications().contains(race) && getJobCertifications().contains(job);
	}
	
	public boolean isOwned() {
		return owned;
	}
	
	public boolean isEquipped() {
		return equipped;
	}
	
	public boolean isEnhanced() {
		return enhanced;
	}
	
	public void setOwned(boolean owned) {
		this.owned = owned;
	}
	
	public void setEquipped(boolean equipped) {
		if (isEquippable()) {
			this.equipped = equipped;
		}
	}
	
	public void setEnhanced(boolean enhanced) {
		if (isEnhancable()) {
			this.enhanced = enhanced;
		}
	}
	
	public boolean isEquippable() {
		return isOwned() && (type == Type.WEAPON || type == Type.ARMOR || type == Type.JEWELLERY);
	}
	
	public boolean isEnhancable() {
		return size == Size.SMALL;
	}
	
	public boolean canBeGiven(Character from, Character to) {
		return from.canGive(this) && to.canReceive(this);
	}
	
	public boolean canBeSold(Character seller, Character buyer) {
		return seller.canSell(this) && buyer.canBuy(this);
	}
	
	public boolean isWeapon() {
		return type == Type.WEAPON;
	}
	
	public boolean isArmor() {
		return type == Type.ARMOR;
	}
	
	public boolean isJewewllery() {
		return type == Type.JEWELLERY;
	}
	
	public boolean isFood() {
		return type == Type.FOOD;
	}
	
	public boolean isSmall() {
		return size == Size.SMALL;
	}
	
	public boolean isMedium() {
		return size == Size.MEDIUM;
	}
	
	public boolean isLarge() {
		return size == Size.LARGE;
	}
	
	public void becomeDamaged(int amount) {
		if (amount < 0) {
			throw new DamageException();
		}
		condition -= amount;
		if (condition < MIN_CONDITION) {
			condition = MIN_CONDITION;
		}
	}
	
	public void becomeRestored(int amount) {
		if (amount < 0) {
			throw new RestoreException();
		}
		condition += amount;
		if (condition > MAX_CONDITION) {
			condition = MAX_CONDITION;
		}
	}
}
