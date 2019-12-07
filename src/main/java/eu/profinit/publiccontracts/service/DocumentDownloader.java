package eu.profinit.publiccontracts.service;

import eu.profinit.publiccontracts.util.PropertyManager;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Interface for file content downloading from web.
 */
public interface DocumentDownloader {

    URLConnection retrieveURLConnection() throws IOException, InterruptedException;

    String getMimeType() throws IOException, InterruptedException;

    int getContentLength() throws IOException, InterruptedException;

    String downloadFileToString() throws IOException, InterruptedException;

    void setUrl(URL url);

    void setProperties(PropertyManager properties);

}
