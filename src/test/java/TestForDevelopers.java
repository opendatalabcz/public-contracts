import dto.CompanyDto;
import generated.isvz.mmr.schemas.vz_z_profilu_zadavatele.v100.ProfilStructure;
import org.joda.time.DateTime;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.DatabaseService;
import service.ISVZCrawlerService;
import service.ISVZService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
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

    @org.junit.Test
    public void test() throws IOException, SQLException {
        final List<CompanyDto> allSubmitters = isvzCrawlerService.findAllSubmitters();
        databaseService.saveSubmitters(allSubmitters);


    }

    @org.junit.Test
    public void test2() throws SQLException {
        final Date lastDate;
        try {
             lastDate = databaseService.loadRetrievalLastDate(2014);
        } catch (RuntimeException e) {
            return;
        }
        final List<CompanyDto> companyDtos = databaseService.loadSubmitters();
        int numberOfErrors = 0;
        for (CompanyDto companyDto : companyDtos) {
            final ProfilStructure profilStructure = isvzService.findProfilStructure(companyDto.getUrl(), 2014, lastDate);

            try {
                databaseService.saveSubmitter(profilStructure);
            } catch (Exception e) {
                numberOfErrors++;
            }

        }
        final DateTime now = DateTime.now();
        final DateTime lastDayOfTheYear = new DateTime(2014, 12, 31, 0, 0);
        final boolean after = now.isAfter(lastDayOfTheYear);

        databaseService.saveRetrieval(2014, after, lastDayOfTheYear.toDate(), numberOfErrors);

    }

    @org.junit.Test
    public void test3(){
         isvzService.findProfilStructure("https://veza.cz/Contracts.aspx/1087", 2015, null);
         isvzService.findProfilStructure("https://pro-za.cz/contracts/6d76a7", 2015, null);
         isvzService.findProfilStructure("https://www.egordion.cz/nabidkaGORDION/profilNIDV", 2015, null);
    }
}