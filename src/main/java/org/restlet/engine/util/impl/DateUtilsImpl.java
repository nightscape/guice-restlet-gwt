/**
 * Copyright 2005-2009 Noelios Technologies.
 * 
 * The contents of this file are subject to the terms of one of the following
 * open source licenses: LGPL 3.0 or LGPL 2.1 or CDDL 1.0 or EPL 1.0 (the
 * "Licenses"). You can select the license that you prefer but you may not use
 * this file except in compliance with one of these Licenses.
 * 
 * You can obtain a copy of the LGPL 3.0 license at
 * http://www.opensource.org/licenses/lgpl-3.0.html
 * 
 * You can obtain a copy of the LGPL 2.1 license at
 * http://www.opensource.org/licenses/lgpl-2.1.php
 * 
 * You can obtain a copy of the CDDL 1.0 license at
 * http://www.opensource.org/licenses/cddl1.php
 * 
 * You can obtain a copy of the EPL 1.0 license at
 * http://www.opensource.org/licenses/eclipse-1.0.php
 * 
 * See the Licenses for the specific language governing permissions and
 * limitations under the Licenses.
 * 
 * Alternatively, you can obtain a royalty free commercial license with less
 * limitations, transferable or non-transferable, directly at
 * http://www.noelios.com/products/restlet-engine
 * 
 * Restlet is a registered trademark of Noelios Technologies.
 */

package org.restlet.engine.util.impl;

import java.util.Date;
import java.util.List;

import org.restlet.engine.util.DateParser;
import org.restlet.engine.util.DateUtils;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.inject.Inject;

/**
 * Date manipulation utilities.
 * 
 * @author Jerome Louvel
 * @author Piyush Purang (ppurang@gmail.com)
 */
public final class DateUtilsImpl implements DateUtils {
	
	@Inject
    public DateUtilsImpl(DateParser dateParser) {
		super();
		this.dateParser = dateParser;
	}

	/**
     * Class acting as an immutable date class based on the
     * {@link java.util.Date} class.
     * 
     * Throws {@link UnsupportedOperationException} when muttable methods are
     * invoked.
     * 
     * @author Piyush Purang (ppurang@gmail.com)
     * @see java.util.Date
     * @see <a href="http://discuss.fogcreek.com/joelonsoftware3/default.asp?cmd=show&ixPost=73959&ixReplies=24"
     *      >Immutable Date</a>
     */
    private static final class ImmutableDate extends Date {
        // TODO Are we serializable?
        private static final long serialVersionUID = -5946186780670229206L;

        /**
         * Returns an ImmutableDate object wrapping the given date.
         * 
         * @param date
         *            object to be made immutable
         * @return an immutable date object
         */
        public static ImmutableDate valueOf(Date date) {
            return new ImmutableDate(date);
        }

        /** Delegate being wrapped */
        private final Date delegate;

        /**
         * Private constructor. A factory method is provided.
         * 
         * @param date
         *            date to be made immutable
         */
        private ImmutableDate(Date date) {
            this.delegate = (Date) date.clone();
        }

        /** {@inheritDoc} */
        @Override
        public boolean after(Date when) {
            return this.delegate.after(when);
        }

        /** {@inheritDoc} */
        @Override
        public boolean before(Date when) {
            return this.delegate.before(when);
        }

        /** {@inheritDoc} */
        @Override
        public Object clone() {
            throw new UnsupportedOperationException(
                    "ImmutableDate is immutable");
        }

        /** {@inheritDoc} */
        @Override
        public int compareTo(Date anotherDate) {
            return this.delegate.compareTo(anotherDate);
        }

        /** {@inheritDoc} */
        @Override
        public boolean equals(Object obj) {
            return this.delegate.equals(obj);
        }

        /** {@inheritDoc} */
        @Override
        public long getTime() {
            return this.delegate.getTime();
        }

        /** {@inheritDoc} */
        @Override
        public int hashCode() {
            return this.delegate.hashCode();
        }

        /** {@inheritDoc} */
        @Override
        public String toString() {
            return this.delegate.toString();
        }
    }

    private final DateParser dateParser;
    /* (non-Javadoc)
	 * @see org.restlet.engine.util.impl.DateUtils#after(java.util.Date, java.util.Date)
	 */
    public boolean after(final Date baseDate, final Date afterDate) {
        if ((baseDate == null) || (afterDate == null)) {
            throw new IllegalArgumentException(
                    "Can't compare the dates, at least one of them is null");
        }

        final long baseTime = baseDate.getTime() / 1000;
        final long afterTime = afterDate.getTime() / 1000;
        return baseTime < afterTime;
    }

    /* (non-Javadoc)
	 * @see org.restlet.engine.util.impl.DateUtils#before(java.util.Date, java.util.Date)
	 */
    public boolean before(final Date baseDate, final Date beforeDate) {
        if ((baseDate == null) || (beforeDate == null)) {
            throw new IllegalArgumentException(
                    "Can't compare the dates, at least one of them is null");
        }

        final long baseTime = baseDate.getTime() / 1000;
        final long beforeTime = beforeDate.getTime() / 1000;
        return beforeTime < baseTime;
    }

    /* (non-Javadoc)
	 * @see org.restlet.engine.util.impl.DateUtils#equals(java.util.Date, java.util.Date)
	 */
    public boolean equals(final Date baseDate, final Date otherDate) {
        if ((baseDate == null) || (otherDate == null)) {
            throw new IllegalArgumentException(
                    "Can't compare the dates, at least one of them is null");
        }

        final long baseTime = baseDate.getTime() / 1000;
        final long otherTime = otherDate.getTime() / 1000;
        return otherTime == baseTime;
    }

    /* (non-Javadoc)
	 * @see org.restlet.engine.util.impl.DateUtils#format(java.util.Date, java.lang.String)
	 */
    public String format(final Date date, final String format) {
        if (date == null) {
            throw new IllegalArgumentException("Date is null");
        }

        /*
         * GWT difference: DateTimeFormat is not passed a Locale in the same way
         * as SimpleDateFormat. It derives locale information from the GWT
         * application's locale.
         * 
         * Default timezone is GMT unless specified via a GMT:hhmm, GMT:+hhmm,
         * or GMT:-hhmm string.
         */
        final DateTimeFormat formatter = DateTimeFormat.getFormat(format);
        return formatter.format(date);
    }

    /* (non-Javadoc)
	 * @see org.restlet.engine.util.impl.DateUtils#parse(java.lang.String, java.util.List)
	 */
    public Date parse(final String date, final List<String> formats) {
        Date result = null;

        if (date == null) {
            throw new IllegalArgumentException("Date is null");
        }

        String format = null;
        final int formatsSize = formats.size();
        for (int i = 0; (result == null) && (i < formatsSize); i++) {
            format = formats.get(i);
            dateParser.parseDate(date, format);
        }

        return result;
    }

    /* (non-Javadoc)
	 * @see org.restlet.engine.util.impl.DateUtils#unmodifiable(java.util.Date)
	 */
    public Date unmodifiable(Date date) {
        return (date == null) ? null : ImmutableDate.valueOf(date);
    }



}
