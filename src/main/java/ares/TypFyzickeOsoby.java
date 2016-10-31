
package ares;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigInteger;


/**
 * <p>Java class for typ_fyzicke_osoby complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="typ_fyzicke_osoby">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Typ_FO_kod" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="Typ_FO" type="{http://wwwinfo.mfcr.cz/ares/xml_doc/schemas/uvis_datatypes/v_1.0.1}popis" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "typ_fyzicke_osoby", namespace = "http://wwwinfo.mfcr.cz/ares/xml_doc/schemas/ares/ares_datatypes/v_1.0.2", propOrder = {
    "typFOKod",
    "typFO"
})
public class TypFyzickeOsoby {

    @XmlElement(name = "Typ_FO_kod", namespace = "http://wwwinfo.mfcr.cz/ares/xml_doc/schemas/ares/ares_datatypes/v_1.0.2")
    protected BigInteger typFOKod;
    @XmlElement(name = "Typ_FO", namespace = "http://wwwinfo.mfcr.cz/ares/xml_doc/schemas/ares/ares_datatypes/v_1.0.2")
    protected String typFO;

    /**
     * Gets the value of the typFOKod property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTypFOKod() {
        return typFOKod;
    }

    /**
     * Sets the value of the typFOKod property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTypFOKod(BigInteger value) {
        this.typFOKod = value;
    }

    /**
     * Gets the value of the typFO property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypFO() {
        return typFO;
    }

    /**
     * Sets the value of the typFO property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypFO(String value) {
        this.typFO = value;
    }

}
