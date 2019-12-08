package eu.profinit.publiccontracts;

import eu.profinit.publiccontracts.db.DatabaseConnectionFactory;
import eu.profinit.publiccontracts.dto.DocumentDto;
import eu.profinit.publiccontracts.dto.SourceInfoDto;
import eu.profinit.publiccontracts.dto.SubmitterDto;
import eu.profinit.publiccontracts.generated.isvz.mmr.schemas.vz_z_profilu_zadavatele.v100.ProfilStructure;
import eu.profinit.publiccontracts.service.DatabaseService;
import eu.profinit.publiccontracts.service.ISVZCrawlerService;
import eu.profinit.publiccontracts.service.ISVZService;
import eu.profinit.publiccontracts.util.*;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;

public class Main {

    static final Logger logger = Logger.getLogger(Main.class);
    public static AtomicInteger numberOfErrors = new AtomicInteger();
    public static AtomicInteger numberOfDocuments = new AtomicInteger();

    public static void main(String[] args) throws SQLException, IOException, InterruptedException {

        if (args.length == 0) {
            printWrongCommand();
            System.exit(0);
        }

        final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring.xml"});

        try {
            final String command = args[0];
            switch (command) {
                case "init": {
                    if (args.length > 1) {
                        closeAppWithWrongCommand(context);
                    }
                    initDatabase(context);
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
                    reloadErrors(args, context);
                    break;
                }
                case "fetch-ico": {
                    fetchICO(args, context);
                    break;
                }
                case "process-documents": {
                    processDocuments(context);
                    break;
                }
                default:
                    collectData(context, args);
                    break;
            }
        } catch (IllegalArgumentException e) {
            closeAppWithWrongCommand(context);
        } finally {
            context.close();
        }
    }

    private static void reloadErrors(String[] args, ClassPathXmlApplicationContext context) throws SQLException, IOException, InterruptedException {
        boolean skipFetching = false;
        if (args.length == 4 && "-skipFetching".equals(args[3])) {
            skipFetching = true;
        } else if (args.length != 3) {
            throw new IllegalArgumentException("Bad parameters were given to the program.");
        }

        String fromDate = args[1];
        String toDate = args[2];

        Calendar fromCal = Calendar.getInstance();
        Calendar toCal = Calendar.getInstance();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.FORMAT.ddMMyyyy);
            fromCal.setTime(sdf.parse(fromDate));
            toCal.setTime(sdf.parse(toDate));
        } catch (Exception e) {
            throw new IllegalArgumentException("Bad parameters were given to the program.");
        }

        DatabaseService databaseService = context.getBean(DatabaseService.class);
        ISVZService isvzService = context.getBean(ISVZService.class);
        List<SourceInfoDto> sourceInfoDtos = databaseService.loadSources();

        List<Interval> intervals = DateUtils.createMonthlyIntervals(fromCal, toCal);
        for (Interval interval: intervals) {
            String date = DateUtils.convertDateTimeToString(interval.getStart(), DateUtils.FORMAT.ddMMyyyy);

            Set<String> errorList = databaseService.loadErrorUrlsForDate(date);
            Iterator<SourceInfoDto> iterator = sourceInfoDtos.iterator();
            while (iterator.hasNext()) {
                SourceInfoDto next = iterator.next();
                if (!errorList.contains(next.getUrl())) {
                    iterator.remove();
                }
            }
            databaseService.deleteErrors(date);
        }

        collectDataInternal(fromCal, toCal, databaseService, isvzService, sourceInfoDtos, skipFetching);
    }

    private static void collectData(ClassPathXmlApplicationContext context, String[] args) throws SQLException, InterruptedException, IOException {
        boolean skipFetching = false;
        if (args.length == 3 && "-skipFetching".equals(args[2])) {
            skipFetching = true;
        } else if (args.length != 2) {
            throw new IllegalArgumentException("Bad parameters were given to the program.");
        }
        String fromDate = args[0];
        String toDate = args[1];

        Calendar fromCal = Calendar.getInstance();
        Calendar toCal = Calendar.getInstance();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.FORMAT.MMyyyy);
            fromCal.setTime(sdf.parse(fromDate));
            toCal.setTime(sdf.parse(toDate));
        } catch (Exception e) {
            printWrongCommand();
            context.close();
            System.exit(0);
            return;
        }

        if (fromCal.get(Calendar.YEAR)< 1989) {
            System.out.println("There probably aren't any data before year 1989. Dont waste your time!");
            context.close();
            System.exit(0);
        }

        final Date date = fromCal.getTime();
        final Date present = DateTime.now().toDate();
        if (date.after(present)) {
            System.out.println("Wrong year! This year hasn't even been yet");
            context.close();
            System.exit(0);
        }
        final DatabaseService databaseService = context.getBean(DatabaseService.class);
        final ISVZService isvzService = context.getBean(ISVZService.class);

        final List<SourceInfoDto> sourceInfoDtos = databaseService.loadSources();

        collectDataInternal(fromCal, toCal, databaseService, isvzService, sourceInfoDtos, skipFetching);
    }

    /**
     * Main procedure, that downloads contracts.
     *
     * @param fromCal collection start date
     * @param toCal collection end date
     * @param databaseService
     * @param isvzService
     * @param sourceInfoDtos
     * @throws IOException
     * @throws InterruptedException
     * @throws SQLException
     */
    private static void collectDataInternal(Calendar fromCal, Calendar toCal, final DatabaseService databaseService, final ISVZService isvzService, List<SourceInfoDto> sourceInfoDtos, boolean skipFetching) throws IOException, InterruptedException, SQLException {
        logger.info("processing total number of sources: " + sourceInfoDtos.size());
        final List<List<SourceInfoDto>> lists = new ArrayList<>();
        final Properties properties = ResourceManager.loadProperties();
        final String numberOfThreadsString = properties.getProperty("public-contract.thread.number");
        final int numberOfThreads = Integer.parseInt(numberOfThreadsString);
        final PropertyManager propertyManager = PropertyManager.createProperties(databaseService.loadProperties());
        for (int i = 0; i < numberOfThreads; i++) {
            lists.add(sourceInfoDtos.subList((i * sourceInfoDtos.size() / numberOfThreads), ((i + 1) * sourceInfoDtos.size() / numberOfThreads)));
        }
        final List<Interval> intervals = DateUtils.createMonthlyIntervals(fromCal, toCal);
        for (Interval interval: intervals) {
            final String fromDate = DateUtils.convertDateTimeToString(interval.getStart(), DateUtils.FORMAT.ddMMyyyy);
            final String toDate = DateUtils.convertDateTimeToString(interval.getEnd(), DateUtils.FORMAT.ddMMyyyy);
            if (databaseService.isDateCompleted(fromDate)) {
                System.out.println("Month has already been loaded! If you are trying to reload current month to gain recent data, please delete the month first. This madness will never work out for anybody");
                continue;
            }
            logger.info("processing interval: " + fromDate + " - " + toDate);
            final List<Thread> threads = new ArrayList<>();
            for (final List<SourceInfoDto> list : lists) {
                final Thread t = new Thread() {
                    public void run() {
                        String threadName = Thread.currentThread().getName();
                        int i = 0;
                        for (SourceInfoDto sourceInfoDto : list) {
                            logger.info(threadName + ":sources:" + ++i + "/" + list.size() +
                                    "(" + sourceInfoDto.getUrl() + ")");
                            if (sourceInfoDto.getIco().equals(" ")) {
                                continue;
                            }
                            final ProfilStructure profilStructure;
                            try {
                                //fetching profile
                                profilStructure = isvzService.findProfilStructure(sourceInfoDto.getUrl(), fromDate, toDate);
                            } catch (Exception e) {
                                try {
                                    final StringBuilder sb = new StringBuilder();
                                    sb.append(e.getMessage());
                                    Throwable cause = e.getCause();
                                    while (cause != null) {
                                        sb.append("\n");
                                        sb.append(cause.getMessage());
                                        cause = cause.getCause();
                                    }
                                    databaseService.saveError(sourceInfoDto, sb.toString(), fromDate, e.getClass().toString());
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
                                    databaseService.saveError(sourceInfoDto, e.getMessage(), fromDate, e.getClass().toString());
                                } catch (SQLException e1) {
                                    logger.error(e.getMessage());
                                    e1.printStackTrace();
                                }
                                numberOfErrors.incrementAndGet();
                                logger.error("error during transforming to dto " + sourceInfoDto.getName() + ", " + sourceInfoDto.getIco() + ", " + sourceInfoDto.getUrl() + "\n" + e.getMessage());
                                continue;
                            }

                            if (!skipFetching) {
                                DocumentFetcher.fetchDocuments(submitterDto, propertyManager);
                            }

                            try {
                                databaseService.saveSubmitter(submitterDto, fromDate);
                            } catch (Exception e) {
                                try {
                                    databaseService.saveError(sourceInfoDto, e.getMessage(), fromDate, e.getClass().toString());
                                } catch (SQLException e1) {
                                    e1.printStackTrace();
                                }
                                numberOfErrors.incrementAndGet();
                                logger.error("error during saving " + sourceInfoDto.getName() + ", " + sourceInfoDto.getIco() + ", " + sourceInfoDto.getUrl() + "\n" + e.getMessage());
                                continue;
                            }
                        }
                        logger.info(threadName + ":done");
                    }
                };
                t.start();
                threads.add(t);
            }

            for (Thread thread : threads) {
                thread.join();
            }
            final DateTime now = DateTime.now();
            final DateTime lastDayOfTheYear = now;
            final boolean after = now.isAfter(lastDayOfTheYear);
            final int numberOfSources = databaseService.loadSources().size();
            final int numberOfErrors = Main.numberOfErrors.intValue();
            final int numberOfDocuments = Main.numberOfDocuments.intValue();
            databaseService.saveRetrieval(fromDate, after, (after ? lastDayOfTheYear.toDate() : now.toDate()), numberOfErrors, numberOfSources - numberOfErrors, numberOfDocuments);
            resetCounters();
        }
    }

    /**
     * Initializing procedure for processing the documents.
     *
     * @param context
     * @throws SQLException
     * @throws IOException
     * @throws InterruptedException
     */
    private static void processDocuments(ClassPathXmlApplicationContext context) throws SQLException, IOException, InterruptedException {
        final DatabaseService databaseService = context.getBean(DatabaseService.class);

        final List<DocumentDto> documentDtos = databaseService.loadDocuments();

        processDocumentsInternal(databaseService, documentDtos);
    }

    /**
     * The procedure that downloads and processes documents.
     *
     * @param databaseService
     * @param documentDtos documents to be processed
     * @throws IOException
     * @throws InterruptedException
     * @throws SQLException
     */
    private static void processDocumentsInternal(final DatabaseService databaseService, List<DocumentDto> documentDtos) throws IOException, InterruptedException, SQLException {
        logger.info("processing total number of documents: " + documentDtos.size());
        final List<List<DocumentDto>> lists = new ArrayList<>();
        final Properties properties = ResourceManager.loadProperties();
        final String numberOfThreadsString = properties.getProperty("public-contract.thread.number");
        final int numberOfThreads = Integer.parseInt(numberOfThreadsString);
        final PropertyManager propertyManager = PropertyManager.createProperties(databaseService.loadProperties());
        for (int i = 0; i < documentDtos.size(); i+=10) {
            lists.add(documentDtos.subList(i, i + 10 > documentDtos.size() ? documentDtos.size() : i + 10));
        }
        AtomicInteger partition = new AtomicInteger(0);
        final List<Thread> threads = new ArrayList<>();
        for (int thread = 0; thread < numberOfThreads; thread++) {
            final Thread t = new Thread() {
                public void run() {
                    String threadName = Thread.currentThread().getName();
                    int part = partition.getAndIncrement();
                    while (part < lists.size()) {
                        List<DocumentDto> list = lists.get(part);
                        int i = 0;
                        for (DocumentDto documentDto : list) {
                            logger.info(threadName + ":part:" + part + "/" + lists.size() + ":document:" + ++i + "/" + list.size() +
                                    "(" + documentDto.getUrl() + ")");
                            DocumentFetcher.fetchDocument(documentDto, propertyManager);
                            try {
                                databaseService.saveDocument(null, documentDto);
                            } catch (SQLException e) {
                                logger.error(e.getMessage());
                            }
                        }
                        part = partition.getAndIncrement();
                    }
                    logger.info(threadName + ":done");
                }
            };
            t.start();
            threads.add(t);
        }
        for (Thread thread : threads) {
            thread.join();
        }
        final DateTime now = DateTime.now();
        final int numberOfDocuments = Main.numberOfDocuments.intValue();
        databaseService.saveRetrieval(null, true, now.toDate(), 0,0, numberOfDocuments);
        resetCounters();
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

    private static Date createDateFromYear(int year) {
        final Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        return calendar.getTime();
    }

    private static void printWrongCommand() {
        System.out.println("Wrong argument!");
        System.out.println("Valid arguments are:");
        System.out.println("'init' - update existing db from other project to public contract (run only once)");
        System.out.println("'reload-sources' - deletes and reloads urls of submitters (ETA 20 minutes)");
        System.out.println("'reload-errors yyyy [-skipFetching]' - tries to collect data that failed before");
        System.out.println("'fetch-ico ico [-skipFetching]' - collect all years for selected ico");
        System.out.println("'mmyyyy mmyyyy [-skipFetching]' - e.g. '012017 062017' - search and save data for active submitters for time interval from the first day of the first given month to the last day of the second given month");
        System.out.println("'process-documents' - processes (downloads and extracts) documents flagged in the database to be processed");
        System.out.println("Paramaters:");
        System.out.println("-skipFetching - suppresses the processing of document (downloading and extraction), so only the documents links are scrapped");
    }

    private static void initDatabase(ClassPathXmlApplicationContext context) throws FileNotFoundException {
        final DatabaseConnectionFactory databaseConnectionFactory = context.getBean(DatabaseConnectionFactory.class);

        // Initialize object for ScripRunner
        final Connection connection = databaseConnectionFactory.getConnection();
        final ScriptRunner sr = new ScriptRunner(connection);

        final InputStream initStream = Main.class.getResourceAsStream("sql/init.sql");
        final InputStream dataStream = Main.class.getResourceAsStream("sql/data.sql");
        final Reader initReader = new InputStreamReader(initStream);
        final Reader dataReader = new InputStreamReader(dataStream);

        sr.runScript(initReader);
        sr.runScript(dataReader);
    }

    private static void fetchICO(String[] args, ClassPathXmlApplicationContext context) throws IOException, InterruptedException {
        boolean skipFetching = false;
        if (args.length == 3 && "-skipFetching".equals(args[2])) {
            skipFetching = true;
        } else if (args.length != 2) {
            throw new IllegalArgumentException("Bad parameters were given to the program.");
        }

        String ico = args[1];
        final DatabaseService databaseService = context.getBean(DatabaseService.class);
        final ISVZService isvzService = context.getBean(ISVZService.class);

        try {
            //this is important
            databaseService.getSubmitter(ico);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        Calendar fromCal = Calendar.getInstance();
        fromCal.set(2010,1,1);
        Calendar toCal = Calendar.getInstance();
        try {
            final List<SourceInfoDto> sourceInfoDtos = databaseService.loadSource(ico);

            collectDataInternal(fromCal, toCal, databaseService, isvzService, sourceInfoDtos, skipFetching);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void resetCounters() {
        numberOfErrors = new AtomicInteger();
        numberOfDocuments = new AtomicInteger();
    }

}
