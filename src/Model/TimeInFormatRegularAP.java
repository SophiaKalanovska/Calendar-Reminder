//ERROR IS POSSIBLY HERE

package Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Constants.TimeFormats;

public class TimeInFormatRegularAP {
	private String textFieldValue;
	private String time;
	private String finalTime;
	private TimeFormats timeFormats;

	public TimeInFormatRegularAP(String textFieldValue) {
		this.textFieldValue = textFieldValue;
		timeFormats = new TimeFormats();

	}

	/**
	 * If we find a time of the 12 hour format we return true, otherwise we
	 * return false
	 */
	public boolean isTimeInFormat(String textValue) {
		textValue = textValue.toLowerCase();
		Pattern patternTime = Pattern.compile(timeFormats.getTime_apWithDDot());
		Matcher matcherTime = patternTime.matcher(textValue);

		if (matcherTime.find()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * If we find a time we put it under the 24 hour format: 19:34
	 */
	public String time() throws ParseException {
		textFieldValue = textFieldValue.toLowerCase();
		Pattern pattern = Pattern.compile(timeFormats.getTime_apWithDDot());
		Matcher matcher = pattern.matcher(textFieldValue);
		if (matcher.find()) {
			time = matcher.group();

			// This here should convert the 12 hour format to a 24 hour format
			SimpleDateFormat timeFormat12 = new SimpleDateFormat("hh:mm a"); // This
																				// is
																				// normally
																				// under
																				// 11:12
																				// am/pm
																				// format
			SimpleDateFormat timeFormat24 = new SimpleDateFormat("HH:mm"); // and
																			// this
																			// is
																			// 24
																			// hour
																			// format
			Date dateTime = timeFormat12.parse(time);
			finalTime = timeFormat24.format(dateTime);
			return finalTime;
		}
		return time;
	}

	public String getTime() {
		return time;
	}
}