package service.impl;

import generated.isvz.mmr.schemas.vz_z_profilu_zadavatele.v100.ProfilStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import service.ISVZService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.net.URI;
import java.util.Arrays;
import java.util.List;


public class ISVZServiceImpl implements ISVZService {

    private static final String URI_SUFFIX = "/XMLdataVZ?od=0101{from}&do=0101{to}";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ProfilStructure findProfilStructure(String urlPrefix, int year) throws Exception {

        final String url = (urlPrefix.trim() + URI_SUFFIX).replace("{from}", String.valueOf(year)).replace("{to}", String.valueOf(year + 1));

        URI uri = new URI(url.replace(" ", "%20"));

        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
        final HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        while (true) {
            final ResponseEntity<String> exchange = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
            final HttpStatus statusCode = exchange.getStatusCode();
            //Handling redirect
            final List<HttpStatus> httpStatuses = Arrays.asList(HttpStatus.MOVED_PERMANENTLY, HttpStatus.MOVED_TEMPORARILY, HttpStatus.FOUND, HttpStatus.PERMANENT_REDIRECT, HttpStatus.TEMPORARY_REDIRECT, HttpStatus.SEE_OTHER, HttpStatus.USE_PROXY);
            if (httpStatuses.contains(statusCode)) {
                uri = exchange.getHeaders().getLocation();
                continue;
            }
            final String body = exchange.getBody();
            final JAXBContext jaxbContext = JAXBContext.newInstance(ProfilStructure.class);
            final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            //Some xml responses had chars before "<?xml.. which resulted in xml parse error"
            final int i = body.indexOf("<?xml");
            final Source source = new StreamSource(new StringReader(body.substring(i != -1 ? i : 0)));
            final JAXBElement<ProfilStructure> root = unmarshaller.unmarshal(source, ProfilStructure.class);
            return root.getValue();
        }
    }
}


