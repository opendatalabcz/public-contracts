package eu.profinit.publiccontracts.util.download;

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
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefaultDownloader implements DocumentDownloader{

    protected URL url;

    protected HttpURLConnection urlConnection = null;

    public DefaultDownloader() {

    }

    public DefaultDownloader(URL url) {
        this.url = url;
    }

    @Override
    public URLConnection retrieveURLConnection() throws IOException {
        this.urlConnection = (HttpURLConnection) url.openConnection();
        int responseCode = urlConnection.getResponseCode();
        if (responseCode != 200) {
            throw new IllegalArgumentException(url.toString() + " cannot access data: error " + responseCode);
        }
        return urlConnection;
    }

    @Override
    public String downloadFileToString(Map<String,String> supportedTypes) throws IOException {
        String contentType = getUrlConnection().getContentType();
        if (contentType != null) {
            contentType = contentType.toLowerCase();
            for (String type: supportedTypes.values()) {
                if (contentType.contains(type)){
                    String contentFileName = getContentFileName();
                    try (BufferedInputStream in = new BufferedInputStream(urlConnection.getInputStream())) {
                        String text = parseText(in);
                        System.out.println("downloaded: \"" + contentFileName + "\" from: " + url.toString());
                        return text;
                    }
                }
            }
        }
        throw new IllegalArgumentException(url.toString() + " does not have supported format: " + contentType);
    }

    protected static String parseText(InputStream inputStream) {
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
            throw new IllegalArgumentException("TIKA was not able to extract text of file", e);
        }
    }

    protected static String parseFileExtension(String fileName) {
        Pattern pattern = Pattern.compile("\\.(\\w*)$");
        Matcher matcher = pattern.matcher(fileName);
        matcher.find();
        String fileExtension = matcher.group(1);
        return fileExtension.toLowerCase();
    }


    protected String getContentFileName() {
        String contentDisposition = urlConnection.getHeaderField("Content-Disposition");
        Pattern pattern = Pattern.compile("filename=[\"]?([^;\"]*)[\";]?");
        Matcher matcher = pattern.matcher(contentDisposition);
        matcher.find();
        String fileName = matcher.group(1);
        return fileName;
    }


    public HttpURLConnection getUrlConnection() throws IOException {
        if (urlConnection == null) {
            urlConnection = (HttpURLConnection) retrieveURLConnection();
        }
        return urlConnection;
    }

    @Override
    public void setUrl(URL url) {
        this.url = url;
    }
}
