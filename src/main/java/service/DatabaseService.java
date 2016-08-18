package service;

import dto.SourceInfoDto;
import dto.SubmitterDto;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Set;


public interface DatabaseService {

    void saveSubmitter(SubmitterDto submitterDto, int year) throws SQLException;

    void saveSources(List<SourceInfoDto> sourceInfoDtos) throws SQLException;

    List<SourceInfoDto> loadSources() throws SQLException;

    void deleteSources() throws SQLException;

    void deleteErrors(int year) throws SQLException;

    void saveRetrieval(int year, boolean complete, Date lastDate, int numberOfErrors, int numberOfRecords) throws SQLException;

    boolean isYearCompleted(int year) throws SQLException;

    void saveError(SourceInfoDto sourceInfoDto, String message, int year, String errorClass) throws SQLException;

    Set<String> loadErrorUrlsForYear(int year) throws SQLException;

    void deleteCollectedData(Integer year) throws SQLException;

}
