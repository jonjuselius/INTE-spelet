package Exceptions;

public class UseException extends IllegalArgumentException {
	@Override
	public String getMessage() {
		return "Item can't be used!";
	}
}