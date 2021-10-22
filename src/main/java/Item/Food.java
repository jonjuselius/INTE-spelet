package Item;

public abstract class Food extends Item {
	/**
	 * Food is an abstract noninstantiable item. An example of food is an egg.
	 * Unlike other type of items, food can be consumed. There is also possible
	 * to check if food has been consumed or not. The purpose of food is to eat,
	 * and by eating there is a possibility that health could be restored.
	 *
	 * isConsumed: Checks if the food has been eaten or not
	 * consume: The food becomes consumed, unless it already has been consumed.
	 */
	public static final Type TYPE = Type.FOOD;
	private boolean consumed;

	public Food(int weight, int value, String[] jobCertifications, String[] raceCerticiations, Size size, int condition) {
		super(weight, value, jobCertifications, raceCerticiations, size, TYPE, condition);
	}

	public boolean isConsumed() {
		return consumed;
	}

	public void consume() {
		if (consumed) {
			throw new IllegalStateException();
		}
		consumed = true;
	} // ska foodklassen ens ansvara för detta?
}
