package org.restlet.data;

import org.restlet.representation.Representation;

public interface RequestFactory {

    /**
     * @param method
     *            The call's method.
     * @param resourceRef
     *            The resource reference.
     */
    public Request createRequest(Method method, Reference resourceRef);
    /**
     * @param method
     *            The call's method.
     * @param resourceUri
     *            The resource URI.
     */
    public Request createRequest(Method method, String resourceUri);

    /**
     * @param method
     *            The call's method.
     * @param resourceUri
     *            The resource URI.
     * @param entity
     *            The entity.
     */
    public Request createRequest(Method method, String resourceUri, Representation entity);

}
