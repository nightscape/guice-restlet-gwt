package org.restlet;

import java.util.List;

import org.restlet.data.Protocol;

public interface ClientFactory {
	/**
	 * @param context
	 *          The context.
	 * @param protocol
	 *          The connector protocol.
	 */
	public Client createClient(Context context, Protocol protocol);

	/**
	 * @param protocols
	 *          The connector protocols.
	 */
	public Client createClient(List<Protocol> protocols);

	/**
	 * @param protocol
	 *          The connector protocol.
	 */
	public Client createClient(Protocol protocol);
}
