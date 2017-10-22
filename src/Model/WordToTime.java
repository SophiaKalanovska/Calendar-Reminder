package Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordToTime {

	private String textFieldValue;
	private String time;

	public WordToTime(String textFieldValue) {
		this.textFieldValue = textFieldValue;

	}

	/**
	 * Checks if the user entered to words "evening" or "morning"
	 */
	public boolean isItTime(String textValue) {
		textValue = textValue.toLowerCase();
		// Searching if the user entered the keywords "evening" or "morning"
		Pattern eveningPattern = Pattern.compile("evening");
		Pattern morningPattern = Pattern.compile("morning");
		Matcher eveningMatcher = eveningPattern.matcher(textValue);
		Matcher morningMatcher = morningPattern.matcher(textValue);

		//If the word 'evening' is entered we return true
		if (eveningMatcher.find()) {
			return true;
		}
		// If the word 'morning' is entered we return true
		else if (morningMatcher.find()) {
			return true;
		// any other case return false
		} else {
			return false;
		}

	}

	/**
	 * Converts 'morning' or 'evening' into 20:00 or 09:00
	 */
	public String theTime() {
		textFieldValue = textFieldValue.toLowerCase();
		Pattern eveningPattern = Pattern.compile("evening");
		Pattern morningPattern = Pattern.compile("morning");
		Matcher eveningMatcher = eveningPattern.matcher(textFieldValue);
		Matcher morningMatcher = morningPattern.matcher(textFieldValue);

		// If we find the evening we convert to the string "20:00"
		if (eveningMatcher.find()) {
			time = "20:00";

		// If we find 'morning' we convert it to the string "09:00"
		} else if (morningMatcher.find()) {
			time = "09:00";

		}
		return time;
	}

}