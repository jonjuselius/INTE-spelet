package Item;

public class Sword extends Equipment {
	
	public static final int WEIGHT = 10;
	public static final int VALUE = 200;
	public static final String[] JOB_CERTIFICATIONS = {"Knight"};
	public static final String[] RACE_CERTIFICATIONS = {"Human", "Ogre", "Elf"};
	
	public Sword() {
		this(Equipment.MAX_CONDITION);
	}
	
	public Sword(int condition) {
		super(WEIGHT, VALUE, condition, JOB_CERTIFICATIONS, RACE_CERTIFICATIONS);
	}
}
