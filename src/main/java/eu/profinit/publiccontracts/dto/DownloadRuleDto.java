package eu.profinit.publiccontracts.dto;


public class DownloadRuleDto {

    private String condition;

    private String downloader;

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDownloader() {
        return downloader;
    }

    public void setDownloader(String downloader) {
        this.downloader = downloader;
    }

}
