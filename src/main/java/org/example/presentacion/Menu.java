package org.example.presentacion;

import org.example.domain.*;
import org.example.utils.enums.UnitMeasurement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;


public class Menu {
    private static final Logger log = LoggerFactory.getLogger(Menu.class);
    static Scanner scanner = new Scanner(System.in);
    static Service service = new Service();

    public static void main(String[] args) {
        displayMenu();
    }

    public static void displayMenu(){
        boolean exit = false;
        boolean selectEntity = false;

        while (!exit) {
            System.out.println("Select Data Source:");
            System.out.println("1. Database");
            System.out.println("2. XML");
            System.out.println("3. JAXB");
            System.out.println("4. Exit");
            System.out.print("Opción: ");

            if(!scanner.hasNextInt()){
                System.out.println("Insert a valid option");
                scanner.next();
                continue;
            }
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Data Base Selected");
                    service.useDatabase();
                    selectEntity = true;
                    exit = true;
                    break;
                case 2:
                    System.out.println("XML Selected");
                    service.useXML();
                    selectEntity = true;
                    exit = true;
                    break;
                case 3:
                    System.out.println("JAXB Selected");
                    service.useJAXB();
                    selectEntity = true;
                    exit = true;
                    break;
                case 4:
                    System.out.println("Closing program...");
                    exit = true;
                    break;
                default:
                    System.out.println("Select a valid option");
            }
            System.out.println();
        }
        if(selectEntity){
            selectEntity();
        }
    }



    public static void selectEntity(){
        boolean exit = false;

        while (!exit){
            System.out.println("Select entity to use");
            System.out.println("1. Owner");
            System.out.println("2. Farm");
            System.out.println("3. Animal");
            System.out.println("4. Store");
            System.out.println("5. Product");
            System.out.println("6. Buy Product to Farm");
            System.out.println("7. Back to select data source");

            if(!scanner.hasNextInt()){
                System.out.println("Insert a valid option");
                scanner.next();
                continue;
            }
            int option = scanner.nextInt();

            if(option == 7){
                exit = true;
            }else{
                selectCRUD(option);
            }

            System.out.println();
        }

    }

    public static void selectCRUD(int entity){
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
                    switch (entity) {
                        case 1:
                            createOwner();
                            break;
                        case 2:
                            createFarm();
                            break;
                        case 3:
                            createAnimal();
                            break;
                        case 4:
                            createStore();
                            break;
                        case 5:
                            createProduct();
                            break;
                        case 6:
                            purchaseProductToFarm();
                            break;
                    }
                    break;
                case 2:
                    switch (entity) {
                        case 1:
                            updateOwner();
                            break;
                        case 2:
                            updateFarm();
                            break;
                        case 3:
                            updateAnimal();
                            break;
                        case 4:
                            updateStore();
                            break;
                        case 5:
                            updateProduct();
                            break;
                        case 6:
                            updateFarmPurchase();
                            break;
                    }
                    break;
                case 3:
                    switch (entity) {
                        case 1:
                            deleteOwnerById();
                            break;
                        case 2:
                            deleteFarmById();
                            break;
                        case 3:
                            deleteAnimalById();
                            break;
                        case 4:
                            deleteStoreById();
                            break;
                        case 5:
                            deleteProductById();
                            break;
                        case 6:
                            deleteFarmPurchase();
                    }
                    break;
                case 4:
                    switch (entity) {
                        case 1:
                            findAllOwners();
                            break;
                        case 2:
                            findAllFarms();
                            break;
                        case 3:
                            findAllAnimal();
                            break;
                        case 4:
                            findAllStores();
                            break;
                        case 5:
                            findAllProducts();
                            break;
                        case 6:
                            findAllFarmPurchases();
                            break;
                    }
                    break;
                case 5:
                    switch (entity) {
                        case 1:
                            findOwnerById();
                            break;
                        case 2:
                            findFarmById();
                            break;
                        case 3:
                            findAnimalById();
                            break;
                        case 4:
                            findStoreById();
                            break;
                        case 5:
                            findProductById();
                            break;
                        case 6:
                            findFarmPurchaseById();
                            break;
                    }
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

    public static void purchaseProductToFarm(){
        boolean exit = false;
        FarmSupplyProductBought f = new FarmSupplyProductBought();
        while (!exit){
            System.out.println("Select farm to make a purchase");
            List<Farm> farms = service.findAllFarms();
            for(int i = 0; i<farms.size();i++){
                System.out.println((i+1)+". "+farms.get(i));
            }
            if(!scanner.hasNextInt()){
                scanner.next();
                System.out.println("Please insert a  valid value");
                continue;
            }
            int farmSelected = scanner.nextInt();
            farmSelected--;
            if(farmSelected>=farms.size() || farmSelected<0){
                System.out.println("Please insert a  valid value");
                continue;
            }

            System.out.println("Select Product to buy");
            List<Product> products = service.findAllProducts();
            for(int i = 0; i<products.size();i++){
                System.out.println((i+1)+". "+products.get(i));
            }
            if(!scanner.hasNextInt()){
                scanner.next();
                System.out.println("Please insert a  valid value");
                continue;
            }
            int productSelected = scanner.nextInt();
            productSelected--;
            if(productSelected>=products.size() || productSelected<0){
                System.out.println("Please insert a  valid value");
                continue;
            }

            System.out.println("Insert amount");
            if(!scanner.hasNextInt()){
                scanner.next();
                System.out.println("Please insert a  valid value");
                continue;
            }
            int amount = scanner.nextInt();
            if(amount<=0){
                System.out.println("Please insert an amount greater than 0");
                continue;
            }

            Farm farm = farms.get(farmSelected);
            Product product = products.get(productSelected);

            f.setFarm_id(farm.getId());
            f.setProduct_id(product.getId());
            f.setPurchaseDate(new Date(System.currentTimeMillis()));
            f.setAmount(amount);
            f.setTotal(product.getPrice()*amount);
            exit = true;
        }
        service.createFarmSupplyProductBought(f);

        log.info("Supply bought successfully");
    }

    public static void updateFarmPurchase(){
        boolean exit = false;
        FarmSupplyProductBought update = new FarmSupplyProductBought();
        while (!exit){
            System.out.println("Select purchase to Update");
            List<FarmSupplyProductBought> f = service.findAllFarmSupplyProductBought();
            for(int i = 0; i<f.size();i++){
                System.out.println((i+1)+". "+f.get(i));
            }
            if(!scanner.hasNextInt()){
                scanner.next();
                System.out.println("Please insert a  valid value");
                continue;
            }
            int pruchaseSelected = scanner.nextInt();
            pruchaseSelected--;
            if(pruchaseSelected>=f.size() || pruchaseSelected<0){
                System.out.println("Please insert a  valid value");
                continue;
            }

            System.out.println("Select farm for the update");
            List<Farm> farms = service.findAllFarms();
            for(int i = 0; i<farms.size();i++){
                System.out.println((i+1)+". "+farms.get(i));
            }
            if(!scanner.hasNextInt()){
                scanner.next();
                System.out.println("Please insert a  valid value");
                continue;
            }
            int farmSelected = scanner.nextInt();
            farmSelected--;
            if(farmSelected>=farms.size() || farmSelected<0){
                System.out.println("Please insert a  valid value");
                continue;
            }

            System.out.println("Select Product for the update");
            List<Product> products = service.findAllProducts();
            for(int i = 0; i<products.size();i++){
                System.out.println((i+1)+". "+products.get(i));
            }
            if(!scanner.hasNextInt()){
                scanner.next();
                System.out.println("Please insert a  valid value");
                continue;
            }
            int productSelected = scanner.nextInt();
            productSelected--;
            if(productSelected>=products.size() || productSelected<0){
                System.out.println("Please insert a  valid value");
                continue;
            }

            System.out.println("Insert amount to update");
            if(!scanner.hasNextInt()){
                scanner.next();
                System.out.println("Please insert a  valid value");
                continue;
            }
            int amount = scanner.nextInt();
            if(amount<=0){
                System.out.println("Please insert an amount greater than 0");
                continue;
            }

            Farm farm = farms.get(farmSelected);
            Product product = products.get(productSelected);

            update.setId(f.get(pruchaseSelected).getId());
            update.setFarm_id(farm.getId());
            update.setProduct_id(product.getId());
            update.setPurchaseDate(new Date(System.currentTimeMillis()));
            update.setAmount(amount);
            update.setTotal(product.getPrice()*amount);
            exit = true;
        }
        service.updateFarmSupplyProductBoughtById(update.getId(),update);

        log.info("Purchase updated successfully");
    }

    public static void deleteFarmPurchase(){
        boolean exit = false;
        FarmSupplyProductBought delete = null;
        while (!exit){
            System.out.println("Select purchase to Update");
            List<FarmSupplyProductBought> f = service.findAllFarmSupplyProductBought();
            for(int i = 0; i<f.size();i++){
                System.out.println((i+1)+". "+f.get(i));
            }
            if(!scanner.hasNextInt()){
                scanner.next();
                System.out.println("Please insert a  valid value");
                continue;
            }
            int pruchaseSelected = scanner.nextInt();
            pruchaseSelected--;
            if(pruchaseSelected>=f.size() || pruchaseSelected<0){
                System.out.println("Please insert a  valid value");
                continue;
            }
            delete = f.get(pruchaseSelected);
            exit = true;
        }
        service.deleteFarmSupplyProductBoughtById(delete.getId());
        log.info("Purchase deleted successfully");
    }

    public static void findAllFarmPurchases(){
        List<FarmSupplyProductBought> f = service.findAllFarmSupplyProductBought();
        for(int i =0; i<f.size();i++){
            System.out.println((i+1)+". "+f.get(i));
        }
    }

    public static void findFarmPurchaseById(){
        boolean exit = false;
        int option = -1;

        while (!exit){
            System.out.println("Insert an id");
            if(!scanner.hasNextInt()){
                System.out.println("Please insert a valid value");
            }
            option = scanner.nextInt();
            exit = true;
        }
        System.out.println(service.findFarmSupplyProductBoughtById(option));
    }

    public static void createOwner(){
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
        service.createOwner(newOwner);
    }

    public static void updateOwner(){
        boolean exit = false;
        List<Owner> ownerList = null;

        while (!exit){
            System.out.println("Select Owner to Update");

            ownerList = service.findAllOwners();

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
            service.updateOwnerById(ownerList.get(option-1).getId(),ownerUpdated);
            exit = true;
            System.out.println();
        }
    }

    public static void deleteOwnerById(){
        boolean exit = false;
        List<Owner> ownersList = null;
        while (!exit){
            System.out.println("Select Owner to Delete");

            ownersList = service.findAllOwners();

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
            service.deleteOwnerById(ownersList.get(option-1).getId());
            exit = true;
            System.out.println();
        }
    }

    public static List<Owner> findAllOwners(){
        List<Owner> ownersList = service.findAllOwners();
        for(int i = 0; i<ownersList.size();i++){
            System.out.println((i+1)+". "+ownersList.get(i));
        }
        return ownersList;
    }

    public static void findOwnerById(){
        boolean exit = false;

        while(!exit){
            System.out.println("Insert an Owner Id");

            if(!scanner.hasNextInt()){
                scanner.next();
                System.out.println("Please insert a number integer");
                continue;
            }

            int option = scanner.nextInt();

            Owner ownerFound = service.findOwnerById(option);

            if(ownerFound==null){
                System.out.println("Owner with id "+option+" not found");
            }else{
                System.out.println("Owner found with id "+option+" :");
                System.out.println(ownerFound);
            }
            exit = true;
        }
    }


    public static void createFarm(){
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
            ownerList = findAllOwners();
            if(!scanner.hasNextInt()){
                System.out.println("Please select a valid option");
                continue;
            }
            int option = scanner.nextInt();

            newFarm.setName(farmName);
            newFarm.setAddress(farmAddress);
            newFarm.setOwner_id(ownerList.get(option-1).getId());
            exit = true;
        }
        service.createFarm(newFarm);
    }

    public static void updateFarm(){
        boolean exit = false;
        List<Farm> farmList = null;
        List<Owner> ownerList = null;

        while (!exit){
            System.out.println("Select Farm to Update");

            farmList = service.findAllFarms();

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
            ownerList = findAllOwners();
            if(!scanner.hasNextInt()){
                System.out.println("Please select a valid option");
                continue;
            }
            int newOwner = scanner.nextInt();
            if(newOwner<0 || newOwner>ownerList.size()){
                System.out.println("Please select a valid option");
                continue;
            }
            Farm newFarm = new Farm();
            newFarm.setName(name);
            newFarm.setAddress(address);
            newFarm.setOwner_id(ownerList.get(newOwner-1).getId());
            service.updateFarmById(farmList.get(option-1).getId(),newFarm);
            exit = true;
            System.out.println();
        }
    }

    public static void deleteFarmById(){
        boolean exit = false;
        List<Farm> farmsList = null;
        while (!exit){
            System.out.println("Select Farm to Delete");

            farmsList = service.findAllFarms();

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
            service.deleteFarmById(farmsList.get(option-1).getId());
            exit = true;
            System.out.println();
        }
    }

    public static void findAllFarms(){
        List<Farm> farmsList = service.findAllFarms();
        for(int i = 0; i<farmsList.size();i++){
            System.out.println((i+1)+". "+farmsList.get(i));
        }
    }

    public static void findFarmById(){

        boolean exit = false;

        while(!exit){
            System.out.println("Insert an Farm Id");

            if(!scanner.hasNextInt()){
                scanner.next();
                System.out.println("Please insert a number integer");
                continue;
            }

            int option = scanner.nextInt();

            Farm farmFound = service.findFarmById(option);

            if(farmFound==null){
                System.out.println("Farm with id "+option+" not found");
            }else{
                System.out.println("Farm found with id "+option+" :");
                System.out.println(farmFound);
            }
            exit = true;
        }

    }

    public static void createAnimal(){
        boolean exit = false;
        Animal newAnimal = new Animal();
        while (!exit){
            System.out.println("Insert animal name");
            scanner.nextLine();
//            scanner.hasNextLine();
            String animalName = scanner.nextLine();
            if(animalName.isBlank()){
                System.out.println("Please enter a valid value");
                continue;
            }

            newAnimal.setName(animalName);
            exit = true;
        }
        service.createAnimal(newAnimal);
    }

    public static void updateAnimal(){

        boolean exit = false;
        List<Animal> animalsList = null;

        while (!exit){
            System.out.println("Select Animal to Update");

            animalsList = service.findAllAnimals();

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
            service.updateAnimalById(animalsList.get(option-1).getId(),newAnimal);
            exit = true;
            System.out.println();
        }

    }

    public static void deleteAnimalById(){
        boolean exit = false;
        List<Animal> animalsList = null;
        while (!exit){
            System.out.println("Select Animal to Delete");

            animalsList = service.findAllAnimals();

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
            service.deleteAnimalById(animalsList.get(option-1).getId());
            exit = true;
            System.out.println();
        }
    }

    public static void findAllAnimal(){
        List<Animal> animalsList = service.findAllAnimals();
        for(int i = 0; i<animalsList.size();i++){
            System.out.println((i+1)+". "+animalsList.get(i));
        }
    }

    public static void findAnimalById(){

        boolean exit = false;

        while(!exit){
            System.out.println("Insert an Animal Id");

            if(!scanner.hasNextInt()){
                scanner.next();
                System.out.println("Please insert a number integer");
                continue;
            }

            int option = scanner.nextInt();

            Animal animalFound = service.findAnimalById(option);

            if(animalFound==null){
                System.out.println("Animal with id "+option+" not found");
            }else{
                System.out.println("Animal found with id "+option+" :");
                System.out.println(animalFound);
            }
            exit = true;
        }
    }

    public static void createProduct(){
        boolean exit = false;
        Product newProduct = new Product();
        UnitMeasurement[] units = UnitMeasurement.values();
        while (!exit){
            scanner.nextLine();
            System.out.println("Insert product name");
            String name = scanner.nextLine();
            if(name.isBlank()){
                System.out.println("Please enter a valid value");
                continue;
            }

            System.out.println("Insert product price");
            if(!scanner.hasNextDouble()){
                scanner.next();
                System.out.println("Please enter a valid value");
                continue;
            }
            double price = scanner.nextDouble();

            System.out.println("Select an unit measurement type");


            for(int i=0;i< units.length;i++){
                System.out.println(i+1+". "+units[i].unit);
            }
            if(!scanner.hasNextInt()){
                scanner.next();
                System.out.println("Please enter a valid value");
                continue;
            }
            int unitSelected = scanner.nextInt();
            if(unitSelected>units.length){
                System.out.println("Please enter a valid value");
                continue;
            }

            newProduct.setName(name);
            newProduct.setPrice(price);
            newProduct.setPrice(price);
            newProduct.setUnitMeasurement(units[unitSelected-1].unit);
            exit = true;
        }
        service.createProduct(newProduct);
    }

    public static void updateProduct(){
        UnitMeasurement[] units = UnitMeasurement.values();
        boolean exit = false;
        List<Product> productsList = null;

        while (!exit){
            System.out.println("Select Animal to Update");

            productsList = service.findAllProducts();

            for(int i = 0; i<productsList.size();i++){
                System.out.println((i+1)+". "+productsList.get(i));
            }


            if(!scanner.hasNextInt()){
                scanner.next();
                System.out.println("Select a valid option");
                continue;
            }

            int option = scanner.nextInt();

            if(option<1 || option> productsList.size()){
                System.out.println("Select a valid option");
                continue;
            }
            scanner.nextLine();
            System.out.println("Enter new product name");
            scanner.hasNextLine();
            String name = scanner.nextLine();

            if(name.isBlank()){
                System.out.println("Please enter a valid name value");
                continue;
            }

            System.out.println("Enter new product price");
            if(!scanner.hasNextDouble()){
                System.out.println("Please enter a valid price value");
                continue;
            }

            double price = scanner.nextDouble();

            System.out.println("Select a new  unit measurement type");

            for(int i=0;i< units.length;i++){
                System.out.println(i+1+". "+units[i].unit);
            }
            if(!scanner.hasNextInt()){
                scanner.next();
                System.out.println("Please enter a valid value");
                continue;
            }
            int unitSelected = scanner.nextInt();
            if(unitSelected>units.length){
                System.out.println("Please enter a valid value");
                continue;
            }

            Product productUpdated = new Product();
            productUpdated.setName(name);
            productUpdated.setPrice(price);
            productUpdated.setUnitMeasurement(units[unitSelected-1].unit);
            service.updateProductById(productsList.get(option-1).getId(),productUpdated);
            exit = true;
            System.out.println();
        }
    }

    public static void deleteProductById(){
        boolean exit = false;
        List<Product> productsList = null;
        while (!exit){
            System.out.println("Select Product to Delete");

            productsList = service.findAllProducts();

            for(int i = 0; i<productsList.size();i++){
                System.out.println((i+1)+". "+productsList.get(i));
            }


            if(!scanner.hasNextInt()){
                scanner.next();
                System.out.println("Select a valid option");
                continue;
            }

            int option = scanner.nextInt();

            if(option<1 || option> productsList.size()){
                System.out.println("Select a valid option");
                continue;
            }
            service.deleteProductById(productsList.get(option-1).getId());
            exit = true;
            System.out.println();
        }
    }

    public static void findAllProducts(){
        List<Product> productsList = service.findAllProducts();
        for(int i = 0; i<productsList.size();i++){
            System.out.println((i+1)+". "+productsList.get(i));
        }
    }

    public static void findProductById(){
        boolean exit = false;

        while(!exit){
            System.out.println("Insert an Product Id");

            if(!scanner.hasNextInt()){
                scanner.next();
                System.out.println("Please insert a number integer");
                continue;
            }

            int option = scanner.nextInt();

            Product productFound = service.findProductById(option);

            if(productFound==null){
                System.out.println("Product with id "+option+" not found");
            }else{
                System.out.println("Product found with id "+option+" :");
                System.out.println(productFound);
            }
            exit = true;
        }
    }

    public static void createStore(){
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
        service.createStore(store);
    }

    public static void updateStore(){
        boolean exit = false;
        List<Store> storesList = null;

        while (!exit){
            System.out.println("Select Store to Update");

            storesList = service.findAllStores();

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
            service.updateStoreById(storesList.get(option-1).getId(),storeUpdated);
            exit = true;
            System.out.println();
        }
    }

    public static void deleteStoreById(){

        boolean exit = false;
        List<Store> storesList = null;
        while (!exit){
            System.out.println("Select Store to Delete");

            storesList = service.findAllStores();

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
            service.deleteStoreById(storesList.get(option-1).getId());
            exit = true;
            System.out.println();
        }
    }

    public static void findAllStores(){
        List<Store> storesList = service.findAllStores();
        for(int i = 0; i<storesList.size();i++){
            System.out.println((i+1)+". "+storesList.get(i));
        }
    }

    public static void findStoreById(){
        boolean exit = false;

        while(!exit){
            System.out.println("Insert a Store Id");

            if(!scanner.hasNextInt()){
                scanner.next();
                System.out.println("Please insert a number integer");
                continue;
            }

            int option = scanner.nextInt();

            Store storeFound = service.findStoreById(option);

            if(storeFound==null){
                System.out.println("Store with id "+option+" not found");
            }else{
                System.out.println("Store found with id "+option+" :");
                System.out.println(storeFound);
            }
            exit = true;
        }
    }

}
