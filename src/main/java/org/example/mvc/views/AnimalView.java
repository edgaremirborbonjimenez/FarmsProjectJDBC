package org.example.mvc.views;

import org.example.domain.Animal;
import org.example.mvc.interfaces.GenericController;
import org.example.mvc.interfaces.GenericView;
import org.example.mvc.interfaces.GenericViewModel;

import java.util.List;
import java.util.Scanner;

public class AnimalView implements GenericView<Animal> {

    GenericController<Animal> animalController;
    GenericViewModel<Animal> animalModel;
    Scanner scanner;

    public AnimalView(GenericController<Animal> animalController,GenericViewModel<Animal> animalModel){
        this.animalController = animalController;
        this.animalModel = animalModel;
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
        System.out.println("All animals :");
        List<Animal> animals = this.animalModel.findAll();
        for(int i = 0; i<animals.size();i++){
            System.out.println((i+1)+". "+animals.get(i));
        }
    }

    @Override
    public void showById() {
        boolean exit = false;
        int id = -1;
        while (!exit){
            System.out.println("Enter an Id :");
            if(!scanner.hasNextInt()){
                scanner.next();
                System.out.println("Please Enter a valid id");
            }
            id = scanner.nextInt();
            exit = true;
        }
        Animal animalFound = this.animalModel.findById(id);
        if(animalFound == null){
            System.out.println("Animal with id "+id+" not found");
            return;
        }
        System.out.println("Animal found with id : "+id);
        System.out.println(animalFound);
    }

    @Override
    public void showMenu() {
        boolean exit = false;

        while (!exit){
            System.out.println("What do you want to do?");
            System.out.println("1. Create");
            System.out.println("2. Update");
            System.out.println("3. Delete");
            System.out.println("4. Find All");
            System.out.println("5. Find By Id");
            System.out.println("6. Back to select Entity");

            if(!scanner.hasNextInt()){
                System.out.println("Insert a valid option");
                scanner.next();
                continue;
            }

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    createAnimal();
                    break;
                case 2:
                    updateAnimal();
                    break;
                case 3:
                    deleteAnimalById();
                    break;
                case 4:
                    showAll();
                    break;
                case 5:
                    showById();
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Select a valid option");
            }
            System.out.println();
        }
    }

    public void createAnimal(){
        boolean exit = false;
        Animal newAnimal = new Animal();
        while (!exit){
            System.out.println("Insert animal name");
            scanner.nextLine();
            String animalName = scanner.nextLine();
            if(animalName.isBlank()){
                System.out.println("Please enter a valid value");
                continue;
            }
            newAnimal.setName(animalName);
            exit = true;
        }
        this.animalController.create(newAnimal);
    }

    public void updateAnimal(){

        boolean exit = false;
        List<Animal> animalsList = null;

        while (!exit){
            System.out.println("Select Animal to Update");

            animalsList = this.animalModel.findAll();

            for(int i = 0; i<animalsList.size();i++){
                System.out.println((i+1)+". "+animalsList.get(i));
            }


            if(!scanner.hasNextInt()){
                scanner.next();
                System.out.println("Select a valid option");
                continue;
            }

            int option = scanner.nextInt();

            if(option<1 || option> animalsList.size()){
                System.out.println("Select a valid option");
                continue;
            }
            scanner.nextLine();
            System.out.println("Enter new animal name");
            scanner.hasNextLine();
            String name = scanner.nextLine();

            if(name.isBlank()){
                System.out.println("Please enter a valid value");
                continue;
            }

            Animal newAnimal = new Animal();
            newAnimal.setName(name);
            this.animalController.update(animalsList.get(option-1).getId(),newAnimal);
            exit = true;
            System.out.println();
        }
    }

    public void deleteAnimalById(){
        boolean exit = false;
        List<Animal> animalsList = null;
        while (!exit){
            System.out.println("Select Animal to Delete");

            animalsList = this.animalModel.findAll();

            for(int i = 0; i<animalsList.size();i++){
                System.out.println((i+1)+". "+animalsList.get(i));
            }


            if(!scanner.hasNextInt()){
                scanner.next();
                System.out.println("Select a valid option");
                continue;
            }

            int option = scanner.nextInt();

            if(option<1 || option> animalsList.size()){
                System.out.println("Select a valid option");
                continue;
            }
            this.animalController.delete(animalsList.get(option-1).getId());
            exit = true;
            System.out.println();
        }
    }

}
