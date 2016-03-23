//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.03.23 at 01:46:39 AM CET 
//


package isvz.isvs.micr.schemas.dataelementstypes.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import isvz.isvs.micr.schemas.commontypes.v1.RozsireniStructure;


/**
 * Deklarace jmenn�ho prostoru XML. Obsahuje jmenn� prostor a prefix jmenn�ho prostoru.
 * 
 * <p>Java class for JmennyProstorStructure complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="JmennyProstorStructure">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="URI" type="{urn:cz:isvs:micr:schemas:DataElementsTypes:v1}DatovySlovnikJmennyProstorURIType"/>
 *         &lt;element name="Prefix" type="{urn:cz:isvs:micr:schemas:DataElementsTypes:v1}DatovySlovnikJmennyProstorPrefixType"/>
 *         &lt;element ref="{urn:cz:isvs:micr:schemas:CommonTypes:v1}Rozsireni" minOccurs="0"/>
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
@XmlType(name = "JmennyProstorStructure", propOrder = {
    "uri",
    "prefix",
    "rozsireni"
})
public class JmennyProstorStructure {

    @XmlElement(name = "URI", required = true)
    protected DatovySlovnikJmennyProstorURIType uri;
    @XmlElement(name = "Prefix", required = true)
    protected DatovySlovnikJmennyProstorPrefixType prefix;
    @XmlElement(name = "Rozsireni", namespace = "urn:cz:isvs:micr:schemas:CommonTypes:v1")
    protected RozsireniStructure rozsireni;
    @XmlAttribute(name = "objektID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String objektID;

    /**
     * Gets the value of the uri property.
     * 
     * @return
     *     possible object is
     *     {@link DatovySlovnikJmennyProstorURIType }
     *     
     */
    public DatovySlovnikJmennyProstorURIType getURI() {
        return uri;
    }

    /**
     * Sets the value of the uri property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatovySlovnikJmennyProstorURIType }
     *     
     */
    public void setURI(DatovySlovnikJmennyProstorURIType value) {
        this.uri = value;
    }

    /**
     * Gets the value of the prefix property.
     * 
     * @return
     *     possible object is
     *     {@link DatovySlovnikJmennyProstorPrefixType }
     *     
     */
    public DatovySlovnikJmennyProstorPrefixType getPrefix() {
        return prefix;
    }

    /**
     * Sets the value of the prefix property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatovySlovnikJmennyProstorPrefixType }
     *     
     */
    public void setPrefix(DatovySlovnikJmennyProstorPrefixType value) {
        this.prefix = value;
    }

    /**
     * Gets the value of the rozsireni property.
     * 
     * @return
     *     possible object is
     *     {@link RozsireniStructure }
     *     
     */
    public RozsireniStructure getRozsireni() {
        return rozsireni;
    }

    /**
     * Sets the value of the rozsireni property.
     * 
     * @param value
     *     allowed object is
     *     {@link RozsireniStructure }
     *     
     */
    public void setRozsireni(RozsireniStructure value) {
        this.rozsireni = value;
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
