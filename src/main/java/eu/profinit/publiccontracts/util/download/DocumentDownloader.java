package eu.profinit.publiccontracts.util.download;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public interface DocumentDownloader {

    URLConnection retrieveURLConnection() throws IOException;

    String downloadFileToString() throws IOException;

    void setUrl(URL url);

}
