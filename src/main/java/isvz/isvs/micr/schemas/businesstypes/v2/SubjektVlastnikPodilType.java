//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.03.23 at 01:46:39 AM CET 
//


package isvz.isvs.micr.schemas.businesstypes.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import isvz.isvs.micr.schemas.corecomponenttypes.v1.TextType;


/**
 * Vlastnick� pod�l ur�uje, jakou ��st vlastnictv� obchodn� spole�nosti nebo nemovitosti m� dan� spoluvlastn�k, tj. jak� je jeho majetkov� pod�l nebo obchodn� pod�l. Vlastnick� pod�l je vyj�d�en jako:
 * a) zlomek dvou cel�ch nejv��e �ty�m�stn�ch nesoud�ln�ch ��sel, mezi nimi� se zapisuje znak lom�tka (nap�. 3/7, 1/1)
 * b) dev�tim�stn�m ��slem, kde prvn� �ty�i m�sta je �itatel zlomku a posledn�ch p�t m�st je jmenovatel zlomku.
 * 
 * <p>Java class for SubjektVlastnikPodilType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubjektVlastnikPodilType">
 *   &lt;simpleContent>
 *     &lt;restriction base="&lt;urn:cz:isvs:micr:schemas:CoreComponentTypes:v1>TextType">
 *     &lt;/restriction>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubjektVlastnikPodilType")
public class SubjektVlastnikPodilType
    extends TextType
{


}
