package eu.profinit.publiccontracts.service.impl;

import eu.profinit.publiccontracts.generated.isvz.mmr.schemas.vz_z_profilu_zadavatele.v100.ProfilStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import eu.profinit.publiccontracts.service.ISVZService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;


public class ISVZServiceImpl implements ISVZService {

    private static final String URI_SUFFIX = "/XMLdataVZ?od=0101{from}&do=3112{to}";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ProfilStructure findProfilStructure(String urlPrefix, int year) throws Exception {
        String profilBody = getProfilBody(urlPrefix, year);
        return transformProfilBody(profilBody);
    }

    public String getProfilBody(String urlPrefix, int year) throws URISyntaxException {
        final String url = (urlPrefix.trim() + URI_SUFFIX).replace("{from}", String.valueOf(year)).replace("{to}", String.valueOf(year));

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
            return exchange.getBody();
        }
    }

    public ProfilStructure transformProfilBody(String profilBody) throws JAXBException {
        final JAXBContext jaxbContext = JAXBContext.newInstance(ProfilStructure.class);
        final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        //Some xml responses had chars before "<?xml.. which resulted in xml parse error"
        final int i = profilBody.indexOf("<?xml");
        final Source source = new StreamSource(new StringReader(profilBody.substring(i != -1 ? i : 0)));
        final JAXBElement<ProfilStructure> root = unmarshaller.unmarshal(source, ProfilStructure.class);
        return root.getValue();
    }

}


