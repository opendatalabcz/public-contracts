package eu.profinit.publiccontracts.dto;

import java.sql.Timestamp;

public class DocumentDto {

    private String url;

    private String documentType;

    private int documentVersion;

    private Timestamp documentUploadDate;

    private String documentData;

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
}
