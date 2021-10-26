package Item;

import Map.Map;
import Map.MapPosition;

public class Ring extends Item {
	public static final int WEIGHT = 5;
	public static final int VALUE = 5000;
	public static final Type TYPE = Type.JEWELLERY;
	public static final Size DEFAULT_SIZE = Size.MEDIUM;
	public static final int DEFAULT_CONDITION = Item.MAX_CONDITION;
	public static final String[] JOB_CERTIFICATIONS = {"Knight", "Magician", "Healer"};
	public static final String[] RACE_CERTIFICATIONS = {"Human", "Ogre", "Elf"};
	
	public Ring() {
		this(DEFAULT_CONDITION);
	}
	
	public Ring(int condition) {
		this(condition, DEFAULT_SIZE);
	}
	
	public Ring(Size size) {
		this(DEFAULT_CONDITION, size);
	}
	
	public Ring(int condition, Size size) {
		super(WEIGHT, VALUE, JOB_CERTIFICATIONS, RACE_CERTIFICATIONS, size, TYPE, condition);
	}
}
