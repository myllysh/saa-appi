package com.failed.parsing;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="wml2:MeasurementTimeseries")
@XmlAccessorType(XmlAccessType.FIELD)
public class Point {
    @XmlElement(name="wml2:point")
    private MeasurementPoint measurementPoint;

    Point() {}

    public MeasurementPoint getMeasurementPoint() {
        return this.measurementPoint;
    }

    public void setMeasurementPoint(MeasurementPoint mp) {
        this.measurementPoint = mp;
    }
}
