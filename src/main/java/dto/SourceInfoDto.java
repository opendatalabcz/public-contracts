package dto;

public class SourceInfoDto {
    public SourceInfoDto() {
    }

    public SourceInfoDto(String ico, String name, String url) {
        this.ico = ico;
        this.name = name;
        this.url = url;
    }

    private String ico;
    private String name;
    private String url;

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "SourceInfoDto{" +
                "ico='" + ico + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
