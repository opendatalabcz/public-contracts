import com.drew.lang.Charsets;
import com.google.common.io.Resources;
import eu.profinit.publiccontracts.db.DatabaseConnectionFactory;
import eu.profinit.publiccontracts.dto.ContractDto;
import eu.profinit.publiccontracts.dto.DocumentDto;
import eu.profinit.publiccontracts.dto.SourceInfoDto;
import eu.profinit.publiccontracts.dto.SubmitterDto;
import eu.profinit.publiccontracts.generated.isvz.mmr.schemas.vz_z_profilu_zadavatele.v100.ProfilStructure;
import eu.profinit.publiccontracts.generated.isvz.mmr.schemas.vz_z_profilu_zadavatele.v100.VZStructure;
import eu.profinit.publiccontracts.generated.isvz.mmr.schemas.vz_z_profilu_zadavatele.v100.ZakazkaStructure;
import eu.profinit.publiccontracts.service.ISVZCrawlerService;
import eu.profinit.publiccontracts.service.ISVZService;
import eu.profinit.publiccontracts.service.impl.DefaultDownloader;
import eu.profinit.publiccontracts.util.DocumentFetcher;
import eu.profinit.publiccontracts.util.PropertyManager;
import eu.profinit.publiccontracts.util.SubmitterTransformer;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.ocr.TesseractOCRConfig;
import org.apache.tika.parser.pdf.PDFParserConfig;
import org.apache.tika.sax.BodyContentHandler;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xml.sax.SAXException;

import javax.imageio.ImageIO;
import javax.xml.bind.JAXBException;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-for-developers.xml"})
public class TestForDevelopers {

    @Autowired
    private DatabaseConnectionFactory databaseConnectionFactory;

    @Autowired
    private ISVZCrawlerService isvzCrawlerService;

    @Autowired
    private ISVZService isvzService;

    @Ignore
    @Test
    public void test() {

    }

    @Test
    public void testDB() {
        final Connection connection = databaseConnectionFactory.getConnection();
        final PreparedStatement preparedStatement;
        try {
            List<String> tables = new ArrayList<>();
            preparedStatement = connection.prepareStatement("SELECT tablename FROM pg_catalog.pg_tables WHERE schemaname = 'public';");
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String tablename = resultSet.getString("tablename");
                tables.add(tablename);
            }
            assert tables.contains("entity");
            assert tables.contains("retrieval");
            assert tables.contains("submitter");
            assert tables.contains("contract");
            assert tables.contains("candidate");
            assert tables.contains("supplier");
            assert tables.contains("subsupplier");
            assert tables.contains("error");
            assert tables.contains("source");
            assert tables.contains("document");
            assert tables.contains("parameter");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void testVestnikverejnychzakazekCrawler() {
        try {
            final List<SourceInfoDto> submitters = isvzCrawlerService.findSubmitters();
            assert submitters.size()==50;
            for (SourceInfoDto submitter : submitters) {
                assert submitter.getUrl() != null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testProfilStructure() {
        try {
            ProfilStructure profilStructure = isvzService.findProfilStructure("https://ezak.mzp.cz/profile_display_2.html", "012018", "122018");
            assert "489950".equals(profilStructure.getProfilKod().getValue());
            assert "00164801".equals(profilStructure.getZadavatel().getIcoVlastni().getValue());
            List<ZakazkaStructure> zakazka = profilStructure.getZakazka();
            assert zakazka.size() == 179;
            ZakazkaStructure zakazkaStructure = zakazka.get(0);
            assert "26490951".equals(zakazkaStructure.getDodavatel().get(0).getIco().getValue());
            assert "05732051".equals(zakazkaStructure.getUcastnik().get(0).getIco().getValue());
            assert BigDecimal.valueOf(1949310.00).compareTo(zakazkaStructure.getUcastnik().get(0).getCenaSDph().getValue()) == 0;
            VZStructure vz = zakazkaStructure.getVZ();
            assert vz.getDokument().size() == 2;
            assert "https://ezak.mzp.cz/document_download_35272.html".equals(vz.getDokument().get(0).getUrl().getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSubmitterTransformer() {
        try {
            String profilBody = readResourceFile("reference/test_profil_489950_2018.xml");
            ProfilStructure profilStructure = isvzService.transformProfilBody(profilBody);
            SubmitterDto submitterDto = SubmitterTransformer.transformSubmitterToDto(profilStructure);
            assert "00164801".equals(submitterDto.getIco());
            ContractDto contractDto = submitterDto.getContractDtos().get(0);
            assert "zakázka malého rozsahu".equals(contractDto.getKind());
            assert contractDto.getCandidateDtos().size() == 9;
            assert contractDto.getCandidateDtos().get(0).getPrice() == 1949310.0;
            assert "05732051".equals(contractDto.getCandidateDtos().get(0).getIco());
            assert contractDto.getSupplierDtos().size() == 1;
            assert contractDto.getSupplierDtos().get(0).getPrice() == 1028016.0;
            assert "26490951".equals(contractDto.getSupplierDtos().get(0).getIco());
            assert contractDto.getDocumentDtos().size() == 2;
            DocumentDto documentDto = contractDto.getDocumentDtos().get(0);
            assert "https://ezak.mzp.cz/document_download_35272.html".equals(documentDto.getUrl());
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDocumentFetcherEzak() {
        try {
            PropertyManager propertyManager = getPropertyManagerStub();
            DocumentDto documentDto = new DocumentDto();
            String referenceDocument = readResourceFile("reference/test_document_ezak_35272.txt");
            documentDto.setUrl("https://ezak.mzp.cz/document_download_35272.html");
            String document = DocumentFetcher.fetchDocument(documentDto, propertyManager);
            assert referenceDocument.equals(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDocumentFetcherNenNipez() {
        try {
            PropertyManager propertyManager = getPropertyManagerStub();
            DocumentDto documentDto = new DocumentDto();
            String referenceDocument = readResourceFile("reference/test_document_nennipez_395220285.txt");
            documentDto.setUrl("https://nen.nipez.cz/Soubor.aspx?id=395220285&typ=.pdf&velikost=643670B");
            String document = DocumentFetcher.fetchDocument(documentDto, propertyManager);
            assert referenceDocument.equals(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDocumentFetcherTendermarket() {
        try {
            PropertyManager propertyManager = getPropertyManagerStub();
            DocumentDto documentDto = new DocumentDto();
            String referenceDocument = readResourceFile("reference/test_document_tendermarket_1149320.txt");
            documentDto.setUrl("https://www.tenderarena.cz/filedownloadservlet.do?idSouboru=1149320");
            String document = DocumentFetcher.fetchDocument(documentDto, propertyManager);
            assert referenceDocument.equals(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDocumentFetcherVhodneUverejneni() {
        try {
            PropertyManager propertyManager = getPropertyManagerStub();
            DocumentDto documentDto = new DocumentDto();
            String referenceDocument = readResourceFile("reference/test_document_vhodneuverejneni_1950922.txt");
            documentDto.setUrl("https://www.vhodne-uverejneni.cz/index.php?m=xenorders&h=orderdocument&a=detail&document=1950922");
            String document = DocumentFetcher.fetchDocument(documentDto, propertyManager);
            assert referenceDocument.equals(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Ignore
    public void testDocumentFetcher() {
        try {
            PropertyManager propertyManager = getPropertyManagerStub();
            DocumentDto documentDto = new DocumentDto();
            documentDto.setUrl("https://zakazky.cuni.cz/document_download_16605.html");
            String document = DocumentFetcher.fetchDocument(documentDto, propertyManager);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Ignore
    public void tikaPdfReadTest() throws TikaException, SAXException, IOException {
        Parser parser = new AutoDetectParser();
        Metadata metadata = new Metadata();
        BodyContentHandler handler = new BodyContentHandler(Integer.MAX_VALUE);

        TesseractOCRConfig config = new TesseractOCRConfig();
        config.setLanguage("ces");
        PDFParserConfig pdfConfig = new PDFParserConfig();
        pdfConfig.setExtractInlineImages(true);

        // To parse images in files those lines are needed
        ParseContext parseContext = new ParseContext();
        parseContext.set(TesseractOCRConfig.class, config);
        parseContext.set(PDFParserConfig.class, pdfConfig);
        parseContext.set(Parser.class, parser); // need to add this to make sure
        parseContext.set(Parser.class, parser); // need to add this to make sure
        // recursive parsing happens!

        FileInputStream stream = new FileInputStream("C:\\Users\\mvancl\\Documents\\temp\\diplomka\\dokumenty\\e-zakazky\\2e9ebf7e-0a64-47e7-80d4-2970b80738d8\\P14V00000001\\smlouva o dílo, plošina.pdf");
        parser.parse(stream, handler, metadata, parseContext);
        String text = handler.toString();
        System.out.println(text);
    }

    @Test
    @Ignore
    public void tikaPdfToImageReadTest() throws TikaException, SAXException, IOException {

        PDDocument document = PDDocument.load(new File("C:\\Users\\mvancl\\Documents\\temp\\diplomka\\dokumenty\\vhodne-uverejneni\\zakladni-skola-velke-hamry-skolni-541-prispevkova-organizace\\odborne-ucebny-v-zs-velke-hamry-stavebni-prace-ucebny-a-remeslna-dilna\\Kupní smlouva STAVO-UNION.pdf"));
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        for (int page = 0; page < document.getNumberOfPages(); ++page) {
            BufferedImage bim = pdfRenderer.renderImageWithDPI(
                    page, 300, ImageType.RGB);

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(bim, "jpeg", os);
            InputStream is = new ByteArrayInputStream(os.toByteArray());

            Parser parser = new AutoDetectParser();
            Metadata metadata = new Metadata();
            BodyContentHandler handler = new BodyContentHandler(Integer.MAX_VALUE);

            TesseractOCRConfig config = new TesseractOCRConfig();
            config.setLanguage("ces");
            PDFParserConfig pdfConfig = new PDFParserConfig();
            pdfConfig.setExtractInlineImages(true);

            // To parse images in files those lines are needed
            ParseContext parseContext = new ParseContext();
            parseContext.set(TesseractOCRConfig.class, config);
            parseContext.set(PDFParserConfig.class, pdfConfig);
            parseContext.set(Parser.class, parser); // need to add this to make sure
            // recursive parsing happens!

            parser.parse(is, handler, metadata, parseContext);
            String text = handler.toString();
            System.out.println(text);

        }
        document.close();

    }

    @Test
    @Ignore
    public void parseFromPdfThroughImageTest() throws IOException {
        FileInputStream stream = new FileInputStream("C:\\Users\\mvancl\\Documents\\temp\\diplomka\\dokumenty\\vhodne-uverejneni\\zakladni-skola-velke-hamry-skolni-541-prispevkova-organizace\\odborne-ucebny-v-zs-velke-hamry-stavebni-prace-ucebny-a-remeslna-dilna\\Kupní smlouva STAVO-UNION.pdf");
        DefaultDownloader downloader = new DefaultDownloader();
        String text = downloader.parseFromPdfThroughImage(stream);
        System.out.println(text);
    }

    private PropertyManager getPropertyManagerStub() {
        PropertyManager propertyManager = new PropertyManager();
        propertyManager.putValue(PropertyManager.DOWNLOAD_RULE, "ezak", "eu.profinit.publiccontracts.service.impl.EZakDownloader");
        propertyManager.putValue(PropertyManager.DOWNLOAD_RULE, "nen.nipez.cz", "eu.profinit.publiccontracts.service.impl.NenNipezDownloader");
        propertyManager.putValue(PropertyManager.DOWNLOAD_RULE, "gemin", "eu.profinit.publiccontracts.service.impl.GeminDownloader");
        propertyManager.putValue(PropertyManager.DOWNLOAD_RULE, "vhodne-uverejneni", "eu.profinit.publiccontracts.service.impl.VhodneUverejneniDownloader");
        propertyManager.putValue(PropertyManager.DOWNLOAD_RULE, "MAX_SIZE", "104857600");
        propertyManager.putValue(PropertyManager.SUPPORTED_MIME_TYPE, "PDF", "application/pdf");
        propertyManager.putValue(PropertyManager.SUPPORTED_MIME_TYPE, "X-DOWNLOAD", "application/x-download");
        return propertyManager;
    }

    private String readResourceFile(String fileName) throws IOException {
        URL resource = Resources.getResource(fileName);
        String profilBody = Resources.toString(resource, Charsets.UTF_8);
        return profilBody;
    }

}