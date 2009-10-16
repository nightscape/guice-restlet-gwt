package org.restlet.engine.http;

import org.restlet.data.Parameter;
import org.restlet.representation.Representation;

public interface Util {

	/**
	 * Copies entity headers into a response and ensures that a non null
	 * representation is returned when at least one entity header is present.
	 * 
	 * @param responseHeaders
	 *            The headers to copy.
	 * @param representation
	 *            The Representation to update.
	 * @return a representation with the entity headers of the response or null
	 *         if no representation has been provided and the response has not
	 *         sent any entity header.
	 * @throws NumberFormatException
	 * @see org.restlet.util.Engine#copyResponseHeaders(Iterable, Response,
	 *      Logger)
	 * @see HttpClientConverter#copyResponseTransportHeaders(Iterable, Response,
	 *      Logger)
	 */
	Representation copyResponseEntityHeaders(
			Iterable<Parameter> responseHeaders, Representation representation)
			throws NumberFormatException;

	/**
	 * Parse the Content-Disposition header value
	 * 
	 * @param value
	 *            Content-disposition header
	 * @return Filename
	 */
	String parseContentDisposition(String value);

}