package Item;

public class Wand extends Equipment {
	
	public static final int WEIGHT = 5;
	public static final int VALUE = 500;
	public static final String[] JOB_CERTIFICATIONS = {"Magician", "Healer"};
	public static final String[] RACE_CERTIFICATIONS = {"Human", "Ogre", "Elf"};
	
	public Wand() {
		this(Equipment.MAX_CONDITION);
	}
	
	public Wand(int condition) {
		super(WEIGHT, VALUE, condition, JOB_CERTIFICATIONS, RACE_CERTIFICATIONS);
	}
}
