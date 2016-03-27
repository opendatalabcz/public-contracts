package service.impl;

import dto.CompanyDto;
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


    @Override
    public List<CompanyDto> findAllSubmitters() throws IOException {
        final List<CompanyDto> companyDtos = new ArrayList<>();
        Document doc = Jsoup.connect("https://vestnikverejnychzakazek.cz/cs/Searching/ShowPublicPublisherProfiles").timeout(TIMEOUT).userAgent(USER_AGENT).get();
        while (true) {
            final Elements tbody = doc.getElementsByTag("tbody");
            final Element element = tbody.get(0);
            final Elements tr = element.getElementsByTag("tr");
            for (Element submitter : tr) {
                final Elements a = submitter.getElementsByTag("a");
                final Element urlElement = a.get(0);
                final String url = urlElement.attr("href");
                final Element icoElement = submitter.getElementsByTag("td").get(3);
                final String ico = icoElement.val();
                final Element nameElement = submitter.getElementsByTag("td").get(2);
                final String name = nameElement.val();

                final CompanyDto companyDto = new CompanyDto(ico, name, url);
                companyDtos.add(companyDto);
            }
            final Element nextElement = doc.getElementsByClass("t-arrow-next").get(0);
            final String urlToNextPage = nextElement.parent().attr("href");
            logger.info("crawling on page: " + urlToNextPage);
            if (urlToNextPage.equals("#")) {
                break;
            }
            doc = Jsoup.connect("https://vestnikverejnychzakazek.cz" + urlToNextPage).timeout(TIMEOUT).userAgent(USER_AGENT).get();
        }
        return companyDtos;
    }
}
