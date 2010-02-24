package org.restlet.engine;

import java.util.Map;

import org.restlet.Context;
import org.restlet.Uniform;
import org.restlet.data.Parameter;
import org.restlet.util.Series;

public interface Helper<T extends Context> extends Uniform{



	/**
	 * Returns the map of attributes exchanged between the API and the Engine
	 * via this helper.
	 * 
	 * @return The map of attributes.
	 */
	Map<String, Object> getAttributes();

	/**
	 * Returns the helped Restlet context.
	 * 
	 * @return The helped Restlet context.
	 */
	Context getContext();



	/**
	 * Returns the helped Restlet parameters.
	 * 
	 * @return The helped Restlet parameters.
	 */
	Series<Parameter> getParameters();




	/** Start callback. */
	void start() throws Exception;

	/** Stop callback. */
	void stop() throws Exception;

	/**
	 * Update callback with less impact than a {@link #stop()} followed by a
	 * {@link #start()}.
	 */
	void update() throws Exception;

}