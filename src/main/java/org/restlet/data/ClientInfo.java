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

package org.restlet.data;

import java.util.List;

import org.restlet.configuration.annotations.Address;
import org.restlet.configuration.annotations.Agent;
import org.restlet.configuration.annotations.Port;

import com.google.inject.Inject;

/**
 * Client specific data related to a call.
 * 
 * @author Jerome Louvel
 */
public final class ClientInfo {
	/** The character set preferences. */
	private final List<Preference<CharacterSet>>	acceptedCharacterSets;

	/** The encoding preferences. */
	private final List<Preference<Encoding>>		acceptedEncodings;

	/** The language preferences. */
	private final List<Preference<Language>>		acceptedLanguages;

	/** The media preferences. */
	private final List<Preference<MediaType>>		acceptedMediaTypes;

	/** The IP addresses. */
	private final List<String>					addresses;

	/** The agent name. */
	private final String							agent;

	/** The list of product tokens taken from the agent name. */
	private final List<Product>					agentProducts;

	/** The port number. */
	private final int								port;


	@Inject
	public ClientInfo(List<Preference<CharacterSet>> acceptedCharacterSets,
			List<Preference<Encoding>> acceptedEncodings,
			List<Preference<Language>> acceptedLanguages,
			List<Preference<MediaType>> acceptedMediaTypes,
			@Address List<String> addresses, @Agent String agent, List<Product> agentProducts,
			@Port int port) {
		super();
		this.acceptedCharacterSets = acceptedCharacterSets;
		this.acceptedEncodings = acceptedEncodings;
		this.acceptedLanguages = acceptedLanguages;
		this.acceptedMediaTypes = acceptedMediaTypes;
		this.addresses = addresses;
		this.agent = agent;
		this.agentProducts = agentProducts;
		this.port = port;
	}

	/**
	 * Returns the modifiable list of character set preferences. Creates a new
	 * instance if no one has been set.
	 * 
	 * @return The character set preferences.
	 */
	public List<Preference<CharacterSet>> getAcceptedCharacterSets() {
		return this.acceptedCharacterSets;
	}

	/**
	 * Returns the modifiable list of encoding preferences. Creates a new
	 * instance if no one has been set.
	 * 
	 * @return The encoding preferences.
	 */
	public List<Preference<Encoding>> getAcceptedEncodings() {
		return this.acceptedEncodings;
	}

	/**
	 * Returns the modifiable list of language preferences. Creates a new
	 * instance if no one has been set.
	 * 
	 * @return The language preferences.
	 */
	public List<Preference<Language>> getAcceptedLanguages() {
		return this.acceptedLanguages;
	}

	/**
	 * Returns the modifiable list of media type preferences. Creates a new
	 * instance if no one has been set.
	 * 
	 * @return The media type preferences.
	 */
	public List<Preference<MediaType>> getAcceptedMediaTypes() {
		return this.acceptedMediaTypes;
	}

	/**
	 * Returns the client's IP address which is the first address in the list of
	 * client addresses, if this list exists and isn't empty.
	 * 
	 * @return The client's IP address.
	 */
	public String getAddress() {
		return (this.addresses == null) ? null
				: (this.addresses.isEmpty() ? null : this.addresses.get(0));
	}

	/**
	 * Returns the modifiable list of client IP addresses.<br>
	 * <br>
	 * The first address is the one of the immediate client component as
	 * returned by the getClientAdress() method and the last address should
	 * correspond to the origin client (frequently a user agent).<br>
	 * <br>
	 * This is useful when the user agent is separated from the origin server by
	 * a chain of intermediary components. Creates a new instance if no one has
	 * been set.
	 * 
	 * @return The client IP addresses.
	 */
	public List<String> getAddresses() {
		return this.addresses;
	}

	/**
	 * Returns the agent name (ex: "Noelios-Restlet-Engine/1.1").
	 * 
	 * @return The agent name.
	 */
	public String getAgent() {
		return this.agent;
	}

	/**
	 * Returns the list of product tokens from the user agent name.
	 * 
	 * @return The list of product tokens from the user agent name.
	 */
	public List<Product> getAgentProducts() {
		return this.agentProducts;
	}

	/**
	 * Returns the port number which sent the call. If no port is specified, -1
	 * is returned.
	 * 
	 * @return The port number which sent the call.
	 */
	public int getPort() {
		return this.port;
	}


}
