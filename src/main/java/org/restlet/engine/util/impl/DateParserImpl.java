package org.restlet.engine.util.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.restlet.engine.util.DateParser;

public class DateParserImpl implements DateParser {
	@Override
	public Date parseDate(String date, String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);

		try {
			return dateFormat.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
}