package eu.profinit.publiccontracts.util;

import eu.profinit.publiccontracts.Main;
import eu.profinit.publiccontracts.dto.ContractDto;
import eu.profinit.publiccontracts.dto.DocumentDto;
import eu.profinit.publiccontracts.dto.SubmitterDto;
import eu.profinit.publiccontracts.service.impl.DefaultDownloader;
import eu.profinit.publiccontracts.service.DocumentDownloader;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Class for fetching all available documents of a submitter.
 */
public class DocumentFetcher {

    final static Logger logger = Logger.getLogger(DocumentFetcher.class);

    /**
     * Fetches all documents for the submitter.
     * @param submitterDto submitter object
     * @param properties for download handling
     * @return list of document objects
     * @throws IOException
     */
    public static List<DocumentDto> fetchDocuments(SubmitterDto submitterDto, PropertyManager properties) throws IOException {
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
    public static String fetchDocument(DocumentDto documentDto, PropertyManager properties) throws IOException {
        DocumentDownloader downloader = createDownloader(documentDto, properties);
        documentDto.setDownloader(downloader.getClass().getName());

        String urlString = documentDto.getUrl();
        URL url = new URL(urlString);
        downloader.setUrl(url);
        try {
            URLConnection urlConnection = downloader.retrieveURLConnection();
            String text = downloader.downloadFileToString(properties);
            documentDto.setDocumentData(text);
            Main.numberOfDocuments.incrementAndGet();
            return text;
        } catch (Exception e) {
            logger.info(e.getMessage());
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
        DocumentDownloader downloader;
        if (properties.containsKey(PropertyManager.DOWNLOAD_RULE, url)) {
            String downloaderClassName = properties.getValue(PropertyManager.DOWNLOAD_RULE, url);
            downloader = (DocumentDownloader) ResourceManager.createClassInstance(downloaderClassName);
            if (downloader != null) {
                return downloader;
            }
        }
        return new DefaultDownloader();
    }

}
