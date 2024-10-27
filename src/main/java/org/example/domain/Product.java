package org.example.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.xml.bind.annotation.*;
import org.example.utils.enums.UnitMeasurement;

import java.util.List;

@XmlRootElement(name="product")
@XmlType(propOrder = {"id","name","price","unitMeasurement"})
@XmlAccessorType(XmlAccessType.FIELD)
@JsonPropertyOrder({"id","name","price","unitMeasurement"})
public class Product {
    @XmlAttribute
    @JsonProperty("id")
    int id;
    @JsonProperty("name")
    String name;
    @JsonProperty("price")
    double price;
    @JsonProperty("unitMeasurement")
    String unitMeasurement;

    public Product(){}

    public String getUnitMeasurement() {
        return unitMeasurement;
    }

    public void setUnitMeasurement(String unitMeasurement) {
        this.unitMeasurement = unitMeasurement;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", unitMeasurement=" + unitMeasurement +
                '}';
    }
}
