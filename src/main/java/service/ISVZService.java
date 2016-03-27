package service;

import generated.isvz.mmr.schemas.vz_z_profilu_zadavatele.v100.ProfilStructure;

import java.util.Date;


public interface ISVZService {


    ProfilStructure findProfilStructure(String urlPrefix, int year, Date lastDay);
}
