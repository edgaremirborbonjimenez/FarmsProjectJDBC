package org.example.mvc.views;

import org.example.domain.Animal;
import org.example.mvc.interfaces.GenericController;
import org.example.mvc.interfaces.GenericView;
import org.example.mvc.interfaces.GenericViewModel;

import java.util.Scanner;

public class AnimalView implements GenericView<Animal> {

    GenericController<Animal> animalController;
    GenericViewModel<Animal> 
    Scanner scanner;

    public AnimalView(GenericController<Animal> animalController){
        this.animalController = animalController;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void showCreated(Animal data) {
        if(data == null){
            System.out.println("Something went wrong creating the Animal");
            return;
        }

        System.out.println("Animal was created:");
        System.out.println("New animal :");
        System.out.println(data);
    }

    @Override
    public void showUpdated(Animal data) {
        if(data == null){
            System.out.println("Something went wrong updating the Animal");
            return;
        }
        System.out.println("Animal was updated:");
        System.out.println("New animal data :");
        System.out.println(data);
    }

    @Override
    public void showDeleted(Animal data) {
        if(data == null){
            System.out.println("Something went wrong deleting the Animal");
            return;
        }
        System.out.println("Animal was deleted :");
        System.out.println("Animal deleted :");
        System.out.println(data);
    }

    @Override
    public void showAll() {
        for(int i = 0; i<){

        }
    }

    @Override
    public void showById() {

    }

    @Override
    public void showMenu() {

    }
}
