package Exceptions;

public class GiveException extends IllegalArgumentException {
	@Override
	public String getMessage() {
		return "The item can't be given!";
	}
}