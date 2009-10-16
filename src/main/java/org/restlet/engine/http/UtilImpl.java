/**
 * 
 */
package org.restlet.engine.http;

import org.restlet.data.CharacterSet;
import org.restlet.data.Encoding;
import org.restlet.data.Language;
import org.restlet.data.Parameter;
import org.restlet.data.Tag;
import org.restlet.representation.Representation;

public class UtilImpl implements Util {
  /* (non-Javadoc)
	 * @see org.restlet.engine.http.Util#copyResponseEntityHeaders(java.lang.Iterable, org.restlet.representation.Representation)
	 */
  public Representation copyResponseEntityHeaders(
          Iterable<Parameter> responseHeaders, Representation representation)
          throws NumberFormatException {
      final Representation result = (representation == null) ? Representation
              .createEmpty() : representation;
      boolean entityHeaderFound = false;

      for (final Parameter header : responseHeaders) {
          if (header.getName().equalsIgnoreCase(
                  HttpConstants.HEADER_CONTENT_TYPE)) {
              final ContentType contentType = new ContentType(header
                      .getValue());
              result.setMediaType(contentType.getMediaType());
              final CharacterSet characterSet = contentType.getCharacterSet();
              if (characterSet != null) {
                  result.setCharacterSet(characterSet);
              }
              entityHeaderFound = true;
          } else if (header.getName().equalsIgnoreCase(
                  HttpConstants.HEADER_CONTENT_LENGTH)) {
              entityHeaderFound = true;
          } else if (header.getName().equalsIgnoreCase(
                  HttpConstants.HEADER_EXPIRES)) {
              result.setExpirationDate(HttpClientCall.parseDate(header.getValue(), false));
              entityHeaderFound = true;
          } else if (header.getName().equalsIgnoreCase(
                  HttpConstants.HEADER_CONTENT_ENCODING)) {
              final HeaderReader hr = new HeaderReader(header.getValue());
              String value = hr.readValue();
              while (value != null) {
                  final Encoding encoding = new Encoding(value);
                  if (!encoding.equals(Encoding.IDENTITY)) {
                      result.getEncodings().add(encoding);
                  }
                  value = hr.readValue();
              }
              entityHeaderFound = true;
          } else if (header.getName().equalsIgnoreCase(
                  HttpConstants.HEADER_CONTENT_LANGUAGE)) {
              final HeaderReader hr = new HeaderReader(header.getValue());
              String value = hr.readValue();
              while (value != null) {
                  result.getLanguages().add(new Language(value));
                  value = hr.readValue();
              }
              entityHeaderFound = true;
          } else if (header.getName().equalsIgnoreCase(
                  HttpConstants.HEADER_LAST_MODIFIED)) {
              result.setModificationDate(HttpClientCall.parseDate(header.getValue(), false));
              entityHeaderFound = true;
          } else if (header.getName().equalsIgnoreCase(
                  HttpConstants.HEADER_ETAG)) {
              result.setTag(Tag.parse(header.getValue()));
              entityHeaderFound = true;
          } else if (header.getName().equalsIgnoreCase(
                  HttpConstants.HEADER_CONTENT_LOCATION)) {
              result.setIdentifier(header.getValue());
              entityHeaderFound = true;
          } else if (header.getName().equalsIgnoreCase(
                  HttpConstants.HEADER_CONTENT_DISPOSITION)) {
              result.setDownloadName(parseContentDisposition(header
                      .getValue()));
              entityHeaderFound = true;
          }
      }

      if (!entityHeaderFound) {
          return null;
      }

      return result;
  }

  /* (non-Javadoc)
	 * @see org.restlet.engine.http.Util#parseContentDisposition(java.lang.String)
	 */
  public String parseContentDisposition(String value) {
      if (value != null) {
          String key = "FILENAME=\"";
          int index = value.toUpperCase().indexOf(key);
          if (index > 0) {
              return value
                      .substring(index + key.length(), value.length() - 1);
          } else {
              key = "FILENAME=";
              index = value.toUpperCase().indexOf(key);
              if (index > 0) {
                  return value
                          .substring(index + key.length(), value.length());
              }
          }
      }
      return null;
  }
}