package service;

import dto.CompanyDto;
import generated.isvz.mmr.schemas.vz_z_profilu_zadavatele.v100.ProfilStructure;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;


public interface DatabaseService {

    void saveSubmitter(ProfilStructure profilStructure) throws SQLException;

    void saveSubmitters(List<CompanyDto> companyDtos) throws SQLException;

    List<CompanyDto> loadSubmitters() throws SQLException;

    void saveRetrieval(int year, boolean complete, Date lastDate, int numberOfErrors) throws SQLException;

    Date loadRetrievalLastDate(int year) throws SQLException;

}
