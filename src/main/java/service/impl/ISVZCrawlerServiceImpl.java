package service.impl;

import dto.SourceInfoDto;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import service.ISVZCrawlerService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ISVZCrawlerServiceImpl implements ISVZCrawlerService {


    final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36";
    final static int TIMEOUT = 3600 * 1000;
    //huge timeout in case connection is interrupted
    final static Logger logger = Logger.getLogger(ISVZCrawlerServiceImpl.class);
    private String url;


    @Override
    public List<SourceInfoDto> findAllSubmitters() throws IOException {
        final List<SourceInfoDto> sourceInfoDtos = new ArrayList<>();
        Document doc = Jsoup.connect(url).timeout(TIMEOUT).userAgent(USER_AGENT).get();
        while (true) {
            final Elements tbody = doc.getElementsByTag("tbody");
            final Element element = tbody.get(0);
            final Elements tr = element.getElementsByTag("tr");
            for (Element submitter : tr) {
                final Element urlElementColumn = submitter.getElementsByTag("td").get(4);
                final Elements a = urlElementColumn.getElementsByTag("a");
                final Element urlElement = a.get(0);
                final String url = urlElement.attr("href");
                final Element icoElement = submitter.getElementsByTag("td").get(3);
                final String ico = icoElement.text();
                final Element nameElement = submitter.getElementsByTag("td").get(2);
                final String name = nameElement.text();
                final SourceInfoDto sourceInfoDto = new SourceInfoDto(ico, name, url);
                sourceInfoDtos.add(sourceInfoDto);
            }
            final Element nextElement = doc.getElementsByClass("k-i-arrow-e").get(0);
            final String urlToNextPage = nextElement.parent().attr("href");
            logger.info("crawling on page: " + urlToNextPage);
            if (urlToNextPage.equals("#")) {
                break;
            }
            doc = Jsoup.connect("https://vestnikverejnychzakazek.cz" + urlToNextPage).timeout(TIMEOUT).userAgent(USER_AGENT).get();
        }
        return sourceInfoDtos;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
