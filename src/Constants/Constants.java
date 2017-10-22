package Constants;

public class Constants {
	private final String months_t;
	private final String days_t;
	private final String days_d_a;
	private final String months_d;
	private final String days_d;
	private final String year_d;

	public Constants() {
		months_t = "(january|february|march|april|may|june|july|august|september|october|november|december)";
		days_t = "(monday|tuesday|wednesday|thursday|friday|saturday|sunday)";
		days_d = "(3[01]|[12][0-9]|0?[1-9])";
		days_d_a = "(" + days_d + "(th|rd|nd|st)?)";
		months_d = "(1[012]|0?[1-9])";
		year_d = "((19|20)\\d\\d)";
	}

	/**
	 * Depending on it's place in the month, the day will have a different
	 * suffix : eg. 1st or 11th or 22nd.
	 */
	public String getDayOfMonthSuffix(int n) {
		if (n >= 1 && n <= 31) {
			if (n >= 11 && n <= 13) {
				return "th";
			}
			switch (n % 10) {
			case 1:
				return "st";
			case 2:
				return "nd";
			case 3:
				return "rd";
			default:
				return "th";
			}
		}
		return "th";
	}

	/**
	 * getter method of the regular expression (January to December)
	 */
	public String getMonths_t() {
		return months_t;
	}

	/**
	 * getter method of the regular expression (Monday to Sunday)
	 */
	public String getDays_t() {
		return days_t;
	}

	/**
	 * getter method of the regular expression (days of month with suffix)
	 */
	public String getDays_d_a() {
		return days_d_a;
	}
	
	/**
	 * getter method of the regular expression (days of month)
	 */
	public String getDays_d() {
		return days_d;
	}

	/**
	 * getter method of the regular expression (months, eg: 1-12)
	 */
	public String getMonths_d() {
		return months_d;
	}


	/**
	 * getter method of the regular expression (years either in 20th and 21st century)
	 */
	public String getYear_d() {
		return year_d;
	}

}
