package org.restlet.data;

import org.restlet.representation.Representation;

public class RequestBuilder {

	private final RequestFactory	requestFactory;

	public RequestBuilder(RequestFactory requestFactory) {
		super();
		this.requestFactory = requestFactory;
	}
	
	public InnerRequestBuilder createRequest(Method method, String uri) {
		return new InnerRequestBuilder(method, uri);
	}

	public class InnerRequestBuilder {
		private Method		method;
		private String		uri;
		private MediaType	mediaType	= null;
		private String		content;

		public InnerRequestBuilder(Method method, String uri) {
			this.method = method;
			this.uri = uri;
		}

		public InnerRequestBuilder withMediaType(MediaType mediaType) {
			this.mediaType = mediaType;
			return this;
		}

		public InnerRequestBuilder withContent(String content) {
			this.content = content;
			return this;
		}

		public Request build() {
			return requestFactory.createRequest(method, uri,
					new Representation(this.mediaType) {

						@Override
						public String getText() {
							return InnerRequestBuilder.this.content;
						}
					});
		}

	}
}
