//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.19 at 01:50:51 AM CEST 
//


package eu.profinit.publiccontracts.generated.isvz.mmr.schemas.vz_z_profilu_zadavatele.v100;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.businesstypes.v2.SubjektObchodniJmenoType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.corecomponenttypes.v1.CenaType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.corecomponenttypes.v1.IndikatorType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.dataelementstypes.v1.DatovyPrvekJednoduchyTypIndikatorType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.spacetypes.v1.StatKodType;


/**
 * Struktura pro p�ed�n� z�kladn�ch informac�ch o ��astn�kovi zad�vac�ho ��zen�
 * 
 * <p>Java class for UcastnikZRStructure complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UcastnikZRStructure">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ico" type="{urn:cz:isvz:mmr:schemas:vz-z-profilu-zadavatele:v100}IcoType" minOccurs="0"/>
 *         &lt;element name="nazev_ucastnika" type="{urn:cz:isvs:micr:schemas:BusinessTypes:v2}SubjektObchodniJmenoType"/>
 *         &lt;choice>
 *           &lt;element name="zeme_sidla" type="{urn:cz:isvs:micr:schemas:SpaceTypes:v1}StatKodType" minOccurs="0"/>
 *           &lt;element name="misto_podnikani" type="{urn:cz:isvs:micr:schemas:SpaceTypes:v1}StatKodType" minOccurs="0"/>
 *           &lt;element name="bydliste" type="{urn:cz:isvs:micr:schemas:SpaceTypes:v1}StatKodType" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element name="cena_bez_dph" type="{urn:cz:isvs:micr:schemas:CoreComponentTypes:v1}CenaType"/>
 *         &lt;element name="cena_s_dph" type="{urn:cz:isvs:micr:schemas:CoreComponentTypes:v1}CenaType" minOccurs="0"/>
 *         &lt;element name="sdruzeni" type="{urn:cz:isvs:micr:schemas:DataElementsTypes:v1}DatovyPrvekJednoduchyTypIndikatorType"/>
 *         &lt;element name="vedouci_ucastnik_ico" type="{urn:cz:isvz:mmr:schemas:vz-z-profilu-zadavatele:v100}IcoType" minOccurs="0"/>
 *         &lt;element name="vyrazen_mim_cena" type="{urn:cz:isvs:micr:schemas:CoreComponentTypes:v1}IndikatorType" minOccurs="0"/>
 *         &lt;element name="vyrazen_mim_cena_zduvodneni" type="{urn:cz:isvz:mmr:schemas:vz-z-profilu-zadavatele:v100}PoznamkaType" minOccurs="0"/>
 *         &lt;element name="odstoupil" type="{urn:cz:isvs:micr:schemas:DataElementsTypes:v1}DatovyPrvekJednoduchyTypIndikatorType" minOccurs="0"/>
 *         &lt;element name="odstoupil_zduvodneni" type="{urn:cz:isvz:mmr:schemas:vz-z-profilu-zadavatele:v100}PoznamkaType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UcastnikZRStructure", propOrder = {
    "ico",
    "nazevUcastnika",
    "zemeSidla",
    "mistoPodnikani",
    "bydliste",
    "cenaBezDph",
    "cenaSDph",
    "sdruzeni",
    "vedouciUcastnikIco",
    "vyrazenMimCena",
    "vyrazenMimCenaZduvodneni",
    "odstoupil",
    "odstoupilZduvodneni"
})
public class UcastnikZRStructure {

    protected IcoType ico;
    @XmlElement(name = "nazev_ucastnika", required = true)
    protected SubjektObchodniJmenoType nazevUcastnika;
    @XmlElement(name = "zeme_sidla")
    protected StatKodType zemeSidla;
    @XmlElement(name = "misto_podnikani")
    protected StatKodType mistoPodnikani;
    protected StatKodType bydliste;
    @XmlElement(name = "cena_bez_dph", required = true)
    protected CenaType cenaBezDph;
    @XmlElement(name = "cena_s_dph")
    protected CenaType cenaSDph;
    @XmlElement(required = true)
    protected DatovyPrvekJednoduchyTypIndikatorType sdruzeni;
    @XmlElement(name = "vedouci_ucastnik_ico")
    protected IcoType vedouciUcastnikIco;
    @XmlElement(name = "vyrazen_mim_cena")
    protected IndikatorType vyrazenMimCena;
    @XmlElement(name = "vyrazen_mim_cena_zduvodneni")
    protected PoznamkaType vyrazenMimCenaZduvodneni;
    protected DatovyPrvekJednoduchyTypIndikatorType odstoupil;
    @XmlElement(name = "odstoupil_zduvodneni")
    protected PoznamkaType odstoupilZduvodneni;

    /**
     * Gets the value of the ico property.
     * 
     * @return
     *     possible object is
     *     {@link IcoType }
     *     
     */
    public IcoType getIco() {
        return ico;
    }

    /**
     * Sets the value of the ico property.
     * 
     * @param value
     *     allowed object is
     *     {@link IcoType }
     *     
     */
    public void setIco(IcoType value) {
        this.ico = value;
    }

    /**
     * Gets the value of the nazevUcastnika property.
     * 
     * @return
     *     possible object is
     *     {@link SubjektObchodniJmenoType }
     *     
     */
    public SubjektObchodniJmenoType getNazevUcastnika() {
        return nazevUcastnika;
    }

    /**
     * Sets the value of the nazevUcastnika property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubjektObchodniJmenoType }
     *     
     */
    public void setNazevUcastnika(SubjektObchodniJmenoType value) {
        this.nazevUcastnika = value;
    }

    /**
     * Gets the value of the zemeSidla property.
     * 
     * @return
     *     possible object is
     *     {@link StatKodType }
     *     
     */
    public StatKodType getZemeSidla() {
        return zemeSidla;
    }

    /**
     * Sets the value of the zemeSidla property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatKodType }
     *     
     */
    public void setZemeSidla(StatKodType value) {
        this.zemeSidla = value;
    }

    /**
     * Gets the value of the mistoPodnikani property.
     * 
     * @return
     *     possible object is
     *     {@link StatKodType }
     *     
     */
    public StatKodType getMistoPodnikani() {
        return mistoPodnikani;
    }

    /**
     * Sets the value of the mistoPodnikani property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatKodType }
     *     
     */
    public void setMistoPodnikani(StatKodType value) {
        this.mistoPodnikani = value;
    }

    /**
     * Gets the value of the bydliste property.
     * 
     * @return
     *     possible object is
     *     {@link StatKodType }
     *     
     */
    public StatKodType getBydliste() {
        return bydliste;
    }

    /**
     * Sets the value of the bydliste property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatKodType }
     *     
     */
    public void setBydliste(StatKodType value) {
        this.bydliste = value;
    }

    /**
     * Gets the value of the cenaBezDph property.
     * 
     * @return
     *     possible object is
     *     {@link CenaType }
     *     
     */
    public CenaType getCenaBezDph() {
        return cenaBezDph;
    }

    /**
     * Sets the value of the cenaBezDph property.
     * 
     * @param value
     *     allowed object is
     *     {@link CenaType }
     *     
     */
    public void setCenaBezDph(CenaType value) {
        this.cenaBezDph = value;
    }

    /**
     * Gets the value of the cenaSDph property.
     * 
     * @return
     *     possible object is
     *     {@link CenaType }
     *     
     */
    public CenaType getCenaSDph() {
        return cenaSDph;
    }

    /**
     * Sets the value of the cenaSDph property.
     * 
     * @param value
     *     allowed object is
     *     {@link CenaType }
     *     
     */
    public void setCenaSDph(CenaType value) {
        this.cenaSDph = value;
    }

    /**
     * Gets the value of the sdruzeni property.
     * 
     * @return
     *     possible object is
     *     {@link DatovyPrvekJednoduchyTypIndikatorType }
     *     
     */
    public DatovyPrvekJednoduchyTypIndikatorType getSdruzeni() {
        return sdruzeni;
    }

    /**
     * Sets the value of the sdruzeni property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatovyPrvekJednoduchyTypIndikatorType }
     *     
     */
    public void setSdruzeni(DatovyPrvekJednoduchyTypIndikatorType value) {
        this.sdruzeni = value;
    }

    /**
     * Gets the value of the vedouciUcastnikIco property.
     * 
     * @return
     *     possible object is
     *     {@link IcoType }
     *     
     */
    public IcoType getVedouciUcastnikIco() {
        return vedouciUcastnikIco;
    }

    /**
     * Sets the value of the vedouciUcastnikIco property.
     * 
     * @param value
     *     allowed object is
     *     {@link IcoType }
     *     
     */
    public void setVedouciUcastnikIco(IcoType value) {
        this.vedouciUcastnikIco = value;
    }

    /**
     * Gets the value of the vyrazenMimCena property.
     * 
     * @return
     *     possible object is
     *     {@link IndikatorType }
     *     
     */
    public IndikatorType getVyrazenMimCena() {
        return vyrazenMimCena;
    }

    /**
     * Sets the value of the vyrazenMimCena property.
     * 
     * @param value
     *     allowed object is
     *     {@link IndikatorType }
     *     
     */
    public void setVyrazenMimCena(IndikatorType value) {
        this.vyrazenMimCena = value;
    }

    /**
     * Gets the value of the vyrazenMimCenaZduvodneni property.
     * 
     * @return
     *     possible object is
     *     {@link PoznamkaType }
     *     
     */
    public PoznamkaType getVyrazenMimCenaZduvodneni() {
        return vyrazenMimCenaZduvodneni;
    }

    /**
     * Sets the value of the vyrazenMimCenaZduvodneni property.
     * 
     * @param value
     *     allowed object is
     *     {@link PoznamkaType }
     *     
     */
    public void setVyrazenMimCenaZduvodneni(PoznamkaType value) {
        this.vyrazenMimCenaZduvodneni = value;
    }

    /**
     * Gets the value of the odstoupil property.
     * 
     * @return
     *     possible object is
     *     {@link DatovyPrvekJednoduchyTypIndikatorType }
     *     
     */
    public DatovyPrvekJednoduchyTypIndikatorType getOdstoupil() {
        return odstoupil;
    }

    /**
     * Sets the value of the odstoupil property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatovyPrvekJednoduchyTypIndikatorType }
     *     
     */
    public void setOdstoupil(DatovyPrvekJednoduchyTypIndikatorType value) {
        this.odstoupil = value;
    }

    /**
     * Gets the value of the odstoupilZduvodneni property.
     * 
     * @return
     *     possible object is
     *     {@link PoznamkaType }
     *     
     */
    public PoznamkaType getOdstoupilZduvodneni() {
        return odstoupilZduvodneni;
    }

    /**
     * Sets the value of the odstoupilZduvodneni property.
     * 
     * @param value
     *     allowed object is
     *     {@link PoznamkaType }
     *     
     */
    public void setOdstoupilZduvodneni(PoznamkaType value) {
        this.odstoupilZduvodneni = value;
    }

}