package Item;

public class Sword extends Equipment {
	
	public static final int SWORD_WEIGHT = 10;
	public static final int SWORD_VALUE = 200;
	
	public Sword() {
		this(Equipment.MAX_CONDITION);
	}
	
	public Sword(int condition) {
		super(SWORD_WEIGHT, SWORD_VALUE, condition);
	}
}
