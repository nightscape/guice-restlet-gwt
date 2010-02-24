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

package org.restlet.engine;

import java.util.HashMap;
import java.util.Map;

import org.restlet.Context;
import org.restlet.Uniform;
import org.restlet.data.Parameter;
import org.restlet.data.Request;
import org.restlet.data.Response;
import org.restlet.util.Series;

/**
 * Delegate used by API classes to get support from the implementation classes.
 * Note that this is an SPI class that is not intended for public usage.
 * 
 * @author Jerome Louvel
 */
public abstract class AbstractHelper<T extends Context> implements Helper<T> {

    /**
     * The map of attributes exchanged between the API and the Engine via this
     * helper.
     */
    private final Map<String, Object> attributes;

    /**
     * The helped Restlet.
     */
    private T context;

    /**
     * Constructor.
     * 
     * @param context
     *            The helped Restlet.
     */
    public AbstractHelper(T context) {
        this.attributes = new HashMap<String, Object>();
        assert context != null;
        this.context = context;
    }

	/**
	 * Creates a new context.
	 * 
	 * @param loggerName
	 *            The JDK's logger name to use for contextual logging.
	 * @return The new context.
	 */
    public abstract Context createContext(String loggerName);

    /* (non-Javadoc)
	 * @see org.restlet.engine.Helper#getAttributes()
	 */
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    /* (non-Javadoc)
	 * @see org.restlet.engine.Helper#getContext()
	 */
    public Context getContext() {
        return this.context;
    }

	/* (non-Javadoc)
	 * @see org.restlet.engine.Helper#getParameters()
	 */
    public Series<Parameter> getParameters() {
        return getContext().getParameters();
    }

    /* (non-Javadoc)
	 * @see org.restlet.engine.Helper#handle(org.restlet.data.Request, org.restlet.data.Response, org.restlet.Uniform)
	 */
    public void handle(Request request, Response response, Uniform callback) {

    }

	/**
	 * Sets the helped Restlet.
	 * 
	 * @param helpedRestlet
	 *            The helped Restlet.
	 */
    public void setHelped(T helpedRestlet) {
        this.context = helpedRestlet;
    }

    /* (non-Javadoc)
	 * @see org.restlet.engine.Helper#start()
	 */
    public abstract void start() throws Exception;

    /* (non-Javadoc)
	 * @see org.restlet.engine.Helper#stop()
	 */
    public abstract void stop() throws Exception;

    /* (non-Javadoc)
	 * @see org.restlet.engine.Helper#update()
	 */
    public abstract void update() throws Exception;
}
