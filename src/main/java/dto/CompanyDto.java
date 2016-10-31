package dto;


public class CompanyDto {

    private String ico;
    private String name;

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

    @Override
    public String toString() {
        return "CompanyDto{" +
                "ico='" + ico + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
