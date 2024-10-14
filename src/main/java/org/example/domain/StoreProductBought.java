package org.example.domain;

public class StoreProductBought {
    Integer id;
    Integer amount;
    Double total;
    Integer product_id;
    Integer store_id;

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

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getStore_id() {
        return store_id;
    }

    public void setStore_id(Integer store_id) {
        this.store_id = store_id;
    }

    @Override
    public String toString() {
        return "StoreProductBought{" +
                "id=" + id +
                ", amount=" + amount +
                ", total=" + total +
                ", product_id=" + product_id +
                ", store_id=" + store_id +
                '}';
    }
}
