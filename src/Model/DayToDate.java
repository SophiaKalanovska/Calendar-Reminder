package Model;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayToDate {

	private static String day;
	private static int futuredayOfWeek;
	private static int daysTillTheDay;
	private String textFieldValue;
	private String regexp;
	private String regexp2;

	private SimpleDateFormat dateFormatter;
	private Calendar calTemp;

	public DayToDate(String textFieldValue) {

		this.textFieldValue = textFieldValue;
		regexp = ("next monday|next tuesday|next wednesday|next thursday|next friday|next saturday|next sunday");
		regexp2 = ("monday|tuesday|wednesday|thursday|friday|saturday|sunday");
	}
	
	/**
	 * If we find a date in the format 'Monday' or 'next Wednesday', we return true,
	 * otherwise we return false
	 */
	public boolean isItDay(String textValue) {
		textValue = textValue.toLowerCase();
		Pattern pattern = Pattern.compile(regexp);
		Pattern pattern2 = Pattern.compile(regexp2);
		Matcher matcher = pattern.matcher(textValue);
		Matcher matcher2 = pattern2.matcher(textValue);

		if (matcher.find() || matcher2.find()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * If we find a date of the previous format we convert it into the [Day][day number][month] format
	 */
	public String date() {
		textFieldValue = textFieldValue.toLowerCase();
		Calendar cal = Calendar.getInstance();
		Pattern pattern = Pattern.compile(regexp);
		Pattern pattern2 = Pattern.compile(regexp2);
		Matcher matcher = pattern.matcher(textFieldValue);
		Matcher matcher2 = pattern2.matcher(textFieldValue);

		if (matcher2.find()) {
			// day is the day on the week(Monday - Sunday)
			day = matcher2.group();
			// futuredayOfWeek is the number corresponding to the day
			futuredayOfWeek = getDayOfWeekAsInt(day);
			// dayOfWeek is the current day
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
			// if the current day is not in the current week
			if (dayOfWeek > futuredayOfWeek) {
				// result stores how many days till the end of the current week.
				int result = 7 - dayOfWeek;
				// days till the day is the days till the end of the current
				// week plus
				daysTillTheDay = futuredayOfWeek + result;

			} else if (dayOfWeek == futuredayOfWeek) {
				int result = 7 - dayOfWeek;
				daysTillTheDay = futuredayOfWeek + result;
			} else {
				daysTillTheDay = futuredayOfWeek - dayOfWeek;

			}
			if (matcher.find()) {
				calTemp = (Calendar) cal.clone();
				daysTillTheDay += 7;
				calTemp.add(Calendar.DAY_OF_WEEK, daysTillTheDay);
				DayOfWeek dayWanted = DayOfWeek.of(calTemp.get(Calendar.DAY_OF_WEEK));
				dayWanted = dayWanted.minus(1);
				dateFormatter = new SimpleDateFormat("EEEE d MMMM");
			} else {
				calTemp = (Calendar) cal.clone();
				calTemp.add(Calendar.DAY_OF_WEEK, daysTillTheDay);
				DayOfWeek dayWanted = DayOfWeek.of(calTemp.get(Calendar.DAY_OF_WEEK));
				dayWanted = dayWanted.minus(1);
				dateFormatter = new SimpleDateFormat("EEEE d MMMM");

			}

		}
		return dateFormatter.format(calTemp.getTime());
	}

	/*
	 * A method that takes a day and returns a number from 1 to 7 (Monday to
	 * Sunday)
	 */
	public static int getDayOfWeekAsInt(String day) {

		if (day == null) {
			return -1;
		}
		switch (day) {

		case "sunday":
			return 1;
		case "monday":
			return 2;
		case "tuesday":
			return 3;
		case "wednesday":
			return 4;
		case "thursday":
			return 5;
		case "friday":
			return 6;
		case "saturday":
			return 7;

		default:
			return -1;
		}
	}
	
	/**
	 * getter method of the day before it is converted to the correct format
	 */
	public String getDay() {
		return day;
	}
	
	/**
	 * getter method of the regular expression (Monday to Sunday)
	 */
	public String getRegexp() {
		return regexp;
	}
	
	/**
	 * getter method of the regular expression next(Monday to Sunday)
	 */
	public String getRegexp2() {
		return regexp2;
	}

}
