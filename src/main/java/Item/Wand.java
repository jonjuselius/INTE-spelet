package Item;

public class Wand extends Equipment {
	
	public static final int WAND_WEIGHT = 5;
	public static final int WAND_VALUE = 500;
	
	public Wand() {
		this(Equipment.MAX_CONDITION);
	}
	
	public Wand(int condition) {
		super(WAND_WEIGHT, WAND_VALUE, condition);
	}
}
