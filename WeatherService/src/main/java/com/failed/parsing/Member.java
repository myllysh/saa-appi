package com.failed.parsing;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="wfs:member")
@XmlAccessorType(XmlAccessType.FIELD)
public class Member {
    @XmlElement(name="omso:PointTimeSeriesObservation")
    private Observation observation;

    Member() {}

    public Observation getObservation() {
        return this.observation;
    }

    public void setObservation(Observation o) {
        this.observation = o;
    }
}
