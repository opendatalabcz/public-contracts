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
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.businesstypes.v2.EkonomickaCinnostKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.businesstypes.v2.EkonomickaCinnostNACEKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.businesstypes.v2.FinancniUradKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.businesstypes.v2.InstitucionalniSektorKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.businesstypes.v2.NeziskovaInstituceSluzbaKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.businesstypes.v2.OrganizacePlatceDPHIndikatorType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.businesstypes.v2.ProdukceCPAKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.businesstypes.v2.ProdukceKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.businesstypes.v2.SubjektKategorieKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.businesstypes.v2.SubjektPravniFormaKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.businesstypes.v2.SubjektUradRegistraceKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.businesstypes.v2.VladniInstituceFunkceKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.commontypes.v1.BankaKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.commontypes.v1.JedinecnyIdentifikatorType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.commontypes.v1.MenaKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.commontypes.v1.MernaJednotkaKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.dataelementstypes.v1.DatovyPrvekCiselnikPolozkaKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.dataelementstypes.v1.DatovyPrvekSkupinaKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.dataelementstypes.v1.DatovyPrvekVerzeType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.persontypes.v2.OsobaDiagnozaKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.persontypes.v2.OsobaSpotrebaKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.persontypes.v2.OsobaZamestnaniKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.persontypes.v2.OsobaZamestnaniPostaveniKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.persontypes.v2.PracovniPomerDruhKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.persontypes.v2.VzdelaniJednoduchyKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.persontypes.v2.VzdelaniKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.spacetypes.v1.CastObceKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.spacetypes.v1.CastObcePredavaciKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.spacetypes.v1.KatastrKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.spacetypes.v1.KrajNutsKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.spacetypes.v1.KrajPredavaciKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.spacetypes.v1.MestskaCastKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.spacetypes.v1.MestskaCastPredavaciKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.spacetypes.v1.ObecKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.spacetypes.v1.ObecPoverenyUradKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.spacetypes.v1.ObecPoverenyUradNazevZkracenyType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.spacetypes.v1.ObecPoverenyUradStatistickyKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.spacetypes.v1.ObecPredavaciKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.spacetypes.v1.ObecRozsirenaPusobnostKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.spacetypes.v1.ObecRozsirenaPusobnostNazevZkracenyType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.spacetypes.v1.ObecRozsirenaPusobnostStatistickyKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.spacetypes.v1.ObjektAdresaPredavaciKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.spacetypes.v1.ObjektPredavaciKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.spacetypes.v1.OblastNutsKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.spacetypes.v1.OblastPredavaciKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.spacetypes.v1.ObvodPrahaPredavaciKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.spacetypes.v1.ObvodPrahaStatistickyNutsKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.spacetypes.v1.OkresKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.spacetypes.v1.OkresNutsKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.spacetypes.v1.PSCType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.spacetypes.v1.PozemekBonitaKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.spacetypes.v1.SpravniObvodPrahaPredavaciKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.spacetypes.v1.StatKodOmezenyType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.spacetypes.v1.StatKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.spacetypes.v1.UlicePredavaciKodType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.spacetypes.v1.ZakladniSidelniJednotkaKodType;
import eu.profinit.publiccontracts.generated.isvz.mmr.schemas.vz_z_profilu_zadavatele.v100.DruhZadavacihoRizeniType;
import eu.profinit.publiccontracts.generated.isvz.mmr.schemas.vz_z_profilu_zadavatele.v100.StavCastiVzType;
import eu.profinit.publiccontracts.generated.isvz.mmr.schemas.vz_z_profilu_zadavatele.v100.StavVZType;
import eu.profinit.publiccontracts.generated.isvz.mmr.schemas.vz_z_profilu_zadavatele.v100.TypDokumentuType;


/**
 * Znakov� �et�zec (p�smena, ��slice nebo symboly) kter� mohou z d�vod� jasnosti a jazykov� nez�vislosti nahradit definitivn� hodnotu nebo text; voliteln� spolu s dopl�kov�mi informacemi o �daji
 * 
 * <p>Java class for KodType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="KodType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>token">
 *       &lt;attribute name="ciselnikID" type="{http://www.w3.org/2001/XMLSchema}token" />
 *       &lt;attribute name="ciselnikSpravceID" type="{http://www.w3.org/2001/XMLSchema}token" />
 *       &lt;attribute name="ciselnikVerzeID" type="{http://www.w3.org/2001/XMLSchema}token" />
 *       &lt;attribute name="kodJmeno" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="jazykKod" type="{urn:cz:isvs:micr:schemas:CoreComponentTypes:v1}jazykType" />
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
@XmlType(name = "KodType", propOrder = {
    "value"
})
@XmlSeeAlso({
    StavVZType.class,
    TypDokumentuType.class,
    DruhZadavacihoRizeniType.class,
    StavCastiVzType.class,
    MenaKodType.class,
    MernaJednotkaKodType.class,
    BankaKodType.class,
    JedinecnyIdentifikatorType.class,
    SubjektKategorieKodType.class,
    InstitucionalniSektorKodType.class,
    OrganizacePlatceDPHIndikatorType.class,
    EkonomickaCinnostNACEKodType.class,
    SubjektUradRegistraceKodType.class,
    NeziskovaInstituceSluzbaKodType.class,
    ProdukceCPAKodType.class,
    SubjektPravniFormaKodType.class,
    VladniInstituceFunkceKodType.class,
    ProdukceKodType.class,
    FinancniUradKodType.class,
    EkonomickaCinnostKodType.class,
    OsobaZamestnaniKodType.class,
    OsobaSpotrebaKodType.class,
    PracovniPomerDruhKodType.class,
    VzdelaniJednoduchyKodType.class,
    VzdelaniKodType.class,
    OsobaZamestnaniPostaveniKodType.class,
    eu.profinit.publiccontracts.generated.isvs.micr.schemas.persontypes.v2.JazykKodType.class,
    OsobaDiagnozaKodType.class,
    SpravniObvodPrahaPredavaciKodType.class,
    ObecRozsirenaPusobnostNazevZkracenyType.class,
    ObecPoverenyUradStatistickyKodType.class,
    ObvodPrahaPredavaciKodType.class,
    ObecRozsirenaPusobnostStatistickyKodType.class,
    PozemekBonitaKodType.class,
    PSCType.class,
    ObvodPrahaStatistickyNutsKodType.class,
    ObjektAdresaPredavaciKodType.class,
    CastObcePredavaciKodType.class,
    ZakladniSidelniJednotkaKodType.class,
    CastObceKodType.class,
    UlicePredavaciKodType.class,
    ObecPoverenyUradKodType.class,
    OkresKodType.class,
    KatastrKodType.class,
    OblastNutsKodType.class,
    ObecRozsirenaPusobnostKodType.class,
    MestskaCastKodType.class,
    ObecPoverenyUradNazevZkracenyType.class,
    StatKodOmezenyType.class,
    ObjektPredavaciKodType.class,
    StatKodType.class,
    MestskaCastPredavaciKodType.class,
    KrajNutsKodType.class,
    ObecPredavaciKodType.class,
    OblastPredavaciKodType.class,
    ObecKodType.class,
    KrajPredavaciKodType.class,
    OkresNutsKodType.class,
    DatovyPrvekSkupinaKodType.class,
    eu.profinit.publiccontracts.generated.isvs.micr.schemas.dataelementstypes.v1.JazykKodType.class,
    DatovyPrvekCiselnikPolozkaKodType.class,
    DatovyPrvekVerzeType.class
})
public class KodType {

    @XmlValue
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String value;
    @XmlAttribute(name = "ciselnikID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String ciselnikID;
    @XmlAttribute(name = "ciselnikSpravceID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String ciselnikSpravceID;
    @XmlAttribute(name = "ciselnikVerzeID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String ciselnikVerzeID;
    @XmlAttribute(name = "kodJmeno")
    protected String kodJmeno;
    @XmlAttribute(name = "jazykKod")
    protected JazykType jazykKod;
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
     * Gets the value of the ciselnikID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCiselnikID() {
        return ciselnikID;
    }

    /**
     * Sets the value of the ciselnikID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCiselnikID(String value) {
        this.ciselnikID = value;
    }

    /**
     * Gets the value of the ciselnikSpravceID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCiselnikSpravceID() {
        return ciselnikSpravceID;
    }

    /**
     * Sets the value of the ciselnikSpravceID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCiselnikSpravceID(String value) {
        this.ciselnikSpravceID = value;
    }

    /**
     * Gets the value of the ciselnikVerzeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCiselnikVerzeID() {
        return ciselnikVerzeID;
    }

    /**
     * Sets the value of the ciselnikVerzeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCiselnikVerzeID(String value) {
        this.ciselnikVerzeID = value;
    }

    /**
     * Gets the value of the kodJmeno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKodJmeno() {
        return kodJmeno;
    }

    /**
     * Sets the value of the kodJmeno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKodJmeno(String value) {
        this.kodJmeno = value;
    }

    /**
     * Gets the value of the jazykKod property.
     * 
     * @return
     *     possible object is
     *     {@link JazykType }
     *     
     */
    public JazykType getJazykKod() {
        return jazykKod;
    }

    /**
     * Sets the value of the jazykKod property.
     * 
     * @param value
     *     allowed object is
     *     {@link JazykType }
     *     
     */
    public void setJazykKod(JazykType value) {
        this.jazykKod = value;
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
