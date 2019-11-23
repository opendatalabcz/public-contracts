package eu.profinit.publiccontracts.service.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Extends the {@link DefaultDownloader} with web crawling for the final link to the document.
 * Usable for E-ZAK sources, eg. https://ezak.mzp.cz/profile_display_2.html
 */
public class EZakDownloader extends DefaultDownloader {

    public EZakDownloader() {

    }

    public EZakDownloader(URL url) {
        super(url);
    }

    /**
     * Retrieves HTTP URL connection for the URL.
     * Crawls deeper for the link to the target document.
     * @return URLConnection
     * @throws IOException, {@link IllegalArgumentException}
     */
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

    /**
     * Searches the web page for the link to the target document.
     * @return link to the document
     * @throws IOException
     */
    protected String searchForDocumentLink() throws IOException {
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

    /**
     * Replaces the location of the URL file with new one.
     * @param oldUrl link to the old url file
     * @param file new url file
     * @return
     */
    public static String completeLinkWithFile(String oldUrl, String file) {
        String newUrl = oldUrl.replaceFirst("[^/]*$", file);
        return newUrl;
    }

}
