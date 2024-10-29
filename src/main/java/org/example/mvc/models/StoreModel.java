package org.example.mvc.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.Store;
import org.example.interfaces.IDAO;
import org.example.mvc.interfaces.ConfigurationModel;
import org.example.mvc.interfaces.GenericModel;
import org.example.mvc.interfaces.GenericViewModel;

import java.util.List;

public class StoreModel implements GenericModel<Store>, GenericViewModel<Store>, ConfigurationModel<Store> {

    private static final Logger logger = LogManager.getLogger();
    IDAO<Store> storeDAO;

    public StoreModel(){}

    @Override
    public void setDAO(IDAO<Store> dao) {
        this.storeDAO = dao;
    }

    @Override
    public Store create(Store data) {
        try{
            return this.storeDAO.insert(data);
        }catch(Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Store update(int id, Store data) {
        try{
            this.storeDAO.updateById(id,data);
            return this.storeDAO.findById(id);
        }catch(Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Store delete(int id) {
        try{
            Store storeDeleted = this.storeDAO.findById(id);
            this.storeDAO.deleteById(id);
            return storeDeleted;
        }catch(Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Store> findAll() {
        try{
            return this.storeDAO.findAll();
        }catch(Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Store findById(int id) {
        try{
            this.storeDAO.findById(id);
        }catch(Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }
}
