import db.DatabaseConnectionFactory;
import dto.SourceInfoDto;
import dto.SubmitterDto;
import generated.isvz.mmr.schemas.vz_z_profilu_zadavatele.v100.ProfilStructure;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.DatabaseService;
import service.ISVZCrawlerService;
import service.ISVZService;
import util.SubmitterTransformer;
import util.TrustAllCerts;

import java.io.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws SQLException, IOException, NoSuchAlgorithmException, KeyManagementException {

        if (args.length == 0) {
            printWrongCommand();
            return;
        }

        if (args.length > 1) {
            System.out.println("Use only one argument at the time! I don't want you to load data for 8 hours and then reload database");
        }

        final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring.xml"});

        final String command = args[0];
        switch (command) {
            case "reload-db": {
                final Scanner scanner = new Scanner(System.in);
                System.out.println("Are you sure?");
                System.out.println("This command will drop and rebuild all tables related to public contract!");
                System.out.println("Write 'yes' to confirm or anything else to cancel");
                final String confirmation = scanner.nextLine();
                if (confirmation.equals("yes")) {
                    reloadDatabase(context);
                } else {
                    context.close();
                    return;
                }
                break;
            }
            case "reload-source": {
                final Scanner scanner = new Scanner(System.in);
                System.out.println("Are you sure?");
                System.out.println("This command will reload valid submitters of public contract");
                System.out.println("All data collected about public contracts will not be changed");
                System.out.println("Write 'yes' to confirm or anything else to cancel");
                final String confirmation = scanner.nextLine();
                if (confirmation.equals("yes")) {
                    reloadSources(context);
                } else {
                    context.close();
                    return;
                }
                break;
            }
            default:
                final int year;
                try {
                    year = Integer.parseInt(command);
                } catch (Exception e) {
                    printWrongCommand();
                    context.close();
                    return;
                }

                final Date date = createDateFromYear(year);
                final Date present = DateTime.now().toDate();
                if (date.after(present)) {
                    System.out.println("Wrong year! This year hasn't even been yet");
                    context.close();
                    return;
                }

                final DatabaseService databaseService = context.getBean(DatabaseService.class);
                final ISVZService isvzService = context.getBean(ISVZService.class);

                final Date lastDate;
                try {
                    lastDate = databaseService.loadRetrievalLastDate(year);
                } catch (RuntimeException e) {
                    logger.error(e.getMessage());
                    context.close();
                    return;
                }

                final List<SourceInfoDto> sourceInfoDtos = databaseService.loadSources();

                TrustAllCerts.trustAllCertificates();

                int numberOfErrors = 0;

                for (SourceInfoDto sourceInfoDto : sourceInfoDtos) {
                    final ProfilStructure profilStructure;
                    try {
                        profilStructure = isvzService.findProfilStructure(sourceInfoDto.getUrl(), year, lastDate);
                    } catch (Exception e) {
                        databaseService.saveError(sourceInfoDto, e.getMessage(), year);
                        numberOfErrors++;
                        logger.error("error during parsing " + sourceInfoDto.getName() + ", " + sourceInfoDto.getIco() + ", " + sourceInfoDto.getUrl() + "\n" + e.getMessage());
                        continue;
                    }

                    final SubmitterDto submitterDto;
                    try {
                        submitterDto = SubmitterTransformer.transformSubmitterToDto(profilStructure);
                    } catch (Exception e) {
                        databaseService.saveError(sourceInfoDto, e.getMessage(), year);
                        numberOfErrors++;
                        logger.error("error during transforming to dto " + sourceInfoDto.getName() + ", " + sourceInfoDto.getIco() + ", " + sourceInfoDto.getUrl() + "\n" + e.getMessage());
                        continue;
                    }

                    try {
                        databaseService.saveSubmitter(submitterDto);
                    } catch (Exception e) {
                        databaseService.saveError(sourceInfoDto, e.getMessage(), year);
                        numberOfErrors++;
                        logger.error("error during saving " + sourceInfoDto.getName() + ", " + sourceInfoDto.getIco() + ", " + sourceInfoDto.getUrl() + "\n" + e.getMessage());
                        continue;
                    }
                }
                final DateTime now = DateTime.now();
                final DateTime lastDayOfTheYear = new DateTime(year, 12, 31, 0, 0);
                final boolean after = now.isAfter(lastDayOfTheYear);

                databaseService.saveRetrieval(year, after, (after ? lastDayOfTheYear.toDate() : now.toDate()), numberOfErrors);
                break;
        }
        context.close();
    }

    private static Date createDateFromYear(int year) {
        final Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        return calendar.getTime();
    }

    private static void printWrongCommand() {
        System.out.println("Wrong argument!");
        System.out.println("Valid arguments are:");
        System.out.println("'reload-db' - drops all tables from database and creates schema (Do NOT use if you don't want to loose data!!!)");
        System.out.println("'reload-source' - deletes and reloads urls of submitters (ETA 50 minutes)");
        System.out.println("'yyyy' - e.g. '2015' - search and save data for all submitters for 2015 (ETA 8 hours)");
    }

    private static void reloadSources(ClassPathXmlApplicationContext context) throws SQLException, IOException {
        final DatabaseService databaseService = context.getBean(DatabaseService.class);
        final ISVZCrawlerService isvzCrawlerService = context.getBean(ISVZCrawlerService.class);
        logger.info("deleting submitters");
        databaseService.deleteSources();
        logger.info("crawling web for submitters");
        final List<SourceInfoDto> allSubmitters = isvzCrawlerService.findAllSubmitters();
        logger.info("saving submitters");
        databaseService.saveSources(allSubmitters);
    }

    private static void reloadDatabase(ClassPathXmlApplicationContext context) throws FileNotFoundException {
        final DatabaseConnectionFactory databaseConnectionFactory = context.getBean(DatabaseConnectionFactory.class);

        // Initialize object for ScripRunner
        final Connection connection = databaseConnectionFactory.getConnection();
        final ScriptRunner sr = new ScriptRunner(connection);

        final InputStream dropStream = Main.class.getResourceAsStream("sql/drop.sql");
        final Reader dropReader = new InputStreamReader(dropStream);

        sr.runScript(dropReader);

        final InputStream initStream = Main.class.getResourceAsStream("sql/init.sql");
        final Reader initReader = new InputStreamReader(initStream);

        sr.runScript(initReader);

    }
}
