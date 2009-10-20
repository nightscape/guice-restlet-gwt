package org.restlet.engine.http;

import java.util.Date;

public interface DateHelper {

	/**
	 * Formats a date as a header string.
	 * 
	 * @param date
	 *            The date to format.
	 * @param cookie
	 *            Indicates if the date should be in the cookie format.
	 * @return The formatted date.
	 */
	public abstract String formatDate(Date date, boolean cookie);

	/**
	 * Parses a date string.
	 * 
	 * @param date
	 *            The date string to parse.
	 * @param cookie
	 *            Indicates if the date is in the cookie format.
	 * @return The parsed date.
	 */
	public abstract Date parseDate(String date, boolean cookie);

}