package eu.profinit.publiccontracts.util;

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

    public static String fetchTextFromURL(String url) throws IOException {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream())) {
            String text = DocumentFetcher.parseText(in);
            return text;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
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

}
