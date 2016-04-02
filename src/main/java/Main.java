import dto.CompanyDto;
import generated.isvz.mmr.schemas.vz_z_profilu_zadavatele.v100.ProfilStructure;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.DatabaseService;
import service.ISVZCrawlerService;
import service.ISVZService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Main {

    final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws SQLException, IOException {
        final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring.xml"});
        final DatabaseService databaseService = context.getBean(DatabaseService.class);

        final String command = args[0];
        if (command.equals("reload")) {
            final ISVZCrawlerService isvzCrawlerService = context.getBean(ISVZCrawlerService.class);
            logger.info("deleting submitters");
            databaseService.deleteSubmitters();
            logger.info("crawling web for submitters");
            final List<CompanyDto> allSubmitters = isvzCrawlerService.findAllSubmitters();
            logger.info("saving submitters");
            databaseService.saveSubmitters(allSubmitters);

        } else {
            final ISVZService isvzService = context.getBean(ISVZService.class);

            final int year = Integer.parseInt(command);
            final Date lastDate;
            try {
                lastDate = databaseService.loadRetrievalLastDate(year);
            } catch (RuntimeException e) {
                return;
            }
            final List<CompanyDto> companyDtos = databaseService.loadSubmitters();
            int numberOfErrors = 0;
            for (CompanyDto companyDto : companyDtos) {
                final ProfilStructure profilStructure;
                try {
                    profilStructure = isvzService.findProfilStructure(companyDto.getUrl(), year, lastDate);
                } catch (Exception e) {
                    numberOfErrors++;
                    logger.error("error during parsing " + companyDto.getName() + ", " + companyDto.getIco() + ", " + companyDto.getUrl() + "\n" + e.getMessage());
                    continue;
                }
                try {
                    databaseService.saveSubmitter(profilStructure);
                } catch (Exception e) {
                    numberOfErrors++;
                    logger.error("error during saving " + companyDto.getName() + ", " + companyDto.getIco() + ", " + companyDto.getUrl() + "\n" + e.getMessage());
                    continue;
                }
            }
            final DateTime now = DateTime.now();
            final DateTime lastDayOfTheYear = new DateTime(year, 12, 31, 0, 0);
            final boolean after = now.isAfter(lastDayOfTheYear);

            databaseService.saveRetrieval(year, after, lastDayOfTheYear.toDate(), numberOfErrors);
        }

        context.close();
    }
}
