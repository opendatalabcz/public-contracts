import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.ISVZService;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-for-developers.xml"})
public class TestForDevelopers {

    @Autowired
    private ISVZService isvzService;

    @Ignore
    @org.junit.Test
    public void test3() {

// Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                }
        };

// Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
        }

        try {
            isvzService.findProfilStructure("https://veza.cz/Contracts.aspx/1087", 2014, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            isvzService.findProfilStructure("https://pro-za.cz/contracts/6d76a7", 2015, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            isvzService.findProfilStructure("https://www.egordion.cz/nabidkaGORDION/profilNIDV", 2015, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}