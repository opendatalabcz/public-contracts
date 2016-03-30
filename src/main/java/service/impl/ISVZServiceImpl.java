package service.impl;

import generated.isvz.mmr.schemas.vz_z_profilu_zadavatele.v100.ProfilStructure;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import service.ISVZService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class ISVZServiceImpl implements ISVZService {

    private static final String URI_SUFFIX = "/XMLdataVZ?od=0101{from}&do={to}";

    final static Logger logger = Logger.getLogger(ISVZServiceImpl.class);

    @Override
    public ProfilStructure findProfilStructure(String urlPrefix, int year, Date lastDay) {
        final String to;
        if (lastDay != null) {
            final DateTime dateTime = new DateTime(lastDay);
            final int dayOfMonth = dateTime.getDayOfMonth();
            final int monthOfYear = dateTime.getMonthOfYear();
            to =  String.valueOf(dayOfMonth) + String.valueOf(monthOfYear) + String.valueOf(year);
        }else {
            to = "0101" + String.valueOf(year + 1);
        }
        final String url = (urlPrefix.trim() + URI_SUFFIX).replace("{from}", String.valueOf(year)).replace("{to}", to);
        try {
            URI uri = new URI(url);

            final RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters()
                    .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

            final HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
            final HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

            while (true) {
                final ResponseEntity<String> exchange = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
                final HttpStatus statusCode = exchange.getStatusCode();
                //Handling redirect
                final List<HttpStatus> httpStatuses = Arrays.asList(HttpStatus.MOVED_PERMANENTLY, HttpStatus.MOVED_TEMPORARILY, HttpStatus.FOUND, HttpStatus.PERMANENT_REDIRECT, HttpStatus.TEMPORARY_REDIRECT);
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
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(url + ", E: " + e.getMessage());
            return null;
        }

    }
}


