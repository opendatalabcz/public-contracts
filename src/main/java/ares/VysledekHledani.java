
package ares;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Definice elementu vsledku  hledn IO pro seznam registr a vpis z OR
 * 
 * <p>Java class for vysledek_hledani complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="vysledek_hledani">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Kod" type="{http://wwwinfo.mfcr.cz/ares/xml_doc/schemas/ares/ares_datatypes/v_1.0.2}kod_vyhledani"/>
 *         &lt;element name="Datum_vzniku" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="Text" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "vysledek_hledani", namespace = "http://wwwinfo.mfcr.cz/ares/xml_doc/schemas/ares/ares_datatypes/v_1.0.2", propOrder = {
    "kod",
    "datumVzniku",
    "text"
})
public class VysledekHledani {

    @XmlElement(name = "Kod", namespace = "http://wwwinfo.mfcr.cz/ares/xml_doc/schemas/ares/ares_datatypes/v_1.0.2")
    protected int kod;
    @XmlElement(name = "Datum_vzniku", namespace = "http://wwwinfo.mfcr.cz/ares/xml_doc/schemas/ares/ares_datatypes/v_1.0.2")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumVzniku;
    @XmlElement(name = "Text", namespace = "http://wwwinfo.mfcr.cz/ares/xml_doc/schemas/ares/ares_datatypes/v_1.0.2")
    protected String text;

    /**
     * Gets the value of the kod property.
     * 
     */
    public int getKod() {
        return kod;
    }

    /**
     * Sets the value of the kod property.
     * 
     */
    public void setKod(int value) {
        this.kod = value;
    }

    /**
     * Gets the value of the datumVzniku property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumVzniku() {
        return datumVzniku;
    }

    /**
     * Sets the value of the datumVzniku property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumVzniku(XMLGregorianCalendar value) {
        this.datumVzniku = value;
    }

    /**
     * Gets the value of the text property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the value of the text property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setText(String value) {
        this.text = value;
    }

}
