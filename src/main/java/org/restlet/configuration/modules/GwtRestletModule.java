package org.restlet.configuration.modules;

import java.util.Arrays;
import java.util.List;

import org.restlet.Context;
import org.restlet.engine.ClientHelper;
import org.restlet.engine.Helper;
import org.restlet.engine.http.GwtHttpClientHelper;

import com.google.gwt.inject.client.AbstractGinModule;
 import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.TypeLiteral;


public class GwtRestletModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(new TypeLiteral<List<ClientHelper>>() {}).toProvider(GwtClientHelperProvider.class);
		bind(new TypeLiteral<Helper<Context>>() {}).to(new TypeLiteral<GwtHttpClientHelper>(){});
		install(new RestletModule());

	}
	public static class GwtClientHelperProvider implements Provider<List<ClientHelper>> {
		private final GwtHttpClientHelper helper;
		@Inject
		public GwtClientHelperProvider(GwtHttpClientHelper helper) {
			super();
			this.helper = helper;
		}
		public List<ClientHelper> get() {
			return Arrays.asList((ClientHelper) helper);
		}
	}	
	
}
