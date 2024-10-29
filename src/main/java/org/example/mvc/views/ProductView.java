package org.example.mvc.views;

import org.example.domain.Animal;
import org.example.domain.Product;
import org.example.mvc.interfaces.GenericController;
import org.example.mvc.interfaces.GenericModel;
import org.example.mvc.interfaces.GenericView;
import org.example.mvc.interfaces.GenericViewModel;
import org.example.utils.enums.UnitMeasurement;

import java.util.List;
import java.util.Scanner;

public class ProductView implements GenericView<Product> {

    GenericController<Product> productController;
    GenericViewModel<Product> productModel;
    Scanner scanner;

    public ProductView(GenericController<Product> productController, GenericViewModel<Product> productModel){
        this.productController = productController;
        this.productModel = productModel;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void showCreated(Product data) {
        if(data == null){
            System.out.println("Something went wrong creating the Product");
            return;
        }

        System.out.println("Product was created:");
        System.out.println("New product :");
        System.out.println(data);
    }

    @Override
    public void showUpdated(Product data) {
        if(data == null){
            System.out.println("Something went wrong updating the Product");
            return;
        }
        System.out.println("Product was updated:");
        System.out.println("New product data :");
        System.out.println(data);
    }

    @Override
    public void showDeleted(Product data) {
        if(data == null){
            System.out.println("Something went wrong deleting the Product");
            return;
        }
        System.out.println("Product was deleted :");
        System.out.println("Product deleted :");
        System.out.println(data);
    }

    @Override
    public void showAll() {
        System.out.println("All product :");
        List<Product> products = this.productModel.findAll();
        for(int i = 0; i<products.size();i++){
            System.out.println((i+1)+". "+products.get(i));
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
        Product productFound = this.productModel.findById(id);
        if(productFound == null){
            System.out.println("Product with id "+id+" not found");
            return;
        }
        System.out.println("Product found with id : "+id);
        System.out.println(productFound);
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
                    createProduct();
                    break;
                case 2:
                    updateProduct();
                    break;
                case 3:
                    deleteProductById();
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

    public void createProduct(){
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
        this.productController.create(newProduct);
    }

    public void updateProduct(){
        UnitMeasurement[] units = UnitMeasurement.values();
        boolean exit = false;
        List<Product> productsList = null;

        while (!exit){
            System.out.println("Select Animal to Update");

            productsList = this.productModel.findAll();

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
            this.productController.update(productsList.get(option-1).getId(),productUpdated);
            exit = true;
            System.out.println();
        }
    }

    public void deleteProductById(){
        boolean exit = false;
        List<Product> productsList = null;
        while (!exit){
            System.out.println("Select Product to Delete");

            productsList = this.productModel.findAll();

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
            this.productController.delete(productsList.get(option-1).getId());
            exit = true;
            System.out.println();
        }
    }
}
