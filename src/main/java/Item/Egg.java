package Item;

public class Egg extends Food {
	public static final int WEIGHT = 2;
	public static final int VALUE = 10;
	public static final String[] JOB_CERTIFICATIONS = {"Knight", "Magician", "Healer"};
	public static final String[] RACE_CERTIFICATIONS = {"Human", "Ogre", "Elf"};
	public static final Size DEFAULT_SIZE = Size.MEDIUM;
	
	public Egg() {
		this(DEFAULT_SIZE);
	}
	
	public Egg(Size size) {
		super(WEIGHT, VALUE, JOB_CERTIFICATIONS, RACE_CERTIFICATIONS, size);
	}
}
