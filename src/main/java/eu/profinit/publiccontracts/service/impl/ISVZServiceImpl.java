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


/**
 * Service for downloading of submitter profiles.
 * Uses data sources XML data API with <i>/XMLdataVZ?od=0101{from}&do=3112{to}</i> URL extension.
 */
public class ISVZServiceImpl implements ISVZService {

    private static final String URI_SUFFIX = "/XMLdataVZ?od={from}&do={to}";

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Gets the {@link ProfilStructure} of a profile from urlPrefix for a specific yesr.
     * @param urlPrefix of the data source
     * @param fromDate desired start date for the XML data
     * @param toDate desired end date for the XML data
     * @return structure of the profile
     * @throws Exception
     */
    @Override
    public ProfilStructure findProfilStructure(String urlPrefix, String fromDate, String toDate) throws Exception {
        String profilBody = getProfilBody(urlPrefix, fromDate, toDate);
        return transformProfilBody(profilBody);
    }

    /**
     * Retrieves the XML body of the profile from the URL XML data.
     * @param urlPrefix url of the data source
     * @param fromDate desired start date for the XML data
     * @param toDate desired end date for the XML data
     * @return XML body
     * @throws URISyntaxException
     */
    public String getProfilBody(String urlPrefix, String fromDate, String toDate) throws URISyntaxException {
        final String url = (urlPrefix.trim() + URI_SUFFIX).replace("{from}", fromDate).replace("{to}", toDate);

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

    /**
     * Transforms XML body of the profile to {@link ProfilStructure}
     * @param profilBody XML body
     * @return object representation of the profile
     * @throws JAXBException
     */
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


