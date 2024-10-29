package org.example.mvc;

import org.example.mvc.controllers.*;
import org.example.mvc.models.*;
import org.example.mvc.views.*;

public class Execute {
    public static void main(String[] args) {
        start();
    }

    public static void start(){
        MenuController menuController = new MenuController();
        MenuView menuView = new MenuView(menuController);
        MenuModel menuModel = new MenuModel();
        menuController.setMenuModel(menuModel);
        menuController.setMenuView(menuView);

        AnimalController animalController = new AnimalController();
        AnimalModel animalModel = new AnimalModel();
        AnimalView animalView = new AnimalView(animalController,animalModel);
        animalController.setAnimalView(animalView);
        animalController.setAnimalModel(animalModel);

        OwnerController ownerController = new OwnerController();
        OwnerModel ownerModel = new OwnerModel();
        OwnerView ownerView = new OwnerView(ownerController,ownerModel);
        ownerController.setOwnerModel(ownerModel);
        ownerController.setOwnerView(ownerView);

        FarmController farmController = new FarmController();
        FarmModel farmModel = new FarmModel();
        FarmView farmView = new FarmView(farmController,farmModel);
        farmView.setOwnerModel(ownerModel);
        farmController.setFarmModel(farmModel);
        farmController.setFarmView(farmView);


        ProductController productController = new ProductController();
        ProductModel productModel = new ProductModel();
        ProductView productView = new ProductView(productController,productModel);
        productController.setProductModel(productModel);
        productController.setProductView(productView);

        StoreController storeController = new StoreController();
        StoreModel storeModel = new StoreModel();
        StoreView storeView = new StoreView(storeController,storeModel);
        storeController.setStoreModel(storeModel);
        storeController.setStoreView(storeView);

        menuModel.setAnimalModel(animalModel);
        menuModel.setFarmModel(farmModel);
        menuModel.setOwnerModel(ownerModel);
        menuModel.setProductModel(productModel);
        menuModel.setStoreModel(storeModel);

        menuController.setStoreView(storeView);
        menuController.setOwnerView(ownerView);
        menuController.setFarmView(farmView);
        menuController.setProductView(productView);
        menuController.setAnimalView(animalView);

        menuView.selectDataSource();
    }
}
