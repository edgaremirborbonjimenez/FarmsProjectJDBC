package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.daos.jaxb.AnimalJAXB;
import org.example.daos.jaxb.FarmJAXB;
import org.example.daos.jdbc.*;
import org.example.daos.xml.*;
import org.example.domain.*;
import org.example.domain.jaxb.Animals;
import org.example.domain.jaxb.Farms;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;
import org.example.interfaces.IMarsheller;
import org.example.utils.connection.HikariCPImplementation;
import org.example.utils.enums.UnitMeasurement;
import org.example.utils.marshallers.AnimalMarshaller;
import org.example.utils.marshallers.GenericMarshaller;
import org.example.utils.saxhandlers.*;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.util.Properties;


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

        AnimalsXML animalsXML = new AnimalsXML("src/main/resources/xml_data/animals.xml", new AnimalsHandler());
//        animalsXML.findAll().forEach(System.out::println);
//        System.out.println();
//        System.out.println(animalsXML.findById(1));
//        System.out.println();
//        Animal animal = new Animal();
//        animal.setName("Horse");
//        System.out.println(animalsXML.insert(animal));
//        System.out.println();
//        animalsXML.findAll().forEach(System.out::println);
//        System.out.println();
//        System.out.println(animalsXML.deleteById(1));
//        System.out.println();
//        animalsXML.findAll().forEach(System.out::println);
//          Animal animal1 = new Animal();
//          animal.setName("Dog");
//        System.out.println(animalsXML.updateById(2,animal1));

//        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//        Schema schema = schemaFactory.newSchema(new File("src/main/java/org/example/utils/xsd/animal.xsd"));
//        Validator validator =  schema.newValidator();
//
//        validator.validate(new StreamSource(new File("src/main/resources/xml_data/animals.xml")));
//        System.out.println("El archivo es valido");


        OwnerXML ownerXML = new OwnerXML("src/main/resources/xml_data/owners.xml", new OwnerHandler());
//        ownerXML.findAll().forEach(System.out::println);
//        System.out.println();
//        System.out.println(ownerXML.findById(1));
//        System.out.println();
//        Owner owner = new Owner();
//        owner.setFullName("Juan Escutia");
//        owner.setPhone("9879879874");
//        owner.setEmail("juan@gmail.com");
//        System.out.println(ownerXML.insert(owner));
//        System.out.println();
//        ownerXML.findAll().forEach(System.out::println);
//        System.out.println();
//        System.out.println(ownerXML.deleteById(1));
//        System.out.println();
//        ownerXML.findAll().forEach(System.out::println);
//        Owner owner1 = new Owner();
//        owner1.setFullName("Luis Alberto");
//        owner1.setEmail("luis@gmail.com");
//        System.out.println(ownerXML.updateById(2,owner1));

        FarmsXML farmsXML = new FarmsXML("src/main/resources/xml_data/farms.xml", new FarmHandler());
//        farmsXML.findAll().forEach(System.out::println);
//        System.out.println();
//        System.out.println(farmsXML.findById(1));
//        System.out.println();
//        Farm farm = new Farm();
//        farm.setName("Juans Farm");
//        farm.setAddress("Street 3");
//        farm.setOwner_id(1);
//        System.out.println(farmsXML.insert(farm));
//        System.out.println();
//        farmsXML.findAll().forEach(System.out::println);
//        System.out.println();
//        System.out.println(farmsXML.deleteById(1));
//        System.out.println();
//        farmsXML.findAll().forEach(System.out::println);
//        Farm farm1 = new Farm();
//        farm1.setName("Granjona");
//        farm1.setAddress("Valley Lake");
//        farm1.setOwner_id(1);
//        System.out.println(farmsXML.updateById(3,farm1));

        ProductsXML productsXML = new ProductsXML("src/main/resources/xml_data/products.xml", new ProducHandler());
//        productsXML.findAll().forEach(System.out::println);
//        System.out.println();
//        System.out.println(productsXML.findById(1));
//        System.out.println();
//        Product product = new Product();
//        product.setName("CocaCola");
//        product.setPrice(5);
//        product.setUnitMeasurement(UnitMeasurement.pc.unit);
//        System.out.println(productsXML.insert(product));
//        System.out.println();
//        productsXML.findAll().forEach(System.out::println);
//        System.out.println();
//        System.out.println(productsXML.deleteById(1));
//        System.out.println();
//        productsXML.findAll().forEach(System.out::println);
//        Product product1 = new Product();
//        product1.setName("Sabritas");
//        product1.setPrice(3);
//        product1.setUnitMeasurement(UnitMeasurement.pc.unit);
//        System.out.println(productsXML.updateById(3,product1));

        StoresXML storesXML = new StoresXML("src/main/resources/xml_data/stores.xml", new StoreHandler());
//        storesXML.findAll().forEach(System.out::println);
//        System.out.println();
//        System.out.println(storesXML.findById(1));
//        System.out.println();
//        Store store = new Store();
//        store.setName("Abarrotes Choco");
//        store.setAddress("Street 2");
//        System.out.println(storesXML.insert(store));
//        System.out.println();
//        storesXML.findAll().forEach(System.out::println);
//        System.out.println();
//        System.out.println(storesXML.deleteById(1));
//        System.out.println();
//        storesXML.findAll().forEach(System.out::println);
//        Store store1 = new Store();
//        store1.setName("OXXO");
//        store1.setAddress("Street 3");
//        System.out.println(storesXML.updateById(3,store1));

        FarmsAnimalsXML farmsAnimalsXML = new FarmsAnimalsXML("src/main/resources/xml_data/farmAnimals.xml", new FarmsAnimalsHandler());
//        farmsAnimalsXML.findAll().forEach(System.out::println);
//        System.out.println();
//        System.out.println(farmsAnimalsXML.findById(1));
//        System.out.println();
//        FarmAnimal farmAnimal = new FarmAnimal();
//        farmAnimal.setAmount(5);
//        farmAnimal.setFarm_id(1);
//        farmAnimal.setAnimal_id(1);
//        System.out.println(farmsAnimalsXML.insert(farmAnimal));
//        System.out.println();
//        farmsAnimalsXML.findAll().forEach(System.out::println);
//        System.out.println();
//        System.out.println(farmsAnimalsXML.deleteById(1));
//        System.out.println();
//        farmsAnimalsXML.findAll().forEach(System.out::println);
//        FarmAnimal farmAnimal1 = new FarmAnimal();
//        farmAnimal1.setAmount(5);
//        farmAnimal1.setFarm_id(2);
//        farmAnimal1.setAnimal_id(2);
//        System.out.println(farmsAnimalsXML.updateById(3,farmAnimal1));

        FarmsSupplyProductsBoughtXML farmsSupplyProductsBoughtXML = new FarmsSupplyProductsBoughtXML("src/main/resources/xml_data/farmsSupplyProductBought.xml", new FarmsSupplyProductBoughtHandler());
//        farmsSupplyProductsBoughtXML.findAll().forEach(System.out::println);
//        System.out.println();
//        System.out.println(farmsSupplyProductsBoughtXML.findById(1));
//        System.out.println();
//        FarmSupplyProductBought farmSupplyProductBought = new FarmSupplyProductBought();
//        farmSupplyProductBought.setAmount(5);
//        farmSupplyProductBought.setTotal(50d);
//        farmSupplyProductBought.setPurchaseDate(new Date(System.currentTimeMillis()));
//        farmSupplyProductBought.setFarm_id(1);
//        farmSupplyProductBought.setProduct_id(1);
//        System.out.println(farmsSupplyProductsBoughtXML.insert(farmSupplyProductBought));
//        System.out.println();
//        farmsSupplyProductsBoughtXML.findAll().forEach(System.out::println);
//        System.out.println();
//        System.out.println(farmsSupplyProductsBoughtXML.deleteById(1));
//        System.out.println();
//        farmsSupplyProductsBoughtXML.findAll().forEach(System.out::println);
//        FarmSupplyProductBought farmSupplyProductBought1 = new FarmSupplyProductBought();
//        farmSupplyProductBought1.setAmount(5);
//        farmSupplyProductBought1.setTotal(50d);
//        farmSupplyProductBought1.setPurchaseDate(new Date(System.currentTimeMillis()));
//        farmSupplyProductBought1.setFarm_id(1);
//        farmSupplyProductBought1.setProduct_id(1);
//        System.out.println(farmsSupplyProductsBoughtXML.updateById(3,farmSupplyProductBought1));

        FarmsSupplyProductsInventoryXML farmsSupplyProductsInventoryXML = new FarmsSupplyProductsInventoryXML("src/main/resources/xml_data/farmSupplyProductInventory.xml", new FarmSupplyProductInventoryHandler());
//        farmsSupplyProductsInventoryXML.findAll().forEach(System.out::println);
//        System.out.println();
//        System.out.println(farmsSupplyProductsInventoryXML.findById(1));
//        System.out.println();
//        FarmSupplyProductInventory f =  new FarmSupplyProductInventory();
//        f.setAmount(10);
//        f.setFarm_id(1);
//        f.setProduct_id(1);
//        System.out.println(farmsSupplyProductsInventoryXML.insert(f));
//        System.out.println();
//        farmsSupplyProductsInventoryXML.findAll().forEach(System.out::println);
//        System.out.println();
//        System.out.println(farmsSupplyProductsInventoryXML.deleteById(1));
//        System.out.println();
//        farmsSupplyProductsInventoryXML.findAll().forEach(System.out::println);
//        FarmSupplyProductInventory f1 =  new FarmSupplyProductInventory();
//        f1.setAmount(10);
//        f1.setFarm_id(1);
//        f1.setProduct_id(1);
//        System.out.println(farmsSupplyProductsInventoryXML.updateById(3,f1));

        StoresProductsBoughtXML storesProductsBoughtXML = new StoresProductsBoughtXML("src/main/resources/xml_data/storeProductBought.xml", new StoreProductBoughtHandler());
//        storesProductsBoughtXML.findAll().forEach(System.out::println);
//        System.out.println();
//        System.out.println(storesProductsBoughtXML.findById(1));
//        System.out.println();
//        StoreProductBought s = new StoreProductBought();
//        s.setAmount(10);
//        s.setTotal(50d);
//        s.setProduct_id(1);
//        s.setStore_id(1);
//        System.out.println(storesProductsBoughtXML.insert(s));
//        System.out.println();
//        storesProductsBoughtXML.findAll().forEach(System.out::println);
//        System.out.println();
//        System.out.println(storesProductsBoughtXML.deleteById(1));
//        System.out.println();
//        storesProductsBoughtXML.findAll().forEach(System.out::println);
//        StoreProductBought s1 = new StoreProductBought();
//        s1.setAmount(10);
//        s1.setTotal(50d);
//        s1.setProduct_id(1);
//        s1.setStore_id(1);
//        System.out.println(storesProductsBoughtXML.updateById(3,s1));

        Properties properties = new Properties();
        FileReader input = new FileReader("src/main/resources/env.properties");
        properties.load(input);
        IMarsheller<Animals> animalsIMarsheller = new GenericMarshaller<>(properties.getProperty("xml.jaxb.animal"), Animals.class);
        IDAO<Animal> animalIDAO = new AnimalJAXB(animalsIMarsheller);
        Animal animal = new Animal();
        animal.setName("Bull");
        animal = animalIDAO.insert(animal);

        animalIDAO.findAll().forEach(System.out::println);

//        Animal animalUpdated = new Animal();
//        animalUpdated.setName("Horse");
//        animalIDAO.updateById(1,animalUpdated);
//
//        System.out.println(animalIDAO.findById(1));
//
//        animalIDAO.deleteById(1);
//        System.out.println(animalIDAO.findById(1));

//        IMarsheller<Farms> farmsIMarsheller = new GenericMarshaller<>(properties.getProperty("xml.jaxb.farm"), Farms.class);
//        IDAO<Farm> farmIDAO = new FarmJAXB(farmsIMarsheller);
//        Farm farm = new Farm();
//        farm.setName("JoyFarm");
//        farm.setAddress("Town 2");
//        farm.setOwner_id(1);
//        farmIDAO.insert(farm);






















    }

}