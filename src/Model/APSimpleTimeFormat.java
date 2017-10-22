//ERROR COULD BE HERE

package Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Constants.TimeFormats;

public class APSimpleTimeFormat {
	private String textFieldValue;
	private String time;
	private String finalTime;
	private TimeFormats timeFormats;

	public APSimpleTimeFormat(String textFieldValue) {
		this.textFieldValue = textFieldValue;
		timeFormats = new TimeFormats();

	}

	/**
	 * If the time is in the 1am or 3pm format we return true,
	 * otherwise we return false
	 */
	public boolean isTimeInFormat(String textValue) {
		textValue = textValue.toLowerCase();
		Pattern patternTime = Pattern.compile(timeFormats.getTime_apSimple());
		Matcher matcherTime = patternTime.matcher(textValue);

		if (matcherTime.find()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * If we find a time we put it under the 24 hour: 59 minutes format
	 */
	public String time() throws ParseException {

		textFieldValue = textFieldValue.toLowerCase();
		Pattern pattern = Pattern.compile(timeFormats.getTime_apSimple());
		Matcher matcher = pattern.matcher(textFieldValue);
		if (matcher.find()) {
			time = matcher.group();
			// This here should convert the 12 hour format to a 24 hour format
			SimpleDateFormat timeFormat12 = new SimpleDateFormat("hh a"); 
			SimpleDateFormat timeFormat24 = new SimpleDateFormat("HH:mm"); 
			Date dateTime = timeFormat12.parse(time);
			finalTime = timeFormat24.format(dateTime);
		}
		return finalTime;
	}

	/**
	 * A getter method of the time before it is converted into the proper format
	 */
	public String getTime() {
		return time;
	}
}
