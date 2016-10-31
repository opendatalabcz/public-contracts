
package ares;

import javax.xml.bind.annotation.*;


/**
 * Zkladn daje o konkurzu z Centrln evidence padc  (tab. k_konkurz a dle)
 * 
 * <p>Java class for k_konkurz complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="k_konkurz">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="K_klic" type="{http://wwwinfo.mfcr.cz/ares/xml_doc/schemas/ares/ares_datatypes/v_1.0.2}k_klic"/>
 *         &lt;element name="K_upadce" type="{http://wwwinfo.mfcr.cz/ares/xml_doc/schemas/ares/ares_datatypes/v_1.0.2}k_upadce"/>
 *         &lt;element name="Poradi" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="Stav_konkurzu" type="{http://wwwinfo.mfcr.cz/ares/xml_doc/schemas/ares/ares_datatypes/v_1.0.2}stav_konkurzu_CEU"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "k_konkurz", namespace = "http://wwwinfo.mfcr.cz/ares/xml_doc/schemas/ares/ares_datatypes/v_1.0.2", propOrder = {
    "kKlic",
    "kUpadce",
    "poradi",
    "stavKonkurzu"
})
public class KKonkurz {

    @XmlElement(name = "K_klic", namespace = "http://wwwinfo.mfcr.cz/ares/xml_doc/schemas/ares/ares_datatypes/v_1.0.2", required = true)
    protected KKlic kKlic;
    @XmlElement(name = "K_upadce", namespace = "http://wwwinfo.mfcr.cz/ares/xml_doc/schemas/ares/ares_datatypes/v_1.0.2", required = true)
    protected KUpadce kUpadce;
    @XmlElement(name = "Poradi", namespace = "http://wwwinfo.mfcr.cz/ares/xml_doc/schemas/ares/ares_datatypes/v_1.0.2")
    protected short poradi;
    @XmlElement(name = "Stav_konkurzu", namespace = "http://wwwinfo.mfcr.cz/ares/xml_doc/schemas/ares/ares_datatypes/v_1.0.2", required = true)
    @XmlSchemaType(name = "string")
    protected StavKonkurzuCEU stavKonkurzu;

    /**
     * Gets the value of the kKlic property.
     * 
     * @return
     *     possible object is
     *     {@link KKlic }
     *     
     */
    public KKlic getKKlic() {
        return kKlic;
    }

    /**
     * Sets the value of the kKlic property.
     * 
     * @param value
     *     allowed object is
     *     {@link KKlic }
     *     
     */
    public void setKKlic(KKlic value) {
        this.kKlic = value;
    }

    /**
     * Gets the value of the kUpadce property.
     * 
     * @return
     *     possible object is
     *     {@link KUpadce }
     *     
     */
    public KUpadce getKUpadce() {
        return kUpadce;
    }

    /**
     * Sets the value of the kUpadce property.
     * 
     * @param value
     *     allowed object is
     *     {@link KUpadce }
     *     
     */
    public void setKUpadce(KUpadce value) {
        this.kUpadce = value;
    }

    /**
     * Gets the value of the poradi property.
     * 
     */
    public short getPoradi() {
        return poradi;
    }

    /**
     * Sets the value of the poradi property.
     * 
     */
    public void setPoradi(short value) {
        this.poradi = value;
    }

    /**
     * Gets the value of the stavKonkurzu property.
     * 
     * @return
     *     possible object is
     *     {@link StavKonkurzuCEU }
     *     
     */
    public StavKonkurzuCEU getStavKonkurzu() {
        return stavKonkurzu;
    }

    /**
     * Sets the value of the stavKonkurzu property.
     * 
     * @param value
     *     allowed object is
     *     {@link StavKonkurzuCEU }
     *     
     */
    public void setStavKonkurzu(StavKonkurzuCEU value) {
        this.stavKonkurzu = value;
    }

}
