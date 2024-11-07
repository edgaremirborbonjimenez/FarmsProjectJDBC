package org.example.mvc.views;

import org.example.domain.Animal;
import org.example.domain.Farm;
import org.example.domain.Owner;
import org.example.mvc.interfaces.GenericController;
import org.example.mvc.interfaces.GenericView;
import org.example.mvc.interfaces.GenericViewModel;

import java.util.List;
import java.util.Scanner;

public class FarmView implements GenericView<Farm> {

    GenericController<Farm> farmController;
    GenericViewModel<Farm> farmModel;
    GenericViewModel<Owner> ownerModel;
    Scanner scanner;

    public FarmView(GenericController<Farm> farmController,GenericViewModel<Farm> farmModel){
        this.farmController = farmController;
        this.farmModel = farmModel;
        scanner = new Scanner(System.in);
    }

    public void setOwnerModel(GenericViewModel<Owner> ownerModel) {
        this.ownerModel = ownerModel;
    }

    @Override
    public void showCreated(Farm data) {
        if(data == null){
            System.out.println("Something went wrong creating the Farm");
            return;
        }

        System.out.println("Farm was created:");
        System.out.println("New Farm :");
        System.out.println(data);
    }

    @Override
    public void showUpdated(Farm data) {
        if(data == null){
            System.out.println("Something went wrong updating the Farm");
            return;
        }
        System.out.println("Farm was updated:");
        System.out.println("New farm data :");
        System.out.println(data);
    }

    @Override
    public void showDeleted(Farm data) {
        if(data == null){
            System.out.println("Something went wrong deleting the Farm");
            return;
        }
        System.out.println("Farm was deleted :");
        System.out.println("Farm deleted :");
        System.out.println(data);
    }

    @Override
    public void showAll() {
        System.out.println("All farms :");
        List<Farm> farms = this.farmModel.findAll();
        for(int i = 0; i<farms.size();i++){
            System.out.println((i+1)+". "+farms.get(i));
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
        Farm farmFound = this.farmModel.findById(id);
        if(farmFound == null){
            System.out.println("Farm with id "+id+" not found");
            return;
        }
        System.out.println("Farm found with id : "+id);
        System.out.println(farmFound);
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
                    createFarm();
                    break;
                case 2:
                    updateFarm();
                    break;
                case 3:
                    deleteFarmById();
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

    public void createFarm(){
        boolean exit = false;
        Farm newFarm = new Farm();
        List<Owner> ownerList = null;
        while (!exit){
            System.out.println("Insert farm name");
            scanner.nextLine();
            scanner.hasNextLine();
            String farmName = scanner.nextLine();
            if(farmName.isBlank()){
                System.out.println("Please enter a valid name value");
                continue;
            }

            System.out.println("Insert farm address");
            scanner.hasNextLine();
            String farmAddress = scanner.nextLine();
            if(farmAddress.isBlank()){
                System.out.println("Please enter a valid address value");
                continue;
            }

            System.out.println("Select farm´s owner");
            ownerList = ownerModel.findAll();

            for(int i = 0; i<ownerList.size();i++){
                System.out.println((i+1)+". "+ownerList.get(i));
            }

            if(!scanner.hasNextInt()){
                System.out.println("Please select a valid option");
                continue;
            }

            int option = scanner.nextInt();

            if(option>ownerList.size() || option <= 0){
                System.out.println("Please select a valid option");
                continue;
            }

            newFarm.setName(farmName);
            newFarm.setAddress(farmAddress);
            newFarm.setOwner_id(ownerList.get(option-1).getId());
            exit = true;
        }
        farmController.create(newFarm);
    }

    public void updateFarm(){
        boolean exit = false;
        List<Farm> farmList = null;
        List<Owner> ownerList = null;

        while (!exit){
            System.out.println("Select Farm to Update");

            farmList = this.farmModel.findAll();

            for(int i = 0; i<farmList.size();i++){
                System.out.println((i+1)+". "+farmList.get(i));
            }


            if(!scanner.hasNextInt()){
                scanner.next();
                System.out.println("Select a valid option");
                continue;
            }

            int option = scanner.nextInt();

            if(option<1 || option> farmList.size()){
                System.out.println("Select a valid option");
                continue;
            }
            scanner.nextLine();
            System.out.println("Enter new Farm name");
            scanner.hasNextLine();
            String name = scanner.nextLine();

            if(name.isBlank()){
                System.out.println("Please enter a valid name value");
                continue;
            }

            System.out.println("Enter new Farm address");
            scanner.hasNextLine();
            String address = scanner.nextLine();

            if(address.isBlank()){
                System.out.println("Please enter a valid address value");
                continue;
            }

            System.out.println("Select new farm´s owner");
            ownerList = this.ownerModel.findAll();

            for(int i = 0; i<ownerList.size();i++){
                System.out.println((i+1)+". "+ownerList.get(i));
            }

            if(!scanner.hasNextInt()){
                System.out.println("Please select a valid option");
                continue;
            }

            int newOwner = scanner.nextInt();

            if(newOwner<=0 || newOwner>ownerList.size()){
                System.out.println("Please select a valid option");
                continue;
            }
            Farm newFarm = new Farm();
            newFarm.setName(name);
            newFarm.setAddress(address);
            newFarm.setOwner_id(ownerList.get(newOwner-1).getId());
            this.farmController.update(farmList.get(option-1).getId(),newFarm);
            exit = true;
            System.out.println();
        }
    }

    public void deleteFarmById(){
        boolean exit = false;
        List<Farm> farmsList = null;
        while (!exit){
            System.out.println("Select Farm to Delete");

            farmsList = this.farmModel.findAll();

            for(int i = 0; i<farmsList.size();i++){
                System.out.println((i+1)+". "+farmsList.get(i));
            }


            if(!scanner.hasNextInt()){
                scanner.next();
                System.out.println("Select a valid option");
                continue;
            }

            int option = scanner.nextInt();

            if(option<1 || option> farmsList.size()){
                System.out.println("Select a valid option");
                continue;
            }
            this.farmController.delete(farmsList.get(option-1).getId());
            exit = true;
            System.out.println();
        }
    }
}
