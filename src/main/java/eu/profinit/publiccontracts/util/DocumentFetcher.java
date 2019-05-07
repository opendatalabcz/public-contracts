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

public class DocumentFetcher {

    public static List<DocumentDto> fetchDocuments(SubmitterDto submitterDto, List<DownloadRuleDto> downloadRules) throws IOException {
        for (ContractDto contractDto : submitterDto.getContractDtos()) {
            for (DocumentDto documentDto : contractDto.getDocumentDtos()) {
                DocumentFetcher.fetchDocument(documentDto, downloadRules);
            }
        }
        return Collections.emptyList();
    }

    public static String fetchDocument(DocumentDto documentDto, List<DownloadRuleDto> downloadRules) throws IOException {
        DocumentDownloader downloader = createDownloader(documentDto, downloadRules);
        documentDto.setDownloader(downloader.getClass().getName());

        String urlString = documentDto.getUrl();
        URL url = new URL(urlString);
        downloader.setUrl(url);
        try {
            URLConnection urlConnection = downloader.retrieveURLConnection();
            String text = downloader.downloadFileToString();
            documentDto.setDocumentData(text);
            return text;
        } catch (Exception e) {
            e.printStackTrace();
            Main.numberOfErrors.incrementAndGet();
            return null;
        }
    }

    public static DocumentDownloader createDownloader(DocumentDto documentDto, List<DownloadRuleDto> downloadRules) {
        String url = documentDto.getUrl();
        DocumentDownloader downloader;
        for (DownloadRuleDto downloadRule: downloadRules) {
            String condition = downloadRule.getCondition();
            if (url.contains(condition)) {
                String downloaderClassName = downloadRule.getDownloader();
                downloader = (DocumentDownloader) ResourceManager.createClassInstance(downloaderClassName);
                if (downloader != null) {
                    return downloader;
                }
            }
        }
        return new DefaultDownloader();
    }

}
