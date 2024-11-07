package org.example.mvc.controllers;

import org.example.domain.Store;
import org.example.mvc.interfaces.GenericController;
import org.example.mvc.interfaces.GenericModel;
import org.example.mvc.interfaces.GenericView;

public class StoreController implements GenericController<Store> {
    GenericView<Store> storeView;
    GenericModel<Store> storeModel;

    public StoreController(){}

    public StoreController(GenericView<Store> storeView, GenericModel<Store> storeModel){
        this.storeView = storeView;
        this.storeModel = storeModel;
    }

    public void setStoreModel(GenericModel<Store> storeModel) {
        this.storeModel = storeModel;
    }

    public void setStoreView(GenericView<Store> storeView) {
        this.storeView = storeView;
    }

    @Override
    public void create(Store data) {
        Store storeCreated = this.storeModel.create(data);
        this.storeView.showCreated(storeCreated);
        this.storeView.showMenu();
    }

    @Override
    public void update(int id, Store data) {
        Store storeUpdated = this.storeModel.update(id,data);
        this.storeView.showUpdated(storeUpdated);
        this.storeView.showMenu();
    }

    @Override
    public void delete(int id) {
        Store storeDeleted = this.storeModel.delete(id);
        this.storeView.showDeleted(storeDeleted);
        this.storeView.showMenu();
    }
}
