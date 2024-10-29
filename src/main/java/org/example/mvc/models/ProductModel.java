package org.example.mvc.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.Product;
import org.example.interfaces.IDAO;
import org.example.mvc.interfaces.ConfigurationModel;
import org.example.mvc.interfaces.GenericModel;
import org.example.mvc.interfaces.GenericViewModel;

import java.util.List;

public class ProductModel implements GenericModel<Product>, GenericViewModel<Product>, ConfigurationModel<Product> {
    private static final Logger logger = LogManager.getLogger();
    IDAO<Product> productDAO;

    public ProductModel(){}

    @Override
    public void setDAO(IDAO<Product> dao) {
        this.productDAO = dao;
    }

    @Override
    public Product create(Product data) {
        try{
            return this.productDAO.insert(data);
        }catch(Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Product update(int id, Product data) {
        try{
            this.productDAO.updateById(id,data);
            return this.productDAO.findById(id);
        }catch(Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Product delete(int id) {
        try{
            Product productDeleted = this.productDAO.findById(id);
            this.productDAO.deleteById(id);
            return productDeleted;
        }catch(Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Product> findAll() {
        try{
            return this.productDAO.findAll();
        }catch(Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Product findById(int id) {
        try{
            return this.productDAO.findById(id);
        }catch(Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }
}
