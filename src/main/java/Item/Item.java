package Item;

import GameCharacters.Character;
import Map.Map;
import Map.MapPosition;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class Item {
	/**
	 * Item an abstract noninstantiable superclass for things such as food, weapon and armor.
	 * Actual instantiable items include swords, wands, eggs, shields and rings.
	 * Every item has a weight, a value, a size, a type, a condition, and more.
	 *
	 * The default condition for a new item is 100 %, which means that the item is in a perfect
	 * condition, not destroyed or damaged in any way. An item with 0 % condition implies that
	 * the item is in a very poor condition.
	 *
	 * An item can only be used by a character if the character has a race and a job that
	 * qualifies the character for using the item. Every item stores information on which
	 * race and job that it can be used by, e.g. a sword can be used by characters of any race,
	 * but only if "knight" is their job.
	 *
	 * weight: How heavy an item is. Items with higher weight are heavier.
	 * value: How expensive an item is. Items with higher valuer are more expensive.
	 * size: How big an item is. Three size categories are present: small/medium/large.
	 * type: What kind of item an item is. Five item types are: weapon, armor, food, jewellery
	 * condition: How undamaged an item is. An item with max condition (100) is completely undamaged.
	 * jobCertifications: Which jobs that are eligible for using an item, e.g. only knights.
	 * raceCertifications: Which races that are eligible for using an item, e.g. only humans.
	 * canBeUsedBy: Checks if a character can use the item or not, depending on its race and job.
	 */
	public static final int MAX_CONDITION = 100;
	public static final int MIN_CONDITION = 0;
	private int weight;
	private int value;
	private Size size;
	private Type type;
	private int condition;
	private List<String> jobCertifications; // ändra från list till set?
	private List<String> raceCertifications;
	private MapPosition mapPosition;
	
	public Item(int weight, int value, String[] jobCertifications, String[] raceCertifications, Size size, Type type, int condition, MapPosition mapPosition) {
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
		this.mapPosition = mapPosition;
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
}
