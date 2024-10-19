package org.example.domain.jaxb;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.example.domain.Store;

@XmlRootElement(name = "stores")
@XmlAccessorType(XmlAccessType.FIELD)
public class Stores {
    Store[] stores;
    public Stores(){}

    public Store[] getStores() {
        return stores;
    }

    public void setStores(Store[] stores) {
        this.stores = stores;
    }
}
