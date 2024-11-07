package org.example.mvc.controllers;

import org.example.domain.Farm;
import org.example.mvc.interfaces.GenericController;
import org.example.mvc.interfaces.GenericModel;
import org.example.mvc.interfaces.GenericView;

public class FarmController implements GenericController<Farm> {

    GenericModel<Farm> farmModel;
    GenericView<Farm> farmView;

    public FarmController(){}

    public FarmController(GenericView<Farm> farmView, GenericModel<Farm> farmModel){
        this.farmModel = farmModel;
        this.farmView = farmView;
    }

    public void setFarmModel(GenericModel<Farm> farmModel) {
        this.farmModel = farmModel;
    }

    public void setFarmView(GenericView<Farm> farmView) {
        this.farmView = farmView;
    }

    @Override
    public void create(Farm data) {
        Farm farmCreated = this.farmModel.create(data);
        this.farmView.showCreated(farmCreated);
        this.farmView.showMenu();
    }

    @Override
    public void update(int id, Farm data) {
        Farm farmUpdated = this.farmModel.update(id,data);
        this.farmView.showUpdated(farmUpdated);
        this.farmView.showMenu();
    }

    @Override
    public void delete(int id) {
        Farm farmDeleted = this.farmModel.delete(id);
        this.farmView.showDeleted(farmDeleted);
        this.farmView.showMenu();
    }
}
