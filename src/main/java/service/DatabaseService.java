package service;

import dto.SourceInfoDto;
import dto.SubmitterDto;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;


public interface DatabaseService {

    void saveSubmitter(SubmitterDto submitterDto, int year) throws SQLException;

    void saveSources(List<SourceInfoDto> sourceInfoDtos) throws SQLException;

    List<SourceInfoDto> loadSources() throws SQLException;

    void deleteSources() throws SQLException;

    void saveRetrieval(int year, boolean complete, Date lastDate, int numberOfErrors) throws SQLException;

    Date loadRetrievalLastDate(int year) throws SQLException;

    void saveError(SourceInfoDto sourceInfoDto, String message, int year, String errorClass) throws SQLException;

    void deleteCollectedData(Integer year) throws SQLException;

}
