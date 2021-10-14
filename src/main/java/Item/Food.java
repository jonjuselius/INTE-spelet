package Item;

public abstract class Food extends Item implements Consumable {
	
	private boolean consumed;
	
	public Food(int weight, int value) {
		super(weight, value);
	}
	
	@Override
	public boolean isConsumed() {
		return consumed;
	}
	
	@Override
	public void consume() {
		if (consumed) {
			throw new IllegalStateException();
		}
		consumed = true;
	}
}
