/**
 * 
 */
package org.restlet.engine.http;

import java.util.Date;

import org.restlet.engine.util.DateUtils;

public class DateHelperImpl implements DateHelper {
	/* (non-Javadoc)
	 * @see org.restlet.engine.http.DateHelper#formatDate(java.util.Date, boolean)
	 */
	public String formatDate(Date date, boolean cookie) {
		if (cookie) {
			return DateUtils.format(date, DateUtils.FORMAT_RFC_1036.get(0));
		} else {
			return DateUtils.format(date, DateUtils.FORMAT_RFC_1123.get(0));
		}
	}

	/* (non-Javadoc)
	 * @see org.restlet.engine.http.DateHelper#parseDate(java.lang.String, boolean)
	 */
	public Date parseDate(String date, boolean cookie) {
		if (cookie) {
			return DateUtils.parse(date, DateUtils.FORMAT_RFC_1036);
		} else {
			return DateUtils.parse(date, DateUtils.FORMAT_RFC_1123);
		}
	}
}