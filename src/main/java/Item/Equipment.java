package Item;

public abstract class Equipment extends Item implements Equippable {
	
	public static final int MAX_CONDITION = 100;
	public static final int MIN_CONDITION = 0;
	private int condition;
	private boolean equipped;
	private Type type;
	
	public Equipment(int weight, int value, int condition, String[] jobCertifications, String[] raceCertifications, Type type, Size size) {
		super(weight, value, jobCertifications, raceCertifications, size);
		if (condition < MIN_CONDITION || condition > MAX_CONDITION) {
			throw new IllegalArgumentException();
		}
		this.condition = condition;
		this.equipped = false;
		this.type = type;
	}
	
	public int getCondition() {
		return condition;
	}
	
	@Override
	public boolean isEquipped() {
		return equipped;
	}
	
	public Type getType() {
		return type;
	}
	
	@Override
	public void equip() {
		if (equipped) {
			throw new IllegalStateException();
		}
		equipped = true;
	}
	
	@Override
	public void unequip() {
		if (!equipped) {
			throw new IllegalStateException();
		}
		equipped = false;
	}
}
