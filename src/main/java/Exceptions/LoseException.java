package Exceptions;

public class LoseException extends IllegalArgumentException {
	@Override
	public String getMessage() {
		return "The item can't be lost!";
	}
}