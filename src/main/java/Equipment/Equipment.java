package Equipment;

public abstract class Equipment {
	private int condition;
	
	public Equipment() {
		this.condition = 100;
	}
	
	public int getCondition() {
		return condition;
	}
}
