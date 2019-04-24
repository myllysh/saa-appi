
package com.failed.parsing;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name="wfs:FeatureCollection")
//@XmlType(namespace="http://www.opengis.net/wfs/2.0")
@XmlAccessorType(XmlAccessType.NONE)
public class WeatherResultItem {
    @XmlElement(name="wfs:member")
    private Member member;

    WeatherResultItem() {}
}

