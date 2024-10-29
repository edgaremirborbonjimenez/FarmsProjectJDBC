package org.example.mvc.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.Farm;
import org.example.interfaces.IDAO;
import org.example.mvc.interfaces.ConfigurationModel;
import org.example.mvc.interfaces.GenericModel;
import org.example.mvc.interfaces.GenericViewModel;

import java.util.List;

public class FarmModel implements GenericModel<Farm>, GenericViewModel<Farm>, ConfigurationModel<Farm> {

    private static final Logger logger = LogManager.getLogger();
    IDAO<Farm> farmDAO;

    public FarmModel(){}

    @Override
    public void setDAO(IDAO<Farm> dao) {
        this.farmDAO = dao;
    }

    @Override
    public Farm create(Farm data) {
        try{
            return farmDAO.insert(data);
        }catch(Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Farm update(int id, Farm data) {
        try{
            farmDAO.updateById(id,data);
            return findById(id);
        }catch(Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Farm delete(int id) {
        try{
            Farm farmToDelete = farmDAO.findById(id);
            farmDAO.deleteById(id);
            return farmToDelete;
        }catch(Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Farm> findAll() {
        try{
            return farmDAO.findAll();
        }catch(Exception e){
            logger.error(e.getMessage());
        }
        return List.of();
    }

    @Override
    public Farm findById(int id) {
        try{
            return farmDAO.findById(id);
        }catch(Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }
}
