package eu.profinit.publiccontracts.service;

import eu.profinit.publiccontracts.generated.isvz.mmr.schemas.vz_z_profilu_zadavatele.v100.ProfilStructure;

import javax.xml.bind.JAXBException;
import java.net.URISyntaxException;


/**
 * Interface for submitter profile scraping.
 */
public interface ISVZService {

    ProfilStructure findProfilStructure(String urlPrefix, int year) throws Exception;

    String getProfilBody(String urlPrefix, int year) throws URISyntaxException;

    ProfilStructure transformProfilBody(String profilBody) throws JAXBException;

}
