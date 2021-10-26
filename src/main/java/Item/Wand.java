package Item;

import Map.Map;
import Map.MapPosition;

public class Wand extends Item {
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
