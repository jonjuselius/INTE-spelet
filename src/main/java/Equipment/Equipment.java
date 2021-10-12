package Equipment;

public abstract class Equipment {
	public static final int MAX_CONDITION = 100;
	private int condition;
	
	public Equipment() {
		this.condition = MAX_CONDITION;
	}
	
	public int getCondition() {
		return condition;
	}
}
