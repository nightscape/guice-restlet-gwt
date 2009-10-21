package org.restlet.configuration;

import java.util.Arrays;
import java.util.List;

import org.restlet.data.Protocol;
import org.restlet.data.RequestFactory;
import org.restlet.data.impl.RequestFactoryImpl;
import org.restlet.engine.http.CookieReaderFactory;
import org.restlet.engine.http.CookieReaderFactoryImpl;
import org.restlet.engine.http.DateHelper;
import org.restlet.engine.http.DateHelperImpl;
import org.restlet.engine.http.HttpClientConverterFactory;
import org.restlet.engine.http.Util;
import org.restlet.engine.http.UtilImpl;
import org.restlet.engine.impl.HttpClientConverterFactoryImpl;
import org.restlet.engine.util.DateUtils;
import org.restlet.engine.util.impl.DateUtilsImpl;
import org.restlet.util.Engine;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Provider;
import com.google.inject.TypeLiteral;

public class RestletModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(DateUtils.class).to(DateUtilsImpl.class);
		bind(CookieReaderFactory.class).to(CookieReaderFactoryImpl.class);
		bind(RequestFactory.class).to(RequestFactoryImpl.class);
		bind(new TypeLiteral<List<Protocol>>() {}).toProvider(HttpAndHttpsProtocolProvider.class);
		bind(HttpClientConverterFactory.class).to(HttpClientConverterFactoryImpl.class);
		bind(Engine.class).to(org.restlet.engine.Engine.class);
		bind(Util.class).to(UtilImpl.class);
		bind(DateHelper.class).to(DateHelperImpl.class);
		bindConstant().annotatedWith(TimeOut.class).to(1000);
	}
	
	public static class HttpAndHttpsProtocolProvider implements Provider<List<Protocol>> {
		public List<Protocol> get() {
			return Arrays.asList(Protocol.HTTP,Protocol.HTTPS);
		}
	}



}
