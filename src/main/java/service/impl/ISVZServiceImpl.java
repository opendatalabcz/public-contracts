package service.impl;

import isvz.isvz.mmr.schemas.vz_z_profilu_zadavatele.v100.ProfilStructure;
import org.springframework.web.client.RestTemplate;
import service.ISVZService;


public class ISVZServiceImpl implements ISVZService {
    private static final String URI_SUFFIX = "/XMLdataVZ?od=07042015&do=10102015";


    @Override
    public ProfilStructure findProfilStructure(String urlPrefix) {
        final String uri = urlPrefix + URI_SUFFIX;
        final RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, ProfilStructure.class);
    }


}
