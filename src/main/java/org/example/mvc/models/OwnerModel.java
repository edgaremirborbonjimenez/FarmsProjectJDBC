package org.example.mvc.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.Owner;
import org.example.interfaces.IDAO;
import org.example.mvc.interfaces.ConfigurationModel;
import org.example.mvc.interfaces.GenericModel;
import org.example.mvc.interfaces.GenericViewModel;

import java.util.List;

public class OwnerModel implements GenericModel<Owner>, GenericViewModel<Owner>, ConfigurationModel<Owner> {

    private static final Logger logger = LogManager.getLogger();
    IDAO<Owner> ownerIDAO;

    public OwnerModel(){}

    @Override
    public void setDAO(IDAO<Owner> dao) {
        this.ownerIDAO = dao;
    }

    @Override
    public Owner create(Owner data) {
        try{
            return this.ownerIDAO.insert(data);
        }catch(Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Owner update(int id, Owner data) {
        try{
            this.ownerIDAO.updateById(id,data);
            return findById(id);
        }catch(Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Owner delete(int id) {
        try{
            Owner ownerDeleted = this.ownerIDAO.findById(id);
            this.ownerIDAO.deleteById(id);
            return ownerDeleted;
        }catch(Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Owner> findAll() {
        try{
            return this.ownerIDAO.findAll();
        }catch(Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Owner findById(int id) {
        try{
            return this.ownerIDAO.findById(id);
        }catch(Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }
}
