package org.example.mvc.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.daos.jaxb.*;
import org.example.daos.jdbc.*;
import org.example.daos.json.*;
import org.example.daos.mybatis.*;
import org.example.daos.xml.*;
import org.example.domain.*;
import org.example.domain.jaxb.*;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;
import org.example.interfaces.IMarsheller;
import org.example.mvc.controllers.MenuController;
import org.example.utils.connection.HikariCPImplementation;
import org.example.utils.marshallers.GenericMarshaller;
import org.example.utils.saxhandlers.*;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class MenuModel {
    private static final Logger logger = LogManager.getLogger();


    AnimalModel animalModel;
    FarmModel farmModel;
    OwnerModel ownerModel;
    ProductModel productModel;
    StoreModel storeModel;

    public MenuModel(){
    }


    public void useDatabase() {
        IConnection<Connection> hikari = HikariCPImplementation.getInstance();
        hikari.setPoolSize(5);
        AnimalsIDAO animalsIDAO = AnimalsIDAO.getInstance();
        animalsIDAO.setPoolConnection(hikari);
        this.animalModel.setDAO(animalsIDAO);

        FarmsIDAO farmsIDAO = FarmsIDAO.getInstance();
        farmsIDAO.setPoolConnection(hikari);
        this.farmModel.setDAO(farmsIDAO);

        OwnersIDAO ownersIDAO = OwnersIDAO.getInstance();
        ownersIDAO.setPoolConnection(hikari);
        this.ownerModel.setDAO(ownersIDAO);

        ProductsIDAO productsIDAO = ProductsIDAO.getInstance();
        productsIDAO.setPoolConnection(hikari);
        this.productModel.setDAO(productsIDAO);

        StoresIDAO storesIDAO = StoresIDAO.getInstance();
        storesIDAO.setPoolConnection(hikari);
        this.storeModel.setDAO(storesIDAO);

        logger.info("Data Source with the Data Base assigned");
    }

    public void useXML() {

        Properties properties = new Properties();
        try (FileReader input = new FileReader("src/main/resources/env.properties")){
            properties.load(input);

            this.animalModel.setDAO(new AnimalsXML(properties.getProperty("xml.animal"),new AnimalsHandler()));
            this.ownerModel.setDAO(new OwnerXML(properties.getProperty("xml.owner"),new OwnerHandler()));
            this.farmModel.setDAO(new FarmsXML(properties.getProperty("xml.farm"),new FarmHandler()));
            this.productModel.setDAO(new ProductsXML(properties.getProperty("xml.product"), new ProducHandler()));
            this.storeModel.setDAO(new StoresXML(properties.getProperty("xml.store"), new StoreHandler()));
            logger.info("Data Source with the XML assigned");

        }catch (Exception e){
            logger.error(e.getMessage());
        }

    }

    public void useMyBatis(){
        this.animalModel.setDAO(new AnimalMyBatis());
        this.ownerModel.setDAO(new OwnerMyBatis());
        this.farmModel.setDAO(new FarmMyBatis());
        this.productModel.setDAO(new ProductMyBatis());
        this.storeModel.setDAO(new StoreMyBatis());
        logger.info("Data Source with MyBatis assigned");
    }

    public void useJAXB(){
        try{
            Properties properties = new Properties();
            properties.load(org.example.presentacion.Service.class.getClassLoader().getResourceAsStream("env.properties"));

            IMarsheller<Animals> animalsIMarsheller = new GenericMarshaller<>(properties.getProperty("xml.jaxb.animal"), Animals.class);
            IDAO<Animal> animalIDAO = new AnimalJAXB(animalsIMarsheller);
            this.animalModel.setDAO(animalIDAO);

            IMarsheller<Farms> farmsIMarsheller = new GenericMarshaller<>(properties.getProperty("xml.jaxb.farm"),Farms.class);
            IDAO<Farm> farmIDAO = new FarmJAXB(farmsIMarsheller);
            this.farmModel.setDAO(farmIDAO);

            IMarsheller<Owners> ownersIMarsheller = new GenericMarshaller<>(properties.getProperty("xml.jaxb.owner"), Owners.class);
            IDAO<Owner> ownerIDAO = new OwnerJAXB(ownersIMarsheller);
            this.ownerModel.setDAO(ownerIDAO);

            IMarsheller<Products> productsIMarsheller = new GenericMarshaller<>(properties.getProperty("xml.jaxb.product"), Products.class);
            IDAO<Product> productIDAO = new ProductJAXB(productsIMarsheller);
            this.productModel.setDAO(productIDAO);

            IMarsheller<Stores> storesIMarsheller = new GenericMarshaller<>(properties.getProperty("xml.jaxb.store"),Stores.class);
            IDAO<Store> storeIDAO = new StoreJAXB(storesIMarsheller);
            this.storeModel.setDAO(storeIDAO);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    public void useJSON(){
        try {
            Properties properties = new Properties();
            properties.load(org.example.presentacion.Service.class.getClassLoader().getResourceAsStream("env.properties"));
            this.animalModel.setDAO(new AnimalJSON(properties.getProperty("json.animal")));
            this.farmModel.setDAO(new FarmJSON(properties.getProperty("json.farm")));
            this.ownerModel.setDAO(new OwnerJSON(properties.getProperty("json.owner")));
            this.productModel.setDAO(new ProductJSON(properties.getProperty("json.product")));
            this.storeModel.setDAO(new StoreJSON(properties.getProperty("json.store")));
        }catch (IOException e){
            logger.error(e.getMessage());
        }
    }

    public void setProductModel(ProductModel productModel) {
        this.productModel = productModel;
    }

    public void setOwnerModel(OwnerModel ownerModel) {
        this.ownerModel = ownerModel;
    }

    public void setFarmModel(FarmModel farmModel) {
        this.farmModel = farmModel;
    }

    public void setStoreModel(StoreModel storeModel) {
        this.storeModel = storeModel;
    }

    public void setAnimalModel(AnimalModel animalModel) {
        this.animalModel = animalModel;
    }
}
