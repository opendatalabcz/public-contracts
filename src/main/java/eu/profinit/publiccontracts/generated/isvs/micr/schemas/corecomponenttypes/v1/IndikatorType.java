//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.19 at 01:50:51 AM CEST 
//


package eu.profinit.publiccontracts.generated.isvs.micr.schemas.corecomponenttypes.v1;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.businesstypes.v2.OrganizaceMalyStredniPodnikIndikatorType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.dataelementstypes.v1.DatovyPrvekCiselnikIndikatorType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.dataelementstypes.v1.DatovyPrvekInstanceIndikatorType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.dataelementstypes.v1.DatovyPrvekJednoduchyTypIndikatorType;


/**
 * Seznam dvou a pouze dvou hodnot, indikuj�c�ch stavy jako zapnuto/vypnuto, pravda/nepravda apod. Je synonymem pro datov� typ �Boolean�; voliteln� spolu s dopl�kovou informac� o form�tu �daje
 * 
 * <p>Java class for IndikatorType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IndikatorType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>token">
 *       &lt;attribute name="indikatorFormat" type="{http://www.w3.org/2001/XMLSchema}token" />
 *       &lt;attribute name="objektID" type="{http://www.w3.org/2001/XMLSchema}token" />
 *       &lt;anyAttribute/>
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IndikatorType", propOrder = {
    "value"
})
@XmlSeeAlso({
    OrganizaceMalyStredniPodnikIndikatorType.class,
    DatovyPrvekCiselnikIndikatorType.class,
    DatovyPrvekInstanceIndikatorType.class,
    DatovyPrvekJednoduchyTypIndikatorType.class
})
public class IndikatorType {

    @XmlValue
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String value;
    @XmlAttribute(name = "indikatorFormat")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String indikatorFormat;
    @XmlAttribute(name = "objektID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String objektID;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the value property.
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

    /**
     * Gets the value of the indikatorFormat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndikatorFormat() {
        return indikatorFormat;
    }

    /**
     * Sets the value of the indikatorFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndikatorFormat(String value) {
        this.indikatorFormat = value;
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

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * 
     * <p>
     * the map is keyed by the name of the attribute and 
     * the value is the string value of the attribute.
     * 
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     * 
     * 
     * @return
     *     always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

}
