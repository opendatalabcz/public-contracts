
package ares;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for error_kod.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="error_kod">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="versionMismatch"/>
 *     &lt;enumeration value="MustUnderstand"/>
 *     &lt;enumeration value="Client"/>
 *     &lt;enumeration value="Client.RequestNamespace"/>
 *     &lt;enumeration value="Client.SchemaNamespace"/>
 *     &lt;enumeration value="Client.Signature"/>
 *     &lt;enumeration value="Client.Validity"/>
 *     &lt;enumeration value="Client.WellFormedness"/>
 *     &lt;enumeration value="Client.XSLTNamespace"/>
 *     &lt;enumeration value="Server"/>
 *     &lt;enumeration value="Server.DatabaseConsistence"/>
 *     &lt;enumeration value="Server.Program"/>
 *     &lt;enumeration value="Server.Service"/>
 *     &lt;enumeration value="Server.SQLStatement"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "error_kod", namespace = "http://wwwinfo.mfcr.cz/ares/xml_doc/schemas/uvis_datatypes/v_1.0.1")
@XmlEnum
public enum ErrorKod {


    /**
     * nalezen neplatn jmenn prostor pro oblku SOAP
     * 
     */
    @XmlEnumValue("versionMismatch")
    VERSION_MISMATCH("versionMismatch"),

    /**
     * pro dceinn element zhlav SOAP s nastavenm atributu mustUnderstand na True pijmajc strana zcela neporozumla obsahu elementu
     * 
     */
    @XmlEnumValue("MustUnderstand")
    MUST_UNDERSTAND("MustUnderstand"),

    /**
     * klientsk tda chyb, indikujc, e zprva nen korektn formtovna nebo e neobsahuje poadovan informace
     * 
     */
    @XmlEnumValue("Client")
    CLIENT("Client"),

    /**
     * nepodporovan verze dotazu
     * 
     */
    @XmlEnumValue("Client.RequestNamespace")
    CLIENT_REQUEST_NAMESPACE("Client.RequestNamespace"),

    /**
     * nepodporovan verze namespace pro validaci parserem
     * 
     */
    @XmlEnumValue("Client.SchemaNamespace")
    CLIENT_SCHEMA_NAMESPACE("Client.SchemaNamespace"),

    /**
     * chyba oven identity odeslatele pomoc digitlnho podpisu
     * 
     */
    @XmlEnumValue("Client.Signature")
    CLIENT_SIGNATURE("Client.Signature"),

    /**
     * chyba oven validity dokumentu
     * 
     */
    @XmlEnumValue("Client.Validity")
    CLIENT_VALIDITY("Client.Validity"),

    /**
     * chyba oven sprvnho strukturovn dokumentu
     * 
     */
    @XmlEnumValue("Client.WellFormedness")
    CLIENT_WELL_FORMEDNESS("Client.WellFormedness"),

    /**
     * nepodporovan verze namespace pro XSLT validaci
     * 
     */
    @XmlEnumValue("Client.XSLTNamespace")
    CLIENT_XSLT_NAMESPACE("Client.XSLTNamespace"),

    /**
     * serverov tda chyb, indikujc, e zprva nen zpracovna z dvodu problm na stran sluby
     * 
     */
    @XmlEnumValue("Server")
    SERVER("Server"),

    /**
     * nekonzistence dat v databzi sluby
     * 
     */
    @XmlEnumValue("Server.DatabaseConsistence")
    SERVER_DATABASE_CONSISTENCE("Server.DatabaseConsistence"),

    /**
     * intern chyba serverov aplikace
     * 
     */
    @XmlEnumValue("Server.Program")
    SERVER_PROGRAM("Server.Program"),

    /**
     * obecn chyba serverov sluby
     * 
     */
    @XmlEnumValue("Server.Service")
    SERVER_SERVICE("Server.Service"),

    /**
     * intern chyba SQL dotazu
     * 
     */
    @XmlEnumValue("Server.SQLStatement")
    SERVER_SQL_STATEMENT("Server.SQLStatement");
    private final String value;

    ErrorKod(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ErrorKod fromValue(String v) {
        for (ErrorKod c: ErrorKod.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
