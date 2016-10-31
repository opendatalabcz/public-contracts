package service.impl;


import ares.*;
import dto.CompanyInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import service.ARESService;

import java.util.List;


public class ARESServiceImpl implements ARESService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String URI_PREFIX = "http://wwwinfo.mfcr.cz/cgi-bin/ares/darv_bas.cgi?ico=";
    private static final String URI_SUFFIX = "&ver=1.0.2";


    @Override
    public CompanyInfoDto findCompany(final String ico) {
        try {
            final String uri = URI_PREFIX + ico + URI_SUFFIX;
            final RestTemplate restTemplate = new RestTemplate();
            final AresOdpovedi aresOdpovedi = restTemplate.getForObject(uri, AresOdpovedi.class);
            final VypisBasic vypisBasic = aresOdpovedi.getOdpoved().get(0).getVypisBasic().get(0);
            final List<Registrace> registraceOR = vypisBasic.getRegistraceOR();
            final List<RegistraceRZP> registraceRZP = vypisBasic.getRegistraceRZP();


            final AdresaARES adresaARES = vypisBasic.getAdresaARES();

            final String dic = vypisBasic.getDIC().getValue();
            final String obchodniFirma = vypisBasic.getObchodniFirma().getValue();
            final String nazevUlice = adresaARES.getNazevUlice();
            final String cisloOrientacni = adresaARES.getCisloOrientacni();
            final Integer cisloDomovni = adresaARES.getCisloDomovni();
            final String nazevCastiObce = adresaARES.getNazevCastiObce();
            final String nazevObce = adresaARES.getNazevObce();
            final String psc = adresaARES.getPSC();

            final CompanyInfoDto companyInfoDto = new CompanyInfoDto();

            if (registraceOR.size() > 0) {
                final SpisovaZnacka spisovaZnacka = registraceOR.get(0).getSpisovaZnacka().get(0);
                final String soud = spisovaZnacka.getSoud().getText();
                final String oddilVlozka = spisovaZnacka.getOddilVlozka();
                companyInfoDto.setSoud(soud);
                companyInfoDto.setOddilVlozka(oddilVlozka);
            } else {
                final String nazevZU = registraceRZP.get(0).getZivnostenskyUrad().getNazevZU();
                companyInfoDto.setNazevZU(nazevZU);
            }

            companyInfoDto.setCisloDomovni(cisloDomovni);
            companyInfoDto.setCisloOrientacni(cisloOrientacni);
            companyInfoDto.setDic(dic);
            companyInfoDto.setIco(ico);
            companyInfoDto.setNazevCastiObce(nazevCastiObce);
            companyInfoDto.setNazevObce(nazevObce);
            companyInfoDto.setNazevUlice(nazevUlice);
            companyInfoDto.setObchodniFirma(obchodniFirma);
            companyInfoDto.setPsc(psc);

            return companyInfoDto;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
