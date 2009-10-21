package org.restlet.data.impl;

import org.restlet.data.ClientInfo;
import org.restlet.data.Conditions;
import org.restlet.data.Method;
import org.restlet.data.Reference;
import org.restlet.data.Request;
import org.restlet.data.RequestFactory;
import org.restlet.representation.Representation;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class RequestFactoryImpl implements RequestFactory{
	private Provider<Conditions>	conditionsProvider;

	@Inject
	public RequestFactoryImpl(Provider<ClientInfo> clientInfoProvider, Provider<Conditions> conditionsProvider) {
		this.clientInfoProvider = clientInfoProvider;
		this.conditionsProvider = conditionsProvider;
	}
	private final Provider<ClientInfo> clientInfoProvider;

    public Request createRequest(Method method, String resourceUri) {
    	return createRequest(method, resourceUri,null);
    }

    public Request createRequest(Method method, Reference reference) {
		return createRequest(method, reference.toString(), null);
		
	}
	public Request createRequest(Method method, String resourceUri, Representation entity) {
    	return new Request(method, new Reference(resourceUri), entity,clientInfoProvider.get(),conditionsProvider.get());
    }
}
