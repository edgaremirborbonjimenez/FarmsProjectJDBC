package org.example.domain;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.example.utils.marshallers.DateAdapter;

import java.sql.Date;

@XmlRootElement(name="farmSupplyProductBought")
@XmlType(propOrder = {"id","amount","total","purchaseDate","farm_id","product_id"})
public class FarmSupplyProductBought {
    Integer id;
    Integer amount;
    Double total;
    Date purchaseDate;
    Integer farm_id;
    Integer product_id;

    public Integer getId() {
        return id;
    }

    @XmlAttribute
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    @XmlJavaTypeAdapter(DateAdapter.class)
    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Integer getFarm_id() {
        return farm_id;
    }

    @XmlElement(name = "farmId")
    public void setFarm_id(Integer farm_id) {
        this.farm_id = farm_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    @XmlElement(name = "productId")
    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    @Override
    public String toString() {
        return "FarmSupplyProductBought{" +
                "id=" + id +
                ", amount=" + amount +
                ", total=" + total +
                ", purchaseDate=" + purchaseDate +
                ", farm_id=" + farm_id +
                ", product_id=" + product_id +
                '}';
    }
}
