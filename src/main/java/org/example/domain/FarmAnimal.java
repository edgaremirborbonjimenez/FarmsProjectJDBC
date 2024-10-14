package org.example.domain;

public class FarmAnimal {
    Integer id;
    Integer amount;
    Integer farm_id;
    Integer animal_id;

    public FarmAnimal(){}

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

    public Integer getAnimal_id() {
        return animal_id;
    }

    public void setAnimal_id(Integer animal_id) {
        this.animal_id = animal_id;
    }

    @Override
    public String toString() {
        return "FarmAnimal{" +
                "amount=" + amount +
                ", farm_id=" + farm_id +
                ", animal_id=" + animal_id +
                '}';
    }
}
