package eu.profinit.publiccontracts.service;

import eu.profinit.publiccontracts.util.PropertyManager;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Interface for file content downloading from web.
 */
public interface DocumentDownloader {

    URLConnection retrieveURLConnection() throws IOException;

    String downloadFileToString(PropertyManager properties) throws IOException;

    void setUrl(URL url);

}
