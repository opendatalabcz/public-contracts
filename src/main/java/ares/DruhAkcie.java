
package ares;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for druh_akcie.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;simpleType name="druh_akcie">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Akcie na jmno"/>
 *     &lt;enumeration value="Akcie na majitele"/>
 *     &lt;enumeration value="Zamstnaneck akcie na jmno"/>
 *     &lt;enumeration value="Prioritn akcie na majitele"/>
 *     &lt;enumeration value="Prioritn akcie na jmno"/>
 *     &lt;enumeration value="Akcie se zvltnmi prvy"/>
 *     &lt;enumeration value="Kmenov akcie na jmno"/>
 *     &lt;enumeration value="Kmenov akcie na majitele"/>
 *     &lt;enumeration value="Na doruitele"/>
 *     &lt;enumeration value="Na jmno a majitele"/>
 *     &lt;enumeration value="Na dritele"/>
 *     &lt;enumeration value="Bez uveden druhu"/>
 *     &lt;enumeration value="Hromadn akcie"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "druh_akcie", namespace = "http://wwwinfo.mfcr.cz/ares/xml_doc/schemas/ares/ares_datatypes/v_1.0.2")
@XmlEnum
public enum DruhAkcie {

    @XmlEnumValue("Akcie na jm\u00e9no")
    AKCIE_NA_JMeNO("Akcie na jm\u00e9no"),
    @XmlEnumValue("Akcie na majitele")
    AKCIE_NA_MAJITELE("Akcie na majitele"),
    @XmlEnumValue("Zam\u011bstnaneck\u00e9 akcie na jm\u00e9no")
    ZAMeSTNANECKa_AKCIE_NA_JMeNO("Zam\u011bstnaneck\u00e9 akcie na jm\u00e9no"),
    @XmlEnumValue("Prioritn\u00ed akcie na majitele")
    PRIORITNi_AKCIE_NA_MAJITELE("Prioritn\u00ed akcie na majitele"),
    @XmlEnumValue("Prioritn\u00ed akcie na jm\u00e9no")
    PRIORITNi_AKCIE_NA_JMeNO("Prioritn\u00ed akcie na jm\u00e9no"),
    @XmlEnumValue("Akcie se zvl\u00e1\u0161tn\u00edmi pr\u00e1vy")
    AKCIE_SE_ZVLasTNiMI_PRaVY("Akcie se zvl\u00e1\u0161tn\u00edmi pr\u00e1vy"),
    @XmlEnumValue("Kmenov\u00e9 akcie na jm\u00e9no")
    KMENOVa_AKCIE_NA_JMeNO("Kmenov\u00e9 akcie na jm\u00e9no"),
    @XmlEnumValue("Kmenov\u00e9 akcie na majitele")
    KMENOVa_AKCIE_NA_MAJITELE("Kmenov\u00e9 akcie na majitele"),
    @XmlEnumValue("Na doru\u010ditele")
    NA_DORUcITELE("Na doru\u010ditele"),
    @XmlEnumValue("Na jm\u00e9no a majitele")
    NA_JMeNO_A_MAJITELE("Na jm\u00e9no a majitele"),
    @XmlEnumValue("Na dr\u017eitele")
    NA_DRzITELE("Na dr\u017eitele"),
    @XmlEnumValue("Bez uveden\u00ed druhu")
    BEZ_UVEDENi_DRUHU("Bez uveden\u00ed druhu"),
    @XmlEnumValue("Hromadn\u00e1 akcie")
    HROMADNa_AKCIE("Hromadn\u00e1 akcie");
    private final String value;

    DruhAkcie(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DruhAkcie fromValue(String v) {
        for (DruhAkcie c : DruhAkcie.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
