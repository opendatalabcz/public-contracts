package dto;


public class CompanyInfoDto {

    private String ico;
    private String dic;
    private String obchodniFirma;
    private String soud;
    private String oddilVlozka;
    private String nazevZU;
    private String nazevUlice;
    private String cisloOrientacni;
    private Integer cisloDomovni;
    private String nazevCastiObce;
    private String nazevObce;
    private String psc;

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public String getDic() {
        return dic;
    }

    public void setDic(String dic) {
        this.dic = dic;
    }

    public String getObchodniFirma() {
        return obchodniFirma;
    }

    public void setObchodniFirma(String obchodniFirma) {
        this.obchodniFirma = obchodniFirma;
    }

    public String getSoud() {
        return soud;
    }

    public void setSoud(String soud) {
        this.soud = soud;
    }

    public String getOddilVlozka() {
        return oddilVlozka;
    }

    public void setOddilVlozka(String oddilVlozka) {
        this.oddilVlozka = oddilVlozka;
    }

    public String getNazevUlice() {
        return nazevUlice;
    }

    public void setNazevUlice(String nazevUlice) {
        this.nazevUlice = nazevUlice;
    }

    public String getCisloOrientacni() {
        return cisloOrientacni;
    }

    public void setCisloOrientacni(String cisloOrientacni) {
        this.cisloOrientacni = cisloOrientacni;
    }

    public Integer getCisloDomovni() {
        return cisloDomovni;
    }

    public void setCisloDomovni(Integer cisloDomovni) {
        this.cisloDomovni = cisloDomovni;
    }

    public String getNazevCastiObce() {
        return nazevCastiObce;
    }

    public void setNazevCastiObce(String nazevCastiObce) {
        this.nazevCastiObce = nazevCastiObce;
    }

    public String getNazevObce() {
        return nazevObce;
    }

    public void setNazevObce(String nazevObce) {
        this.nazevObce = nazevObce;
    }

    public String getPsc() {
        return psc;
    }

    public void setPsc(String psc) {
        this.psc = psc;
    }

    public String getNazevZU() {
        return nazevZU;
    }

    public void setNazevZU(String nazevZU) {
        this.nazevZU = nazevZU;
    }

    @Override
    public String toString() {
        return "CompanyInfoDto{" +
                "ico='" + ico + '\'' +
                ", dic='" + dic + '\'' +
                ", obchodniFirma='" + obchodniFirma + '\'' +
                ", soud='" + soud + '\'' +
                ", oddilVlozka='" + oddilVlozka + '\'' +
                ", nazevZU='" + nazevZU + '\'' +
                ", nazevUlice='" + nazevUlice + '\'' +
                ", cisloOrientacni='" + cisloOrientacni + '\'' +
                ", cisloDomovni=" + cisloDomovni +
                ", nazevCastiObce='" + nazevCastiObce + '\'' +
                ", nazevObce='" + nazevObce + '\'' +
                ", psc='" + psc + '\'' +
                '}';
    }
}
