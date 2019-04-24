package com.failed.parsing;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="wml2:MeasurementTVP")
@XmlAccessorType(XmlAccessType.FIELD)
public class ValuePair {
    @XmlElement(name="wml2:time")
    private String time;

    @XmlElement(name="wml2:value")
    private Integer value;

    ValuePair() {}

    public String getTime() {
        return this.time;
    }

    public void setTime(String t) {
        this.time = t;
    }

    public Integer getValue() {
        return this.value;
    }

    public void setValue(Integer v) {
        this.value = v;
    }
}
