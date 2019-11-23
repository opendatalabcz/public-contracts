package eu.profinit.publiccontracts.dto;

import java.sql.Timestamp;

public class DocumentDto {

    private Long documentId;

    private String url;

    private String documentType;

    private int documentVersion;

    private Timestamp documentUploadDate;

    private String documentData;

    private String downloader;

    private String mimeType;

    private int fileSize;

    private boolean toProcess;

    private boolean processed;

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public int getDocumentVersion() {
        return documentVersion;
    }

    public void setDocumentVersion(int documentVersion) {
        this.documentVersion = documentVersion;
    }

    public Timestamp getDocumentUploadDate() {
        return documentUploadDate;
    }

    public void setDocumentUploadDate(Timestamp documentUploadDate) {
        this.documentUploadDate = documentUploadDate;
    }

    public String getDocumentData() {
        return documentData;
    }

    public void setDocumentData(String documentData) {
        this.documentData = documentData;
    }

    public String getDownloader() {
        return downloader;
    }

    public void setDownloader(String downloader) {
        this.downloader = downloader;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public boolean isToProcess() {
        return toProcess;
    }

    public void setToProcess(boolean toProcess) {
        this.toProcess = toProcess;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }
}
