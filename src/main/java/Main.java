import db.DatabaseConnectionFactory;
import dto.SourceInfoDto;
import dto.SubmitterDto;
import generated.isvz.mmr.schemas.vz_z_profilu_zadavatele.v100.ProfilStructure;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import service.DatabaseService;
import service.ISVZCrawlerService;
import service.ISVZService;
import util.SubmitterTransformer;

import java.io.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    final static Logger logger = Logger.getLogger(Main.class);
    private static AtomicInteger numberOfErrors = new AtomicInteger();

    public static void main(String[] args) throws SQLException, IOException, NoSuchAlgorithmException, KeyManagementException, InterruptedException {

        if (args.length == 0) {
            printWrongCommand();
            System.exit(0);
        }

        final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring.xml"});

        final String command = args[0];
        switch (command) {
            case "reload-db": {
                if (args.length > 1) {
                    closeAppWithWrongCommand(context);
                }
                reloadDb(context);
                break;
            }

            case "delete-collected-data": {
                if (args.length > 2) {
                    closeAppWithWrongCommand(context);
                }
                deleteCollectedData(args, context);
                break;
            }
            case "reload-sources": {
                if (args.length > 1) {
                    closeAppWithWrongCommand(context);
                }
                reloadSources(context);
                break;
            }
            case "reload-errors": {
                if (args.length != 2) {
                    closeAppWithWrongCommand(context);
                }
                reloadErrors(args, context);
                break;
            }
            default:
                if (args.length > 1) {
                    closeAppWithWrongCommand(context);
                }
                collectData(context, command);
                break;
        }
        context.close();
    }

    private static void reloadErrors(String[] args, ClassPathXmlApplicationContext context) throws SQLException, IOException, InterruptedException {
        final Integer year;
        if (args.length == 2) {
            try {
                year = Integer.parseInt(args[1]);

            } catch (Exception e) {
                context.close();
                printWrongCommand();
                System.exit(0);
                return;
            }
            final DatabaseService databaseService = context.getBean(DatabaseService.class);
            final ISVZService isvzService = context.getBean(ISVZService.class);

            final Set<String> errorList = databaseService.loadErrorUrlsForYear(year);
            final List<SourceInfoDto> sourceInfoDtos = databaseService.loadSources();
            final Iterator<SourceInfoDto> iterator = sourceInfoDtos.iterator();
            while (iterator.hasNext()) {
                final SourceInfoDto next = iterator.next();
                if (!errorList.contains(next.getUrl())) {
                    iterator.remove();
                }
            }
            databaseService.deleteErrors(year);

            collectDataInternal(year, databaseService, isvzService, sourceInfoDtos);
        } else {
            context.close();
            printWrongCommand();
            System.exit(0);
        }
    }


    private static void deleteCollectedData(String[] args, ClassPathXmlApplicationContext context) throws SQLException {
        final Integer year;
        if (args.length == 2) {
            try {
                year = Integer.parseInt(args[1]);
            } catch (Exception e) {
                context.close();
                printWrongCommand();
                System.exit(0);
                return;
            }
        } else {
            year = null;
        }

        final Scanner scanner = new Scanner(System.in);
        System.out.println("Are you sure?");
        if (year == null) {
            System.out.println("This command will delete all collected data exept sources with urls!");
        } else {
            System.out.println("This command will delete all collected for year '" + year + "' data exept sources with urls!");

        }
        System.out.println("Write 'yes' to confirm or 'no' to cancel");
        final String confirmation = scanner.nextLine();
        if (confirmation.equals("yes")) {
            final DatabaseService databaseService = context.getBean(DatabaseService.class);
            databaseService.deleteCollectedData(year);
        } else {
            context.close();
            System.exit(0);
        }
    }

    private static void collectData(ClassPathXmlApplicationContext context, String command) throws SQLException, InterruptedException, IOException {
        final int year;
        try {
            year = Integer.parseInt(command);
        } catch (Exception e) {
            printWrongCommand();
            context.close();
            System.exit(0);
            return;
        }

        if (year < 1989) {
            System.out.println("There probably aren't any data before year 1989. Dont waste your time!");
            context.close();
            System.exit(0);
        }

        final Date date = createDateFromYear(year);
        final Date present = DateTime.now().toDate();
        if (date.after(present)) {
            System.out.println("Wrong year! This year hasn't even been yet");
            context.close();
            System.exit(0);
        }
        final DatabaseService databaseService = context.getBean(DatabaseService.class);
        if(databaseService.isYearCompleted(year)){
            System.out.println("Year has already been loaded! If you are trying to reload current year to gain recent data, please delete the year first. This madness will never work out for anybody");
            context.close();
            System.exit(0);
        }
        final ISVZService isvzService = context.getBean(ISVZService.class);

        final List<SourceInfoDto> sourceInfoDtos = databaseService.loadSources();

        collectDataInternal(year, databaseService, isvzService, sourceInfoDtos);
    }

    private static void collectDataInternal(final int year, final DatabaseService databaseService, final ISVZService isvzService, List<SourceInfoDto> sourceInfoDtos) throws IOException, InterruptedException, SQLException {
        final List<List<SourceInfoDto>> lists = new ArrayList<>();
        final Resource resource = new ClassPathResource("/public-contract.properties");
        final Properties properties = PropertiesLoaderUtils.loadProperties(resource);
        final String numberOfThreadsString = properties.getProperty("public-contract.thread.number");
        final int numberOfThreads = Integer.parseInt(numberOfThreadsString);
        for (int i = 0; i < numberOfThreads; i++) {
            lists.add(sourceInfoDtos.subList((i * sourceInfoDtos.size() / numberOfThreads), ((i + 1) * sourceInfoDtos.size() / numberOfThreads)));
        }
        final List<Thread> threads = new ArrayList<>();
        for (final List<SourceInfoDto> list : lists) {
            final Thread t = new Thread() {
                public void run() {
                    for (SourceInfoDto sourceInfoDto : list) {
                        final ProfilStructure profilStructure;
                        try {
                            profilStructure = isvzService.findProfilStructure(sourceInfoDto.getUrl(), year);
                        } catch (Exception e) {
                            try {
                                final StringBuilder sb = new StringBuilder();
                                sb.append(e.getMessage());
                                Throwable cause = e.getCause();
                                while (cause !=null){
                                    sb.append("\n");
                                    sb.append(cause.getMessage());
                                    cause = cause.getCause();
                                }
                                databaseService.saveError(sourceInfoDto, sb.toString(), year, e.getClass().toString());
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }
                            numberOfErrors.incrementAndGet();
                            logger.error("error during parsing " + sourceInfoDto.getName() + ", " + sourceInfoDto.getIco() + ", " + sourceInfoDto.getUrl() + "\n" + e.getMessage());
                            continue;
                        }

                        final SubmitterDto submitterDto;
                        try {
                            submitterDto = SubmitterTransformer.transformSubmitterToDto(profilStructure);
                        } catch (Exception e) {
                            try {
                                databaseService.saveError(sourceInfoDto, e.getMessage(), year, e.getClass().toString());
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }
                            numberOfErrors.incrementAndGet();
                            logger.error("error during transforming to dto " + sourceInfoDto.getName() + ", " + sourceInfoDto.getIco() + ", " + sourceInfoDto.getUrl() + "\n" + e.getMessage());
                            continue;
                        }

                        try {
                            databaseService.saveSubmitter(submitterDto, year);
                        } catch (Exception e) {
                            try {
                                databaseService.saveError(sourceInfoDto, e.getMessage(), year, e.getClass().toString());
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }
                            numberOfErrors.incrementAndGet();
                            logger.error("error during saving " + sourceInfoDto.getName() + ", " + sourceInfoDto.getIco() + ", " + sourceInfoDto.getUrl() + "\n" + e.getMessage());
                            continue;
                        }
                    }
                }

            };
            t.start();
            threads.add(t);
        }

        for (Thread thread : threads) {
            thread.join();
        }
        final DateTime now = DateTime.now();
        final DateTime lastDayOfTheYear = new DateTime(year, 12, 31, 0, 0);
        final boolean after = now.isAfter(lastDayOfTheYear);
        databaseService.saveRetrieval(year, after, (after ? lastDayOfTheYear.toDate() : now.toDate()), numberOfErrors.intValue());
    }

    private static void closeAppWithWrongCommand(ClassPathXmlApplicationContext context) {
        context.close();
        printWrongCommand();
        System.exit(0);
    }

    private static void reloadSources(ClassPathXmlApplicationContext context) throws SQLException, IOException {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Are you sure?");
        System.out.println("This command will reload valid submitters of public contract");
        System.out.println("All data collected about public contracts will not be changed");
        System.out.println("Write 'yes' to confirm or 'no' to cancel");
        final String confirmation = scanner.nextLine();
        if (confirmation.equals("yes")) {
            final DatabaseService databaseService = context.getBean(DatabaseService.class);
            final ISVZCrawlerService isvzCrawlerService = context.getBean(ISVZCrawlerService.class);
            logger.info("deleting submitters");
            databaseService.deleteSources();
            logger.info("crawling web for submitters");
            final List<SourceInfoDto> allSubmitters = isvzCrawlerService.findAllSubmitters();
            logger.info("saving submitters");
            databaseService.saveSources(allSubmitters);
        } else {
            context.close();
            System.exit(0);
        }
    }


    private static void reloadDb(ClassPathXmlApplicationContext context) throws FileNotFoundException {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Are you sure?");
        System.out.println("This command will drop and rebuild all tables related to public contract!");
        System.out.println("Write 'yes' to confirm or 'no' to cancel");
        final String confirmation = scanner.nextLine();
        if (confirmation.equals("yes")) {
            reloadDatabase(context);
        } else {
            context.close();
            System.exit(0);
        }
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
        System.out.println("'reload-sources' - deletes and reloads urls of submitters (ETA 20 minutes)");
        System.out.println("'delete-collected-data [yyyy]' - delete all collected data except sources with urls, [yyyy] is optional and is used to delete data only for that year");
        System.out.println("'reload-errors yyyy' - tries to collect data that failed before");
        System.out.println("'yyyy' - e.g. '2015' - search and save data for all submitters for 2015");
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
