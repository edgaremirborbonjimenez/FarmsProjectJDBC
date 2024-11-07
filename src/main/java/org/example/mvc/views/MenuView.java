package org.example.mvc.views;

import org.example.mvc.controllers.MenuController;

import java.util.Scanner;

public class MenuView {

    MenuController menuController;
    static Scanner scanner;

    public MenuView(MenuController menuController){
        scanner = new Scanner(System.in);
        this.menuController = menuController;
    }

    public void selectDataSource(){
        boolean exit = false;

        while (!exit) {
            System.out.println("Select Data Source:");
            System.out.println("1. Database");
            System.out.println("2. XML");
            System.out.println("3. JAXB");
            System.out.println("4. JSON");
            System.out.println("5. MyBatis");
            System.out.println("6. Exit");
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
                    this.menuController.useDatabase();
                    selectEntity();
                    break;
                case 2:
                    System.out.println("XML Selected");
                    this.menuController.useXML();
                    selectEntity();
                    break;
                case 3:
                    System.out.println("JAXB Selected");
                    this.menuController.useJAXB();
                    selectEntity();
                    break;
                case 4:
                    System.out.println("JSON Selected");
                    this.menuController.useJSON();
                    selectEntity();
                    break;
                case 5:
                    System.out.println("MyBatis Selected");
                    this.menuController.useMyBatis();
                    selectEntity();
                    break;
                case 6:
                    System.out.println("Closing program...");
                    break;
                default:
                    System.out.println("Select a valid option");
            }
            System.out.println();
        }
    }

    public void selectEntity(){
        boolean exit = false;

        while (!exit){
            System.out.println("Select entity to use");
            System.out.println("1. Owner");
            System.out.println("2. Farm");
            System.out.println("3. Animal");
            System.out.println("4. Store");
            System.out.println("5. Product");
            System.out.println("7. Back to select data source");

            if(!scanner.hasNextInt()){
                System.out.println("Insert a valid option");
                scanner.next();
                continue;
            }
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Owner Selected");
                    this.menuController.goToOwnersCRUD();
                    break;
                case 2:
                    System.out.println("Farm Selected");
                    this.menuController.goToFarmsCRUD();
                    break;
                case 3:
                    System.out.println("Animal Selected");
                    this.menuController.goToAnimalsCRUD();
                    break;
                case 4:
                    System.out.println("Store Selected");
                    this.menuController.goToStoresCRUD();
                    break;
                case 5:
                    System.out.println("Product Selected");
                    this.menuController.goToProductsCRUD();
                    break;
                case 6:
                    System.out.println("Closing program...");
                    exit = true;
                    break;
                default:
                    System.out.println("Select a valid option");
            }

            System.out.println();
        }

    }
}
