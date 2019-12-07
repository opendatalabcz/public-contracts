package eu.profinit.publiccontracts.util;

import eu.profinit.publiccontracts.Main;
import eu.profinit.publiccontracts.dto.ContractDto;
import eu.profinit.publiccontracts.dto.DocumentDto;
import eu.profinit.publiccontracts.dto.SubmitterDto;
import eu.profinit.publiccontracts.service.DocumentDownloader;
import eu.profinit.publiccontracts.service.impl.DefaultDownloader;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for fetching all available documents of a submitter.
 */
public class DocumentFetcher {

    static final Logger logger = Logger.getLogger(DocumentFetcher.class);

    private DocumentFetcher() {
        // hiding implicit construcotr
    }

    /**
     * Fetches all documents for the submitter.
     * @param submitterDto submitter object
     * @param properties for download handling
     * @return list of document objects
     * @throws IOException
     */
    public static List<DocumentDto> fetchDocuments(SubmitterDto submitterDto, PropertyManager properties) {
        int i = 0;
        for (ContractDto contractDto : submitterDto.getContractDtos()) {
            logger.info(Thread.currentThread().getName() + ":contract:" + ++i + "/" + submitterDto.getContractDtos().size() +
                    "(" + contractDto.getCode2() + "," + contractDto.getDocumentDtos().size() + ")");
            for (DocumentDto documentDto : contractDto.getDocumentDtos()) {
                DocumentFetcher.fetchDocument(documentDto, properties);
            }
        }
        return Collections.emptyList();
    }

    /**
     * Fetches a document from its url.
     * @param documentDto document object to be fetched
     * @param properties for download handling
     * @return text content of the document
     * @throws IOException
     */
    public static String fetchDocument(DocumentDto documentDto, PropertyManager properties) {
        DocumentDownloader downloader = createDownloader(documentDto, properties);
        documentDto.setDownloader(downloader.getClass().getName());

        try {
            String urlString = checkUrlFormat(documentDto.getUrl());
            URL url = new URL(urlString);
            downloader.setUrl(url);
            downloader.retrieveURLConnection();
            documentDto.setMimeType(downloader.getMimeType());
            documentDto.setFileSize(downloader.getContentLength());
            if (properties.containsKey(PropertyManager.APPLICATION, "SKIP_DOWNLOADING")) {
                documentDto.setProcessed(false);
                documentDto.setToProcess(true);
            } else {
                String text = downloader.downloadFileToString();
                documentDto.setDocumentData(text);
                documentDto.setProcessed(text != null);
                documentDto.setToProcess(false);
                Main.numberOfDocuments.incrementAndGet();
                return text;
            }
            return null;
        } catch (Exception e) {
            logger.warn(Thread.currentThread().getName() + ":" + e.getMessage());
            return null;
        }
    }

    /**
     * Creates a downloader based on document url and configuration from properties.
     * @param documentDto document object to be downloaded
     * @param properties properties to handle the downloading
     * @return an instance of {@link DocumentDownloader}
     */
    public static DocumentDownloader createDownloader(DocumentDto documentDto, PropertyManager properties) {
        String url = documentDto.getUrl();
        DocumentDownloader downloader = null;
        if (properties.containsKey(PropertyManager.DOWNLOAD_RULE, url)) {
            String downloaderClassName = properties.getValue(PropertyManager.DOWNLOAD_RULE, url);
            downloader = (DocumentDownloader) ResourceManager.createClassInstance(downloaderClassName);
        }
        if (downloader == null) {
            downloader = new DefaultDownloader();
        }
        downloader.setProperties(properties);
        return downloader;
    }

    public static String checkUrlFormat(String orig) throws MalformedURLException {
        Pattern pattern = Pattern.compile("https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#()?&//=]*)");
        Matcher matcher = pattern.matcher(orig);
        if (matcher.find()) {
            return orig;
        }
        if (!orig.startsWith("http")) {
            return "https://" + orig;
        }
        throw new MalformedURLException(orig);
    }

}
