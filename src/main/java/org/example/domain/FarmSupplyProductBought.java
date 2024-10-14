package org.example.domain;

import java.sql.Date;

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

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Integer getFarm_id() {
        return farm_id;
    }

    public void setFarm_id(Integer farm_id) {
        this.farm_id = farm_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

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
