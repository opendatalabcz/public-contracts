package service.impl;

import db.DatabaseConnectionFactory;
import dto.CompanyDto;
import generated.isvs.micr.schemas.businesstypes.v2.SubjektObchodniJmenoType;
import generated.isvs.micr.schemas.corecomponenttypes.v1.CenaType;
import generated.isvz.mmr.schemas.vz_z_profilu_zadavatele.v100.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import service.DatabaseServiceRenamed;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DatabaseServiceRenamedImpl implements DatabaseServiceRenamed {

    @Autowired
    private DatabaseConnectionFactory databaseConnectionFactory;

    private Connection connection;

    final static Logger logger = Logger.getLogger(DatabaseServiceRenamedImpl.class);


    @Override
    public void saveSubmitter(ProfilStructure profilStructure) throws SQLException {
        connection = databaseConnectionFactory.getConnection();


        final ZadavatelStructure submitter = profilStructure.getZadavatel();
        final long submitterId = saveSubmitter(submitter, connection);
        final List<ZakazkaStructure> contracts = profilStructure.getZakazka();
        for (ZakazkaStructure contract : contracts) {
            final KodVZNaUsvzisType kodVzNaUsvzis = contract.getVz().getKodVzNaUsvzis();
            final String code1 = kodVzNaUsvzis == null ? null : kodVzNaUsvzis.getValue();

            final KodVzNaProfiluType kodVzNaProfilu = contract.getVz().getKodVzNaProfilu();
            final String code2 = kodVzNaProfilu == null ? null : kodVzNaProfilu.getValue();

            final NazevVZType nazevVz = contract.getVz().getNazevVz();
            final String name = nazevVz == null ? null : nazevVz.getValue();

            final StavVZType stavVz = contract.getVz().getStavVz();
            final String state = stavVz == null ? null : stavVz.getValue();

            final DruhZadavacihoRizeniType druhZadavacihoRizeni = contract.getVz().getDruhZadavacihoRizeni();
            final String kind = druhZadavacihoRizeni == null ? null : druhZadavacihoRizeni.getValue();

            final long contractId = saveContract(submitterId, code1, code2, name, state, kind);

            final List<UchazecStructure> candidates = contract.getUchazec();
            saveCandidates(contractId, candidates);
            final List<DodavatelStructure> suppliers = contract.getDodavatel();
            saveSuppliers(contractId, suppliers);
        }

    }

    @Override
    public void saveSubmitters(List<CompanyDto> companyDtos) throws SQLException {
        connection = databaseConnectionFactory.getConnection();
        for (CompanyDto companyDto : companyDtos) {
            final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO data (ico, name, url) VALUES (?,?,?);");
            preparedStatement.setString(1, companyDto.getIco());
            preparedStatement.setString(2, companyDto.getName());
            preparedStatement.setString(3, companyDto.getUrl());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<CompanyDto> loadSubmitters() throws SQLException {
        final List<CompanyDto> result = new ArrayList<>();
        connection = databaseConnectionFactory.getConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement("select ico, name, url from data;");
        final ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            final CompanyDto companyDto = new CompanyDto();
            companyDto.setIco(resultSet.getString("ico"));
            companyDto.setName(resultSet.getString("name"));
            companyDto.setUrl(resultSet.getString("url"));
            result.add(companyDto);
        }
        return result;
    }

    @Override
    public void saveRetrieval(int year, boolean complete, Date lastDate, int numberOfErrors) throws SQLException {
        connection = databaseConnectionFactory.getConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO retrieval (year, complete, last_day, number_of_errors) VALUES (?,?,?,?);");
        preparedStatement.setInt(1, year);
        preparedStatement.setBoolean(2, complete);
        final java.sql.Date date = new java.sql.Date(lastDate.getTime());
        preparedStatement.setDate(3, date);
        preparedStatement.setInt(4, numberOfErrors);
        preparedStatement.executeUpdate();

    }

    @Override
    public Date loadRetrievalLastDate(int year) throws SQLException {
        connection = databaseConnectionFactory.getConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement("select complete, last_day from retrieval where year = ?");
        preparedStatement.setInt(1, year);
        final ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            final boolean completed = resultSet.getBoolean("completed");
            final java.sql.Date last_day = resultSet.getDate("last_day");
            if (completed) {
                throw new RuntimeException("year is already completed");
            } else {
                return new Date(last_day.getTime());
            }
        }
        return null;
    }

    private void saveSuppliers(long contractId, List<DodavatelStructure> suppliers) throws SQLException {
        for (DodavatelStructure supplier : suppliers) {
            final IcoType ico = supplier.getIco();
            final String supplierIco = ico == null ? null : ico.getValue();
            final SubjektObchodniJmenoType nazevDodavatele = supplier.getNazevDodavatele();
            final String supplierName = nazevDodavatele == null ? null : nazevDodavatele.getValue();
            final CenaType cenaCelkemDleSmlouvyDPH = supplier.getCenaCelkemDleSmlouvyDPH();
            final Double price;
            if (cenaCelkemDleSmlouvyDPH == null) {
                price = null;
            } else {
                price = cenaCelkemDleSmlouvyDPH.getValue().doubleValue();
            }
            final long supplierId = saveSupplier(contractId, supplierIco, supplierName, price);

            final List<SubdodavatelStructure> subsuppliers = supplier.getSubdodavatel();
            saveSubsuppliers(supplierId, subsuppliers);

        }
    }

    private void saveSubsuppliers(long supplierId, List<SubdodavatelStructure> subsuppliers) throws SQLException {
        for (SubdodavatelStructure subsupplier : subsuppliers) {
            final IcoType icoSub = subsupplier.getIcoSub();
            final String subsupplierIco = icoSub == null ? null : icoSub.getValue();
            final SubjektObchodniJmenoType nazevSub = subsupplier.getNazevSub();
            final String subsupplierName = nazevSub == null ? null : nazevSub.getValue();
            saveSubsupplier(supplierId, subsupplierIco, subsupplierName);
        }
    }

    private void saveSubsupplier(long supplierId, String subsupplierIco, String subsupplierName) throws SQLException {
        final long companyId = saveCompany(subsupplierIco, subsupplierName);
        final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO subsupplier (company_id, supplier_id) VALUES (?,?);");
        preparedStatement.setLong(1, companyId);
        preparedStatement.setLong(2, supplierId);
        preparedStatement.executeUpdate();
    }

    private long saveSupplier(long contractId, String supplierIco, String supplierName, Double price) throws SQLException {
        final long companyId = saveCompany(supplierIco, supplierName);
        final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO supplier (company_id, price, contract_id) VALUES (?,?,?);", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setLong(1, companyId);
        preparedStatement.setDouble(2, price == null ? -1d : price);
        preparedStatement.setLong(3, contractId);
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        rs.next();
        return rs.getLong(1);
    }

    private void saveCandidates(long contractId, List<UchazecStructure> candidates) throws SQLException {
        for (UchazecStructure candidate : candidates) {
            final String ico = candidate.getIco().getValue();
            final String candidateName = candidate.getNazevUchazece().getValue();
            final CenaType cenaSDph = candidate.getCenaSDph();
            final Double price = cenaSDph == null ? null : cenaSDph.getValue().doubleValue();
            saveCandidate(contractId, ico, candidateName, price);
        }
    }

    private void saveCandidate(long contractId, String ico, String candidateName, Double price) throws SQLException {
        final long companyId = saveCompany(ico, candidateName);
        final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO candidate (company_id, price, contract_id) VALUES (?,?,?);");
        preparedStatement.setLong(1, companyId);
        preparedStatement.setDouble(2, price == null ? -1d : price);
        preparedStatement.setLong(3, contractId);
        preparedStatement.executeUpdate();
    }

    private long saveContract(long submitterId, String code1, String code2, String name, String state, String kind) throws SQLException {
        final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO contract (code1,code2, name, state, kind, submitter_id) VALUES (?,?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, code1);
        preparedStatement.setString(2, code2);
        preparedStatement.setString(3, name);
        preparedStatement.setString(4, state);
        preparedStatement.setString(5, kind);
        preparedStatement.setLong(6, submitterId);
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        rs.next();
        return rs.getLong(1);
    }

    private long saveSubmitter(ZadavatelStructure submitter, Connection connection) throws SQLException {
        final String ico = submitter.getIcoVlastni().getValue();
        final String submitterName = submitter.getNazevZadavatele().getValue();
        final long companyId = saveCompany(ico, submitterName);
        final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO submitter (company_id) VALUES (?);", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setLong(1, companyId);
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        rs.next();
        return rs.getLong(1);
    }

    private long saveCompany(String ico, String name) throws SQLException {
        if (ico != null) {
            final PreparedStatement selectStatement = connection.prepareStatement("Select company_id from company where ico = ?");
            selectStatement.setString(1, ico);
            ResultSet rs = selectStatement.executeQuery();
            if (rs.next()) {
                Long companyId = rs.getLong("company_id");
                return companyId;
            }
        }
        final PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO company (ico,name) VALUES (?,?);", Statement.RETURN_GENERATED_KEYS);
        insertStatement.setString(1, ico);
        insertStatement.setString(2, name);
        insertStatement.executeUpdate();
        ResultSet rs2 = insertStatement.getGeneratedKeys();
        rs2.next();
        return rs2.getLong(1);

    }
}
