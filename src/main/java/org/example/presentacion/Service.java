package org.example.presentacion;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.daos.jdbc.*;
import org.example.daos.xml.*;
import org.example.domain.*;
import org.example.interfaces.IConnection;
import org.example.services.*;
import org.example.utils.connection.HikariCPImplementation;
import org.example.utils.saxhandlers.*;

import java.io.FileReader;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;

public class Service {
    private static final Logger logger = LogManager.getLogger();

    static AnimalService animalService;
    static FarmService farmService;
    static FarmSupplyProductInventoryService farmSupplyProductInventoryService;
    static FarmSupplyProductBoughtService farmSupplyProductBoughtService;
    static OwnerService ownerService;
    static ProductService productService;
    static StoreProductBoughtService storeProductBoughtService;
    static StoreService storeService;
    static FarmAnimalService farmAnimalService;

    public Service(){}

    public void useDatabase() {
        IConnection<Connection> hikari = HikariCPImplementation.getInstance();
        hikari.setPoolSize(5);
        AnimalsIDAO animalsIDAO = AnimalsIDAO.getInstance();
        animalsIDAO.setPoolConnection(hikari);
        animalService  = new AnimalService(animalsIDAO);
        FarmsIDAO farmsIDAO = FarmsIDAO.getInstance();
        farmsIDAO.setPoolConnection(hikari);
        farmService = new FarmService(farmsIDAO);
        OwnersIDAO ownersIDAO = OwnersIDAO.getInstance();
        ownersIDAO.setPoolConnection(hikari);
        ownerService = new OwnerService(ownersIDAO);
        ProductsIDAO productsIDAO = ProductsIDAO.getInstance();
        productsIDAO.setPoolConnection(hikari);
        productService = new ProductService(productsIDAO);
        StoresIDAO storesIDAO = StoresIDAO.getInstance();
        storesIDAO.setPoolConnection(hikari);
        storeService = new StoreService(storesIDAO);
        FarmsAnimalsIDAO farmsAnimalsIDAO = FarmsAnimalsIDAO.getInstance();
        farmsAnimalsIDAO.setPoolConnection(hikari);
        farmAnimalService = new FarmAnimalService(farmsAnimalsIDAO);
        FarmsSupplyProductsBoughtIDAO farmsSupplyProductsBoughtIDAO = FarmsSupplyProductsBoughtIDAO.getInstance();
        farmsSupplyProductsBoughtIDAO.setPoolConnection(hikari);
        farmSupplyProductBoughtService = new FarmSupplyProductBoughtService(farmsSupplyProductsBoughtIDAO);
        FarmsSupplyProductsInventoryIDAO farmsSupplyProductsInventoryIDAO = FarmsSupplyProductsInventoryIDAO.getInstance();
        farmsSupplyProductsInventoryIDAO.setPoolConnection(hikari);
        farmSupplyProductInventoryService = new FarmSupplyProductInventoryService(farmsSupplyProductsInventoryIDAO);
        StoresProductsBoughtDAO storesProductsBoughtDAO = StoresProductsBoughtDAO.getInstance();
        storesProductsBoughtDAO.setPoolConnection(hikari);
        storeProductBoughtService = new StoreProductBoughtService(storesProductsBoughtDAO);
        logger.info("Data Source with the Data Base assigned");
    }

    public void useXML() {

        Properties properties = new Properties();
        try (FileReader input = new FileReader("src/main/resources/env.properties")){
            properties.load(input);

            animalService = new AnimalService(new AnimalsXML(properties.getProperty("xml.animal"),new AnimalsHandler()));
            ownerService = new OwnerService(new OwnerXML(properties.getProperty("xml.owner"),new OwnerHandler()));
            farmService = new FarmService(new FarmsXML(properties.getProperty("xml.farm"),new FarmHandler()));
            productService = new ProductService(new ProductsXML(properties.getProperty("xml.product"), new ProducHandler()));
            storeService = new StoreService(new StoresXML(properties.getProperty("xml.store"), new StoreHandler()));
            farmAnimalService = new FarmAnimalService(new FarmsAnimalsXML(properties.getProperty("xml.farm.animal"), new FarmsAnimalsHandler()));
            farmSupplyProductBoughtService = new FarmSupplyProductBoughtService(new FarmsSupplyProductsBoughtXML(properties.getProperty("xml.farm.supply.product.bought"), new FarmsSupplyProductBoughtHandler()));
            farmSupplyProductInventoryService = new FarmSupplyProductInventoryService(new FarmsSupplyProductsInventoryXML(properties.getProperty("xml.supply.product.inventory"), new FarmSupplyProductInventoryHandler()));
            storeProductBoughtService = new StoreProductBoughtService(new StoresProductsBoughtXML(properties.getProperty("xml.store.product.bought"), new StoreProductBoughtHandler()));
            logger.info("Data Source with the XML assigned");

        }catch (Exception e){
            logger.error(e.getMessage());
        }

    }

    public void createAnimal(Animal animal){
        try{
            animalService.insert(animal);
        }catch(Exception e){
            logger.error(e.getMessage());
        }
    }
    public void updateAnimalById(int id, Animal data){
        try{
            animalService.updateById(id,data);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    public void deleteAnimalById(int id){
        try{
            animalService.deleteById(id);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    public List<Animal> findAllAnimals(){
        try{
            return animalService.findAll();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    public Animal findAnimalById(int id){
        try{
            return animalService.findById(id);
        }catch (Exception e){}
        return null;
    }

    public void createFarm(Farm farm) {
        try {
            farmService.insert(farm);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void updateFarmById(int id, Farm data) {
        try {
            farmService.updateById(id, data);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void deleteFarmById(int id) {
        try {
            farmService.deleteById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public List<Farm> findAllFarms() {
        try {
            return farmService.findAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public Farm findFarmById(int id) {
        try {
            return farmService.findById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public void createFarmSupplyProductInventory(FarmSupplyProductInventory inventory) {
        try {
            farmSupplyProductInventoryService.insert(inventory);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void updateFarmSupplyProductInventoryById(int id, FarmSupplyProductInventory data) {
        try {
            farmSupplyProductInventoryService.updateById(id, data);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void deleteFarmSupplyProductInventoryById(int id) {
        try {
            farmSupplyProductInventoryService.deleteById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public List<FarmSupplyProductInventory> findAllFarmSupplyProductInventories() {
        try {
            return farmSupplyProductInventoryService.findAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public FarmSupplyProductInventory findFarmSupplyProductInventoryById(int id) {
        try {
            return farmSupplyProductInventoryService.findById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }
    public void createFarmSupplyProductBought(FarmSupplyProductBought productBought) {
        try {
            farmSupplyProductBoughtService.insert(productBought);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void updateFarmSupplyProductBoughtById(int id, FarmSupplyProductBought data) {
        try {
            farmSupplyProductBoughtService.updateById(id, data);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void deleteFarmSupplyProductBoughtById(int id) {
        try {
            farmSupplyProductBoughtService.deleteById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public List<FarmSupplyProductBought> findAllFarmSupplyProductBought() {
        try {
            return farmSupplyProductBoughtService.findAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public FarmSupplyProductBought findFarmSupplyProductBoughtById(int id) {
        try {
            return farmSupplyProductBoughtService.findById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public void createOwner(Owner owner) {
        try {
            ownerService.insert(owner);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void updateOwnerById(int id, Owner data) {
        try {
            ownerService.updateById(id, data);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void deleteOwnerById(int id) {
        try {
            ownerService.deleteById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public List<Owner> findAllOwners() {
        try {
            return ownerService.findAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public Owner findOwnerById(int id) {
        try {
            return ownerService.findById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public void createProduct(Product product) {
        try {
            productService.insert(product);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void updateProductById(int id, Product data) {
        try {
            productService.updateById(id, data);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void deleteProductById(int id) {
        try {
            productService.deleteById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public List<Product> findAllProducts() {
        try {
            return productService.findAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public Product findProductById(int id) {
        try {
            return productService.findById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public void createStoreProductBought(StoreProductBought productBought) {
        try {
            storeProductBoughtService.insert(productBought);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void updateStoreProductBoughtById(int id, StoreProductBought data) {
        try {
            storeProductBoughtService.updateById(id, data);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void deleteStoreProductBoughtById(int id) {
        try {
            storeProductBoughtService.deleteById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public List<StoreProductBought> findAllStoreProductBoughts() {
        try {
            return storeProductBoughtService.findAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public StoreProductBought findStoreProductBoughtById(int id) {
        try {
            return storeProductBoughtService.findById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public void createStore(Store store) {
        try {
            storeService.insert(store);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void updateStoreById(int id, Store data) {
        try {
            storeService.updateById(id, data);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void deleteStoreById(int id) {
        try {
            storeService.deleteById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public List<Store> findAllStores() {
        try {
            return storeService.findAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public Store findStoreById(int id) {
        try {
            return storeService.findById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public void createFarmAnimal(FarmAnimal farmAnimal) {
        try {
            farmAnimalService.insert(farmAnimal);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void updateFarmAnimalById(int id, FarmAnimal data) {
        try {
            farmAnimalService.updateById(id, data);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void deleteFarmAnimalById(int id) {
        try {
            farmAnimalService.deleteById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public List<FarmAnimal> findAllFarmAnimals() {
        try {
            return farmAnimalService.findAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public FarmAnimal findFarmAnimalById(int id) {
        try {
            return farmAnimalService.findById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }



}
