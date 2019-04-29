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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
            URLConnection urlConnection = url.openConnection();
            String contentType = urlConnection.getContentType().toLowerCase();
            if (contentType == null) {
                throw new IllegalArgumentException(urlString + " does not have text document content");
            }
            if (contentType.contains("application/x-download") ||
                contentType.contains("application/msword") ||
                contentType.contains("application/pdf")) {
                String contentFileName = getContentFileName(urlConnection);
                String fileExtension = parseFileExtension(contentFileName);
                if (fileExtension.contains("pdf")||
                    fileExtension.contains("doc")) {
                    try (BufferedInputStream in = new BufferedInputStream(url.openStream())) {
                        String text = DocumentFetcher.parseText(in);
                        System.out.println("downloaded: \"" + contentFileName + "\" from: " + urlString);
                        return text;
                    }
                }
                return null;
            } else if (contentType.contains("text/html")) {
                String newUrlString = searchForDocumentLink(urlString);
                return fetchTextFromURL(newUrlString);
            }
            throw new IllegalArgumentException(urlString + " does not have text document content");
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

    public static String getContentFileName(URLConnection connection) {
        String contentDisposition = connection.getHeaderField("Content-Disposition");
        Pattern pattern = Pattern.compile("filename=[\"]?([^;\"]*)[\";]?");
        Matcher matcher = pattern.matcher(contentDisposition);
        matcher.find();
        String fileName = matcher.group(1);
        return fileName;
    }

    public static String searchForDocumentLink(String url) throws IOException {
        Document document = Jsoup.connect(url)
                .timeout(0).get();
        Element element = document.getElementById("actual_document_table");
        Elements ahrefs = element.getElementsByTag("a");
        for (Element ahref: ahrefs) {
            String href = ahref.attr("href");
            return completeLinkWithFile(url, href);
        }
        return null;
    }

    public static String completeLinkWithFile(String oldUrl, String file) {
        String newUrl = oldUrl.replaceFirst("[^/]*$", file);
        return newUrl;
    }

    public static String parseFileExtension(String fileName) {
        Pattern pattern = Pattern.compile("\\.(\\w*)$");
        Matcher matcher = pattern.matcher(fileName);
        matcher.find();
        String fileExtension = matcher.group(1);
        return fileExtension.toLowerCase();
    }

}
