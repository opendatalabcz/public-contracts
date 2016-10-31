import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import service.ARESService;
import service.CreateRDFSubmitters;
import service.DatabaseReadService;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-for-developers.xml"})
public class TestForDevelopers {

    @Autowired
    private ARESService aresService;

    @Autowired
    private DatabaseReadService databaseReadService;
    @Autowired
    private CreateRDFSubmitters createRDFSubmitters;

    @org.junit.Test
    @Transactional
    public void test() {

        final List<String> icos = new ArrayList<>();
        icos.add("00252638");
        icos.add("00284637");
        icos.add("61664651");
        icos.add("00298921");
        icos.add("00636169");
        icos.add("70891320");
        icos.add("00025593");
        icos.add("00295892");
        createRDFSubmitters.createRDF(icos);
    }
}