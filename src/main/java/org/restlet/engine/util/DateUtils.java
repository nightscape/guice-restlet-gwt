package org.restlet.engine.util;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public interface DateUtils {

	/** Preferred HTTP date format (RFC 1123). */
	public static final List<String>	FORMAT_RFC_1123	= Arrays.asList("EEE, dd MMM yyyy HH:mm:ss zzz");
	/** Obsoleted HTTP date format (RFC 1036). */
	public static final List<String>	FORMAT_RFC_1036	= Arrays.asList("EEEE, dd-MMM-yy HH:mm:ss zzz");
	/** Obsoleted HTTP date format (ANSI C asctime() format). */
	public static final List<String>	FORMAT_ASC_TIME	= Arrays.asList("EEE MMM dd HH:mm:ss yyyy");
	/** W3C date format (RFC 3339). */
	public static final List<String>	FORMAT_RFC_3339	= Arrays.asList(
																"yyyy-MM-dd'T'HH:mm:ssz",
																"yyyy-MM-dd'T'HH:mmz",
																"yyyy-MM-dd",
																"yyyy-MM",
																"yyyy");
	/** Common date format (RFC 822). */
	public static final List<String>	FORMAT_RFC_822	= Arrays.asList(
																"EEE, dd MMM yy HH:mm:ss z",
																"EEE, dd MMM yy HH:mm z",
																"dd MMM yy HH:mm:ss z",
																"dd MMM yy HH:mm z");

	/**
	 * Compares two date with a precision of one second.
	 * 
	 * @param baseDate
	 *            The base date
	 * @param afterDate
	 *            The date supposed to be after.
	 * @return True if the afterDate is indeed after the baseDate.
	 */
	public abstract boolean after(final Date baseDate, final Date afterDate);

	/**
	 * Compares two date with a precision of one second.
	 * 
	 * @param baseDate
	 *            The base date
	 * @param beforeDate
	 *            The date supposed to be before.
	 * @return True if the beforeDate is indeed before the baseDate.
	 */
	public abstract boolean before(final Date baseDate, final Date beforeDate);

	/**
	 * Compares two date with a precision of one second.
	 * 
	 * @param baseDate
	 *            The base date
	 * @param otherDate
	 *            The other date supposed to be equals.
	 * @return True if both dates are equals.
	 */
	public abstract boolean equals(final Date baseDate, final Date otherDate);

	/**
	 * Formats a Date according to the first format in the array.
	 * 
	 * @param date
	 *            The date to format.
	 * @param format
	 *            The date format to use.
	 * @return The formatted date.
	 */
	public abstract String format(final Date date, final String format);

	/**
	 * Parses a formatted date into a Date object.
	 * 
	 * @param date
	 *            The date to parse.
	 * @param formats
	 *            The date formats to use sorted by completeness.
	 * @return The parsed date.
	 */
	public abstract Date parse(final String date, final List<String> formats);

	/**
	 * Returns an immutable version of a given date.
	 * 
	 * @param date
	 *            The modifiable date.
	 * @return An immutable version of a given date.
	 */
	public abstract Date unmodifiable(Date date);

}