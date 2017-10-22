package Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Constants.Constants;

public class DateInFormat {

	private String textFieldValue;
	private String date;
	private Constants constants;
	private String regexp;

	public DateInFormat(String textFieldValue) {
		this.textFieldValue = textFieldValue;
		constants = new Constants();
		regexp = "(((" + constants.getDays_t() + "\\s*" + constants.getDays_d_a() + ")|(" + constants.getDays_t()
				+ "\\s*" + constants.getDays_d() + "))[,\\s]+" + constants.getMonths_t() + ")";
	}
	
	/**
	 * If we find a date of the format Monday 22nd or Monday 22nd March we return true,
	 * otherwise we return false
	 */
	public boolean isDateInFormat(String textValue) {
		textValue = textValue.toLowerCase();
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(textValue);

		if (matcher.find()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * We convert the date into the format [day][day number][month]
	 */
	public String date() {
		textFieldValue = textFieldValue.toLowerCase();
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(textFieldValue);
		if (matcher.find()) {
			String low = matcher.group();
			String[] parts = low.split(" ");

			String day = parts[0].substring(0, 1).toUpperCase() + parts[0].substring(1);

			if (Integer.parseInt(parts[1].substring(0, 1)) == 0) {
				parts[1] = parts[1].substring(1);
			}
			// when the user inputs without th then it is not added
			String month = parts[2].substring(0, 1).toUpperCase() + parts[2].substring(1);
			;

			date = day + " " + parts[1] + " " + month;
		}
		return date;

	}

	/**
	 * A getter method of the regular expression
	 */
	public String getRegexp() {
		return regexp;
	}
}
