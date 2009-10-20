package org.restlet.engine.http;

import org.restlet.Context;

public interface HttpClientConverterFactory {
	HttpClientConverter createClientConverter(Context context);
}
