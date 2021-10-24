package Item;

import Map.Map;
import Map.MapPosition;

public class Sword extends Item {
	/**
	 * Sword is weapon that can be used by knights, irregardless of race. It is
	 * an instantiable subclass of Item. The default size of a sword is medium,
	 * and the default condition for a new sword is perfect condition. A sword
	 * has a weight of 10 units and a value of 200 units. The purpose of a sword
	 * is to deal damage to enemies.
	 */
	public static final int WEIGHT = 10;
	public static final int VALUE = 200;
	public static final Type TYPE = Type.WEAPON;
	public static final Size DEFAULT_SIZE = Size.MEDIUM;
	public static final int DEFAULT_CONDITION = Item.MAX_CONDITION;
	public static final String[] JOB_CERTIFICATIONS = {"Knight"};
	public static final String[] RACE_CERTIFICATIONS = {"Human", "Ogre", "Elf"};
	
	public Sword(MapPosition mapPosition) {
		this(DEFAULT_CONDITION, DEFAULT_SIZE, mapPosition);
	}
	
	public Sword(int condition, MapPosition mapPosition) {
		this(condition, DEFAULT_SIZE, mapPosition);
	}
	
	public Sword(Size size, MapPosition mapPosition) {
		this(DEFAULT_CONDITION, size, mapPosition);
	}
	
	public Sword(int condition, Size size, MapPosition mapPosition) {
		super(WEIGHT, VALUE, JOB_CERTIFICATIONS, RACE_CERTIFICATIONS, size, TYPE, condition, mapPosition);
	}
	
}
