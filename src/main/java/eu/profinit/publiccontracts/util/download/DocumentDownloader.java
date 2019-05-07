package eu.profinit.publiccontracts.util.download;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

public interface DocumentDownloader {

    URLConnection retrieveURLConnection() throws IOException;

    String downloadFileToString(Map<String,String> supportedTypes) throws IOException;

    void setUrl(URL url);

}
