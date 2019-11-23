package eu.profinit.publiccontracts.service.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

/**
 * Extends the {@link DefaultDownloader} with web crawling for the final link to the document.
 * Usable for E-ZAK sources, eg. https://ezak.mzp.cz/profile_display_2.html
 */
public class GeminDownloader extends EZakDownloader {

    public GeminDownloader() {

    }

    public GeminDownloader(URL url) {
        super(url);
    }

    /**
     * Searches the web page for the link to the target document.
     * @return link to the document
     * @throws IOException
     */
    @Override
    protected String searchForDocumentLink() throws IOException {
        Document document = Jsoup.connect(url.toString())
                .timeout(0).get();
        Element element = document.getElementById("content");
        Elements ahrefs = element.getElementsByTag("a");
        for (Element ahref: ahrefs) {
            return ahref.attr("href");
        }
        return null;
    }

}
