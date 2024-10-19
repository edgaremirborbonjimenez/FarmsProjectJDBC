package org.example.domain;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name="store")
@XmlType(propOrder = {"id","name","address"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Store {
    @XmlAttribute
    int id;
    String name;
    String address;

    public Store(){}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
