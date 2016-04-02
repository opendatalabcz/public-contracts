import dto.CompanyDto;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.DatabaseService;
import service.ISVZCrawlerService;
import service.ISVZService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-for-developers.xml"})
public class TestForDevelopers {

    @Autowired
    private DatabaseService databaseService;
    @Autowired
    private ISVZService isvzService;
    @Autowired
    private ISVZCrawlerService isvzCrawlerService;

    @Ignore
    @org.junit.Test
    public void test() throws IOException, SQLException {
        final List<CompanyDto> allSubmitters = isvzCrawlerService.findAllSubmitters();
        databaseService.saveSubmitters(allSubmitters);


    }

    @Ignore
    @org.junit.Test
    public void test3(){
        try {
            isvzService.findProfilStructure("https://veza.cz/Contracts.aspx/1087", 2015, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            isvzService.findProfilStructure("https://pro-za.cz/contracts/6d76a7", 2015, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            isvzService.findProfilStructure("https://www.egordion.cz/nabidkaGORDION/profilNIDV", 2015, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}