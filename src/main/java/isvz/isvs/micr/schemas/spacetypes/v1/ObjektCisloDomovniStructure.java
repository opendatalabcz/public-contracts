//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.03.23 at 01:46:39 AM CET 
//


package isvz.isvs.micr.schemas.spacetypes.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * C�slo domovn� je ��seln� prostorov� identifik�tor prvku prostorov� identifikace �stavebn� objekt�. C�slo domovn� je datov� prvek, kter� je slo�en z ��seln�ho ozna�en� druhu ��sla domovn�ho a ��sla popisn�ho nebo eviden�n�ho.
 * C�slo domovn� je jedine�n� pouze v r�mci ��sti obce.
 * 
 * <p>Java class for ObjektCisloDomovniStructure complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ObjektCisloDomovniStructure">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DruhKod" type="{urn:cz:isvs:micr:schemas:SpaceTypes:v1}CisloDomovniDruhKodType"/>
 *         &lt;element name="Hodnota" type="{urn:cz:isvs:micr:schemas:SpaceTypes:v1}CisloDomovniHodnotaType"/>
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
@XmlType(name = "ObjektCisloDomovniStructure", propOrder = {
    "druhKod",
    "hodnota"
})
public class ObjektCisloDomovniStructure {

    @XmlElement(name = "DruhKod", required = true)
    protected CisloDomovniDruhKodType druhKod;
    @XmlElement(name = "Hodnota", required = true)
    protected CisloDomovniHodnotaType hodnota;
    @XmlAttribute(name = "objektID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String objektID;

    /**
     * Gets the value of the druhKod property.
     * 
     * @return
     *     possible object is
     *     {@link CisloDomovniDruhKodType }
     *     
     */
    public CisloDomovniDruhKodType getDruhKod() {
        return druhKod;
    }

    /**
     * Sets the value of the druhKod property.
     * 
     * @param value
     *     allowed object is
     *     {@link CisloDomovniDruhKodType }
     *     
     */
    public void setDruhKod(CisloDomovniDruhKodType value) {
        this.druhKod = value;
    }

    /**
     * Gets the value of the hodnota property.
     * 
     * @return
     *     possible object is
     *     {@link CisloDomovniHodnotaType }
     *     
     */
    public CisloDomovniHodnotaType getHodnota() {
        return hodnota;
    }

    /**
     * Sets the value of the hodnota property.
     * 
     * @param value
     *     allowed object is
     *     {@link CisloDomovniHodnotaType }
     *     
     */
    public void setHodnota(CisloDomovniHodnotaType value) {
        this.hodnota = value;
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
