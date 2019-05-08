import eu.profinit.publiccontracts.db.DatabaseConnectionFactory;
import eu.profinit.publiccontracts.dto.SourceInfoDto;
import eu.profinit.publiccontracts.service.DatabaseService;
import eu.profinit.publiccontracts.service.ISVZCrawlerService;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
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

    @Ignore
    @org.junit.Test
    public void test() {

    }

    @org.junit.Test
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

}