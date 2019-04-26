package eu.profinit.publiccontracts.util;

import eu.profinit.publiccontracts.Main;
import eu.profinit.publiccontracts.dto.ContractDto;
import eu.profinit.publiccontracts.dto.DocumentDto;
import eu.profinit.publiccontracts.dto.SubmitterDto;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.ocr.TesseractOCRConfig;
import org.apache.tika.parser.pdf.PDFParserConfig;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocumentFetcher {

    public static List<DocumentDto> fetchDocuments(SubmitterDto submitterDto) throws IOException {
        for (ContractDto contractDto: submitterDto.getContractDtos()) {
            for (DocumentDto documentDto: contractDto.getDocumentDtos()) {
                String url = documentDto.getUrl();
                String documentText = DocumentFetcher.fetchTextFromURL(url);
                documentDto.setDocumentData(documentText);
            }
        }
        return Collections.emptyList();
    }

    public static String fetchTextFromURL(String urlString) throws IOException {
        URL url = new URL(urlString);

        try {
            String contentFileName = getContentFileName(url);
            String fileExtension = parseFileExtension(contentFileName);
            if ("pdf".equals(fileExtension)) {
                try (BufferedInputStream in = new BufferedInputStream(url.openStream())) {
                    String text = DocumentFetcher.parseText(in);
                    System.out.println("downloaded: \"" + contentFileName + "\" from: " + urlString);
                    return text;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            Main.numberOfErrors.incrementAndGet();
            return null;
        }
    }

    public static String parseText(InputStream inputStream) {
        Parser parser = new AutoDetectParser();
        Metadata metadata = new Metadata();
        BodyContentHandler handler = new BodyContentHandler(Integer.MAX_VALUE);

        TesseractOCRConfig config = new TesseractOCRConfig();
        PDFParserConfig pdfConfig = new PDFParserConfig();
        pdfConfig.setExtractInlineImages(true);

        // To parse images in files those lines are needed
        ParseContext parseContext = new ParseContext();
        parseContext.set(TesseractOCRConfig.class, config);
        parseContext.set(PDFParserConfig.class, pdfConfig);
        parseContext.set(Parser.class, parser); // need to add this to make sure
        // recursive parsing happens!
        try {
            parser.parse(inputStream, handler, metadata, parseContext);
            String text = handler.toString();
            return text;
        } catch (IOException | SAXException | TikaException e) {
            throw new IllegalArgumentException("TIKA was not able to exctract text of file", e);
        }
    }

    public static String getContentFileName(URL url) throws IOException {
        URLConnection urlConnection = url.openConnection();
        String contentType = urlConnection.getContentType().toLowerCase();
        if ("application/x-download".equals(contentType)) {
            String contentDisposition = urlConnection.getHeaderField("Content-Disposition");
            Pattern pattern = Pattern.compile("filename=\"(.*)\"");
            Matcher matcher = pattern.matcher(contentDisposition);
            matcher.find();
            String fileName = matcher.group(1);
            return fileName;
        } else if ("text/html; charset=utf-8".equals(contentType.toLowerCase())) {
            return searchForDocument(url);
        }
        throw new IllegalArgumentException(url.toString() + " does not have application/x-download content");
    }

    public static String searchForDocument(URL url) throws IOException {
        try (BufferedInputStream in = new BufferedInputStream(url.openStream())) {
            String text = DocumentFetcher.parseText(in);
            return text;
        }
    }

    public static String parseFileExtension(String fileName) {
        Pattern pattern = Pattern.compile("\\.(\\w*)$");
        Matcher matcher = pattern.matcher(fileName);
        matcher.find();
        String fileExtension = matcher.group(1);
        return fileExtension.toLowerCase();
    }

}
