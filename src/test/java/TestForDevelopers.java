import eu.profinit.publiccontracts.db.DatabaseConnectionFactory;
import eu.profinit.publiccontracts.dto.SourceInfoDto;
import eu.profinit.publiccontracts.generated.isvz.mmr.schemas.vz_z_profilu_zadavatele.v100.ProfilStructure;
import eu.profinit.publiccontracts.generated.isvz.mmr.schemas.vz_z_profilu_zadavatele.v100.VZStructure;
import eu.profinit.publiccontracts.generated.isvz.mmr.schemas.vz_z_profilu_zadavatele.v100.ZakazkaStructure;
import eu.profinit.publiccontracts.service.ISVZCrawlerService;
import eu.profinit.publiccontracts.service.ISVZService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-for-developers.xml"})
public class TestForDevelopers {

    @Autowired
    private DatabaseConnectionFactory databaseConnectionFactory;

    @Autowired
    private ISVZCrawlerService isvzCrawlerService;

    @Autowired
    private ISVZService isvzService;

    @Ignore
    @Test
    public void test() {

    }

    @Test
    public void testDB() {
        final Connection connection = databaseConnectionFactory.getConnection();
        final PreparedStatement preparedStatement;
        try {
            List<String> tables = new ArrayList<>();
            preparedStatement = connection.prepareStatement("SELECT tablename FROM pg_catalog.pg_tables WHERE schemaname = 'public';");
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String tablename = resultSet.getString("tablename");
                tables.add(tablename);
            }
            assert tables.contains("entity");
            assert tables.contains("retrieval");
            assert tables.contains("submitter");
            assert tables.contains("contract");
            assert tables.contains("candidate");
            assert tables.contains("supplier");
            assert tables.contains("subsupplier");
            assert tables.contains("error");
            assert tables.contains("source");
            assert tables.contains("document");
            assert tables.contains("parameter");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void testVestnikverejnychzakazekCrawler() {
        try {
            final List<SourceInfoDto> submitters = isvzCrawlerService.findSubmitters();
            assert submitters.size()==50;
            for (SourceInfoDto submitter : submitters) {
                assert submitter.getUrl() != null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testProfilStructure() {
        try {
            ProfilStructure profilStructure = isvzService.findProfilStructure("https://ezak.mzp.cz/profile_display_2.html", 2018);
            assert "489950".equals(profilStructure.getProfilKod().getValue());
            assert "00164801".equals(profilStructure.getZadavatel().getIcoVlastni().getValue());
            List<ZakazkaStructure> zakazka = profilStructure.getZakazka();
            assert zakazka.size() == 179;
            ZakazkaStructure zakazkaStructure = zakazka.get(0);
            assert "26490951".equals(zakazkaStructure.getDodavatel().get(0).getIco().getValue());
            assert "05732051".equals(zakazkaStructure.getUcastnik().get(0).getIco().getValue());
            assert BigDecimal.valueOf(1949310.00).compareTo(zakazkaStructure.getUcastnik().get(0).getCenaSDph().getValue()) == 0;
            VZStructure vz = zakazkaStructure.getVZ();
            assert vz.getDokument().size() == 2;
            assert "https://ezak.mzp.cz/document_download_35272.html".equals(vz.getDokument().get(0).getUrl().getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}