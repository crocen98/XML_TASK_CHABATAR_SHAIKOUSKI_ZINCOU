//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0-b170531.0717 
//         See <a href="https://jaxb.java.net/">https://jaxb.java.net/</a> 
//         Any modifications to this file will be lost upon recompilation of the source schema. 
//         Generated on: 2019.01.14 at 02:47:37 PM MSK 
//


package com.javatr.entity.gem;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Preciousness.
 * 
 * <p>The following schema fragment specifies the expected         content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Preciousness"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="precious"/&gt;
 *     &lt;enumeration value="semiprecious"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "Preciousness")
@XmlEnum
public enum Preciousness {

    @XmlEnumValue("precious")
    PRECIOUS("precious"),
    @XmlEnumValue("semiprecious")
    SEMIPRECIOUS("semiprecious");
    private final String value;

    Preciousness(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Preciousness fromValue(String v) {
        for (Preciousness c: Preciousness.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
