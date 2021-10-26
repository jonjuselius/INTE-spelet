package Item;

import GameCharacters.Character;
import Map.GameMapPosition;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class Item {
	public static final int MAX_CONDITION = 100;
	public static final int MIN_CONDITION = 0;
	private int weight;
	private int value;
	private Size size;
	private Type type;
	private int condition;
	private List<String> jobCertifications; // ändra från list till set?
	private List<String> raceCertifications;
	private GameMapPosition mapPosition;
	private boolean owned;
	private boolean equipped;
	private boolean enhanced;
	
	public Item(int weight, int value, String[] jobCertifications, String[] raceCertifications, Size size, Type type, int condition) {
		if (condition < MIN_CONDITION || condition > MAX_CONDITION) {
			throw new IllegalArgumentException();
		}
		this.weight = weight;
		this.value = value;
		this.jobCertifications = Arrays.asList(jobCertifications);
		this.raceCertifications = Arrays.asList(raceCertifications);
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
	
	public List<String> getJobCertifications() {
		return Collections.unmodifiableList(jobCertifications);
	}
	
	public List<String> getRaceCertifications() {
		return Collections.unmodifiableList(raceCertifications);
	}
	
	public Size getSize() {
		return size;
	}
	
	public boolean canBeUsedBy(Character character) {
		String race = character.getRace().getClass().getSimpleName();
		String job = character.getJob().getClass().getSimpleName();
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
	
	public boolean canBeEatenBy(Character character) {
		return isFood() && canBeUsedBy(character);
	}
	
	public boolean isDestroyable() {
		return condition > MIN_CONDITION;
	}
	
	public boolean isRecoverable() {
		return condition < MAX_CONDITION;
	}
	
	public void becomeDestroyed() {
		condition = MIN_CONDITION;
	}
	
	public void becomeRecovered() {
		condition = MAX_CONDITION;
	}
	
	public void becomeDamaged(int amount) {
		condition -= amount;
		if (condition < MIN_CONDITION) {
			becomeDestroyed();
		}
	}
	
	public void becomeRestored(int amount) {
		condition += amount;
		if (condition > MAX_CONDITION) {
			becomeRecovered();
		}
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
	
	public boolean isDestroyed() {
		return condition == Item.MIN_CONDITION;
	}
	
	public boolean isPerfect() {
		return condition == Item.MAX_CONDITION;
	}
	
	//@Override
	//public String toString() {
	//	return this.getClass().getSimpleName().toString();
	//}
}
