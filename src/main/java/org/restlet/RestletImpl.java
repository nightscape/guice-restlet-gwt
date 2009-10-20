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

package org.restlet;

import org.restlet.data.Request;
import org.restlet.data.Response;
import org.restlet.data.Status;

import com.google.inject.Inject;

/**
 * Uniform class that provides a context and life cycle support. It has many
 * subclasses that focus on specific ways to process calls. The context property
 * is typically provided by a parent Component as a way to encapsulate access to
 * shared features such as logging and client connectors.
 * 
 * @author Jerome Louvel
 */
public class RestletImpl implements Restlet {
	/** Error message. */
	private static final String	UNABLE_TO_START	= "Unable to start the Restlet";

	/** The context. */
	private Context				context;

	/** Indicates if the restlet was started. */
	private boolean				started;

	/**
	 * Constructor.
	 * 
	 * @param context
	 *            The context.
	 */
	@Inject
	public RestletImpl(Context context) {
		this.context = context;
		this.started = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.restlet.Restlet#getContext()
	 */
	public Context getContext() {
		return this.context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.restlet.Restlet#handle(org.restlet.data.Request,
	 * org.restlet.data.Response, org.restlet.Uniform)
	 */
	public void handle(Request request, Response response, Uniform callback) {
		// Check if the Restlet was started
		if (isStopped()) {
			try {
				start();
			} catch (Exception e) {
				// Occurred while starting the Restlet
				System.err.println(UNABLE_TO_START);
				response.setStatus(Status.SERVER_ERROR_INTERNAL);
			}

			if (!isStarted()) {
				// No exception raised but the Restlet somehow couldn't be
				// started
				System.err.println(UNABLE_TO_START);
				response.setStatus(Status.SERVER_ERROR_INTERNAL);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.restlet.Restlet#isStarted()
	 */
	public boolean isStarted() {
		return this.started;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.restlet.Restlet#isStopped()
	 */
	public boolean isStopped() {
		return !this.started;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.restlet.Restlet#setContext(org.restlet.Context)
	 */
	public void setContext(Context context) {
		this.context = context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.restlet.Restlet#start()
	 */
	public synchronized void start() throws Exception {
		this.started = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.restlet.Restlet#stop()
	 */
	public synchronized void stop() throws Exception {
		this.started = false;
	}

}
