package org.restlet.engine;

import org.restlet.Client;
import org.restlet.Context;

public interface ClientHelperFactory {
    /**
     * Creates a new helper for a given client connector.
     * 
     * @param client
     *            The client to help.
     * @return The new helper.
     */
    public abstract Helper<Context> createHelper(Client client);
}
