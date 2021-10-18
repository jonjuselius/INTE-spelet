package Item;

public class Sword extends Equipment {
	
	public static final int WEIGHT = 10;
	public static final int VALUE = 200;
	public static final String[] JOB_CERTIFICATIONS = {"Knight"};
	public static final String[] RACE_CERTIFICATIONS = {"Human", "Ogre", "Elf"};
	public static final Type TYPE = Type.WEAPON;
	public static final int DEFAULT_CONDITION = Equipment.MAX_CONDITION;
	public static final Size DEFAULT_SIZE = Size.MEDIUM;
	
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
		super(WEIGHT, VALUE, condition, JOB_CERTIFICATIONS, RACE_CERTIFICATIONS, TYPE, size);
	}
}
