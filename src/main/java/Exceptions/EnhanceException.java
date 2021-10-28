package Exceptions;

public class EnhanceException extends IllegalArgumentException {
	@Override
	public String getMessage() {
		return "The item can't be enhanced!";
	}
}