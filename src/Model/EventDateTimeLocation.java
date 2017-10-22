package Model;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Constants.TimeFormats;

public class EventDateTimeLocation {
	// create instances of all date and time classes
	private String textFieldValue;
	private DayToDate dayToDate;
	private DateToDate dateToDate;
	private DateInFormat dateInFormat;
	private WordToTime wordToTime;
	private TimeInFormat timeInFormat;
	private APSimpleTimeFormat apSimpleTimeFormat;
	private TimeInFormatRegularAP timeInFormatRegularAP;
	private TimeFormats timeFormat;
	private String wordAfter2;
	private String event;
	private String big;
	private String small;

	/**
	 * Initialise them and give them a argument textField value
	 */
	public EventDateTimeLocation(String textFieldValue) {
		this.textFieldValue = textFieldValue;
		dayToDate = new DayToDate(textFieldValue);
		dateToDate = new DateToDate(textFieldValue);
		dateInFormat = new DateInFormat(textFieldValue);
		wordToTime = new WordToTime(textFieldValue);
		timeInFormat = new TimeInFormat(textFieldValue);
		timeInFormatRegularAP = new TimeInFormatRegularAP(textFieldValue);
		apSimpleTimeFormat = new APSimpleTimeFormat(textFieldValue);
		timeFormat = new TimeFormats();

	}

	/**
	 * check which format of the date is in the textField and return it when you
	 * find it if the method does not find any of the formats return -
	 */
	public String date() {
		if (dayToDate.isItDay(textFieldValue)) {
			if (dateInFormat.isDateInFormat(textFieldValue)) {
				return (dateInFormat.date());
			} else {
				return (dayToDate.date());
			}
		}

		else if (dateToDate.isItDate(textFieldValue)) {
			try {
				return (dateToDate.date());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		return "-";
	}

	/**
	 * check which format of the time is in the textField and return it when you
	 * find it if the method does not find any of the formats return -
	 */
	public String time() {
		if (wordToTime.isItTime(textFieldValue)) {
			return (wordToTime.theTime());
		}

		else if (timeInFormat.isTimeInFormat(textFieldValue)) {
			if (timeInFormatRegularAP.isTimeInFormat(textFieldValue)) {
				try {
					return (timeInFormatRegularAP.time());
				} catch (ParseException e1) {

					e1.printStackTrace();
				}
			} else {
				try {
					return (timeInFormat.time());
				} catch (ParseException e1) {

					e1.printStackTrace();
				}
			}
		}

		else if (apSimpleTimeFormat.isTimeInFormat(textFieldValue)) {
			try {
				return (apSimpleTimeFormat.time());
			} catch (ParseException e1) {

				e1.printStackTrace();
			}
		}
		return "-";
	}

	/**
	 * check if the user has inputed a string that contains "at" and contains only letters after
	 */
	public String location() {

		textFieldValue = textFieldValue.toLowerCase();

		String locSuffix = "at\\s+[a-zA-Z]*";
		Pattern checkSuffix = Pattern.compile(locSuffix);
		Matcher matchSuffix = checkSuffix.matcher(textFieldValue);
		String location = "";
		while (matchSuffix.find()) {
			location += matchSuffix.group();
		}
		return location.replaceAll("at\\s", "");

	}

	/**
	 * the event is everything that is not specified as date, time or location
	 * the method gets all unnecessary prefixes away
	 */
	public String event() {

		String testtext = textFieldValue;
		testtext = testtext.toLowerCase();

		if (!(location().equals(""))) {
			testtext = testtext.replace(location().toLowerCase(), "");
		}

		if (!(date().equals("-"))) {
			if (dateToDate.isItDate(testtext)) {

				testtext = testtext.replace(dateToDate.getDate(), "");

			}

			if (dayToDate.isItDay(testtext)) {
				if (dateInFormat.isDateInFormat(testtext)) {
					testtext = testtext.replace(dateInFormat.date().toLowerCase(), "");
				} else {
					testtext = testtext.replace(dayToDate.getDay(), "");
					testtext = testtext.replace("next", "");
				}

			} else {

				String substr = date();
				testtext = testtext.replace(substr, "");

			}

		}

		if (!(time().equals("-"))) {

			if (wordToTime.isItTime(testtext)) {
				if (wordToTime.theTime().equals("20:00")) {
					testtext = testtext.replace("in the evening", "");
					testtext = testtext.replace("evening", "");
				}
				if (wordToTime.theTime().equals("09:00")) {
					testtext = testtext.replace("in the morning", "");
					testtext = testtext.replace("morning", "");
				}
			} else {

				if (timeInFormat.isTimeInFormat(testtext)) {
					try {
						testtext = testtext.replace(timeInFormat.time(), "");
					} catch (ParseException e) {
						e.printStackTrace();
					}
					testtext = testtext.replace("am", "");
					testtext = testtext.replace("pm", "");

				} else {
					testtext = testtext.replace(apSimpleTimeFormat.getTime(), "");

				}

			}
		}

		String[] message = testtext.split(" ");
		event = "";

		for (int i = 0; i < message.length; i++) {
			if (message[i].equals("at")) {
				message[i] = "";
			} else if (message[i].equals("on")) {
				message[i] = "";
			} else if (message[i].equals("pm") || message[i].equals("am")) {
				message[i] = "";
			} else if (message[i].equals(timeFormat.getTime_ap())) {
				message[i] = "";
			} else {

				event += message[i] + " ";

			}

		}
		String[] parts = event.split(" ");
		for (int i = 0; i < parts.length; i++) {
			if (!(parts[i].equals(""))) {
				small = parts[i];
				big = parts[i].substring(0, 1).toUpperCase() + parts[i].substring(1);
				event = event.replace(small, big);
				break;
			}
		}
		event = event.replace("  ", "");

		return event;
	}
}