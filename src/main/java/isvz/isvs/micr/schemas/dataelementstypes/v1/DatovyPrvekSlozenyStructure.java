//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.03.23 at 01:46:39 AM CET 
//


package isvz.isvs.micr.schemas.dataelementstypes.v1;

import java.util.ArrayList;
import java.util.List;
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
 * Struktura, zahrnující popis složeného datového prvku.
 * 
 * <p>Java class for DatovyPrvekSlozenyStructure complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DatovyPrvekSlozenyStructure">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SkupinaKod" type="{urn:cz:isvs:micr:schemas:DataElementsTypes:v1}DatovyPrvekSkupinaKodType"/>
 *         &lt;element name="SkupinaNazev" type="{urn:cz:isvs:micr:schemas:DataElementsTypes:v1}DatovyPrvekSkupinaNazevType" minOccurs="0"/>
 *         &lt;element name="Identifikator" type="{urn:cz:isvs:micr:schemas:DataElementsTypes:v1}DatovyPrvekIdentifikatorType" minOccurs="0"/>
 *         &lt;element name="Nazev" type="{urn:cz:isvs:micr:schemas:DataElementsTypes:v1}DatovyPrvekNazevType"/>
 *         &lt;element name="SynonymaSeznam" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Synonymum" type="{urn:cz:isvs:micr:schemas:DataElementsTypes:v1}DatovyPrvekSynonymumType" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Akronym" type="{urn:cz:isvs:micr:schemas:DataElementsTypes:v1}DatovyPrvekAkronymType" minOccurs="0"/>
 *         &lt;element name="Verze" type="{urn:cz:isvs:micr:schemas:DataElementsTypes:v1}DatovyPrvekVerzeType" minOccurs="0"/>
 *         &lt;element name="Definice" type="{urn:cz:isvs:micr:schemas:DataElementsTypes:v1}DatovyPrvekDefiniceType"/>
 *         &lt;element name="Komentar" type="{urn:cz:isvs:micr:schemas:DataElementsTypes:v1}DatovyPrvekKomentarType" minOccurs="0"/>
 *         &lt;element name="PredpisyPravniSeznam" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="PredpisPravni" type="{urn:cz:isvs:micr:schemas:DataElementsTypes:v1}DatovyPrvekPredpisPravniType" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="PredpisyJineSeznam" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="PredpisJiny" type="{urn:cz:isvs:micr:schemas:DataElementsTypes:v1}DatovyPrvekPredpisJinyType" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ZdrojeHodnotSeznam" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ZdrojHodnot" type="{urn:cz:isvs:micr:schemas:DataElementsTypes:v1}DatovyPrvekZdrojHodnotType" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Spravce" type="{urn:cz:isvs:micr:schemas:DataElementsTypes:v1}DatovyPrvekSpravceType"/>
 *         &lt;element name="VyhlaseniDatum" type="{urn:cz:isvs:micr:schemas:DataElementsTypes:v1}VyhlaseniDatumType" minOccurs="0"/>
 *         &lt;element name="PlatnostOdDatum" type="{urn:cz:isvs:micr:schemas:DataElementsTypes:v1}PlatnostOdDatumType" minOccurs="0"/>
 *         &lt;element name="PlatnostDoDatum" type="{urn:cz:isvs:micr:schemas:DataElementsTypes:v1}PlatnostDoDatumType" minOccurs="0"/>
 *         &lt;element name="VytvorilSeznam" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Vytvoril" type="{urn:cz:isvs:micr:schemas:DataElementsTypes:v1}VytvorilType" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="PrispelSeznam" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Prispel" type="{urn:cz:isvs:micr:schemas:DataElementsTypes:v1}PrispelType" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="JmenoXML" type="{urn:cz:isvs:micr:schemas:DataElementsTypes:v1}DatovyPrvekJmenoXMLType" minOccurs="0"/>
 *         &lt;element name="JmenoXMLAnglicky" type="{urn:cz:isvs:micr:schemas:DataElementsTypes:v1}DatovyPrvekJmenoXMLAnglickyType" minOccurs="0"/>
 *         &lt;element name="TypXML" type="{urn:cz:isvs:micr:schemas:DataElementsTypes:v1}DatovyPrvekTypXMLType" minOccurs="0"/>
 *         &lt;element name="RodicovskyTypXML" type="{urn:cz:isvs:micr:schemas:DataElementsTypes:v1}DatovyPrvekRodicovskyTypXMLType" minOccurs="0"/>
 *         &lt;element name="InstanceIndikator" type="{urn:cz:isvs:micr:schemas:DataElementsTypes:v1}DatovyPrvekInstanceIndikatorType"/>
 *         &lt;element name="KonstruktyDilciSeznam" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="KonstruktDilci" type="{urn:cz:isvs:micr:schemas:DataElementsTypes:v1}DatovyPrvekKonstruktDilciStructure" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Model" type="{urn:cz:isvs:micr:schemas:DataElementsTypes:v1}DatovyPrvekSlozenyModelType" minOccurs="0"/>
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
@XmlType(name = "DatovyPrvekSlozenyStructure", propOrder = {
    "skupinaKod",
    "skupinaNazev",
    "identifikator",
    "nazev",
    "synonymaSeznam",
    "akronym",
    "verze",
    "definice",
    "komentar",
    "predpisyPravniSeznam",
    "predpisyJineSeznam",
    "zdrojeHodnotSeznam",
    "spravce",
    "vyhlaseniDatum",
    "platnostOdDatum",
    "platnostDoDatum",
    "vytvorilSeznam",
    "prispelSeznam",
    "jmenoXML",
    "jmenoXMLAnglicky",
    "typXML",
    "rodicovskyTypXML",
    "instanceIndikator",
    "konstruktyDilciSeznam",
    "model",
    "rozsireni"
})
public class DatovyPrvekSlozenyStructure {

    @XmlElement(name = "SkupinaKod", required = true)
    protected DatovyPrvekSkupinaKodType skupinaKod;
    @XmlElement(name = "SkupinaNazev")
    protected DatovyPrvekSkupinaNazevType skupinaNazev;
    @XmlElement(name = "Identifikator")
    protected DatovyPrvekIdentifikatorType identifikator;
    @XmlElement(name = "Nazev", required = true)
    protected DatovyPrvekNazevType nazev;
    @XmlElement(name = "SynonymaSeznam")
    protected SynonymaSeznam synonymaSeznam;
    @XmlElement(name = "Akronym")
    protected DatovyPrvekAkronymType akronym;
    @XmlElement(name = "Verze")
    protected DatovyPrvekVerzeType verze;
    @XmlElement(name = "Definice", required = true)
    protected DatovyPrvekDefiniceType definice;
    @XmlElement(name = "Komentar")
    protected DatovyPrvekKomentarType komentar;
    @XmlElement(name = "PredpisyPravniSeznam")
    protected PredpisyPravniSeznam predpisyPravniSeznam;
    @XmlElement(name = "PredpisyJineSeznam")
    protected PredpisyJineSeznam predpisyJineSeznam;
    @XmlElement(name = "ZdrojeHodnotSeznam")
    protected ZdrojeHodnotSeznam zdrojeHodnotSeznam;
    @XmlElement(name = "Spravce", required = true)
    protected DatovyPrvekSpravceType spravce;
    @XmlElement(name = "VyhlaseniDatum")
    protected VyhlaseniDatumType vyhlaseniDatum;
    @XmlElement(name = "PlatnostOdDatum")
    protected PlatnostOdDatumType platnostOdDatum;
    @XmlElement(name = "PlatnostDoDatum")
    protected PlatnostDoDatumType platnostDoDatum;
    @XmlElement(name = "VytvorilSeznam")
    protected VytvorilSeznam vytvorilSeznam;
    @XmlElement(name = "PrispelSeznam")
    protected PrispelSeznam prispelSeznam;
    @XmlElement(name = "JmenoXML")
    protected DatovyPrvekJmenoXMLType jmenoXML;
    @XmlElement(name = "JmenoXMLAnglicky")
    protected DatovyPrvekJmenoXMLAnglickyType jmenoXMLAnglicky;
    @XmlElement(name = "TypXML")
    protected DatovyPrvekTypXMLType typXML;
    @XmlElement(name = "RodicovskyTypXML")
    protected DatovyPrvekRodicovskyTypXMLType rodicovskyTypXML;
    @XmlElement(name = "InstanceIndikator", required = true)
    protected DatovyPrvekInstanceIndikatorType instanceIndikator;
    @XmlElement(name = "KonstruktyDilciSeznam")
    protected KonstruktyDilciSeznam konstruktyDilciSeznam;
    @XmlElement(name = "Model")
    protected DatovyPrvekSlozenyModelType model;
    @XmlElement(name = "Rozsireni", namespace = "urn:cz:isvs:micr:schemas:CommonTypes:v1")
    protected RozsireniStructure rozsireni;
    @XmlAttribute(name = "objektID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String objektID;

    /**
     * Gets the value of the skupinaKod property.
     * 
     * @return
     *     possible object is
     *     {@link DatovyPrvekSkupinaKodType }
     *     
     */
    public DatovyPrvekSkupinaKodType getSkupinaKod() {
        return skupinaKod;
    }

    /**
     * Sets the value of the skupinaKod property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatovyPrvekSkupinaKodType }
     *     
     */
    public void setSkupinaKod(DatovyPrvekSkupinaKodType value) {
        this.skupinaKod = value;
    }

    /**
     * Gets the value of the skupinaNazev property.
     * 
     * @return
     *     possible object is
     *     {@link DatovyPrvekSkupinaNazevType }
     *     
     */
    public DatovyPrvekSkupinaNazevType getSkupinaNazev() {
        return skupinaNazev;
    }

    /**
     * Sets the value of the skupinaNazev property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatovyPrvekSkupinaNazevType }
     *     
     */
    public void setSkupinaNazev(DatovyPrvekSkupinaNazevType value) {
        this.skupinaNazev = value;
    }

    /**
     * Gets the value of the identifikator property.
     * 
     * @return
     *     possible object is
     *     {@link DatovyPrvekIdentifikatorType }
     *     
     */
    public DatovyPrvekIdentifikatorType getIdentifikator() {
        return identifikator;
    }

    /**
     * Sets the value of the identifikator property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatovyPrvekIdentifikatorType }
     *     
     */
    public void setIdentifikator(DatovyPrvekIdentifikatorType value) {
        this.identifikator = value;
    }

    /**
     * Gets the value of the nazev property.
     * 
     * @return
     *     possible object is
     *     {@link DatovyPrvekNazevType }
     *     
     */
    public DatovyPrvekNazevType getNazev() {
        return nazev;
    }

    /**
     * Sets the value of the nazev property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatovyPrvekNazevType }
     *     
     */
    public void setNazev(DatovyPrvekNazevType value) {
        this.nazev = value;
    }

    /**
     * Gets the value of the synonymaSeznam property.
     * 
     * @return
     *     possible object is
     *     {@link SynonymaSeznam }
     *     
     */
    public SynonymaSeznam getSynonymaSeznam() {
        return synonymaSeznam;
    }

    /**
     * Sets the value of the synonymaSeznam property.
     * 
     * @param value
     *     allowed object is
     *     {@link SynonymaSeznam }
     *     
     */
    public void setSynonymaSeznam(SynonymaSeznam value) {
        this.synonymaSeznam = value;
    }

    /**
     * Gets the value of the akronym property.
     * 
     * @return
     *     possible object is
     *     {@link DatovyPrvekAkronymType }
     *     
     */
    public DatovyPrvekAkronymType getAkronym() {
        return akronym;
    }

    /**
     * Sets the value of the akronym property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatovyPrvekAkronymType }
     *     
     */
    public void setAkronym(DatovyPrvekAkronymType value) {
        this.akronym = value;
    }

    /**
     * Gets the value of the verze property.
     * 
     * @return
     *     possible object is
     *     {@link DatovyPrvekVerzeType }
     *     
     */
    public DatovyPrvekVerzeType getVerze() {
        return verze;
    }

    /**
     * Sets the value of the verze property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatovyPrvekVerzeType }
     *     
     */
    public void setVerze(DatovyPrvekVerzeType value) {
        this.verze = value;
    }

    /**
     * Gets the value of the definice property.
     * 
     * @return
     *     possible object is
     *     {@link DatovyPrvekDefiniceType }
     *     
     */
    public DatovyPrvekDefiniceType getDefinice() {
        return definice;
    }

    /**
     * Sets the value of the definice property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatovyPrvekDefiniceType }
     *     
     */
    public void setDefinice(DatovyPrvekDefiniceType value) {
        this.definice = value;
    }

    /**
     * Gets the value of the komentar property.
     * 
     * @return
     *     possible object is
     *     {@link DatovyPrvekKomentarType }
     *     
     */
    public DatovyPrvekKomentarType getKomentar() {
        return komentar;
    }

    /**
     * Sets the value of the komentar property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatovyPrvekKomentarType }
     *     
     */
    public void setKomentar(DatovyPrvekKomentarType value) {
        this.komentar = value;
    }

    /**
     * Gets the value of the predpisyPravniSeznam property.
     * 
     * @return
     *     possible object is
     *     {@link PredpisyPravniSeznam }
     *     
     */
    public PredpisyPravniSeznam getPredpisyPravniSeznam() {
        return predpisyPravniSeznam;
    }

    /**
     * Sets the value of the predpisyPravniSeznam property.
     * 
     * @param value
     *     allowed object is
     *     {@link PredpisyPravniSeznam }
     *     
     */
    public void setPredpisyPravniSeznam(PredpisyPravniSeznam value) {
        this.predpisyPravniSeznam = value;
    }

    /**
     * Gets the value of the predpisyJineSeznam property.
     * 
     * @return
     *     possible object is
     *     {@link PredpisyJineSeznam }
     *     
     */
    public PredpisyJineSeznam getPredpisyJineSeznam() {
        return predpisyJineSeznam;
    }

    /**
     * Sets the value of the predpisyJineSeznam property.
     * 
     * @param value
     *     allowed object is
     *     {@link PredpisyJineSeznam }
     *     
     */
    public void setPredpisyJineSeznam(PredpisyJineSeznam value) {
        this.predpisyJineSeznam = value;
    }

    /**
     * Gets the value of the zdrojeHodnotSeznam property.
     * 
     * @return
     *     possible object is
     *     {@link ZdrojeHodnotSeznam }
     *     
     */
    public ZdrojeHodnotSeznam getZdrojeHodnotSeznam() {
        return zdrojeHodnotSeznam;
    }

    /**
     * Sets the value of the zdrojeHodnotSeznam property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZdrojeHodnotSeznam }
     *     
     */
    public void setZdrojeHodnotSeznam(ZdrojeHodnotSeznam value) {
        this.zdrojeHodnotSeznam = value;
    }

    /**
     * Gets the value of the spravce property.
     * 
     * @return
     *     possible object is
     *     {@link DatovyPrvekSpravceType }
     *     
     */
    public DatovyPrvekSpravceType getSpravce() {
        return spravce;
    }

    /**
     * Sets the value of the spravce property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatovyPrvekSpravceType }
     *     
     */
    public void setSpravce(DatovyPrvekSpravceType value) {
        this.spravce = value;
    }

    /**
     * Gets the value of the vyhlaseniDatum property.
     * 
     * @return
     *     possible object is
     *     {@link VyhlaseniDatumType }
     *     
     */
    public VyhlaseniDatumType getVyhlaseniDatum() {
        return vyhlaseniDatum;
    }

    /**
     * Sets the value of the vyhlaseniDatum property.
     * 
     * @param value
     *     allowed object is
     *     {@link VyhlaseniDatumType }
     *     
     */
    public void setVyhlaseniDatum(VyhlaseniDatumType value) {
        this.vyhlaseniDatum = value;
    }

    /**
     * Gets the value of the platnostOdDatum property.
     * 
     * @return
     *     possible object is
     *     {@link PlatnostOdDatumType }
     *     
     */
    public PlatnostOdDatumType getPlatnostOdDatum() {
        return platnostOdDatum;
    }

    /**
     * Sets the value of the platnostOdDatum property.
     * 
     * @param value
     *     allowed object is
     *     {@link PlatnostOdDatumType }
     *     
     */
    public void setPlatnostOdDatum(PlatnostOdDatumType value) {
        this.platnostOdDatum = value;
    }

    /**
     * Gets the value of the platnostDoDatum property.
     * 
     * @return
     *     possible object is
     *     {@link PlatnostDoDatumType }
     *     
     */
    public PlatnostDoDatumType getPlatnostDoDatum() {
        return platnostDoDatum;
    }

    /**
     * Sets the value of the platnostDoDatum property.
     * 
     * @param value
     *     allowed object is
     *     {@link PlatnostDoDatumType }
     *     
     */
    public void setPlatnostDoDatum(PlatnostDoDatumType value) {
        this.platnostDoDatum = value;
    }

    /**
     * Gets the value of the vytvorilSeznam property.
     * 
     * @return
     *     possible object is
     *     {@link VytvorilSeznam }
     *     
     */
    public VytvorilSeznam getVytvorilSeznam() {
        return vytvorilSeznam;
    }

    /**
     * Sets the value of the vytvorilSeznam property.
     * 
     * @param value
     *     allowed object is
     *     {@link VytvorilSeznam }
     *     
     */
    public void setVytvorilSeznam(VytvorilSeznam value) {
        this.vytvorilSeznam = value;
    }

    /**
     * Gets the value of the prispelSeznam property.
     * 
     * @return
     *     possible object is
     *     {@link PrispelSeznam }
     *     
     */
    public PrispelSeznam getPrispelSeznam() {
        return prispelSeznam;
    }

    /**
     * Sets the value of the prispelSeznam property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrispelSeznam }
     *     
     */
    public void setPrispelSeznam(PrispelSeznam value) {
        this.prispelSeznam = value;
    }

    /**
     * Gets the value of the jmenoXML property.
     * 
     * @return
     *     possible object is
     *     {@link DatovyPrvekJmenoXMLType }
     *     
     */
    public DatovyPrvekJmenoXMLType getJmenoXML() {
        return jmenoXML;
    }

    /**
     * Sets the value of the jmenoXML property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatovyPrvekJmenoXMLType }
     *     
     */
    public void setJmenoXML(DatovyPrvekJmenoXMLType value) {
        this.jmenoXML = value;
    }

    /**
     * Gets the value of the jmenoXMLAnglicky property.
     * 
     * @return
     *     possible object is
     *     {@link DatovyPrvekJmenoXMLAnglickyType }
     *     
     */
    public DatovyPrvekJmenoXMLAnglickyType getJmenoXMLAnglicky() {
        return jmenoXMLAnglicky;
    }

    /**
     * Sets the value of the jmenoXMLAnglicky property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatovyPrvekJmenoXMLAnglickyType }
     *     
     */
    public void setJmenoXMLAnglicky(DatovyPrvekJmenoXMLAnglickyType value) {
        this.jmenoXMLAnglicky = value;
    }

    /**
     * Gets the value of the typXML property.
     * 
     * @return
     *     possible object is
     *     {@link DatovyPrvekTypXMLType }
     *     
     */
    public DatovyPrvekTypXMLType getTypXML() {
        return typXML;
    }

    /**
     * Sets the value of the typXML property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatovyPrvekTypXMLType }
     *     
     */
    public void setTypXML(DatovyPrvekTypXMLType value) {
        this.typXML = value;
    }

    /**
     * Gets the value of the rodicovskyTypXML property.
     * 
     * @return
     *     possible object is
     *     {@link DatovyPrvekRodicovskyTypXMLType }
     *     
     */
    public DatovyPrvekRodicovskyTypXMLType getRodicovskyTypXML() {
        return rodicovskyTypXML;
    }

    /**
     * Sets the value of the rodicovskyTypXML property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatovyPrvekRodicovskyTypXMLType }
     *     
     */
    public void setRodicovskyTypXML(DatovyPrvekRodicovskyTypXMLType value) {
        this.rodicovskyTypXML = value;
    }

    /**
     * Gets the value of the instanceIndikator property.
     * 
     * @return
     *     possible object is
     *     {@link DatovyPrvekInstanceIndikatorType }
     *     
     */
    public DatovyPrvekInstanceIndikatorType getInstanceIndikator() {
        return instanceIndikator;
    }

    /**
     * Sets the value of the instanceIndikator property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatovyPrvekInstanceIndikatorType }
     *     
     */
    public void setInstanceIndikator(DatovyPrvekInstanceIndikatorType value) {
        this.instanceIndikator = value;
    }

    /**
     * Gets the value of the konstruktyDilciSeznam property.
     * 
     * @return
     *     possible object is
     *     {@link KonstruktyDilciSeznam }
     *     
     */
    public KonstruktyDilciSeznam getKonstruktyDilciSeznam() {
        return konstruktyDilciSeznam;
    }

    /**
     * Sets the value of the konstruktyDilciSeznam property.
     * 
     * @param value
     *     allowed object is
     *     {@link KonstruktyDilciSeznam }
     *     
     */
    public void setKonstruktyDilciSeznam(KonstruktyDilciSeznam value) {
        this.konstruktyDilciSeznam = value;
    }

    /**
     * Gets the value of the model property.
     * 
     * @return
     *     possible object is
     *     {@link DatovyPrvekSlozenyModelType }
     *     
     */
    public DatovyPrvekSlozenyModelType getModel() {
        return model;
    }

    /**
     * Sets the value of the model property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatovyPrvekSlozenyModelType }
     *     
     */
    public void setModel(DatovyPrvekSlozenyModelType value) {
        this.model = value;
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


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="KonstruktDilci" type="{urn:cz:isvs:micr:schemas:DataElementsTypes:v1}DatovyPrvekKonstruktDilciStructure" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "konstruktDilci"
    })
    public static class KonstruktyDilciSeznam {

        @XmlElement(name = "KonstruktDilci", required = true)
        protected List<DatovyPrvekKonstruktDilciStructure> konstruktDilci;

        /**
         * Gets the value of the konstruktDilci property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the konstruktDilci property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getKonstruktDilci().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DatovyPrvekKonstruktDilciStructure }
         * 
         * 
         */
        public List<DatovyPrvekKonstruktDilciStructure> getKonstruktDilci() {
            if (konstruktDilci == null) {
                konstruktDilci = new ArrayList<DatovyPrvekKonstruktDilciStructure>();
            }
            return this.konstruktDilci;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="PredpisJiny" type="{urn:cz:isvs:micr:schemas:DataElementsTypes:v1}DatovyPrvekPredpisJinyType" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "predpisJiny"
    })
    public static class PredpisyJineSeznam {

        @XmlElement(name = "PredpisJiny", required = true)
        protected List<DatovyPrvekPredpisJinyType> predpisJiny;

        /**
         * Gets the value of the predpisJiny property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the predpisJiny property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPredpisJiny().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DatovyPrvekPredpisJinyType }
         * 
         * 
         */
        public List<DatovyPrvekPredpisJinyType> getPredpisJiny() {
            if (predpisJiny == null) {
                predpisJiny = new ArrayList<DatovyPrvekPredpisJinyType>();
            }
            return this.predpisJiny;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="PredpisPravni" type="{urn:cz:isvs:micr:schemas:DataElementsTypes:v1}DatovyPrvekPredpisPravniType" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "predpisPravni"
    })
    public static class PredpisyPravniSeznam {

        @XmlElement(name = "PredpisPravni", required = true)
        protected List<DatovyPrvekPredpisPravniType> predpisPravni;

        /**
         * Gets the value of the predpisPravni property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the predpisPravni property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPredpisPravni().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DatovyPrvekPredpisPravniType }
         * 
         * 
         */
        public List<DatovyPrvekPredpisPravniType> getPredpisPravni() {
            if (predpisPravni == null) {
                predpisPravni = new ArrayList<DatovyPrvekPredpisPravniType>();
            }
            return this.predpisPravni;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Prispel" type="{urn:cz:isvs:micr:schemas:DataElementsTypes:v1}PrispelType" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "prispel"
    })
    public static class PrispelSeznam {

        @XmlElement(name = "Prispel", required = true)
        protected List<PrispelType> prispel;

        /**
         * Gets the value of the prispel property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the prispel property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPrispel().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link PrispelType }
         * 
         * 
         */
        public List<PrispelType> getPrispel() {
            if (prispel == null) {
                prispel = new ArrayList<PrispelType>();
            }
            return this.prispel;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Synonymum" type="{urn:cz:isvs:micr:schemas:DataElementsTypes:v1}DatovyPrvekSynonymumType" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "synonymum"
    })
    public static class SynonymaSeznam {

        @XmlElement(name = "Synonymum", required = true)
        protected List<DatovyPrvekSynonymumType> synonymum;

        /**
         * Gets the value of the synonymum property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the synonymum property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSynonymum().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DatovyPrvekSynonymumType }
         * 
         * 
         */
        public List<DatovyPrvekSynonymumType> getSynonymum() {
            if (synonymum == null) {
                synonymum = new ArrayList<DatovyPrvekSynonymumType>();
            }
            return this.synonymum;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Vytvoril" type="{urn:cz:isvs:micr:schemas:DataElementsTypes:v1}VytvorilType" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "vytvoril"
    })
    public static class VytvorilSeznam {

        @XmlElement(name = "Vytvoril", required = true)
        protected List<VytvorilType> vytvoril;

        /**
         * Gets the value of the vytvoril property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the vytvoril property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getVytvoril().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link VytvorilType }
         * 
         * 
         */
        public List<VytvorilType> getVytvoril() {
            if (vytvoril == null) {
                vytvoril = new ArrayList<VytvorilType>();
            }
            return this.vytvoril;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="ZdrojHodnot" type="{urn:cz:isvs:micr:schemas:DataElementsTypes:v1}DatovyPrvekZdrojHodnotType" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "zdrojHodnot"
    })
    public static class ZdrojeHodnotSeznam {

        @XmlElement(name = "ZdrojHodnot", required = true)
        protected List<DatovyPrvekZdrojHodnotType> zdrojHodnot;

        /**
         * Gets the value of the zdrojHodnot property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the zdrojHodnot property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getZdrojHodnot().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DatovyPrvekZdrojHodnotType }
         * 
         * 
         */
        public List<DatovyPrvekZdrojHodnotType> getZdrojHodnot() {
            if (zdrojHodnot == null) {
                zdrojHodnot = new ArrayList<DatovyPrvekZdrojHodnotType>();
            }
            return this.zdrojHodnot;
        }

    }

}
