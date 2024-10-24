package org.example.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.xml.bind.annotation.*;

@XmlRootElement(name="store")
@XmlType(propOrder = {"id","name","address"})
@XmlAccessorType(XmlAccessType.FIELD)
@JsonPropertyOrder({"id","name","address"})
public class Store {
    @XmlAttribute
    @JsonProperty("id")
    int id;
    @JsonProperty("name")
    String name;
    @JsonProperty("address")
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
