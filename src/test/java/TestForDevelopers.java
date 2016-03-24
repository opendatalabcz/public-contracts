import crawler.ISVZCrawler;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.ISVZService;
import service.InsertIntoDBService;

import java.io.IOException;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-for-developers.xml"})
public class TestForDevelopers {
    private static final String URI_PREFIX = "https://www.softender.cz/cah/";
    private static final String URI_SUFFIX = "/XMLdataVZ?od=07042015&do=10102015";

    //
//    Cílem je vytìžit vždy kód veøejné zakázky, její název, stav, druh zadávacího øízení.
//
//    K ní všechny uchazeèe, vèetnì subdodavatelù i nabídkových cen - ty je nutné uložit jako èíslo. Nakonec je potøeba vytìžit vítìze, vèetnì vítìzných cen.

    @Autowired
    private InsertIntoDBService insertIntoDBService;
    @Autowired
    private ISVZService isvzService;
    @Autowired
    private ISVZCrawler isvzCrawler;

    @org.junit.Test
    public void test() {
        try {
            isvzCrawler.crawl();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void test2() {
            isvzService.findProfilStructure("http://soad.profilzadavatele.cz/");

    }
}
