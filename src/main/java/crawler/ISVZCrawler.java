package crawler;


import isvz.isvz.mmr.schemas.vz_z_profilu_zadavatele.v100.ProfilStructure;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import service.ISVZService;
import service.InsertIntoDBService;

import java.io.IOException;
import java.sql.SQLException;

public class ISVZCrawler {


    final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36";
    final static int TIMEOUT = 10 * 1000;

    @Autowired
    private ISVZService isvzService;
    @Autowired
    private InsertIntoDBService insertIntoDBService;
    final static Logger logger = Logger.getLogger(ISVZService.class);


    public void crawl() throws IOException, SQLException {
        Document doc = Jsoup.connect("https://vestnikverejnychzakazek.cz/cs/Searching/ShowPublicPublisherProfiles").timeout(TIMEOUT).userAgent(USER_AGENT).get();
        while (true) {
            final Elements tbody = doc.getElementsByTag("tbody");
            final Element element = tbody.get(0);
            final Elements tr = element.getElementsByTag("tr");
            for (Element submittor : tr) {
                final Elements a = submittor.getElementsByTag("a");
                final Element link = a.get(0);
                final String href = link.attr("href");

                final ProfilStructure profilStructure;

                profilStructure = isvzService.findProfilStructure(href);
                if (profilStructure == null) {
                    continue;
                }
                if (profilStructure.getZadavatel() == null) {
                    continue;
                }
                try {
                    insertIntoDBService.saveSubmitter(profilStructure);
                } catch (Exception e) {
                    logger.error(href + ", E: " + e.getMessage());
                }

            }
            final Element next = doc.getElementsByClass("t-arrow-next").get(0);
            final String href = next.parent().attr("href");
            if (href.equals("#")) {
                return;
            }
            System.out.println(href);
            doc = Jsoup.connect("https://vestnikverejnychzakazek.cz" + href).timeout(TIMEOUT).userAgent(USER_AGENT).get();
        }
    }
}
