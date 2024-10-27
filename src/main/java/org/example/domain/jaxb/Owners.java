package org.example.domain.jaxb;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.example.domain.Owner;

@XmlRootElement(name = "owners")
@XmlAccessorType(XmlAccessType.FIELD)
public class Owners {
    Owner[] owners;
    public Owners(){
        this.owners = new Owner[0];
    }

    public Owner[] getOwners() {
        return owners;
    }

    public void setOwners(Owner[] owners) {
        this.owners = owners;
    }

}
