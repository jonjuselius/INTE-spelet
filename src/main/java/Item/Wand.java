package Item;

import Map.Map;
import Map.MapPosition;

public class Wand extends Item {
	/**
	 * Wand is a light and quite valuable item, that is instantiable. It is categorized
	 * as a weapon, which means that it belongs to the same category, or item type,
	 * as swords. The purpose of a wand is casting spells in order to deal damage,
	 * restore damage, or creating an environment that is beneficial for the wand user,
	 * for example increasing the stats of the wand user, or decreasing the stats of
	 * an enemy. A new wand comes in full condition, and most wands are medium sized.
	 * Every race can use a wand, as long as their job is either magician or healer.
	 * Knights can't use wands, because using wands requires knowledge of magic.
	 */
	public static final int WEIGHT = 5;
	public static final int VALUE = 500;
	public static final Type TYPE = Type.WEAPON;
	public static final Size DEFAULT_SIZE = Size.MEDIUM;
	public static final int DEFAULT_CONDITION = Item.MAX_CONDITION;
	public static final String[] JOB_CERTIFICATIONS = {"Magician", "Healer"};
	public static final String[] RACE_CERTIFICATIONS = {"Human", "Ogre", "Elf"};
	
	public Wand() {
		this(DEFAULT_CONDITION);
	}
	
	public Wand(int condition) {
		this(condition, DEFAULT_SIZE);
	}
	
	public Wand(Size size) {
		this(DEFAULT_CONDITION, size);
	}
	
	public Wand(int condition, Size size) {
		super(WEIGHT, VALUE, JOB_CERTIFICATIONS, RACE_CERTIFICATIONS, size, TYPE, condition);
	}
}
