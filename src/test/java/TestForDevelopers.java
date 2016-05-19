import dto.SubmitterDto;
import generated.isvz.mmr.schemas.vz_z_profilu_zadavatele.v100.ProfilStructure;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.DatabaseService;
import service.ISVZService;
import util.SubmitterTransformer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-for-developers.xml"})
public class TestForDevelopers {

    @Autowired
    private ISVZService isvzService;
    @Autowired
    private DatabaseService databaseService;

//    @Ignore
    @org.junit.Test
    public void test() {

        System.out.println(1);
        try {
            final ProfilStructure profilStructure = isvzService.findProfilStructure("https://zakazky.vls.cz/", 2015, null);
            final SubmitterDto submitterDto = SubmitterTransformer.transformSubmitterToDto(profilStructure);
//            databaseService.saveSubmitter(submitterDto, 2015);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(2);

        try {
            final ProfilStructure profilStructure = isvzService.findProfilStructure("https://pro-za.cz/contracts/6d76a7", 2015, null);
            final SubmitterDto submitterDto = SubmitterTransformer.transformSubmitterToDto(profilStructure);
//            databaseService.saveSubmitter(submitterDto, 2015);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(3);

        try {
            final ProfilStructure profilStructure = isvzService.findProfilStructure("https://www.egordion.cz/nabidkaGORDION/profilNIDV", 2015, null);
            final SubmitterDto submitterDto = SubmitterTransformer.transformSubmitterToDto(profilStructure);
//            databaseService.saveSubmitter(submitterDto, 2015);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }
}