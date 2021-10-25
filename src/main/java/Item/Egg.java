package Item;

import Map.GameMapPosition;

public class Egg extends Food {
	/**
	 * An egg is an instantiable class of food, which is a type of item.
	 * Eggs can be used by every character, irregardless of job and race.
	 * The purpose of eggs is the same as for other type of foods, namely
	 * to eat in order to satisfy one's hunger, and perhaps restoring some
	 * health by doing so.
	 */
	public static final int WEIGHT = 2;
	public static final int VALUE = 10;
	public static final Size DEFAULT_SIZE = Size.MEDIUM;
	public static final int DEFAULT_CONDITION = Item.MAX_CONDITION;
	public static final String[] JOB_CERTIFICATIONS = {"Knight", "Magician", "Healer"};
	public static final String[] RACE_CERTIFICATIONS = {"Human", "Ogre", "Elf"};
	
	public Egg(GameMapPosition mapPosition) {
		this(DEFAULT_CONDITION, DEFAULT_SIZE, mapPosition);
	}
	
	public Egg(int condition, GameMapPosition mapPosition) {
		this(condition, DEFAULT_SIZE, mapPosition);
	}
	
	public Egg(Size size, GameMapPosition mapPosition) {
		this(DEFAULT_CONDITION, size, mapPosition);
	}
	
	public Egg(int condition, Size size, GameMapPosition mapPosition) {
		super(WEIGHT, VALUE, JOB_CERTIFICATIONS, RACE_CERTIFICATIONS, size, condition, mapPosition);
	}
}
