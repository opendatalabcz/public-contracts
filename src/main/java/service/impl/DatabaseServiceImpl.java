package service.impl;

import db.DatabaseConnectionFactory;
import dto.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import service.DatabaseService;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Date;
import java.util.List;
import java.util.Set;


public class DatabaseServiceImpl implements DatabaseService {

    @Autowired
    private DatabaseConnectionFactory databaseConnectionFactory;

    final static Logger logger = Logger.getLogger(DatabaseServiceImpl.class);


    @Override
    public void saveSubmitter(SubmitterDto submitterDto, int year) throws SQLException {
        final long submitterId = saveSubmitter(submitterDto.getIco(), submitterDto.getName());
        final List<ContractDto> contractDtos = submitterDto.getContractDtos();
        for (ContractDto contractDto : contractDtos) {
            final String code1 = contractDto.getCode1();
            final String code2 = contractDto.getCode2();
            final String kind = contractDto.getKind();
            final String state = contractDto.getState();
            final String name = contractDto.getName();
            final long contractId = saveContract(submitterId, code1, code2, name, state, kind, year);
            final List<CandidateDto> candidateDtos = contractDto.getCandidateDtos();
            final List<SupplierDto> supplierDtos = contractDto.getSupplierDtos();
            saveCandidates(contractId, candidateDtos);
            saveSuppliers(contractId, supplierDtos);
        }

    }

    @Override
    public void saveSources(List<SourceInfoDto> sourceInfoDtos) throws SQLException {
        final Connection connection = databaseConnectionFactory.getConnection();
        for (SourceInfoDto sourceInfoDto : sourceInfoDtos) {
            final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO source (ico, name, url) VALUES (?,?,?);");
            preparedStatement.setQueryTimeout(5);
            preparedStatement.setString(1, sourceInfoDto.getIco());
            preparedStatement.setString(2, sourceInfoDto.getName());
            preparedStatement.setString(3, sourceInfoDto.getUrl());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<SourceInfoDto> loadSources() throws SQLException {
        final Connection connection = databaseConnectionFactory.getConnection();
        final List<SourceInfoDto> result = new ArrayList<>();
        final PreparedStatement preparedStatement = connection.prepareStatement("select ico, name, url from source ORDER BY ico;");
        final ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            final SourceInfoDto sourceInfoDto = new SourceInfoDto();
            sourceInfoDto.setIco(resultSet.getString("ico"));
            sourceInfoDto.setName(resultSet.getString("name"));
            sourceInfoDto.setUrl(resultSet.getString("url"));
            result.add(sourceInfoDto);
        }
        return result;
    }

    @Override
    public void deleteSources() throws SQLException {
        final Connection connection = databaseConnectionFactory.getConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM source;");
        preparedStatement.executeUpdate();
    }

    @Override
    public void deleteErrors(int year) throws SQLException {
        final Connection connection = databaseConnectionFactory.getConnection();
        final PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM error WHERE year = ?");
        deleteStatement.setInt(1, year);
        deleteStatement.execute();
    }

    @Override
    public void saveRetrieval(int year, boolean complete, Date lastDate, int numberOfErrors, int numberOfRecords) throws SQLException {
        final Connection connection = databaseConnectionFactory.getConnection();
        final PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM retrieval WHERE year = ?");
        deleteStatement.setInt(1, year);
        deleteStatement.execute();

        final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO retrieval (year, success, date , num_bad_records, num_records_inserted) VALUES (?,?,?,?,?);");
        preparedStatement.setInt(1, year);
        preparedStatement.setBoolean(2, true);
        preparedStatement.setTimestamp(3, new Timestamp(new Date().getTime()));
        preparedStatement.setInt(4, numberOfErrors);
        preparedStatement.setInt(5, numberOfRecords);
        preparedStatement.executeUpdate();

    }

    @Override
    public boolean isYearCompleted(int year) throws SQLException {
        final Connection connection = databaseConnectionFactory.getConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM retrieval WHERE year = ?");
        preparedStatement.setInt(1, year);
        final ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return true;
        }
        return false;
    }

    @Override
    public void saveError(SourceInfoDto sourceInfoDto, String message, int year, String errorClass) throws SQLException {
        final Connection connection = databaseConnectionFactory.getConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO error (ico, name, message, url, year, error_class) VALUES (?,?,?,?,?,?);");
        preparedStatement.setQueryTimeout(5);
        preparedStatement.setString(1, sourceInfoDto.getIco());
        preparedStatement.setString(2, sourceInfoDto.getName());
        preparedStatement.setString(3, message);
        preparedStatement.setString(4, sourceInfoDto.getUrl());
        preparedStatement.setInt(5, year);
        preparedStatement.setString(6, errorClass);
        preparedStatement.executeUpdate();
    }

    @Override
    public Set<String> loadErrorUrlsForYear(int year) throws SQLException {
        final Connection connection = databaseConnectionFactory.getConnection();
        final Set<String> result = new HashSet<>();
        final PreparedStatement preparedStatement = connection.prepareStatement("select url from error WHERE YEAR = ? ORDER BY ico;");
        preparedStatement.setInt(1, year);
        final ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            result.add(resultSet.getString("url"));
        }
        return result;
    }

    @Override
    public void deleteCollectedData(Integer year) throws SQLException {
        final String sql;
        if (year == null) {
            sql = "delete from subsupplier;\n" +
                    "delete from supplier;\n" +
                    "delete from candidate;\n" +
                    "delete from contract;\n" +
                    "delete from retrieval;\n" +
                    "delete from submitter;\n" +
                    "delete from entity;\n" +
                    "delete from error;";
        } else {
            sql = "DELETE  from subsupplier ss USING supplier s WHERE ss.supplier_id=s.supplier_id and s.contract_id in (select c.contract_id from contract c WHERE c.year = ?);\n" +
                    "DELETE  from supplier s WHERE s.contract_id in (select c.contract_id from contract c  WHERE c.year = ?);\n" +
                    "DELETE  from candidate s WHERE s.contract_id in (select c.contract_id from contract c  WHERE c.year = ?);\n" +
                    "DELETE  from contract WHERE year = ?;\n" +
                    "DELETE  from error WHERE year = ?;" +
                    "DELETE  from retrieval WHERE year = ?;";

        }
        final PreparedStatement statement = databaseConnectionFactory.getConnection().prepareStatement(sql);

        if (year != null) {
            statement.setInt(1, year);
            statement.setInt(2, year);
            statement.setInt(3, year);
            statement.setInt(4, year);
            statement.setInt(5, year);
            statement.setInt(6, year);
        }
        statement.executeUpdate();
    }

    private void saveSuppliers(long contractId, List<SupplierDto> supplierDtos) throws SQLException {
        for (SupplierDto supplierDto : supplierDtos) {
            final String ico = supplierDto.getIco();
            final String name = supplierDto.getName();
            final Double price = supplierDto.getPrice();
            final long supplierId = saveSupplier(contractId, ico, name, price);
            final List<SubSupplierDto> subSupplierDtos = supplierDto.getSubSupplierDtos();
            saveSubsuppliers(supplierId, subSupplierDtos);
        }
    }

    private void saveSubsuppliers(long supplierId, List<SubSupplierDto> subSupplierDtos) throws SQLException {
        for (SubSupplierDto subSupplierDto : subSupplierDtos) {
            final String ico = subSupplierDto.getIco();
            final String name = subSupplierDto.getName();
            saveSubsupplier(supplierId, ico, name);
        }
    }

    private void saveSubsupplier(long supplierId, String subsupplierIco, String subsupplierName) throws SQLException {
        final Connection connection = databaseConnectionFactory.getConnection();
        final int companyId = saveCompany(subsupplierIco, subsupplierName);
        final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO subsupplier (entity_id, supplier_id) VALUES (?,?);");
        preparedStatement.setQueryTimeout(5);
        preparedStatement.setInt(1, companyId);
        preparedStatement.setLong(2, supplierId);
        preparedStatement.executeUpdate();
    }

    private long saveSupplier(long contractId, String supplierIco, String supplierName, Double price) throws SQLException {
        final Connection connection = databaseConnectionFactory.getConnection();
        final int companyId = saveCompany(supplierIco, supplierName);
        final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO supplier (entity_id, price, contract_id) VALUES (?,?,?);", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setQueryTimeout(5);
        preparedStatement.setInt(1, companyId);
        preparedStatement.setDouble(2, price == null ? -1d : price);
        preparedStatement.setLong(3, contractId);
        preparedStatement.executeUpdate();
        final ResultSet rs = preparedStatement.getGeneratedKeys();
        rs.next();
        return rs.getLong("supplier_id");
    }

    private void saveCandidates(long contractId, List<CandidateDto> candidateDtos) throws SQLException {
        for (CandidateDto candidateDto : candidateDtos) {
            final String ico = candidateDto.getIco();
            final String name = candidateDto.getName();
            final Double price = candidateDto.getPrice();
            saveCandidate(contractId, ico, name, price);
        }
    }

    private void saveCandidate(long contractId, String ico, String candidateName, Double price) throws SQLException {
        final Connection connection = databaseConnectionFactory.getConnection();
        final int companyId = saveCompany(ico, candidateName);
        final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO candidate (entity_id, price, contract_id) VALUES (?,?,?);");
        preparedStatement.setQueryTimeout(5);
        preparedStatement.setInt(1, companyId);
        preparedStatement.setDouble(2, price == null ? -1d : price);
        preparedStatement.setLong(3, contractId);
        preparedStatement.executeUpdate();
    }

    private long saveContract(long submitterId, String code1, String code2, String name, String state, String kind, int year) throws SQLException {
        final Connection connection = databaseConnectionFactory.getConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO contract (code1,code2, name, state, kind, submitter_id, year) VALUES (?,?,?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setQueryTimeout(5);
        preparedStatement.setString(1, code1);
        preparedStatement.setString(2, code2);
        preparedStatement.setString(3, name);
        preparedStatement.setString(4, state);
        preparedStatement.setString(5, kind);
        preparedStatement.setLong(6, submitterId);
        preparedStatement.setInt(7, year);
        preparedStatement.executeUpdate();
        final ResultSet rs = preparedStatement.getGeneratedKeys();
        rs.next();
        return rs.getLong("contract_id");
    }

    private long saveSubmitter(String ico, String name) throws SQLException {
        final Connection connection = databaseConnectionFactory.getConnection();
        final PreparedStatement selectStatement = connection.prepareStatement("Select s.submitter_id from submitter s join entity c on c.entity_id=s.entity_id where c.ico = ?");
        selectStatement.setString(1, ico);
        final ResultSet selectResultSet = selectStatement.executeQuery();
        if (selectResultSet.next()) {
            return selectResultSet.getLong("submitter_id");
        }

        final int companyId = saveCompany(ico, name);
        final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO submitter (entity_id) VALUES (?);", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setQueryTimeout(5);
        preparedStatement.setInt(1, companyId);
        preparedStatement.executeUpdate();
        final ResultSet rs = preparedStatement.getGeneratedKeys();
        rs.next();
        return rs.getLong("submitter_id");
    }

    private int saveCompany(String ico, String name) throws SQLException {
        final Connection connection = databaseConnectionFactory.getConnection();
        if (ico != null) {
            final PreparedStatement selectStatement = connection.prepareStatement("Select entity_id from entity where ico = ?");
            selectStatement.setString(1, ico);
            final ResultSet rs = selectStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("entity_id");
            }
        }
        final PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO entity (ico,name, entity_type) VALUES (?,?,'company');", Statement.RETURN_GENERATED_KEYS);
        insertStatement.setString(1, ico);
        insertStatement.setString(2, name);
        insertStatement.setQueryTimeout(5);
        insertStatement.executeUpdate();
        final ResultSet rs2 = insertStatement.getGeneratedKeys();
        rs2.next();
        return rs2.getInt("entity_id");

    }
}
