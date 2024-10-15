package org.example.presentacion;

import org.example.daos.jdbc.AnimalsIDAO;
import org.example.interfaces.IConnection;
import org.example.services.*;
import org.example.utils.connection.HikariCPImplementation;

import java.sql.Connection;
import java.util.Scanner;


public class Menu {

    static AnimalService animalService;
    static FarmService farmService;
    static FarmSupplyProductInventoryService farmSupplyProductInventoryService;
    static FarmSupplyProductBoughtService farmSupplyProductBoughtService;
    static OwnerService ownerService;
    static ProductService productService;
    static StoreProductBoughtService storeProductBoughtService;
    static StoreService storeService;


    public static void main(String[] args) {
        displayMenu();
    }

    public static void displayMenu(){
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Select Data Source:");
            System.out.println("1. Database");
            System.out.println("2. XML");
            System.out.println("3. Exit");
            System.out.print("Opción: ");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Data Base Selected");
                    useDatabase();
                    break;
                case 2:
                    System.out.println("XML Selected");
                    useXML();
                    break;
                case 3:
                    System.out.println("Closing program...");
                    exit = true;
                    break;
                default:
                    System.out.println("Select a valid option");
            }
            System.out.println();
        }
        scanner.close();
    }

    public static void useDatabase() {
        IConnection<Connection> hikari = HikariCPImplementation.getInstance();
        hikari.setPoolSize(5);
        AnimalsIDAO animalsIDAO = AnimalsIDAO.getInstance();
        animalsIDAO.setPoolConnection(hikari);

    }

    public static void useXML() {
        // Aquí colocas la lógica para trabajar con XML
        System.out.println("Manejando datos con XML...");
        // Lógica de lectura y escritura de archivos XML
    }
}
