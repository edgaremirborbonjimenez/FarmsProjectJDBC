package org.example.domain.jaxb;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.example.domain.Animal;

@XmlRootElement(name = "animals")
@XmlAccessorType(XmlAccessType.FIELD)
public class Animals {
    Animal[] animals;
    public Animals(){
        this.animals = new Animal[0];
    }

    public Animal[] getAnimals() {
        return animals;
    }

    public void setAnimals(Animal[] animals) {
        this.animals = animals;
    }
}
