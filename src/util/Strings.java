package util;

public class Strings {
	
	public static final String EMPTY = "";

	public static boolean isNullOrEmpty(String input) {
		return input == null || input.equals(EMPTY);
	}
}

