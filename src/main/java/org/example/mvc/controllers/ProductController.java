package org.example.mvc.controllers;

import org.example.domain.Product;
import org.example.mvc.interfaces.GenericController;
import org.example.mvc.interfaces.GenericModel;
import org.example.mvc.interfaces.GenericView;

public class ProductController implements GenericController<Product> {

    GenericView<Product> productView;
    GenericModel<Product> productModel;

    public ProductController(GenericView<Product> productView, GenericModel<Product> productModel){
        this.productModel = productModel;
        this.productView = productView;
    }

    @Override
    public void create(Product data) {
        Product productCreated = this.productModel.create(data);
        this.productView.showCreated(productCreated);
        this.productView.showMenu();
    }

    @Override
    public void update(int id, Product data) {
        Product productUpdated = this.productModel.update(id,data);
        this.productView.showUpdated(productUpdated);
        this.productView.showMenu();
    }

    @Override
    public void delete(int id) {
        Product productDeleted = this.productModel.delete(id);
        this.productView.showDeleted(productDeleted);
        this.productView.showMenu();
    }
}
