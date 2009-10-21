package org.restlet.engine.http;

import org.restlet.engine.util.DateUtils;

import com.google.inject.Inject;

public class CookieReaderFactoryImpl implements CookieReaderFactory {
	private final DateUtils dateUtils;
	
	@Inject
	public CookieReaderFactoryImpl(DateUtils dateUtils) {
		super();
		this.dateUtils = dateUtils;
	}


	@Override
	public CookieReader getCookieReader(String header) {
		return new CookieReader(header, dateUtils);
	}

}
