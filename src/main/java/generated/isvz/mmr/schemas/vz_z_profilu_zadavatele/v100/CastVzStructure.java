//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.03.23 at 01:46:39 AM CET 
//


package generated.isvz.mmr.schemas.vz_z_profilu_zadavatele.v100;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import generated.isvs.micr.schemas.corecomponenttypes.v1.CenaType;
import generated.isvs.micr.schemas.corecomponenttypes.v1.DatumCasType;
import generated.isvs.micr.schemas.corecomponenttypes.v1.KodType;
import org.w3c.dom.Element;


/**
 * <p>Java class for CastVzStructure complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="CastVzStructure">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cislo_casti" type="{urn:cz:isvs:micr:schemas:CoreComponentTypes:v1}KodType" minOccurs="0"/>
 *         &lt;element name="nazev_casti_vz" type="{urn:cz:isvz:mmr:schemas:vz-z-profilu-zadavatele:v100}NazevType" minOccurs="0"/>
 *         &lt;element name="popis_predmetu_vz" type="{urn:cz:isvz:mmr:schemas:vz-z-profilu-zadavatele:v100}PoznamkaType" minOccurs="0"/>
 *         &lt;element name="stav" type="{urn:cz:isvz:mmr:schemas:vz-z-profilu-zadavatele:v100}StavCastiVzType" minOccurs="0"/>
 *         &lt;element name="hlavni_cpv" type="{urn:cz:isvz:mmr:schemas:vz-z-profilu-zadavatele:v100}PredmetKodType" minOccurs="0"/>
 *         &lt;element name="vedlejsi_cpv" type="{urn:cz:isvz:mmr:schemas:vz-z-profilu-zadavatele:v100}PredmetKodType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="predpokladana_hodnota" type="{urn:cz:isvs:micr:schemas:CoreComponentTypes:v1}CenaType" minOccurs="0"/>
 *         &lt;element name="celkova_konecna_hodnota_v_kc_bez_dph" type="{urn:cz:isvs:micr:schemas:CoreComponentTypes:v1}CenaType" minOccurs="0"/>
 *         &lt;element name="nuts_hlavniho_mista_plneni" type="{urn:cz:isvz:mmr:schemas:vz-z-profilu-zadavatele:v100}KrajNutsKodType" minOccurs="0"/>
 *         &lt;element name="datum_proplaceni" type="{urn:cz:isvs:micr:schemas:CoreComponentTypes:v1}DatumCasType" minOccurs="0"/>
 *         &lt;element name="ocekavane_zahajeni_plneni" type="{urn:cz:isvs:micr:schemas:CoreComponentTypes:v1}DatumCasType" minOccurs="0"/>
 *         &lt;element name="ocekavany_konec_plneni" type="{urn:cz:isvs:micr:schemas:CoreComponentTypes:v1}DatumCasType" minOccurs="0"/>
 *         &lt;element name="datum_zruseni" type="{urn:cz:isvs:micr:schemas:CoreComponentTypes:v1}DatumCasType" minOccurs="0"/>
 *         &lt;element name="duvod_zruseni" type="{urn:cz:isvz:mmr:schemas:vz-z-profilu-zadavatele:v100}PoznamkaType" minOccurs="0"/>
 *         &lt;element name="zpusob_hodnoceni" type="{urn:cz:isvz:mmr:schemas:vz-z-profilu-zadavatele:v100}ZpusobHodnoceniType" minOccurs="0"/>
 *         &lt;element name="zpusob_hodnoceni_textem" type="{urn:cz:isvz:mmr:schemas:vz-z-profilu-zadavatele:v100}NazevType" minOccurs="0"/>
 *         &lt;any processContents='skip' namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CastVzStructure", propOrder = {
        "cisloCasti",
        "nazevCastiVz",
        "popisPredmetuVz",
        "stav",
        "hlavniCpv",
        "vedlejsiCpv",
        "predpokladanaHodnota",
        "celkovaKonecnaHodnotaVKcBezDph",
        "nutsHlavnihoMistaPlneni",
        "datumProplaceni",
        "ocekavaneZahajeniPlneni",
        "ocekavanyKonecPlneni",
        "datumZruseni",
        "duvodZruseni",
        "zpusobHodnoceni",
        "zpusobHodnoceniTextem",
        "any"
})
public class CastVzStructure {

    @XmlElement(name = "cislo_casti")
    protected KodType cisloCasti;
    @XmlElement(name = "nazev_casti_vz")
    protected NazevType nazevCastiVz;
    @XmlElement(name = "popis_predmetu_vz")
    protected PoznamkaType popisPredmetuVz;
    protected StavCastiVzType stav;
    @XmlElement(name = "hlavni_cpv")
    protected PredmetKodType hlavniCpv;
    @XmlElement(name = "vedlejsi_cpv")
    protected List<PredmetKodType> vedlejsiCpv;
    @XmlElement(name = "predpokladana_hodnota")
    protected CenaType predpokladanaHodnota;
    @XmlElement(name = "celkova_konecna_hodnota_v_kc_bez_dph")
    protected CenaType celkovaKonecnaHodnotaVKcBezDph;
    @XmlElement(name = "nuts_hlavniho_mista_plneni")
    protected KrajNutsKodType nutsHlavnihoMistaPlneni;
    @XmlElement(name = "datum_proplaceni")
    protected DatumCasType datumProplaceni;
    @XmlElement(name = "ocekavane_zahajeni_plneni")
    protected DatumCasType ocekavaneZahajeniPlneni;
    @XmlElement(name = "ocekavany_konec_plneni")
    protected DatumCasType ocekavanyKonecPlneni;
    @XmlElement(name = "datum_zruseni")
    protected DatumCasType datumZruseni;
    @XmlElement(name = "duvod_zruseni")
    protected PoznamkaType duvodZruseni;
    @XmlElement(name = "zpusob_hodnoceni")
    protected ZpusobHodnoceniType zpusobHodnoceni;
    @XmlElement(name = "zpusob_hodnoceni_textem")
    protected NazevType zpusobHodnoceniTextem;
    @XmlAnyElement
    protected List<Element> any;

    /**
     * Gets the value of the cisloCasti property.
     *
     * @return possible object is
     * {@link KodType }
     */
    public KodType getCisloCasti() {
        return cisloCasti;
    }

    /**
     * Sets the value of the cisloCasti property.
     *
     * @param value allowed object is
     *              {@link KodType }
     */
    public void setCisloCasti(KodType value) {
        this.cisloCasti = value;
    }

    /**
     * Gets the value of the nazevCastiVz property.
     *
     * @return possible object is
     * {@link NazevType }
     */
    public NazevType getNazevCastiVz() {
        return nazevCastiVz;
    }

    /**
     * Sets the value of the nazevCastiVz property.
     *
     * @param value allowed object is
     *              {@link NazevType }
     */
    public void setNazevCastiVz(NazevType value) {
        this.nazevCastiVz = value;
    }

    /**
     * Gets the value of the popisPredmetuVz property.
     *
     * @return possible object is
     * {@link PoznamkaType }
     */
    public PoznamkaType getPopisPredmetuVz() {
        return popisPredmetuVz;
    }

    /**
     * Sets the value of the popisPredmetuVz property.
     *
     * @param value allowed object is
     *              {@link PoznamkaType }
     */
    public void setPopisPredmetuVz(PoznamkaType value) {
        this.popisPredmetuVz = value;
    }

    /**
     * Gets the value of the stav property.
     *
     * @return possible object is
     * {@link StavCastiVzType }
     */
    public StavCastiVzType getStav() {
        return stav;
    }

    /**
     * Sets the value of the stav property.
     *
     * @param value allowed object is
     *              {@link StavCastiVzType }
     */
    public void setStav(StavCastiVzType value) {
        this.stav = value;
    }

    /**
     * Gets the value of the hlavniCpv property.
     *
     * @return possible object is
     * {@link PredmetKodType }
     */
    public PredmetKodType getHlavniCpv() {
        return hlavniCpv;
    }

    /**
     * Sets the value of the hlavniCpv property.
     *
     * @param value allowed object is
     *              {@link PredmetKodType }
     */
    public void setHlavniCpv(PredmetKodType value) {
        this.hlavniCpv = value;
    }

    /**
     * Gets the value of the vedlejsiCpv property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the vedlejsiCpv property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVedlejsiCpv().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link PredmetKodType }
     */
    public List<PredmetKodType> getVedlejsiCpv() {
        if (vedlejsiCpv == null) {
            vedlejsiCpv = new ArrayList<PredmetKodType>();
        }
        return this.vedlejsiCpv;
    }

    /**
     * Gets the value of the predpokladanaHodnota property.
     *
     * @return possible object is
     * {@link CenaType }
     */
    public CenaType getPredpokladanaHodnota() {
        return predpokladanaHodnota;
    }

    /**
     * Sets the value of the predpokladanaHodnota property.
     *
     * @param value allowed object is
     *              {@link CenaType }
     */
    public void setPredpokladanaHodnota(CenaType value) {
        this.predpokladanaHodnota = value;
    }

    /**
     * Gets the value of the celkovaKonecnaHodnotaVKcBezDph property.
     *
     * @return possible object is
     * {@link CenaType }
     */
    public CenaType getCelkovaKonecnaHodnotaVKcBezDph() {
        return celkovaKonecnaHodnotaVKcBezDph;
    }

    /**
     * Sets the value of the celkovaKonecnaHodnotaVKcBezDph property.
     *
     * @param value allowed object is
     *              {@link CenaType }
     */
    public void setCelkovaKonecnaHodnotaVKcBezDph(CenaType value) {
        this.celkovaKonecnaHodnotaVKcBezDph = value;
    }

    /**
     * Gets the value of the nutsHlavnihoMistaPlneni property.
     *
     * @return possible object is
     * {@link KrajNutsKodType }
     */
    public KrajNutsKodType getNutsHlavnihoMistaPlneni() {
        return nutsHlavnihoMistaPlneni;
    }

    /**
     * Sets the value of the nutsHlavnihoMistaPlneni property.
     *
     * @param value allowed object is
     *              {@link KrajNutsKodType }
     */
    public void setNutsHlavnihoMistaPlneni(KrajNutsKodType value) {
        this.nutsHlavnihoMistaPlneni = value;
    }

    /**
     * Gets the value of the datumProplaceni property.
     *
     * @return possible object is
     * {@link DatumCasType }
     */
    public DatumCasType getDatumProplaceni() {
        return datumProplaceni;
    }

    /**
     * Sets the value of the datumProplaceni property.
     *
     * @param value allowed object is
     *              {@link DatumCasType }
     */
    public void setDatumProplaceni(DatumCasType value) {
        this.datumProplaceni = value;
    }

    /**
     * Gets the value of the ocekavaneZahajeniPlneni property.
     *
     * @return possible object is
     * {@link DatumCasType }
     */
    public DatumCasType getOcekavaneZahajeniPlneni() {
        return ocekavaneZahajeniPlneni;
    }

    /**
     * Sets the value of the ocekavaneZahajeniPlneni property.
     *
     * @param value allowed object is
     *              {@link DatumCasType }
     */
    public void setOcekavaneZahajeniPlneni(DatumCasType value) {
        this.ocekavaneZahajeniPlneni = value;
    }

    /**
     * Gets the value of the ocekavanyKonecPlneni property.
     *
     * @return possible object is
     * {@link DatumCasType }
     */
    public DatumCasType getOcekavanyKonecPlneni() {
        return ocekavanyKonecPlneni;
    }

    /**
     * Sets the value of the ocekavanyKonecPlneni property.
     *
     * @param value allowed object is
     *              {@link DatumCasType }
     */
    public void setOcekavanyKonecPlneni(DatumCasType value) {
        this.ocekavanyKonecPlneni = value;
    }

    /**
     * Gets the value of the datumZruseni property.
     *
     * @return possible object is
     * {@link DatumCasType }
     */
    public DatumCasType getDatumZruseni() {
        return datumZruseni;
    }

    /**
     * Sets the value of the datumZruseni property.
     *
     * @param value allowed object is
     *              {@link DatumCasType }
     */
    public void setDatumZruseni(DatumCasType value) {
        this.datumZruseni = value;
    }

    /**
     * Gets the value of the duvodZruseni property.
     *
     * @return possible object is
     * {@link PoznamkaType }
     */
    public PoznamkaType getDuvodZruseni() {
        return duvodZruseni;
    }

    /**
     * Sets the value of the duvodZruseni property.
     *
     * @param value allowed object is
     *              {@link PoznamkaType }
     */
    public void setDuvodZruseni(PoznamkaType value) {
        this.duvodZruseni = value;
    }

    /**
     * Gets the value of the zpusobHodnoceni property.
     *
     * @return possible object is
     * {@link ZpusobHodnoceniType }
     */
    public ZpusobHodnoceniType getZpusobHodnoceni() {
        return zpusobHodnoceni;
    }

    /**
     * Sets the value of the zpusobHodnoceni property.
     *
     * @param value allowed object is
     *              {@link ZpusobHodnoceniType }
     */
    public void setZpusobHodnoceni(ZpusobHodnoceniType value) {
        this.zpusobHodnoceni = value;
    }

    /**
     * Gets the value of the zpusobHodnoceniTextem property.
     *
     * @return possible object is
     * {@link NazevType }
     */
    public NazevType getZpusobHodnoceniTextem() {
        return zpusobHodnoceniTextem;
    }

    /**
     * Sets the value of the zpusobHodnoceniTextem property.
     *
     * @param value allowed object is
     *              {@link NazevType }
     */
    public void setZpusobHodnoceniTextem(NazevType value) {
        this.zpusobHodnoceniTextem = value;
    }

    /**
     * Gets the value of the any property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the any property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAny().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link Element }
     */
    public List<Element> getAny() {
        if (any == null) {
            any = new ArrayList<Element>();
        }
        return this.any;
    }

}
