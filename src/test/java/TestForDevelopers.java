import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import service.ARESService;
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

    @org.junit.Test
    @Transactional
    public void test() {
        System.out.println(aresService.findCompany("68407700"));

        final List<String> icos = new ArrayList<>();
        icos.add("48034711");
        System.out.println(databaseReadService.getSubmitters(icos));
    }
}