package eu.profinit.publiccontracts.util.download;

import eu.profinit.publiccontracts.util.PropertyManager;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public interface DocumentDownloader {

    URLConnection retrieveURLConnection() throws IOException;

    String downloadFileToString(PropertyManager properties) throws IOException;

    void setUrl(URL url);

}
