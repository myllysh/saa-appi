package com.failed.parsing;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="om:result")
@XmlAccessorType(XmlAccessType.FIELD)
public class MeasurementTimeSeries {
    @XmlElement(name="wml2:MeasurementTimeseries")
    private Point point;

    MeasurementTimeSeries() {}

    public Point getPoint() {
        return this.point;
    }

    public void setPoint(Point p) {
        this.point = p;
    }
}
