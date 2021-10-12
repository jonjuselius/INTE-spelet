package Equipment;

public abstract class Equipment {
	public static final int MAX_CONDITION = 100;
	public static final int MIN_CONDITION = 0;
	private int condition;
	
	public Equipment() {
		this.condition = MAX_CONDITION;
	}
	
	public Equipment(int condition) {
		if (condition < MIN_CONDITION || condition > MAX_CONDITION) {
			throw new IllegalArgumentException();
		}
		this.condition = condition;
	}
	
	public int getCondition() {
		return condition;
	}
}
