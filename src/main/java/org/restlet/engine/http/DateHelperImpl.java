/**
 * 
 */
package org.restlet.engine.http;

import java.util.Date;

import org.restlet.engine.util.DateUtils;

import com.google.inject.Inject;

public class DateHelperImpl implements DateHelper {
	private final DateUtils dateUtils;
	
	@Inject
	public DateHelperImpl(DateUtils dateUtils) {
		this.dateUtils = dateUtils;
	}

	/* (non-Javadoc)
	 * @see org.restlet.engine.http.DateHelper#formatDate(java.util.Date, boolean)
	 */
	public String formatDate(Date date, boolean cookie) {
		if (cookie) {
			return dateUtils.format(date, DateUtils.FORMAT_RFC_1036.get(0));
		} else {
			return dateUtils.format(date, DateUtils.FORMAT_RFC_1123.get(0));
		}
	}

	/* (non-Javadoc)
	 * @see org.restlet.engine.http.DateHelper#parseDate(java.lang.String, boolean)
	 */
	public Date parseDate(String date, boolean cookie) {
		if (cookie) {
			return dateUtils.parse(date, DateUtils.FORMAT_RFC_1036);
		} else {
			return dateUtils.parse(date, DateUtils.FORMAT_RFC_1123);
		}
	}
}