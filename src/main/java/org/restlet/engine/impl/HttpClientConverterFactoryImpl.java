package org.restlet.engine.impl;

import org.restlet.Context;
import org.restlet.engine.http.CookieReaderFactory;
import org.restlet.engine.http.CookieUtils;
import org.restlet.engine.http.HttpClientConverter;
import org.restlet.engine.http.HttpClientConverterFactory;
import org.restlet.engine.http.ResponseTransportHeaderCopier;
import org.restlet.engine.util.DateUtils;

import com.google.inject.Inject;

public class HttpClientConverterFactoryImpl implements
		HttpClientConverterFactory {
	private final CookieUtils cookieUtils;
	private final CookieReaderFactory	cookieReaderFactory;
	private final DateUtils	dateUtils;
	private final ResponseTransportHeaderCopier	headerCopier;
	@Inject
	public HttpClientConverterFactoryImpl(CookieUtils cookieUtils,
			CookieReaderFactory cookieReaderFactory, DateUtils dateUtils,
			ResponseTransportHeaderCopier headerCopier) {
		super();
		this.cookieUtils = cookieUtils;
		this.cookieReaderFactory = cookieReaderFactory;
		this.dateUtils = dateUtils;
		this.headerCopier = headerCopier;
	}
	@Override
	public HttpClientConverter createClientConverter(Context context) {
		return new HttpClientConverter(context, cookieUtils, cookieReaderFactory, dateUtils, headerCopier);
	}

}
