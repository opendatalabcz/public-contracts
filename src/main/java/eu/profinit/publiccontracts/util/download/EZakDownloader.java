package eu.profinit.publiccontracts.util.download;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class EZakDownloader extends DefaultDownloader {

    public EZakDownloader() {

    }

    public EZakDownloader(URL url) {
        super(url);
    }

    @Override
    public URLConnection retrieveURLConnection() throws IOException {
        this.urlConnection = (HttpURLConnection) url.openConnection();
        int responseCode = urlConnection.getResponseCode();
        if (responseCode != 200) {
            throw new IllegalArgumentException(url.toString() + " cannot access data: " + responseCode);
        }
        String contentType = urlConnection.getContentType();
        if (contentType == null) {
            throw new IllegalArgumentException(url.toString() + " does not have supported format");
        }
        if (contentType.toLowerCase().contains("text/html")) {
            String newUrlString = searchForDocumentLink();
            url = new URL(newUrlString);
            return retrieveURLConnection();
        }
        return urlConnection;
    }

    private String searchForDocumentLink() throws IOException {
        Document document = Jsoup.connect(url.toString())
                .timeout(0).get();
        Element element = document.getElementById("actual_document_table");
        Elements ahrefs = element.getElementsByTag("a");
        for (Element ahref: ahrefs) {
            String href = ahref.attr("href");
            return completeLinkWithFile(url.toString(), href);
        }
        return null;
    }

    public static String completeLinkWithFile(String oldUrl, String file) {
        String newUrl = oldUrl.replaceFirst("[^/]*$", file);
        return newUrl;
    }

}
