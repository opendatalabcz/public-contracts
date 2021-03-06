//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.19 at 01:50:51 AM CEST 
//


package eu.profinit.publiccontracts.generated.isvs.micr.schemas.dataelementstypes.v.yl0013.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DatovyKonstruktDilciDruhContentType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DatovyKonstruktDilciDruhContentType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="jednoduch� datov� prvek"/>
 *     &lt;enumeration value="sekvence"/>
 *     &lt;enumeration value="slo�en� datov� prvek"/>
 *     &lt;enumeration value="volba"/>
 *     &lt;enumeration value="XML atribut"/>
 *     &lt;enumeration value="XML konstrukt any"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DatovyKonstruktDilciDruhContentType", namespace = "urn:cz:isvs:micr:schemas:DataElementsTypes:v:YL0013:v1")
@XmlEnum
public enum DatovyKonstruktDilciDruhContentType {

    @XmlEnumValue("jednoduch\u00fd datov\u00fd prvek")
    JEDNODUCH�_DATOV�_PRVEK("jednoduch\u00fd datov\u00fd prvek"),
    @XmlEnumValue("sekvence")
    SEKVENCE("sekvence"),
    @XmlEnumValue("slo\u017een\u00fd datov\u00fd prvek")
    SLO�EN�_DATOV�_PRVEK("slo\u017een\u00fd datov\u00fd prvek"),
    @XmlEnumValue("volba")
    VOLBA("volba"),
    @XmlEnumValue("XML atribut")
    XML_ATRIBUT("XML atribut"),
    @XmlEnumValue("XML konstrukt any")
    XML_KONSTRUKT_ANY("XML konstrukt any");
    private final String value;

    DatovyKonstruktDilciDruhContentType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DatovyKonstruktDilciDruhContentType fromValue(String v) {
        for (DatovyKonstruktDilciDruhContentType c: DatovyKonstruktDilciDruhContentType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
