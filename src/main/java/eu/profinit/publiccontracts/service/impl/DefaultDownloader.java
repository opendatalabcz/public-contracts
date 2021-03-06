package eu.profinit.publiccontracts.service.impl;

import eu.profinit.publiccontracts.service.DocumentDownloader;
import eu.profinit.publiccontracts.util.PropertyManager;
import org.apache.http.client.HttpResponseException;
import org.apache.log4j.Logger;
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
import org.xml.sax.SAXException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class represents default document downloader.
 * 1. Retrieves URL connection to a document file on the web (only direct links).
 * 2. Downloads the document file data.
 * 3. Extracts text data from the document.
 */
public class DefaultDownloader implements DocumentDownloader {

    static final Logger logger = Logger.getLogger(DefaultDownloader.class);

    static final String OCR_LANGUAGE = "ces";

    protected URL url;

    protected PropertyManager properties;

    protected HttpURLConnection urlConnection = null;

    public DefaultDownloader() {

    }

    public DefaultDownloader(URL url) {
        this.url = url;
    }

    /**
     * Retrieves HTTP URL connection for the URL.
     * Throws error when the response code is not 200.
     * @return URLConnection
     * @throws IOException, {@link IllegalArgumentException}
     */
    @Override
    public URLConnection retrieveURLConnection() throws IOException, InterruptedException {
        this.urlConnection = (HttpURLConnection) url.openConnection();
        int responseCode = urlConnection.getResponseCode();
        if (responseCode != 200) {
            String threadName = Thread.currentThread().getName();
            if (properties.containsKey(PropertyManager.DOWNLOAD_RULE, "URL_AWAITING")) {
                while (responseCode == 429) {
                    Duration duration = Duration.ofMinutes(1);
                    logger.info(threadName + ":waiting " + duration +" link: " + url.toString());
                    Thread.sleep(duration.toMillis());
                    this.urlConnection = (HttpURLConnection) url.openConnection();
                    responseCode = urlConnection.getResponseCode();
                }
                while (responseCode == 403) {
                    Duration duration = Duration.ofHours(1);
                    logger.info(threadName + ":waiting " + duration +" link: " + url.toString());
                    Thread.sleep(duration.toMillis());
                    this.urlConnection = (HttpURLConnection) url.openConnection();
                    responseCode = urlConnection.getResponseCode();
                }
            }
            if (responseCode != 200) {
                throw new HttpResponseException(responseCode, "error: " + url.toString() + " cannot access data: error " + responseCode);
            }
        }
        return urlConnection;
    }


    /**
     * Gets the mime type of target document
     * @return mime type
     * @throws IOException
     */
    @Override
    public String getMimeType() throws IOException, InterruptedException {
        String contentType = getUrlConnection().getContentType();
        if (contentType != null) {
            contentType = contentType.toLowerCase();
        }
        return contentType;
    }

    /**
     * Gets the content length of target document
     * @return content length
     * @throws IOException
     */
    @Override
    public int getContentLength() throws IOException, InterruptedException {
        return getUrlConnection().getContentLength();
    }

    /**
     * Downloads file from the URL connection and extracts its text content.
     * Uses {@link PropertyManager} to filter the supported mime types.
     * @return text content of the file from the URL
     * @throws IOException, {@link IllegalArgumentException}
     */
    @Override
    public String downloadFileToString() throws IOException, InterruptedException {
        String text = null;
        String mimeType = getMimeType();
        String contentFileName = getContentFileName();
        String threadName = Thread.currentThread().getName();
        int fileSize = getContentLength();
        logger.info(threadName + ":document:\"" + contentFileName + "\"(" + mimeType + ")[" +  fileSize + "] link: " + url.toString());
        if (properties.containsValue(PropertyManager.SUPPORTED_MIME_TYPE, mimeType)) {
            if (fileSize < Integer.parseInt(properties.getValue(PropertyManager.DOWNLOAD_RULE, "MAX_SIZE"))) {
                try (BufferedInputStream in = new BufferedInputStream(urlConnection.getInputStream())) {
                    if ("application/pdf".equals(mimeType)) {
                        text = parseFromPdfThroughImage(in);
                    } else {
                        text = parseText(in);
                    }
                }
            } else {
                logger.warn(threadName + ":document: file too big (" + fileSize + ") link:" + url.toString());
            }
        } else {
            logger.warn(threadName + ":document: unsupported type (" + mimeType + ") link:" + url.toString());
        }
        return text;
    }

    /**
     * Reads a pdf file from the input stream, converts its pages to images and extracts its text content.
     * @param in
     * @return the content of the file extracted with OCR
     * @throws IOException
     */
    public String parseFromPdfThroughImage(InputStream in) throws IOException {
        StringBuilder builder = new StringBuilder();
        PDDocument document = PDDocument.load(in);
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        for (int page = 0; page < document.getNumberOfPages(); ++page) {
            BufferedImage bim = pdfRenderer.renderImageWithDPI(
                    page, 300, ImageType.RGB);

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(bim, "jpeg", os);
            InputStream is = new ByteArrayInputStream(os.toByteArray());

            builder.append(parseText(is));
        }
        document.close();
        return builder.toString();
    }

    /**
     * Uses Apache TIKA to extract text content from input stream.
     * @param inputStream
     * @return text content of the stream
     * @throws {@link IllegalArgumentException}
     */
    protected static String parseText(InputStream inputStream) {
        Parser parser = new AutoDetectParser();
        Metadata metadata = new Metadata();
        BodyContentHandler handler = new BodyContentHandler(Integer.MAX_VALUE);

        TesseractOCRConfig config = new TesseractOCRConfig();
        config.setLanguage(OCR_LANGUAGE);
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
        if (matcher.find()) {
            String fileExtension = matcher.group(1);
            return fileExtension.toLowerCase();
        }
        return "unknown_extension";
    }


    /**
     * Parses file name from the HTTP URL connection header.
     * @return name of the file
     */
    protected String getContentFileName() {
        String contentDisposition = urlConnection.getHeaderField("Content-Disposition");
        Pattern pattern = Pattern.compile("filename.*=[\"']?([^;\"]*)[\";]?");
        Matcher matcher = pattern.matcher(contentDisposition);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "unknown_name";
    }

    public HttpURLConnection getUrlConnection() throws IOException, InterruptedException {
        if (urlConnection == null) {
            urlConnection = (HttpURLConnection) retrieveURLConnection();
        }
        return urlConnection;
    }

    @Override
    public void setUrl(URL url) {
        this.url = url;
    }

    @Override
    public void setProperties(PropertyManager properties) {
        this.properties = properties;
    }
}
