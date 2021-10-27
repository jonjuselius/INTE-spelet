package Inventory;

public class Wallet {
	
	private int amount;
	
	public int getAmount() {
		return amount;
	}
	
	public void gain(int money) {
		if (money < 0) {
			throw new IllegalArgumentException("You can't add a negative amount to a wallet!");
		}
		setAmount(getAmount() + money);
	}
	
	public void lose(int money) {
		if (money < 0) {
			throw new IllegalArgumentException("You can't lose a negative amount from a wallet!");
		}
		setAmount(getAmount() - money);
	}
	
	private void setAmount(int money) {
		this.amount = money;
	}
}
