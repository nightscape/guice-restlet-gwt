package org.restlet;

import org.restlet.data.Request;
import org.restlet.data.Response;

public interface Restlet {

	/**
	 * Returns the context.
	 * 
	 * @return The context.
	 */
	public abstract Context getContext();

	/**
	 * Handles a call. Subclasses overriding this method should make sure that
	 * they call super.handle(request, response) before adding their own logic.
	 * 
	 * @param request
	 *            The request to handle.
	 * @param response
	 *            The response to update.
	 * @param callback
	 *            The callback invoked upon request completion.
	 */
	public abstract void handle(Request request, Response response,
			Uniform callback);

	/**
	 * Indicates if the Restlet is started.
	 * 
	 * @return True if the Restlet is started.
	 */
	public abstract boolean isStarted();

	/**
	 * Indicates if the Restlet is stopped.
	 * 
	 * @return True if the Restlet is stopped.
	 */
	public abstract boolean isStopped();

	/**
	 * Sets the context.
	 * 
	 * @param context
	 *            The context.
	 */
	public abstract void setContext(Context context);

	/** Starts the Restlet. */
	public abstract void start() throws Exception;

	/** Stops the Restlet. */
	public abstract void stop() throws Exception;

}