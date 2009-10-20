package org.restlet.data.impl;

import org.restlet.data.ClientInfo;
import org.restlet.data.Method;
import org.restlet.data.Reference;
import org.restlet.data.Request;
import org.restlet.data.RequestFactory;
import org.restlet.representation.Representation;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class RequestFactoryImpl implements RequestFactory{
	@Inject
	public RequestFactoryImpl(Provider<ClientInfo> clientInfoProvider) {
		this.clientInfoProvider = clientInfoProvider;
	}
	private final Provider<ClientInfo> clientInfoProvider;

    public Request createRequest(Method method, Reference resourceRef,ClientInfo clientInfo) {
    	return createRequest(method, resourceRef, null);
    }
    public Request createRequest(Method method, String resourceUri) {
    	return createRequest(method, new Reference(resourceUri));
    }

    public Request createRequest(Method method, Reference reference) {
		return createRequest(method, reference, null);
		
	}
	public Request createRequest(Method method, String resourceUri, Representation entity) {
    	return new Request(method, new Reference(resourceUri), entity,clientInfoProvider.get());
    }
}
