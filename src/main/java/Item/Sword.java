package Item;

public class Sword extends Item {
	public static final int WEIGHT = 10;
	public static final int VALUE = 200;
	public static final Type TYPE = Type.WEAPON;
	public static final Size DEFAULT_SIZE = Size.MEDIUM;
	public static final int DEFAULT_CONDITION = Item.MAX_CONDITION;
	public static final String[] JOB_CERTIFICATIONS = {"Knight"};
	public static final String[] RACE_CERTIFICATIONS = {"Human", "Ogre", "Elf"};
	
	public Sword() {
		this(DEFAULT_CONDITION, DEFAULT_SIZE);
	}
	
	public Sword(int condition) {
		this(condition, DEFAULT_SIZE);
	}
	
	public Sword(Size size) {
		this(DEFAULT_CONDITION, size);
	}
	
	public Sword(int condition, Size size) {
		super(WEIGHT, VALUE, JOB_CERTIFICATIONS, RACE_CERTIFICATIONS, size, TYPE, condition);
	}
	
}
