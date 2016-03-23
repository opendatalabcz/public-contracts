package service;

import isvz.isvz.mmr.schemas.vz_z_profilu_zadavatele.v100.ProfilStructure;

import java.sql.SQLException;


public interface InsertIntoDBService {

    void saveSubmitter(ProfilStructure profilStructure) throws SQLException;
}
