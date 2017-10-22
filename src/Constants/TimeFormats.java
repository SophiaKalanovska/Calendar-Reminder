
package Constants;

public class TimeFormats {

	private final String time_regular; // Time under the 24 hour format: 19:34
	private final String time_apWithDDot; // Time under the am/pm format: 03:56 am or 7:23 pm
	private final String time_apSimple; //Time under the am/pm format 1pm or 1am
	private final String time_ap;
	
	public TimeFormats() {
		
		time_regular = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
		time_apWithDDot = "(" + time_regular + "(\\s)" + "(am|pm))";
		time_ap = "([1-9])|10|11|12)";
		time_apSimple = "(" + "(1[0-2]|0?[1-9])" + "(\\s)" + "(am|pm))";
		
	}
	
	/**
	 * getter method of the regular expression (time in format 00:00)
	 */
	public String getTime_regular() {
		return time_regular;
	}

	/**
	 * getter method of the regular expression (time in format 00:00 am/pm)
	 */
	public String getTime_apWithDDot() {
		return time_apWithDDot;
	}

	/**
	 * getter method of the regular expression (time in format 1-12)
	 */
	public String getTime_apSimple() {
		return time_apSimple;
	}

	/**
	 * getter method of the regular expression (time in format 1-12 am/pm)
	 */
	public String getTime_ap() {
		return time_ap;
	}
	
}