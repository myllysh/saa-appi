package com.failed.parsing;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="wml2:point")
@XmlAccessorType(XmlAccessType.FIELD)
public class MeasurementPoint {
    @XmlElement(name="wml2:MeasurementTVP")
    private ValuePair valuePair;

    MeasurementPoint() {}

    public ValuePair getValuePair() {
        return this.valuePair;
    }

    public void setValuePair(ValuePair vp) {
        this.valuePair = vp;
    }
}
