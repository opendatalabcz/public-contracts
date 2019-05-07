package eu.profinit.publiccontracts.util;

import eu.profinit.publiccontracts.Main;
import eu.profinit.publiccontracts.dto.ContractDto;
import eu.profinit.publiccontracts.dto.DocumentDto;
import eu.profinit.publiccontracts.dto.DownloadRuleDto;
import eu.profinit.publiccontracts.dto.SubmitterDto;
import eu.profinit.publiccontracts.util.download.DefaultDownloader;
import eu.profinit.publiccontracts.util.download.DocumentDownloader;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DocumentFetcher {

    public static List<DocumentDto> fetchDocuments(SubmitterDto submitterDto, Map<String, Map<String, String>> properties) throws IOException {
        for (ContractDto contractDto : submitterDto.getContractDtos()) {
            for (DocumentDto documentDto : contractDto.getDocumentDtos()) {
                DocumentFetcher.fetchDocument(documentDto, properties);
            }
        }
        return Collections.emptyList();
    }

    public static String fetchDocument(DocumentDto documentDto, Map<String, Map<String, String>> properties) throws IOException {
        DocumentDownloader downloader = createDownloader(documentDto, properties.get("download_rule"));
        documentDto.setDownloader(downloader.getClass().getName());

        String urlString = documentDto.getUrl();
        URL url = new URL(urlString);
        downloader.setUrl(url);
        try {
            URLConnection urlConnection = downloader.retrieveURLConnection();
            String text = downloader.downloadFileToString(properties.get("supported_mime_type"));
            documentDto.setDocumentData(text);
            return text;
        } catch (Exception e) {
            e.printStackTrace();
            Main.numberOfErrors.incrementAndGet();
            return null;
        }
    }

    public static DocumentDownloader createDownloader(DocumentDto documentDto, Map<String,String> downloadRules) {
        String url = documentDto.getUrl();
        DocumentDownloader downloader;
        for (String condition: downloadRules.keySet()) {
            if (url.contains(condition)) {
                String downloaderClassName = downloadRules.get(condition);
                downloader = (DocumentDownloader) ResourceManager.createClassInstance(downloaderClassName);
                if (downloader != null) {
                    return downloader;
                }
            }
        }
        return new DefaultDownloader();
    }

}
