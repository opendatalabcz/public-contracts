
package ares;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Informace o chyb vetn krtk npovdy
 * 
 * <p>Java class for chyba complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="chyba">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Kod" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Popis" type="{http://wwwinfo.mfcr.cz/ares/xml_doc/schemas/uvis_datatypes/v_1.0.1}popis"/>
 *         &lt;element name="Napoveda" type="{http://wwwinfo.mfcr.cz/ares/xml_doc/schemas/uvis_datatypes/v_1.0.1}popis"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "chyba", namespace = "http://wwwinfo.mfcr.cz/ares/xml_doc/schemas/ares/ares_datatypes/v_1.0.2", propOrder = {
    "kod",
    "popis",
    "napoveda"
})
public class Chyba {

    @XmlElement(name = "Kod", namespace = "http://wwwinfo.mfcr.cz/ares/xml_doc/schemas/ares/ares_datatypes/v_1.0.2", required = true)
    protected String kod;
    @XmlElement(name = "Popis", namespace = "http://wwwinfo.mfcr.cz/ares/xml_doc/schemas/ares/ares_datatypes/v_1.0.2", required = true)
    protected String popis;
    @XmlElement(name = "Napoveda", namespace = "http://wwwinfo.mfcr.cz/ares/xml_doc/schemas/ares/ares_datatypes/v_1.0.2", required = true)
    protected String napoveda;

    /**
     * Gets the value of the kod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKod() {
        return kod;
    }

    /**
     * Sets the value of the kod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKod(String value) {
        this.kod = value;
    }

    /**
     * Gets the value of the popis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPopis() {
        return popis;
    }

    /**
     * Sets the value of the popis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPopis(String value) {
        this.popis = value;
    }

    /**
     * Gets the value of the napoveda property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNapoveda() {
        return napoveda;
    }

    /**
     * Sets the value of the napoveda property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNapoveda(String value) {
        this.napoveda = value;
    }

}
