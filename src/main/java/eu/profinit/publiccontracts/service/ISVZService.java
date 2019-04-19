package eu.profinit.publiccontracts.service;

import eu.profinit.publiccontracts.generated.isvz.mmr.schemas.vz_z_profilu_zadavatele.v100.ProfilStructure;


public interface ISVZService {


    ProfilStructure findProfilStructure(String urlPrefix, int year) throws Exception;
}
