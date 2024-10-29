package org.example.mvc.views;

import org.example.domain.Animal;
import org.example.domain.Owner;
import org.example.mvc.interfaces.GenericController;
import org.example.mvc.interfaces.GenericModel;
import org.example.mvc.interfaces.GenericView;

import java.util.List;
import java.util.Scanner;

public class OwnerView implements GenericView<Owner> {

    GenericController<Owner> ownerController;
    GenericModel<Owner> ownerModel;
    Scanner scanner;

    public OwnerView(GenericController<Owner> ownerController,GenericModel<Owner> ownerModel){
        this.ownerController = ownerController;
        this.ownerModel = ownerModel;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void showCreated(Owner data) {
        if(data == null){
            System.out.println("Something went wrong creating the Owner");
            return;
        }

        System.out.println("Owner was created:");
        System.out.println("New owner :");
        System.out.println(data);
    }

    @Override
    public void showUpdated(Owner data) {
        if(data == null){
            System.out.println("Something went wrong updating the Owner");
            return;
        }
        System.out.println("Owner was updated:");
        System.out.println("New owner data :");
        System.out.println(data);
    }

    @Override
    public void showDeleted(Owner data) {
        if(data == null){
            System.out.println("Something went wrong deleting the Owner");
            return;
        }
        System.out.println("Owner was deleted :");
        System.out.println("Owner deleted :");
        System.out.println(data);
    }

    @Override
    public void showAll() {
        System.out.println("All owners :");
        List<Owner> owners = this.ownerModel.findAll();
        for(int i = 0; i<owners.size();i++){
            System.out.println((i+1)+". "+owners.get(i));
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
        Owner ownerFound = this.ownerModel.findById(id);
        if(ownerFound == null){
            System.out.println("Owner with id "+id+" not found");
            return;
        }
        System.out.println("Owner found with id : "+id);
        System.out.println(ownerFound);
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
                    createOwner();
                    break;
                case 2:
                    updateOwner();
                    break;
                case 3:
                    deleteOwnerById();
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

    public void createOwner(){
        boolean exit = false;
        Owner newOwner = new Owner();
        while (!exit){
            System.out.println("Insert owner name");
            scanner.nextLine();
            scanner.hasNextLine();
            String ownerName = scanner.nextLine();
            if(ownerName.isBlank()){
                System.out.println("Please enter a valid name value");
                continue;
            }

            System.out.println("Insert owner email");
            scanner.hasNextLine();
            String ownerEmail = scanner.nextLine();
            if(ownerEmail.isBlank()){
                System.out.println("Please enter a valid email value");
                continue;
            }

            System.out.println("Insert owner phone");
            scanner.hasNextLine();
            String ownerPhone = scanner.nextLine();
            if(ownerPhone.isBlank()){
                System.out.println("Please enter a valid phone value");
                continue;
            }

            newOwner.setFullName(ownerName);
            newOwner.setEmail(ownerEmail);
            newOwner.setPhone(ownerPhone);

            exit = true;
        }
        this.ownerModel.create(newOwner);
    }

    public void updateOwner(){
        boolean exit = false;
        List<Owner> ownerList = null;

        while (!exit){
            System.out.println("Select Owner to Update");

            ownerList = this.ownerModel.findAll();

            for(int i = 0; i<ownerList.size();i++){
                System.out.println((i+1)+". "+ownerList.get(i));
            }


            if(!scanner.hasNextInt()){
                scanner.next();
                System.out.println("Select a valid option");
                continue;
            }

            int option = scanner.nextInt();

            if(option<1 || option> ownerList.size()){
                System.out.println("Select a valid option");
                continue;
            }
            scanner.nextLine();
            System.out.println("Enter new Owner name");
            scanner.hasNextLine();
            String name = scanner.next();

            if(name.isBlank()){
                System.out.println("Please enter a valid name value");
                continue;
            }

            scanner.nextLine();
            System.out.println("Enter new Owner email");
            scanner.hasNextLine();
            String email = scanner.nextLine();

            if(email.isBlank()){
                System.out.println("Please enter a valid email value");
                continue;
            }

//            scanner.nextLine();
            System.out.println("Enter new Owner phone");
            scanner.hasNextLine();
            String phone = scanner.nextLine();

            if(phone.isBlank()){
                System.out.println("Please enter a valid phone value");
                continue;
            }

            Owner ownerUpdated = new Owner();
            ownerUpdated.setFullName(name);
            ownerUpdated.setEmail(email);
            ownerUpdated.setPhone(phone);
            this.ownerController.update(ownerList.get(option-1).getId(),ownerUpdated);
            exit = true;
            System.out.println();
        }
    }

    public void deleteOwnerById(){
        boolean exit = false;
        List<Owner> ownersList = null;
        while (!exit){
            System.out.println("Select Owner to Delete");

            ownersList = this.ownerModel.findAll();

            for(int i = 0; i<ownersList.size();i++){
                System.out.println((i+1)+". "+ownersList.get(i));
            }


            if(!scanner.hasNextInt()){
                scanner.next();
                System.out.println("Select a valid option");
                continue;
            }

            int option = scanner.nextInt();

            if(option<1 || option> ownersList.size()){
                System.out.println("Select a valid option");
                continue;
            }
            this.ownerController.delete(ownersList.get(option-1).getId());
            exit = true;
            System.out.println();
        }
    }
}
