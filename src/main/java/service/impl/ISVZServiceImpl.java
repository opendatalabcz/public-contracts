package service.impl;

import isvz.isvz.mmr.schemas.vz_z_profilu_zadavatele.v100.ProfilStructure;
import org.apache.log4j.Logger;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import service.ISVZService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.nio.charset.Charset;


public class ISVZServiceImpl implements ISVZService {
//    private static final String URI_SUFFIX = "";
    private static final String URI_SUFFIX = "/XMLdataVZ?od=24032015&do=23032016";

    final static Logger logger = Logger.getLogger(ISVZServiceImpl.class);

    @Override
    public ProfilStructure findProfilStructure(String urlPrefix){
        final String uri = urlPrefix.trim() + URI_SUFFIX;
        System.out.println(uri);
        try {

            final RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters()
                    .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

//            HttpHeaders headers = new HttpHeaders();
//            headers.setAccept(Arrays.asList(MediaType.ALL));
//            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
//            final ResponseEntity<String> exchange = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
//            final String body = exchange.getBody();


            final String xmlInString = restTemplate.getForObject(uri, String.class);
            System.out.println(xmlInString);
            final JAXBContext jaxbContext = JAXBContext.newInstance(ProfilStructure.class);
            final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            final Source source = new StreamSource(new StringReader(xmlInString));
            final JAXBElement<ProfilStructure> root = unmarshaller.unmarshal(source, ProfilStructure.class);
            return root.getValue();
        }catch (Exception e){
            logger.error(uri + ", E: " + e.getMessage());
            return null;
        }
//        https://bt-n.profilzadavatele.cz/XMLdataVZ?od=24032015&amp;do=23032016"
//        http://bt-n.profilzadavatele.cz//XMLdataVZ?od=24032015&do=23032016
    }
}


