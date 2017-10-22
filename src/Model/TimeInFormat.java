//THIS WORKS

package Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Constants.TimeFormats;

public class TimeInFormat {

	private String textFieldValue;
	private String time;
	private String finalTime;
	private TimeFormats timeFormats;

	public TimeInFormat(String textFieldValue){
		
		this.textFieldValue = textFieldValue;
		timeFormats = new TimeFormats();
	}
	
	/**
	 * Checks if we found a time of the format 24 hours and minutes
	 */
	
	public boolean isTimeInFormat(String textValue){
		
	textValue = textValue.toLowerCase();
	
	Pattern patternTime= Pattern.compile(timeFormats.getTime_regular());
	Matcher matcherTime = patternTime.matcher(textValue);

	if (matcherTime.find()){
		 return true;
	 }else{
		 return false;
	 }
    }
	
	/**
	 * If we find a time we put it under the 24 hour format: HH:mm
	 */
	public String time() throws ParseException {
		

		textFieldValue = textFieldValue.toLowerCase();
		
		Pattern pattern= Pattern.compile(timeFormats.getTime_regular());
		Matcher matcher = pattern.matcher(textFieldValue);
		if (matcher.find()){
			time = matcher.group();
			
			SimpleDateFormat timeFormat1 = new SimpleDateFormat("HH:mm");
			Date dateTime = timeFormat1.parse(time);
			finalTime = timeFormat1.format(dateTime);
			return finalTime;
		}
		return finalTime;	
	}
	
	
	/**
	 * A getter method of the time before it is converted into the proper format
	 */
	
	public String getTime(){
		return time;
	}
}