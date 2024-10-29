package org.example.mvc.controllers;

import org.example.domain.Animal;
import org.example.mvc.interfaces.GenericController;
import org.example.mvc.interfaces.GenericModel;
import org.example.mvc.interfaces.GenericView;

public class AnimalController implements GenericController<Animal> {

    GenericModel<Animal> animalModel;
    GenericView<Animal> animalView;

    public AnimalController(GenericView<Animal> animalView,GenericModel<Animal> animalModel){
        this.animalModel = animalModel;
        this.animalView = animalView;
    }


    @Override
    public void create(Animal data) {
        Animal animalCreated = this.animalModel.create(data);
        this.animalView.showCreated(animalCreated);
        this.animalView.showMenu();
    }

    @Override
    public void update(int id, Animal data) {
        Animal animalUpdated = this.animalModel.update(id,data);
        this.animalView.showUpdated(animalUpdated);
        this.animalView.showMenu();
    }

    @Override
    public void delete(int id) {
        Animal animalDeleted = this.animalModel.delete(id);
        this.animalView.showDeleted(animalDeleted);
        this.animalView.showMenu();
    }
}
