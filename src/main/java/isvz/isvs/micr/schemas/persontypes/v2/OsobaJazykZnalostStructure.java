//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.03.23 at 01:46:39 AM CET 
//


package isvz.isvs.micr.schemas.persontypes.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Slo�en� datov� prvek o znalosti jazyka a o osv�d�en� t�to znalosti.
 * 
 * 
 * <p>Java class for OsobaJazykZnalostStructure complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OsobaJazykZnalostStructure">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{urn:cz:isvs:micr:schemas:PersonTypes:v2}Jazyk"/>
 *         &lt;element name="ZnalostNazev" type="{urn:cz:isvs:micr:schemas:PersonTypes:v2}OsobaJazykZnalostNazevType"/>
 *         &lt;element name="ZnalostOsvedceni" type="{urn:cz:isvs:micr:schemas:PersonTypes:v2}OsobaJazykZnalostOsvedceniType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="objektID" type="{http://www.w3.org/2001/XMLSchema}token" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OsobaJazykZnalostStructure", propOrder = {
    "jazyk",
    "znalostNazev",
    "znalostOsvedceni"
})
public class OsobaJazykZnalostStructure {

    @XmlElement(name = "Jazyk", required = true)
    protected JazykType jazyk;
    @XmlElement(name = "ZnalostNazev", required = true)
    protected OsobaJazykZnalostNazevType znalostNazev;
    @XmlElement(name = "ZnalostOsvedceni", required = true)
    protected OsobaJazykZnalostOsvedceniType znalostOsvedceni;
    @XmlAttribute(name = "objektID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String objektID;

    /**
     * Gets the value of the jazyk property.
     * 
     * @return
     *     possible object is
     *     {@link JazykType }
     *     
     */
    public JazykType getJazyk() {
        return jazyk;
    }

    /**
     * Sets the value of the jazyk property.
     * 
     * @param value
     *     allowed object is
     *     {@link JazykType }
     *     
     */
    public void setJazyk(JazykType value) {
        this.jazyk = value;
    }

    /**
     * Gets the value of the znalostNazev property.
     * 
     * @return
     *     possible object is
     *     {@link OsobaJazykZnalostNazevType }
     *     
     */
    public OsobaJazykZnalostNazevType getZnalostNazev() {
        return znalostNazev;
    }

    /**
     * Sets the value of the znalostNazev property.
     * 
     * @param value
     *     allowed object is
     *     {@link OsobaJazykZnalostNazevType }
     *     
     */
    public void setZnalostNazev(OsobaJazykZnalostNazevType value) {
        this.znalostNazev = value;
    }

    /**
     * Gets the value of the znalostOsvedceni property.
     * 
     * @return
     *     possible object is
     *     {@link OsobaJazykZnalostOsvedceniType }
     *     
     */
    public OsobaJazykZnalostOsvedceniType getZnalostOsvedceni() {
        return znalostOsvedceni;
    }

    /**
     * Sets the value of the znalostOsvedceni property.
     * 
     * @param value
     *     allowed object is
     *     {@link OsobaJazykZnalostOsvedceniType }
     *     
     */
    public void setZnalostOsvedceni(OsobaJazykZnalostOsvedceniType value) {
        this.znalostOsvedceni = value;
    }

    /**
     * Gets the value of the objektID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjektID() {
        return objektID;
    }

    /**
     * Sets the value of the objektID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjektID(String value) {
        this.objektID = value;
    }

}
