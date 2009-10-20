package org.restlet.data;

import org.restlet.representation.Representation;

public interface FormFromRepresentationFactory {
	public Form create(Representation representation);
}
