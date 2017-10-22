package Model;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import Constants.Constants;

public class DateToDate {

	private String textFieldValue;
	private String date;
	private String finalDate;
	private String finalDay;
	private Constants constants;
	private String regexp;

	public DateToDate(String textFieldValue) {
		this.textFieldValue = textFieldValue;
		constants = new Constants();
		regexp = "(" + constants.getDays_d() + "[/-]" + constants.getMonths_d() + "[/-]" + constants.getYear_d() + ")";
	}
	
	/**
	 * If we find a date of the format days/-months-/year ( 21/01/1998 or 21-01-1998 ) we return true,
	 * otherwise we return false
	 */
	public boolean isItDate(String textValue) {
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
	 * If the method finds a date of the format days/-months-/year ( 21/01/1998 or 21-01-1998 ) 
	 * it converts it into the proper format
	 */
	public String date() throws ParseException {

		textFieldValue = textFieldValue.toLowerCase();
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(textFieldValue);
		if (matcher.find()) {
			date = matcher.group();
			String[] parts = date.split("[/-]");

			String day = parts[0];
			String month = parts[1];

			if (Integer.parseInt(parts[0].substring(0, 1)) == 0) {
				day = parts[0].substring(1);
			}

			// Prints the month
			String finalMonth = getMonth(Integer.parseInt(month));

			// print day of the week
			String dayOfWeek = getDayOfWeek(date);

			finalDate = dayOfWeek + " " + day + " " + finalMonth;

		}
		return finalDate;
	}

	public String getMonth(int month) {
		return new DateFormatSymbols().getMonths()[month - 1];
	}

	public String getDayOfWeek(String date) throws ParseException {
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
		Date dt1 = format1.parse(date);
		DateFormat format2 = new SimpleDateFormat("EEEE");
		finalDay = format2.format(dt1);
		return finalDay;
	}

	/**
	 * A getter method of the date before it is converted into the proper format
	 */
	public String getDate() {
		return date;
	}

}
