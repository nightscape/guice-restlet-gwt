package org.restlet.configuration.providers;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Provider;

public class EmptyListProvider<T> implements Provider<List<T>> {

	@Override
	public List<T> get() {
		return new ArrayList<T>();
	}

}
