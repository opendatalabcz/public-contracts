import isvz.isvz.mmr.schemas.vz_z_profilu_zadavatele.v100.ProfilStructure;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.ISVZService;
import service.InsertIntoDBService;

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

    @org.junit.Test
    public void test() {
        final ProfilStructure profilStructure = isvzService.findProfilStructure("https://www.softender.cz/cah/");
        try {
            insertIntoDBService.saveSubmitter(profilStructure);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
