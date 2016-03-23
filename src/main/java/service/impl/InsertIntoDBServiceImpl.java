package service.impl;

import isvz.isvs.micr.schemas.corecomponenttypes.v1.CenaType;
import isvz.isvz.mmr.schemas.vz_z_profilu_zadavatele.v100.*;
import db.DatabaseConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import service.InsertIntoDBService;

import java.sql.*;
import java.util.List;


public class InsertIntoDBServiceImpl implements InsertIntoDBService {

    @Autowired
    private DatabaseConnectionFactory databaseConnectionFactory;

    @Override
    public void saveSubmitter(ProfilStructure profilStructure) throws SQLException {


        final Connection connection = databaseConnectionFactory.getConnection();


        final ZadavatelStructure submitter = profilStructure.getZadavatel();
        final long submitterId = saveSubmitter(submitter, connection);
        final List<ZakazkaStructure> contracts = profilStructure.getZakazka();
        for (ZakazkaStructure contract : contracts) {
            final String code1 = contract.getVz().getKodVzNaUsvzis().getValue();
            final String code2 = contract.getVz().getKodVzNaProfilu().getValue();
            final String name = contract.getVz().getNazevVz().getValue();
            final String state = contract.getVz().getStavVz().getValue();
            final String kind = contract.getVz().getDruhZadavacihoRizeni().getValue();
            final long contractId = saveContract(connection, submitterId, code1, code2, name, state, kind);

            final List<UchazecStructure> candidates = contract.getUchazec();
            saveCandidates(connection, contractId, candidates);
            final List<DodavatelStructure> suppliers = contract.getDodavatel();
            saveSuppliers(connection, contractId, suppliers);


        }


    }

    private void saveSuppliers(Connection connection, long contractId, List<DodavatelStructure> suppliers) throws SQLException {
        for (DodavatelStructure supplier : suppliers) {
            final String supplierIco = supplier.getIco().getValue();
            final String supplierName = supplier.getNazevDodavatele().getValue();
            final CenaType cenaCelkemDleSmlouvyDPH = supplier.getCenaCelkemDleSmlouvyDPH();
            final Double price;
            if(cenaCelkemDleSmlouvyDPH == null){
                price = null;
            }else{
                price = cenaCelkemDleSmlouvyDPH.getValue().doubleValue();
            }
            final long supplierId = saveSupplier(connection, contractId, supplierIco, supplierName, price);

            final List<SubdodavatelStructure> subsuppliers = supplier.getSubdodavatel();
            saveSubsuppliers(connection, supplierId, subsuppliers);

        }
    }

    private void saveSubsuppliers(Connection connection, long supplierId, List<SubdodavatelStructure> subsuppliers) throws SQLException {
        for (SubdodavatelStructure subsupplier : subsuppliers) {
            final String subsupplierIco = subsupplier.getIcoSub().getValue();
            final String subsupplierName = subsupplier.getNazevSub().getValue();
            saveSubsupplier(connection, supplierId, subsupplierIco, subsupplierName);
        }
    }

    private void saveSubsupplier(Connection connection, long supplierId, String subsupplierIco, String subsupplierName) throws SQLException {
        final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO subsupplier (ico,name, subsupplier_id) VALUES (?,?,?);");
        preparedStatement.setString(1, subsupplierIco);
        preparedStatement.setString(2, subsupplierName);
        preparedStatement.setLong(3, supplierId);
        preparedStatement.executeUpdate();
    }

    private long saveSupplier(Connection connection, long contractId, String supplierIco, String supplierName, Double price) throws SQLException {
        final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO supplier (ico,name, price, contract_id) VALUES (?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, supplierIco);
        preparedStatement.setString(2, supplierName);
        preparedStatement.setDouble(3, price == null ? -1d : price);
        preparedStatement.setLong(4, contractId);
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        rs.next();
        return rs.getLong(1);
    }

    private void saveCandidates(Connection connection, long contractId, List<UchazecStructure> candidates) throws SQLException {
        for (UchazecStructure candidate : candidates) {
            final String ico = candidate.getIco().getValue();
            final String candidateName = candidate.getNazevUchazece().getValue();
            final List<NabidkaStructure> offers = candidate.getNabidka();
            final Double price;
            if (!offers.isEmpty()) {
                price = offers.get(0).getNabidkovaCenaSDph().getValue().doubleValue();
            } else {
                price = null;
            }
            saveCandidate(connection, contractId, ico, candidateName, price);
        }
    }

    private void saveCandidate(Connection connection, long contractId, String ico, String candidateName, Double price) throws SQLException {
        final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO candidate (ico,name, price, contract_id) VALUES (?,?,?,?);");
        preparedStatement.setString(1, ico);
        preparedStatement.setString(2, candidateName);
        preparedStatement.setDouble(3, price == null ? -1d : price);
        preparedStatement.setLong(4, contractId);
        preparedStatement.executeUpdate();
    }

    private long saveContract(Connection connection, long submitterId, String code1, String code2, String name, String state, String kind) throws SQLException {
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
        final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO submitter (ico,name) VALUES (?,?);", Statement.RETURN_GENERATED_KEYS);
        final String ico = submitter.getIcoVlastni().getValue();
        preparedStatement.setString(1, ico);
        final String submitterName = submitter.getNazevZadavatele().getValue();
        preparedStatement.setString(2, submitterName);
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        rs.next();
        return rs.getLong(1);
    }
}
