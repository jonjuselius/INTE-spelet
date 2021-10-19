package Item;

public class Shield extends Item {
	
	public static final int WEIGHT = 100;
	public static final int VALUE = 1000;
	public static final String[] JOB_CERTIFICATIONS = {"Knight"};
	public static final String[] RACE_CERTIFICATIONS = {"Human", "Ogre", "Elf"};
	public static final Type TYPE = Type.ARMOR;
	public static final int DEFAULT_CONDITION = Item.MAX_CONDITION;
	public static final Size DEFAULT_SIZE = Size.MEDIUM;
	
	public Shield() {
		this(Item.MAX_CONDITION);
	}
	
	public Shield(int condition) {
		this(condition, DEFAULT_SIZE);
	}
	
	public Shield(Size size) {
		this(DEFAULT_CONDITION, size);
	}
	
	public Shield(int condition, Size size) {
		super(WEIGHT, VALUE, JOB_CERTIFICATIONS, RACE_CERTIFICATIONS, size, TYPE, condition);
	}
}
