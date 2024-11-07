package org.example.mvc.views;

import org.example.domain.Animal;
import org.example.domain.Store;
import org.example.mvc.interfaces.GenericController;
import org.example.mvc.interfaces.GenericView;
import org.example.mvc.interfaces.GenericViewModel;

import java.util.List;
import java.util.Scanner;

public class StoreView implements GenericView<Store> {

    GenericController<Store> storeController;
    GenericViewModel<Store> storeModel;
    Scanner scanner;

    public StoreView(GenericController<Store> storeController,GenericViewModel<Store> storeModel){
        this.storeController = storeController;
        this.storeModel = storeModel;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void showCreated(Store data) {
        if(data == null){
            System.out.println("Something went wrong creating the Store");
            return;
        }

        System.out.println("Store was created:");
        System.out.println("New store :");
        System.out.println(data);
    }

    @Override
    public void showUpdated(Store data) {
        if(data == null){
            System.out.println("Something went wrong updating the Store");
            return;
        }
        System.out.println("Store was updated:");
        System.out.println("New store data :");
        System.out.println(data);
    }

    @Override
    public void showDeleted(Store data) {
        if(data == null){
            System.out.println("Something went wrong deleting the Store");
            return;
        }
        System.out.println("Store was deleted :");
        System.out.println("Store deleted :");
        System.out.println(data);
    }

    @Override
    public void showAll() {
        System.out.println("All stores :");
        List<Store> stores = this.storeModel.findAll();
        for(int i = 0; i<stores.size();i++){
            System.out.println((i+1)+". "+stores.get(i));
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
        Store storeFound = this.storeModel.findById(id);
        if(storeFound == null){
            System.out.println("Store with id "+id+" not found");
            return;
        }
        System.out.println("Store found with id : "+id);
        System.out.println(storeFound);
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
                    createStore();
                    break;
                case 2:
                    updateStore();
                    break;
                case 3:
                    deleteStoreById();
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

    public void createStore(){
        boolean exit = false;
        Store store = new Store();
        while (!exit){
            System.out.println("Insert store name");
            scanner.nextLine();
            scanner.hasNextLine();
            String name = scanner.nextLine();
            if(name.isBlank()){
                System.out.println("Please enter a valid value");
                continue;
            }

            System.out.println("Insert store address");
            scanner.hasNextLine();
            String address = scanner.nextLine();
            if(address.isBlank()){
                System.out.println("Please enter a valid value");
                continue;
            }

            store.setName(name);
            store.setAddress(address);
            exit = true;
        }
        this.storeController.create((store));
    }

    public void updateStore(){
        boolean exit = false;
        List<Store> storesList = null;

        while (!exit){
            System.out.println("Select Store to Update");

            storesList = this.storeModel.findAll();

            for(int i = 0; i<storesList.size();i++){
                System.out.println((i+1)+". "+storesList.get(i));
            }


            if(!scanner.hasNextInt()){
                scanner.next();
                System.out.println("Select a valid option");
                continue;
            }

            int option = scanner.nextInt();

            if(option<1 || option> storesList.size()){
                System.out.println("Select a valid option");
                continue;
            }
            scanner.nextLine();
            System.out.println("Enter new store name");
            scanner.hasNextLine();
            String name = scanner.nextLine();

            if(name.isBlank()){
                System.out.println("Please enter a valid name value");
                continue;
            }

            System.out.println("Enter new store address");
            scanner.hasNextLine();
            String address = scanner.nextLine();

            if(address.isBlank()){
                System.out.println("Please enter a valid address value");
                continue;
            }
            Store storeUpdated = new Store();
            storeUpdated.setName(name);
            storeUpdated.setAddress(address);
            this.storeController.update(storesList.get(option-1).getId(),storeUpdated);
            exit = true;
            System.out.println();
        }
    }

    public void deleteStoreById(){

        boolean exit = false;
        List<Store> storesList = null;
        while (!exit){
            System.out.println("Select Store to Delete");

            storesList = this.storeModel.findAll();

            for(int i = 0; i<storesList.size();i++){
                System.out.println((i+1)+". "+storesList.get(i));
            }


            if(!scanner.hasNextInt()){
                scanner.next();
                System.out.println("Select a valid option");
                continue;
            }

            int option = scanner.nextInt();

            if(option<1 || option> storesList.size()){
                System.out.println("Select a valid option");
                continue;
            }
            this.storeController.delete(storesList.get(option-1).getId());
            exit = true;
            System.out.println();
        }
    }
}
