package org.example.domain;

import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement(name="farm")
@XmlType(propOrder = {"id","name","address","owner_id"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Farm {
    @XmlAttribute
    int id;
    String name;
    String address;
    @XmlElement(name = "ownerId")
    int owner_id;
    @XmlTransient
    List<Product> products;

    public Farm(){}

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    @Override
    public String toString() {
        return "Farm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", owner_id=" + owner_id +
                '}';
    }
}
