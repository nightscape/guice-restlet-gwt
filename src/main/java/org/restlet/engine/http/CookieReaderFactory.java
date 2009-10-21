package org.restlet.engine.http;

public interface CookieReaderFactory {
	public CookieReader getCookieReader(String header);
}
