package org.example.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.example.utils.json.CustomDateDeserializer;
import org.example.utils.json.CustomDateSerializer;
import org.example.utils.marshallers.DateAdapter;

import java.sql.Date;

@XmlRootElement(name="farmSupplyProductBought")
@XmlType(propOrder = {"id","amount","total","purchaseDate","farm_id","product_id"})
@JsonPropertyOrder({"id","amount","total","purchaseDate","farm_id","product_id"})
public class FarmSupplyProductBought {
    @JsonProperty("id")
    Integer id;

    @JsonProperty("amount")
    Integer amount;

    @JsonProperty("total")
    Double total;

    @JsonProperty("purchaseDate")
    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    Date purchaseDate;

    @JsonProperty("farmId")
    Integer farm_id;

    @JsonProperty("productId")
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
