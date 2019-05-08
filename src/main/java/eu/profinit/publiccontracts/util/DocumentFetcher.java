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

public class DocumentFetcher {

    public static List<DocumentDto> fetchDocuments(SubmitterDto submitterDto, PropertyManager properties) throws IOException {
        for (ContractDto contractDto : submitterDto.getContractDtos()) {
            for (DocumentDto documentDto : contractDto.getDocumentDtos()) {
                DocumentFetcher.fetchDocument(documentDto, properties);
            }
        }
        return Collections.emptyList();
    }

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
            return text;
        } catch (Exception e) {
            e.printStackTrace();
            Main.numberOfErrors.incrementAndGet();
            return null;
        }
    }

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
