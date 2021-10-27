package Exceptions;

public class UnequipException extends IllegalArgumentException {
	@Override
	public String getMessage() {
		return "The item can't be unequipped!";
	}
}