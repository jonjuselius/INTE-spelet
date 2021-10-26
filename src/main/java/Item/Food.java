package Item;

import Map.Map;
import Map.MapPosition;

public abstract class Food extends Item {
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
