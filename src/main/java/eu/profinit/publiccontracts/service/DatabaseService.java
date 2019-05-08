package eu.profinit.publiccontracts.service;

import eu.profinit.publiccontracts.dto.ParameterDto;
import eu.profinit.publiccontracts.dto.SourceInfoDto;
import eu.profinit.publiccontracts.dto.SubmitterDto;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Set;


public interface DatabaseService {

    void saveSubmitter(SubmitterDto submitterDto, int year) throws SQLException;
    
    public long getSubmitter(String ico) throws SQLException;

    void saveSources(List<SourceInfoDto> sourceInfoDtos) throws SQLException;

    List<SourceInfoDto> loadSources() throws SQLException;
    
    List<SourceInfoDto> loadSource(String ico) throws SQLException;

    List<ParameterDto> loadProperties() throws SQLException;

    void deleteSources() throws SQLException;

    void deleteErrors(int year) throws SQLException;

    void saveRetrieval(int year, boolean complete, Date lastDate, int numberOfErrors, int numberOfRecords) throws SQLException;

    boolean isYearCompleted(int year) throws SQLException;

    void saveError(SourceInfoDto sourceInfoDto, String message, int year, String errorClass) throws SQLException;

    Set<String> loadErrorUrlsForYear(int year) throws SQLException;

    void deleteCollectedData(Integer year) throws SQLException;

}
