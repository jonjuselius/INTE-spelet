package Exceptions;

public class BuyException extends IllegalArgumentException {
	@Override
	public String getMessage() {
		return "The item can't be bought!";
	}
}