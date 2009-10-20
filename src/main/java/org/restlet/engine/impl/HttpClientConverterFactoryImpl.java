package org.restlet.engine.impl;

import org.restlet.Context;
import org.restlet.engine.http.CookieUtils;
import org.restlet.engine.http.HttpClientConverter;
import org.restlet.engine.http.HttpClientConverterFactory;

import com.google.inject.Inject;

public class HttpClientConverterFactoryImpl implements
		HttpClientConverterFactory {
	private final CookieUtils cookieUtils;
	@Inject
	public HttpClientConverterFactoryImpl(CookieUtils cookieUtils) {
		super();
		this.cookieUtils = cookieUtils;
	}
	@Override
	public HttpClientConverter createClientConverter(Context context) {
		return new HttpClientConverter(context, cookieUtils);
	}

}
