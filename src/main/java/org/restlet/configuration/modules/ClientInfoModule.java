package org.restlet.configuration.modules;

import java.util.List;

import org.restlet.configuration.annotations.Address;
import org.restlet.configuration.annotations.Agent;
import org.restlet.configuration.annotations.Port;
import org.restlet.configuration.providers.EmptyListProvider;
import org.restlet.data.CharacterSet;
import org.restlet.data.Encoding;
import org.restlet.data.Language;
import org.restlet.data.MediaType;
import org.restlet.data.Preference;
import org.restlet.data.Product;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.TypeLiteral;


public class ClientInfoModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(new TypeLiteral<List<Preference<CharacterSet>>>() {}).toProvider(EmptyCharacterSetPreferenceProvider.class);
		bind(new TypeLiteral<List<Preference<Encoding>>>() {}).toProvider(EmptyEncodingPreferenceProvider.class);
		bind(new TypeLiteral<List<Preference<Language>>>() {}).toProvider(EmptyLanguagePreferenceProvider.class);
		bind(new TypeLiteral<List<Preference<MediaType>>>() {}).toProvider(EmptyMediaTypePreferenceProvider.class);
		bind(new TypeLiteral<List<Product>>() {}).toProvider(EmptyProductListProvider.class);
		bind(new TypeLiteral<List<String>>() {}).annotatedWith(Address.class).toProvider(EmptyAddressListProvider.class);
		bindConstant().annotatedWith(Port.class).to(-1);
		bindConstant().annotatedWith(Agent.class).to("");

	}
	public static final class EmptyCharacterSetPreferenceProvider extends EmptyListProvider<Preference<CharacterSet>> {}
	public static final class EmptyEncodingPreferenceProvider extends EmptyListProvider<Preference<Encoding>> {}
	public static final class EmptyLanguagePreferenceProvider extends EmptyListProvider<Preference<Language>> {}
	public static final class EmptyMediaTypePreferenceProvider extends EmptyListProvider<Preference<MediaType>> {}
	public static final class EmptyAddressListProvider extends EmptyListProvider<String> {}
	public static final class EmptyProductListProvider extends EmptyListProvider<Product> {}

}
