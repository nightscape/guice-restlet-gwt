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

package org.restlet.engine.http;

import org.restlet.Uniform;
import org.restlet.data.Method;
import org.restlet.data.Parameter;
import org.restlet.data.Request;
import org.restlet.data.Response;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.util.Series;

/**
 * Low-level HTTP client call.
 * 
 * @author Jerome Louvel
 */
public abstract class HttpClientCall extends HttpCall {
	
	private final Util util;
	
	/** The parent HTTP client helper. */
    private HttpClientHelper helper;

    /**
     * Constructor setting the request address to the local host.
     * 
     * @param helper
     *            The parent HTTP client helper.
     * @param method
     *            The method name.
     * @param requestUri
     *            The request URI.
     */
    public HttpClientCall(HttpClientHelper helper, String method,
            String requestUri, Util util) {
        this.helper = helper;
        setMethod(method);
        setRequestUri(requestUri);
        this.util = util;
        // TODO: fix me?
        // setClientAddress(getLocalAddress());
    }

    /**
     * Returns the content length of the request entity if know,
     * {@link Representation#UNKNOWN_SIZE} otherwise.
     * 
     * @return The request content length.
     */
    protected long getContentLength() {
        return getContentLength(getResponseHeaders());
    }

    /**
     * Returns the HTTP client helper.
     * 
     * @return The HTTP client helper.
     */
    public HttpClientHelper getHelper() {
        return this.helper;
    }

    /**
     * Returns the representation wrapping the given stream.
     * 
     * @param entity
     *            The response entity.
     * @return The wrapping representation.
     */
    protected Representation getRepresentation(String entity) {
        return new StringRepresentation(entity);
    }

    /**
     * Returns the request entity string if it exists.
     * 
     * @return The request entity string if it exists.
     */
    public abstract String getRequestEntityString();

    /**
     * Returns the response entity if available. Note that no metadata is
     * associated by default, you have to manually set them from your headers.
     * 
     * @param response
     *            the Response to get the entity from
     * @return The response entity if available.
     */
    public Representation getResponseEntity(Response response) {
        Representation result = null;
        // boolean available = false;
        long size = Representation.UNKNOWN_SIZE;

        // Compute the content length
        final Series<Parameter> responseHeaders = getResponseHeaders();
        final String transferEncoding = responseHeaders.getFirstValue(
                HttpConstants.HEADER_TRANSFER_ENCODING, true);
        if ((transferEncoding != null)
                && !"identity".equalsIgnoreCase(transferEncoding)) {
            size = Representation.UNKNOWN_SIZE;
        } else {
            size = getContentLength();
        }

        if (!getMethod().equals(Method.HEAD.getName())
                && !response.getStatus().isInformational()
                && !response.getStatus()
                        .equals(Status.REDIRECTION_NOT_MODIFIED)
                && !response.getStatus().equals(Status.SUCCESS_NO_CONTENT)
                && !response.getStatus().equals(Status.SUCCESS_RESET_CONTENT)
                && !response.getStatus().equals(Status.SUCCESS_PARTIAL_CONTENT)) {
            // Make sure that an InputRepresentation will not be instantiated
            // while the stream is closed.
            final String text = getResponseEntityString(size);
            result = getRepresentation(text);
        }

        result = util.copyResponseEntityHeaders(responseHeaders, result);
        if (result != null) {
            result.setSize(size);
            // Informs that the size has not been specified in the header.
            if (size == Representation.UNKNOWN_SIZE) {
                System.err
                        .println("The length of the message body is unknown. The entity must be handled carefully and consumed entirely in order to surely release the connection.");
            }
        }
        // }

        return result;
    }

    /**
     * Returns the response entity string if it exists.
     * 
     * @param size
     *            The expected entity size or -1 if unknown.
     * @return The response entity stream if it exists.
     */
    public abstract String getResponseEntityString(long size);

    @Override
    protected boolean isClientKeepAlive() {
        return true;
    }

    @Override
    protected boolean isServerKeepAlive() {
        final String header = getResponseHeaders().getFirstValue(
                HttpConstants.HEADER_CONNECTION, true);
        return (header == null) || !header.equalsIgnoreCase("close");
    }

    /**
     * Sends the request to the client. Commits the request line, headers and
     * optional entity and send them over the network.
     * 
     * @param request
     *            The high-level request.
     * @param response
     *            The high-level response.
     * @param callback
     *            The callback invoked upon request completion.
     */
    public abstract void sendRequest(Request request, Response response,
            Uniform callback) throws Exception;

    /**
     * Indicates if the request entity should be chunked.
     * 
     * @return True if the request should be chunked
     */
    protected boolean shouldRequestBeChunked(Request request) {
        return request.isEntityAvailable()
                && (request.getEntity() != null)
                && (request.getEntity().getSize() == Representation.UNKNOWN_SIZE);
    }
}
