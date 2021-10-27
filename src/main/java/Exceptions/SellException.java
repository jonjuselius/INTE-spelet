package Exceptions;

public class SellException extends IllegalArgumentException {
	@Override
	public String getMessage() {
		return "The item can't be sold!";
	}
}