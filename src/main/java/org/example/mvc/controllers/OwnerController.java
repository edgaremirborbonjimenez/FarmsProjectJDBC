package org.example.mvc.controllers;

import org.example.domain.Owner;
import org.example.mvc.interfaces.GenericController;
import org.example.mvc.interfaces.GenericModel;
import org.example.mvc.interfaces.GenericView;

public class OwnerController implements GenericController<Owner> {

    GenericView<Owner> ownerView;
    GenericModel<Owner> ownerModel;

    public OwnerController(GenericView<Owner> ownerView,GenericModel<Owner> ownerModel){
        this.ownerModel = ownerModel;
        this.ownerView = ownerView;
    }

    @Override
    public void create(Owner data) {
        Owner ownerCreated = this.ownerModel.create(data);
        this.ownerView.showCreated(ownerCreated);
        this.ownerView.showMenu();
    }

    @Override
    public void update(int id, Owner data) {
        Owner ownerUpdated = this.ownerModel.update(id,data);
        this.ownerView.showUpdated(ownerUpdated);
        this.ownerView.showMenu();
    }

    @Override
    public void delete(int id) {
        Owner ownerDeleted = this.ownerModel.delete(id);
        this.ownerView.showDeleted(ownerDeleted);
        this.ownerView.showMenu();
    }
}
