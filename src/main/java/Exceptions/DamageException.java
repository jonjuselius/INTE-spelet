package Exceptions;

public class DamageException extends IllegalArgumentException {
	@Override
	public String getMessage() {
		return "The item can't be damaged!";
	}
}