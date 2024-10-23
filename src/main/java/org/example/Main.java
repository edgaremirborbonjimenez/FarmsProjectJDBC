package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.daos.jaxb.*;
import org.example.daos.jdbc.*;
import org.example.daos.xml.*;
import org.example.domain.*;
import org.example.domain.jaxb.*;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;
import org.example.interfaces.IMarsheller;
import org.example.utils.connection.HikariCPImplementation;
import org.example.utils.enums.UnitMeasurement;
import org.example.utils.marshallers.GenericMarshaller;
import org.example.utils.saxhandlers.*;

import java.io.FileReader;
import java.sql.Connection;
import java.util.Properties;


public class Main {

    private static final Logger logger = LogManager.getLogger();


    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();
        properties.load(Main.class.getClassLoader().getResourceAsStream("env.properties"));
//        IMarsheller<Animals> animalsIMarsheller = new GenericMarshaller<>(properties.getProperty("xml.jaxb.animal"), Animals.class);
//        IDAO<Animal> animalIDAO = new AnimalJAXB(animalsIMarsheller);
//        Animal animal = new Animal();
//        animal.setName("Toro");
//        animal = animalIDAO.insert(animal);
//
//        System.out.println(animalIDAO.findById(2));

//        IMarsheller<Farms> farmsIMarsheller = new GenericMarshaller<>(properties.getProperty("xml.jaxb.farm"),Farms.class);
//        IDAO<Farm> farmIDAO = new FarmJAXB(farmsIMarsheller);
//        Farm farm = new Farm();
//        farm.setName("JoyFarm");
//        farm.setAddress("Town 1");
//        farm.setOwner_id(1);

//        farm = farmIDAO.insert(farm);
//        farm.setName("Farmmmm");
//        farmIDAO.updateById(2,farm);

//        farmIDAO.deleteById(1);
//        System.out.println(farmIDAO.findById(2));
//        farmIDAO.findAll().forEach(System.out::println);

//        IMarsheller<Owners> ownersIMarsheller = new GenericMarshaller<>(properties.getProperty("xml.jaxb.owner"), Owners.class);
//        IDAO<Owner> ownerIDAO = new OwnerJAXB(ownersIMarsheller);
//        Owner owner = new Owner();
//        owner.setFullName("Edgar Emir Borbon Jimenez");
//        owner.setPhone("321654987");
//        owner.setEmail("edemboji@gmail.com");
//        ownerIDAO.insert(owner);

//        owner.setEmail("emir@gmail.com");
//        owner.setPhone("987987987");
//        owner.setFullName("Emir");
//        System.out.println(ownerIDAO.updateById(2,owner));

//        ownerIDAO.deleteById(2);

        //ownerIDAO.findAll().forEach(System.out::println);
//        System.out.println(ownerIDAO.findById(4));

//    IMarsheller<Products> productsIMarsheller = new GenericMarshaller<>(properties.getProperty("xml.jaxb.product"), Products.class);
//    IDAO<Product> productIDAO = new ProductJAXB(productsIMarsheller);
//
//    Product product = new Product();
//    product.setName("Agua");
//    product.setPrice(5);
//    product.setUnitMeasurement(UnitMeasurement.L.unit);
////    productIDAO.insert(product);
//
//        product.setName("Carne");
//        product.setPrice(10);
//        product.setUnitMeasurement(UnitMeasurement.lb.unit);
////        productIDAO.updateById(4,product);
////        productIDAO.deleteById(2);
//
//        System.out.println(productIDAO.findById(3));
//
//        productIDAO.findAll().forEach(System.out::println);

//    IMarsheller<Stores> storesIMarsheller = new GenericMarshaller<>(properties.getProperty("xml.jaxb.store"),Stores.class);
//    IDAO<Store> storeIDAO = new StoreJAXB(storesIMarsheller);
//
//    Store store = new Store();
//    store.setName("Abarrotes");
//    store.setAddress("Street 5");
//
////    storeIDAO.insert(store);
//        store.setName("extra");
//        store.setAddress("Street");
//        storeIDAO.updateById(4,store);

//        storeIDAO.deleteById(2);

//        System.out.println(storeIDAO.findById(2));
//        storeIDAO.findAll().forEach(System.out::println);

    IMarsheller<FarmSuppliesProductBought> farmSupplyB = new GenericMarshaller<>(properties.getProperty("xml.jaxb.farmsSupplyProductBought"), FarmSuppliesProductBought.class );
    IDAO<FarmSupplyProductBought> farmSuplyBDAO = new FarmsSupplyProductsBoughtJAXB(farmSupplyB);
//
        FarmSupplyProductBought f = new FarmSupplyProductBought();
        f.setFarm_id(1);
        f.setProduct_id(3);
        f.setTotal(50d);
        f.setAmount(10);

        farmSuplyBDAO.insert(f);
//        f.setTotal(100d);
//        farmSuplyBDAO.updateById(4,f);

//        farmSuplyBDAO.deleteById(2);

//        System.out.println(farmSuplyBDAO.findById(1));

//        farmSuplyBDAO.findAll().forEach(System.out::println);

    }

}