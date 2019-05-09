package eu.profinit.publiccontracts.service.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Extends the {@link DefaultDownloader} with http redirection with cookie handling.
 * Usable for NIPEZ sources, eg. https://nen.nipez.cz/profil/MPO
 */
public class NenNipezDownloader extends DefaultDownloader {

    public NenNipezDownloader() {}

    public NenNipezDownloader(URL url) {
        super(url);
    }

    /**
     * Retrieves HTTP URL connection for the URL.
     * Manages http redirection with http header properties handling.
     * @return URLConnection
     * @throws IOException, {@link IllegalArgumentException}
     */
    @Override
    public URLConnection retrieveURLConnection() throws IOException {
        Map<String, String> requestProperties = retrieveProperties();
        this.urlConnection = (HttpURLConnection) url.openConnection();
        setRequestProperties(requestProperties);
        int responseCode = urlConnection.getResponseCode();
        if (responseCode != 200) {
            throw new IllegalArgumentException(url.toString() + " cannot access data: error " + responseCode);
        }
        return urlConnection;
    }

    /**
     * Sets cookie properties to the URL request header.
     * @param requestProperties
     */
    public void setRequestProperties(Map<String, String> requestProperties) {
        for (String key: requestProperties.keySet()) {
            if ("Set-Cookie".equals(key)) {
                urlConnection.addRequestProperty("Cookie", requestProperties.get(key));
            }
        }
    }

    /**
     * Retrieves properties from http response header the URL connection.
     * @return key-value map of the properties
     * @throws IOException
     */
    public Map<String, String> retrieveProperties() throws IOException {
        HttpURLConnection tmpConnection = (HttpURLConnection)url.openConnection();
        tmpConnection.setInstanceFollowRedirects(false);
        int responseCode = tmpConnection.getResponseCode();
        if (responseCode >= 400) {
            throw new IllegalArgumentException(url.toString() + " cannot access data: error " + responseCode);
        }

        Map<String, String> requestProperties = new HashMap<>();
        Map<String, List<String>> headerFields = tmpConnection.getHeaderFields();
        for (String propertyKey: headerFields.keySet()) {
            if (propertyKey == null) {
                continue;
            }
            List<String> values = headerFields.get(propertyKey);
            StringJoiner propertyBuilder = new StringJoiner(";");
            if ("Set-Cookie".equals(propertyKey)) {
                propertyBuilder.add("TSCGUID=44012c4c-70b4-4485-813b-506937a2b88b");
            }
            for (String value: values) {
                propertyBuilder.add(value);
            }
            String propertyValue = propertyBuilder.toString();
            requestProperties.put(propertyKey, propertyValue);
        }
        return requestProperties;
    }

}
