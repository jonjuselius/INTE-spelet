package Item;

public abstract class Food extends Item {

	public static final Type TYPE = Type.FOOD;
	public static final int DEFAULT_CONDITION = Item.MAX_CONDITION;
	private boolean consumed;

	public Food(int weight, int value, String[] jobCertifications, String[] raceCerticiations, Size size) {
		super(weight, value, jobCertifications, raceCerticiations, size, TYPE, DEFAULT_CONDITION);
	}

	public boolean isConsumed() {
		return consumed;
	}

	public void consume() {
		if (consumed) {
			throw new IllegalStateException();
		}
		consumed = true;
	}
}
