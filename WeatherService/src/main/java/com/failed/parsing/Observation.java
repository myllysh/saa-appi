package com.failed.parsing;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="omso:PointTimeSeriesObservation")
@XmlAccessorType(XmlAccessType.NONE)
public class Observation {
    @XmlElement(name="om:result")
    private MeasurementTimeSeries series;

    Observation() {}

    public MeasurementTimeSeries getSeries() {
        return this.series;
    }

    public void setSeries(MeasurementTimeSeries s) {
        this.series = s;
    }
}
