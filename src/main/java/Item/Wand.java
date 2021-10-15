package Item;

public class Wand extends Equipment {
	
	public static final int WEIGHT = 5;
	public static final int VALUE = 500;
	public static final String[] JOB_CERTIFICATIONS = {"Magician", "Healer"};
	public static final String[] RACE_CERTIFICATIONS = {"Human", "Ogre", "Elf"};
	public static final Type TYPE = Type.WEAPON;
	public static final int DEFAULT_CONDITION = Equipment.MAX_CONDITION;
	public static final Size DEFAULT_SIZE = Size.MEDIUM;
	
	public Wand() {
		this(Equipment.MAX_CONDITION);
	}
	
	public Wand(int condition) {
		this(condition, DEFAULT_SIZE);
	}
	
	public Wand(Size size) {
		this(DEFAULT_CONDITION, size);
	}
	
	public Wand(int condition, Size size) {
		super(WEIGHT, VALUE, condition, JOB_CERTIFICATIONS, RACE_CERTIFICATIONS, TYPE, size);
	}
}
