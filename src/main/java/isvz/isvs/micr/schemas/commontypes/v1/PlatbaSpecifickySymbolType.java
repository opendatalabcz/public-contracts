//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.03.23 at 01:46:39 AM CET 
//


package isvz.isvs.micr.schemas.commontypes.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import isvz.isvs.micr.schemas.corecomponenttypes.v1.IdentifikatorType;


/**
 * Datov� prvek pro ��seln� �daj, kter� pl�tce (p��kazce) uv�d� v p��padech, kdy je to nutn� k z��tov�n� platby v bance.
 * Struktura specifick�ho symbolu nen� stanovena. Obsah specifick�ho symbolu ur�uje banka, kter� pot�ebuje specifick� symbol k z��tov�n� platby, pop�. Cesk� n�rodn� banka.
 * 
 * <p>Java class for PlatbaSpecifickySymbolType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PlatbaSpecifickySymbolType">
 *   &lt;simpleContent>
 *     &lt;restriction base="&lt;urn:cz:isvs:micr:schemas:CoreComponentTypes:v1>IdentifikatorType">
 *     &lt;/restriction>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PlatbaSpecifickySymbolType")
public class PlatbaSpecifickySymbolType
    extends IdentifikatorType
{


}
