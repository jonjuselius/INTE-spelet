package Exceptions;

public class GainException extends IllegalArgumentException {
	@Override
	public String getMessage() {
		return "The item can't be gained!";
	}
}