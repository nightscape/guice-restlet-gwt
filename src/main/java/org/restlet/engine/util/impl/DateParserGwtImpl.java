package org.restlet.engine.util.impl;

import java.util.Date;

import org.restlet.engine.util.DateParser;

import com.google.gwt.i18n.client.DateTimeFormat;

public class DateParserGwtImpl implements DateParser {

	@Override
	public Date parseDate(String date, String format) {
        /*
         * GWT difference: DateTimeFormat parser is is not passed a Locale
         * in the same way as SimpleDateFormat. It derives locale
         * information from the GWT application's locale.
         * 
         * Default timezone is GMT unless specified via a GMT:hhmm,
         * GMT:+hhmm, or GMT:-hhmm string.
         */
        System.out.println("Here comes the fucker");
        
        final DateTimeFormat parser = DateTimeFormat.getFormat(format);

        try {
            return parser.parse(date);
        } catch (IllegalArgumentException e) {
            // Ignores error as the next format may work better
        	return null;
        }

	}

}
