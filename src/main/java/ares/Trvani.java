
package ares;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Datumy zatku a konce lenstv v orgnu pro leny dozorch rad a statutrnch orgn
 * 
 * <p>Java class for trvani complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="trvani">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Datum_zacatku" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="Datum_konce" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "trvani", namespace = "http://wwwinfo.mfcr.cz/ares/xml_doc/schemas/ares/ares_datatypes/v_1.0.2", propOrder = {
    "datumZacatku",
    "datumKonce"
})
public class Trvani {

    @XmlElement(name = "Datum_zacatku", namespace = "http://wwwinfo.mfcr.cz/ares/xml_doc/schemas/ares/ares_datatypes/v_1.0.2")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumZacatku;
    @XmlElement(name = "Datum_konce", namespace = "http://wwwinfo.mfcr.cz/ares/xml_doc/schemas/ares/ares_datatypes/v_1.0.2")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumKonce;

    /**
     * Gets the value of the datumZacatku property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumZacatku() {
        return datumZacatku;
    }

    /**
     * Sets the value of the datumZacatku property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumZacatku(XMLGregorianCalendar value) {
        this.datumZacatku = value;
    }

    /**
     * Gets the value of the datumKonce property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumKonce() {
        return datumKonce;
    }

    /**
     * Sets the value of the datumKonce property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumKonce(XMLGregorianCalendar value) {
        this.datumKonce = value;
    }

}
