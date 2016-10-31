
package ares;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tzu_osoba.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;simpleType name="tzu_osoba">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="T"/>
 *     &lt;enumeration value="Z"/>
 *     &lt;enumeration value="U"/>
 *     &lt;enumeration value="E"/>
 *     &lt;enumeration value="Tuzemsk"/>
 *     &lt;enumeration value="Zahranin"/>
 *     &lt;enumeration value="Uprchlk"/>
 *     &lt;enumeration value="Evropsk spolenost"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "tzu_osoba", namespace = "http://wwwinfo.mfcr.cz/ares/xml_doc/schemas/ares/ares_datatypes/v_1.0.2")
@XmlEnum
public enum TzuOsoba {

    T("T"),
    Z("Z"),
    U("U"),
    E("E"),
    @XmlEnumValue("Tuzemsk\u00e1")
    TUZEMSKa("Tuzemsk\u00e1"),
    @XmlEnumValue("Zahrani\u010dn\u00ed")
    ZAHRANIcNi("Zahrani\u010dn\u00ed"),
    @XmlEnumValue("Uprchl\u00edk")
    UPRCHLiK("Uprchl\u00edk"),
    @XmlEnumValue("Evropsk\u00e1 spole\u010dnost")
    EVROPSKa_SPOLEcNOST("Evropsk\u00e1 spole\u010dnost");
    private final String value;

    TzuOsoba(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TzuOsoba fromValue(String v) {
        for (TzuOsoba c : TzuOsoba.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
