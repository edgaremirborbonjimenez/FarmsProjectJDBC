package org.example.domain;

public class FarmSupplyProductInventory {
    Integer id;
    Integer amount;
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
        return "FarmSupplyProductInventory{" +
                "id=" + id +
                ", amount=" + amount +
                ", farm_id=" + farm_id +
                ", product_id=" + product_id +
                '}';
    }
}
