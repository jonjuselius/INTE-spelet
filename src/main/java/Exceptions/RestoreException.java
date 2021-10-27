package Exceptions;

public class RestoreException extends IllegalArgumentException {
	@Override
	public String getMessage() {
		return "The item can't be restored!";
	}
}