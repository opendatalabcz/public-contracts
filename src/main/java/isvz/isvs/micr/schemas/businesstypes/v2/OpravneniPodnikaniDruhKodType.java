//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.03.23 at 01:46:39 AM CET 
//


package isvz.isvs.micr.schemas.businesstypes.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * K�d druhu dokladu, kter�m ekonomick� subjekt potvrzuje sv� opr�vn�n� k podnik�n�.
 * 
 * <p>Java class for OpravneniPodnikaniDruhKodType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OpravneniPodnikaniDruhKodType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;urn:cz:isvs:micr:schemas:BusinessTypes:c:AL0086:v1>OpravneniPodnikaniDruhKodContentType">
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OpravneniPodnikaniDruhKodType", propOrder = {
    "value"
})
public class OpravneniPodnikaniDruhKodType {

    @XmlValue
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String value;

    /**
     * K�d druhu dokladu, kter�m ekonomick� subjekt potvrzuje sv� opr�vn�n� k podnik�n�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

}
