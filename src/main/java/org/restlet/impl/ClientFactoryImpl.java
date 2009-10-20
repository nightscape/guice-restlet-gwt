package org.restlet.impl;

import java.util.Arrays;
import java.util.List;

import org.restlet.Client;
import org.restlet.ClientFactory;
import org.restlet.Context;
import org.restlet.data.Protocol;
import org.restlet.data.RequestFactory;
import org.restlet.engine.Helper;

import com.google.inject.Inject;

public class ClientFactoryImpl implements ClientFactory{
	@Inject
	public ClientFactoryImpl(Helper<Context> helper,
			RequestFactory requestFactory) {
		this.helper = helper;
		this.requestFactory = requestFactory;
	}

	private final Helper<Context> helper;
	private final RequestFactory requestFactory;
	/**
	 * Constructor.
	 * 
	 * @param context
	 *          The context.
	 * @param protocol
	 *          The connector protocol.
	 */
	public Client createClient(Context context, Protocol protocol) {
		return createClient(context, (protocol == null) ? null : Arrays.asList(protocol));
	}

	public Client createClient(Context context, List<Protocol> list) {
		return new Client(context, list, helper, requestFactory);
	}

	/**
	 * Constructor.
	 * 
	 * @param protocols
	 *          The connector protocols.
	 */
	public Client createClient(List<Protocol> protocols) {
		return createClient(null, protocols);
	}

	/**
	 * Constructor.
	 * 
	 * @param protocol
	 *          The connector protocol.
	 */
	public Client createClient(Protocol protocol) {
		return createClient(null, protocol);
	}
}
