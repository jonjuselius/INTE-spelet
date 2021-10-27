package Inventory;

public class Wallet {
	
	private int amount;
	
	public int getAmount() {
		return amount;
	}
	
	public void gain(int money) {
		setAmount(getAmount() + money);
	}
	
	public void lose(int money) {
		setAmount(getAmount() - money);
	}
	
	private void setAmount(int money) {
		this.amount = money;
	}
}
