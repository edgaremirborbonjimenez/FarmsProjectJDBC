package org.example.domain.jaxb;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.example.domain.FarmSupplyProductBought;

@XmlRootElement(name = "farmSuppliesProductsBought")
@XmlAccessorType(XmlAccessType.FIELD)
public class FarmSuppliesProductBought {
    FarmSupplyProductBought [] farmSuppliesProductsBought;
    public FarmSuppliesProductBought(){
        this.farmSuppliesProductsBought = new FarmSupplyProductBought[0];
    }

    public FarmSupplyProductBought[] getFarmSuppliesProductsBought() {
        return farmSuppliesProductsBought;
    }

    public void setFarmSuppliesProductsBought(FarmSupplyProductBought[] farmSuppliesProductsBought) {
        this.farmSuppliesProductsBought = farmSuppliesProductsBought;
    }
}
