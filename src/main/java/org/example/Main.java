package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.daos.jdbc.*;
import org.example.interfaces.IConnection;
import org.example.utils.connection.HikariCPImplementation;

import java.sql.Connection;


public class Main {

    private static final Logger logger = LogManager.getLogger();


    public static void main(String[] args) throws Exception {

        IConnection<Connection> hikari = HikariCPImplementation.getInstance();
        hikari.setPoolSize(5);
//        HikariCPImplementation.getInstance().getConnectionFromPool();
//        HikariCPImplementation.getInstance().getConnectionFromPool();
//        HikariCPImplementation.getInstance().getConnectionFromPool();
//        HikariCPImplementation.getInstance().getConnectionFromPool();
//        Connection con = HikariCPImplementation.getInstance().getConnectionFromPool();
//        //con.close();
//        HikariCPImplementation.getInstance().releaseConnection(con);
//        HikariCPImplementation.getInstance().getConnectionFromPool();


        AnimalsIDAO animalsIDAO = AnimalsIDAO.getInstance();
        animalsIDAO.setPoolConnection(hikari);
//        Animal animal = new Animal();
//        animal.setName("Chicken");
//        Animal created = animalsIDAO.insert(animal);
//        System.out.println(created);
//        created.setName("RedChicken");
//        int amountUpdated = animalsIDAO.updateById(created.getId(), created);
//        System.out.println(amountUpdated);
//        int rowsAffected = animalsIDAO.deleteById(5);
//        System.out.println(rowsAffected);
//        animalsIDAO.findAll().forEach(a-> System.out.println(a));
//        System.out.println(animalsIDAO.findById(1));

        FarmsIDAO farmsIDAO = FarmsIDAO.getInstance();
        farmsIDAO.setPoolConnection(hikari);
//        Farm farm = new Farm();
//        farm.setName("EmirFarm");
//        farm.setAddress("Street");
//        farm.setOwner_id(1);
//        Farm farmCreated = farmsIDAO.insert(farm);
//        System.out.println(farmCreated);
//        farmsIDAO.findAll().forEach(f-> System.out.println(f.toString()));
//        Farm update = new Farm();
//        update.setAddress("Street 2");
//        System.out.println(farmsIDAO.updateById(farm.getId(),update));
//        System.out.println(farmsIDAO.findById(farm.getId()));

        OwnersIDAO ownersIDAO = OwnersIDAO.getInstance();
        ownersIDAO.setPoolConnection(hikari);
//        Owner owner = new Owner();
//        owner.setFullName("Peter");
//        owner.setPhone("9876547898");
//        owner.setEmail("hello@gmail.com");
//        Owner ownerCreated = ownersIDAO.insert(owner);
//        System.out.println(ownerCreated);
//        Owner update = new Owner();
//        update.setPhone("0000000000");
//        System.out.println(ownersIDAO.updateById(ownerCreated.getId(),update));
//        ownersIDAO.findAll().forEach(o -> System.out.println(o));
//        System.out.println(ownersIDAO.findById(ownerCreated.getId()));
//        System.out.println(ownersIDAO.deleteById(ownerCreated.getId()));

        ProductsIDAO productsIDAO = ProductsIDAO.getInstance();
        productsIDAO.setPoolConnection(hikari);
//        Product newProduct = new Product();
//        newProduct.setName("Eggs");
//        newProduct.setPrice(20.99);
//        newProduct.setUnitMeasurement(UnitMeasurement.pc.unit);
//        Product productCreated = productsIDAO.insert(newProduct);
//        Product update = new Product();
//        update.setPrice(15.99);
//        System.out.println(productsIDAO.updateById(productCreated.getId(),update));
//        productsIDAO.findAll().forEach(p -> System.out.println(p));
//        System.out.println(productsIDAO.findById(productCreated.getId()));

        StoresIDAO storesIDAO = StoresIDAO.getInstance();
        storesIDAO.setPoolConnection(hikari);
//        Store newStore = new Store();
//        newStore.setName("Abarrotes");
//        newStore.setAddress("Street");
//        Store storeCreated = storesIDAO.insert(newStore);
//        System.out.println(storeCreated);
//        Store update = new Store();
//        update.setAddress("Street2");
//        System.out.println(storesIDAO.updateById(storeCreated.getId(),update));
//        storesIDAO.findAll().forEach(System.out::println);
//        System.out.println(storesIDAO.findById(storeCreated.getId()));

        FarmsAnimalsIDAO farmsAnimalsIDAO = FarmsAnimalsIDAO.getInstance();
        farmsAnimalsIDAO.setPoolConnection(hikari);
//        FarmAnimal farmAnimal = new FarmAnimal();
//        farmAnimal.setAmount(20);
//        farmAnimal.setFarm_id(1);
//        farmAnimal.setAnimal_id(1);
//        FarmAnimal farmAnimalCreated = farmsAnimalsIDAO.insert(farmAnimal);
//        System.out.println(farmAnimalCreated);
//        FarmAnimal update = new FarmAnimal();
//        update.setAnimal_id(2);
//        System.out.println(farmsAnimalsIDAO.updateById(farmAnimalCreated.getId(),update));
//        farmsAnimalsIDAO.findAll().forEach(System.out::println);
//        System.out.println(farmsAnimalsIDAO.findById(farmAnimalCreated.getId()));

        FarmsSupplyProductsBoughtIDAO farmsSupplyProductsBoughtIDAO = FarmsSupplyProductsBoughtIDAO.getInstance();
        farmsSupplyProductsBoughtIDAO.setPoolConnection(hikari);
//        FarmSupplyProductBought f = new FarmSupplyProductBought();
//        f.setAmount(20);
//        f.setTotal(15.99*20);
//        f.setFarm_id(1);
//        f.setProduct_id(5);
//        FarmSupplyProductBought created = farmsSupplyProductsBoughtIDAO.insert(f);
//        System.out.println(created);
//        FarmSupplyProductBought update = new FarmSupplyProductBought();
//        update.setFarm_id(2);
//        System.out.println(farmsSupplyProductsBoughtIDAO.updateById(created.getId(),update));
//        farmsSupplyProductsBoughtIDAO.findAll().forEach(System.out::println);
//        System.out.println(farmsSupplyProductsBoughtIDAO.findById(created.getId()));
        FarmsSupplyProductsInventoryIDAO farmsSupplyProductsInventoryIDAO = FarmsSupplyProductsInventoryIDAO.getInstance();
        farmsSupplyProductsInventoryIDAO.setPoolConnection(hikari);
//        FarmSupplyProductInventory f = new FarmSupplyProductInventory();
//        f.setAmount(20);
//        f.setFarm_id(1);
//        f.setProduct_id(5);
//        FarmSupplyProductInventory created = farmsSupplyProductsInventoryIDAO.insert(f);
//        System.out.println(created);
//        FarmSupplyProductInventory update = new FarmSupplyProductInventory();
//        update.setAmount(30);
//        System.out.println(farmsSupplyProductsInventoryIDAO.updateById(created.getId(),update));
//        farmsSupplyProductsInventoryIDAO.findAll().forEach(System.out::println);
//        System.out.println(farmsSupplyProductsInventoryIDAO.findById(created.getId()));

        StoresProductsBoughtDAO storesProductsBoughtDAO = StoresProductsBoughtDAO.getInstance();
        storesProductsBoughtDAO.setPoolConnection(hikari);
//        StoreProductBought newObject = new StoreProductBought();
//        newObject.setAmount(20);
//        newObject.setTotal(20*15.99);
//        newObject.setStore_id(1);
//        newObject.setProduct_id(5);
//        StoreProductBought created = storesProductsBoughtDAO.insert(newObject);
//        System.out.println(created);
//        StoreProductBought update = new StoreProductBought();
//        update.setStore_id(2);
//        System.out.println(storesProductsBoughtDAO.updateById(created.getId(),update));
//        storesProductsBoughtDAO.findAll().forEach(System.out::println);
//        System.out.println(storesProductsBoughtDAO.findById(created.getId()));


    }
}