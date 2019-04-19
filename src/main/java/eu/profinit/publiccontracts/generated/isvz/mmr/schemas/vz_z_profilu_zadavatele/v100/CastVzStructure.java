//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.19 at 01:50:51 AM CEST 
//


package eu.profinit.publiccontracts.generated.isvz.mmr.schemas.vz_z_profilu_zadavatele.v100;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.corecomponenttypes.v1.DatumCasType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.corecomponenttypes.v1.KodType;


/**
 * <p>Java class for CastVzStructure complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CastVzStructure">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cislo_casti" type="{urn:cz:isvs:micr:schemas:CoreComponentTypes:v1}KodType"/>
 *         &lt;element name="nazev_casti_vz" type="{urn:cz:isvz:mmr:schemas:vz-z-profilu-zadavatele:v100}NazevType"/>
 *         &lt;element name="stav" type="{urn:cz:isvz:mmr:schemas:vz-z-profilu-zadavatele:v100}StavCastiVzType"/>
 *         &lt;element name="ucastnik" type="{urn:cz:isvz:mmr:schemas:vz-z-profilu-zadavatele:v100}UcastnikZRStructure" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="dodavatel" type="{urn:cz:isvz:mmr:schemas:vz-z-profilu-zadavatele:v100}DodavatelStructure" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="datum_uzavreni_smlouvy" type="{urn:cz:isvs:micr:schemas:CoreComponentTypes:v1}DatumCasType" minOccurs="0"/>
 *         &lt;element name="datum_zruseni" type="{urn:cz:isvs:micr:schemas:CoreComponentTypes:v1}DatumCasType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CastVzStructure", propOrder = {
    "cisloCasti",
    "nazevCastiVz",
    "stav",
    "ucastnik",
    "dodavatel",
    "datumUzavreniSmlouvy",
    "datumZruseni"
})
public class CastVzStructure {

    @XmlElement(name = "cislo_casti", required = true)
    protected KodType cisloCasti;
    @XmlElement(name = "nazev_casti_vz", required = true)
    protected NazevType nazevCastiVz;
    @XmlElement(required = true)
    protected StavCastiVzType stav;
    protected List<UcastnikZRStructure> ucastnik;
    protected List<DodavatelStructure> dodavatel;
    @XmlElement(name = "datum_uzavreni_smlouvy")
    protected DatumCasType datumUzavreniSmlouvy;
    @XmlElement(name = "datum_zruseni")
    protected DatumCasType datumZruseni;

    /**
     * Gets the value of the cisloCasti property.
     * 
     * @return
     *     possible object is
     *     {@link KodType }
     *     
     */
    public KodType getCisloCasti() {
        return cisloCasti;
    }

    /**
     * Sets the value of the cisloCasti property.
     * 
     * @param value
     *     allowed object is
     *     {@link KodType }
     *     
     */
    public void setCisloCasti(KodType value) {
        this.cisloCasti = value;
    }

    /**
     * Gets the value of the nazevCastiVz property.
     * 
     * @return
     *     possible object is
     *     {@link NazevType }
     *     
     */
    public NazevType getNazevCastiVz() {
        return nazevCastiVz;
    }

    /**
     * Sets the value of the nazevCastiVz property.
     * 
     * @param value
     *     allowed object is
     *     {@link NazevType }
     *     
     */
    public void setNazevCastiVz(NazevType value) {
        this.nazevCastiVz = value;
    }

    /**
     * Gets the value of the stav property.
     * 
     * @return
     *     possible object is
     *     {@link StavCastiVzType }
     *     
     */
    public StavCastiVzType getStav() {
        return stav;
    }

    /**
     * Sets the value of the stav property.
     * 
     * @param value
     *     allowed object is
     *     {@link StavCastiVzType }
     *     
     */
    public void setStav(StavCastiVzType value) {
        this.stav = value;
    }

    /**
     * Gets the value of the ucastnik property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ucastnik property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUcastnik().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UcastnikZRStructure }
     * 
     * 
     */
    public List<UcastnikZRStructure> getUcastnik() {
        if (ucastnik == null) {
            ucastnik = new ArrayList<UcastnikZRStructure>();
        }
        return this.ucastnik;
    }

    /**
     * Gets the value of the dodavatel property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dodavatel property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDodavatel().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DodavatelStructure }
     * 
     * 
     */
    public List<DodavatelStructure> getDodavatel() {
        if (dodavatel == null) {
            dodavatel = new ArrayList<DodavatelStructure>();
        }
        return this.dodavatel;
    }

    /**
     * Gets the value of the datumUzavreniSmlouvy property.
     * 
     * @return
     *     possible object is
     *     {@link DatumCasType }
     *     
     */
    public DatumCasType getDatumUzavreniSmlouvy() {
        return datumUzavreniSmlouvy;
    }

    /**
     * Sets the value of the datumUzavreniSmlouvy property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatumCasType }
     *     
     */
    public void setDatumUzavreniSmlouvy(DatumCasType value) {
        this.datumUzavreniSmlouvy = value;
    }

    /**
     * Gets the value of the datumZruseni property.
     * 
     * @return
     *     possible object is
     *     {@link DatumCasType }
     *     
     */
    public DatumCasType getDatumZruseni() {
        return datumZruseni;
    }

    /**
     * Sets the value of the datumZruseni property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatumCasType }
     *     
     */
    public void setDatumZruseni(DatumCasType value) {
        this.datumZruseni = value;
    }

}