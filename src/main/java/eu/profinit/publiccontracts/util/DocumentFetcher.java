package eu.profinit.publiccontracts.util;

import eu.profinit.publiccontracts.Main;
import eu.profinit.publiccontracts.dto.ContractDto;
import eu.profinit.publiccontracts.dto.DocumentDto;
import eu.profinit.publiccontracts.dto.SubmitterDto;
import eu.profinit.publiccontracts.util.download.DefaultDownloader;
import eu.profinit.publiccontracts.util.download.DocumentDownloader;
import eu.profinit.publiccontracts.util.download.EZakDownloader;
import eu.profinit.publiccontracts.util.download.NenNipezDownloader;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.List;

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
            DocumentDownloader downloader = createDownloader(url);
            URLConnection urlConnection = downloader.retrieveURLConnection();
            String text = downloader.downloadFileToString();
            return text;
        } catch (Exception e) {
            e.printStackTrace();
            Main.numberOfErrors.incrementAndGet();
            return null;
        }
    }

    public static DocumentDownloader createDownloader(URL url) {
        String host = url.getHost();
        if (host.contains("nen.nipez.cz")) {
            return new NenNipezDownloader(url);
        }
        if (host.contains("ezak")) {
            return new EZakDownloader(url);
        }
        return new DefaultDownloader(url);
    }

}
