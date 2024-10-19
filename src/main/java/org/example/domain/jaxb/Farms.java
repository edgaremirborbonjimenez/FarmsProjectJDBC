package org.example.domain.jaxb;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.example.domain.Farm;

@XmlRootElement(name = "farms")
@XmlAccessorType(XmlAccessType.FIELD)
public class Farms {
    Farm[] farms;
    public Farms(){}

    public Farm[] getFarms() {
        return farms;
    }

    public void setFarms(Farm[] farms) {
        this.farms = farms;
    }
}
