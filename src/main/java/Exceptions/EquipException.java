package Exceptions;

public class EquipException extends IllegalArgumentException {
	@Override
	public String getMessage() {
		return "The item can't be equipped!";
	}
}