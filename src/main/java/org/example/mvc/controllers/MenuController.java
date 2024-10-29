package org.example.mvc.controllers;


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
import org.example.mvc.models.*;
import org.example.mvc.views.*;
import org.example.utils.connection.HikariCPImplementation;
import org.example.utils.marshallers.GenericMarshaller;
import org.example.utils.saxhandlers.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;


public class MenuController {

    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
    MenuView menuView;
    MenuModel menuModel;
    AnimalView animalView;
    FarmView farmView;
    OwnerView ownerView;
    StoreView storeView;
    ProductView productView;

    public MenuController(){
    }

    public void goToAnimalsCRUD(){
        this.animalView.showMenu();
    }

    public void goToFarmsCRUD(){
        this.farmView.showMenu();
    }

    public void goToOwnersCRUD(){
        this.ownerView.showMenu();
    }

    public void goToStoresCRUD(){
        this.storeView.showMenu();
    }

    public void goToProductsCRUD(){
        this.productView.showMenu();
    }

    public void setMenuView(MenuView menuView) {
        this.menuView = menuView;
    }

    public void setMenuModel(MenuModel menuModel) {
        this.menuModel = menuModel;
    }

    public void useDatabase() {
        this.menuModel.useDatabase();
    }

    public void useXML() {
        this.menuModel.useXML();
    }

    public void useMyBatis(){
        this.menuModel.useMyBatis();
    }

    public void useJAXB(){
        this.menuModel.useJAXB();
    }

    public void useJSON(){
        this.menuModel.useJSON();
    }

    public void setProductView(ProductView productView) {
        this.productView = productView;
    }

    public void setOwnerView(OwnerView ownerView) {
        this.ownerView = ownerView;
    }

    public void setFarmView(FarmView farmView) {
        this.farmView = farmView;
    }

    public void setStoreView(StoreView storeView) {
        this.storeView = storeView;
    }

    public void setAnimalView(AnimalView animalView) {
        this.animalView = animalView;
    }
}
