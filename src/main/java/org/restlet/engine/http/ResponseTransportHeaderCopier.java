/**
 * 
 */
package org.restlet.engine.http;

import java.util.Set;

import org.restlet.data.ChallengeRequest;
import org.restlet.data.Dimension;
import org.restlet.data.Method;
import org.restlet.data.Parameter;
import org.restlet.data.Response;
import org.restlet.engine.Engine;
import org.restlet.engine.util.AuthenticationUtils;

import com.google.inject.Inject;

public class ResponseTransportHeaderCopier {
/**
 * Copies headers into a response.
 * 
 * @param headers
 *            The headers to copy.
 * @param response
 *            The response to update.
 * @see Engine#copyResponseHeaders(Iterable, Response)
 * @see HttpClientCall#copyResponseEntityHeaders(Iterable,
 *      org.restlet.resource.Representation)
 */
public void copyResponseTransportHeaders(
        Iterable<Parameter> headers, Response response) {
    // Read info from headers
    for (final Parameter header : headers) {
        if (header.getName()
                .equalsIgnoreCase(HttpConstants.HEADER_LOCATION)) {
            response.setLocationRef(header.getValue());
        } else if ((header.getName()
                .equalsIgnoreCase(HttpConstants.HEADER_SET_COOKIE))
                || (header.getName()
                        .equalsIgnoreCase(HttpConstants.HEADER_SET_COOKIE2))) {
            try {
                final CookieReader cr = cookieReaderFactory.getCookieReader(header.getValue());
                response.getCookieSettings().add(cr.readCookieSetting());
            } catch (Exception e) {
                System.err
                        .println("Error during cookie setting parsing. Header: "
                                + header.getValue());
            }
        } else if (header.getName().equalsIgnoreCase(
                HttpConstants.HEADER_WWW_AUTHENTICATE)) {
            final ChallengeRequest request = AuthenticationUtils
                    .parseAuthenticateHeader(header.getValue());
            response.setChallengeRequest(request);
        } else if (header.getName().equalsIgnoreCase(
                HttpConstants.HEADER_SERVER)) {
            response.getServerInfo().setAgent(header.getValue());
        } else if (header.getName().equalsIgnoreCase(
                HttpConstants.HEADER_ALLOW)) {
            final HeaderReader hr = new HeaderReader(header.getValue());
            String value = hr.readValue();
            final Set<Method> allowedMethods = response.getAllowedMethods();
            while (value != null) {
                allowedMethods.add(Method.valueOf(value));
                value = hr.readValue();
            }
        } else if (header.getName().equalsIgnoreCase(
                HttpConstants.HEADER_VARY)) {
            final HeaderReader hr = new HeaderReader(header.getValue());
            String value = hr.readValue();
            final Set<Dimension> dimensions = response.getDimensions();
            while (value != null) {
                if (value.equalsIgnoreCase(HttpConstants.HEADER_ACCEPT)) {
                    dimensions.add(Dimension.MEDIA_TYPE);
                } else if (value
                        .equalsIgnoreCase(HttpConstants.HEADER_ACCEPT_CHARSET)) {
                    dimensions.add(Dimension.CHARACTER_SET);
                } else if (value
                        .equalsIgnoreCase(HttpConstants.HEADER_ACCEPT_ENCODING)) {
                    dimensions.add(Dimension.ENCODING);
                } else if (value
                        .equalsIgnoreCase(HttpConstants.HEADER_ACCEPT_LANGUAGE)) {
                    dimensions.add(Dimension.LANGUAGE);
                } else if (value
                        .equalsIgnoreCase(HttpConstants.HEADER_AUTHORIZATION)) {
                    dimensions.add(Dimension.AUTHORIZATION);
                } else if (value
                        .equalsIgnoreCase(HttpConstants.HEADER_USER_AGENT)) {
                    dimensions.add(Dimension.CLIENT_AGENT);
                } else if (value.equals("*")) {
                    dimensions.add(Dimension.UNSPECIFIED);
                }

                value = hr.readValue();
            }
        }
    }
}
private final CookieReaderFactory	cookieReaderFactory;
@Inject
public ResponseTransportHeaderCopier(CookieReaderFactory cookieReaderFactory) {
	super();
	this.cookieReaderFactory = cookieReaderFactory;
}
}