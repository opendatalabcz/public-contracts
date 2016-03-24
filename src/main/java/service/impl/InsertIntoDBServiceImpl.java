package service.impl;

import db.DatabaseConnectionFactory;
import isvz.isvs.micr.schemas.businesstypes.v2.SubjektObchodniJmenoType;
import isvz.isvs.micr.schemas.corecomponenttypes.v1.CenaType;
import isvz.isvz.mmr.schemas.vz_z_profilu_zadavatele.v100.*;
import org.springframework.beans.factory.annotation.Autowired;
import service.InsertIntoDBService;

import java.sql.*;
import java.util.List;


public class InsertIntoDBServiceImpl implements InsertIntoDBService {

    @Autowired
    private DatabaseConnectionFactory databaseConnectionFactory;

    private Connection connection;

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
