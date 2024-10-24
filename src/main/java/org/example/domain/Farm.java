package org.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement(name="farm")
@XmlType(propOrder = {"id","name","address","owner_id"})
@XmlAccessorType(XmlAccessType.FIELD)
@JsonPropertyOrder({"id","name","address","owner_id"})
public class Farm {
    @XmlAttribute
    @JsonProperty("id")
    int id;
    @JsonProperty("name")
    String name;
    @JsonProperty("address")
    String address;
    @JsonProperty("ownerId")
    @XmlElement(name = "ownerId")
    int owner_id;
    @JsonIgnore
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
