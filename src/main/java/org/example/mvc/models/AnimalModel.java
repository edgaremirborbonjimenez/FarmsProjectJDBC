package org.example.mvc.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.Animal;
import org.example.interfaces.IDAO;
import org.example.mvc.interfaces.ConfigurationModel;
import org.example.mvc.interfaces.GenericModel;
import org.example.mvc.interfaces.GenericViewModel;

import java.util.List;

public class AnimalModel implements GenericModel<Animal>, GenericViewModel<Animal>, ConfigurationModel<Animal> {

    private static final Logger logger = LogManager.getLogger();
    private IDAO<Animal> animalDAO;

    public AnimalModel(){}

    @Override
    public void setDAO(IDAO<Animal> dao) {
        animalDAO = dao;
    }

    @Override
    public Animal create(Animal data) {
        try{
            return animalDAO.insert(data);
        }catch(Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Animal update(int id, Animal data) {
        try{
            animalDAO.updateById(id, data);
            return findById(id);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Animal delete(int id) {
        try{
            Animal animalToDelete = findById(id);
            animalDAO.deleteById(id);
            return animalToDelete;
        }catch(Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Animal> findAll() {
        try{
            return animalDAO.findAll();
        }catch(Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Animal findById(int id) {
        try{
            return animalDAO.findById(id);
        }catch(Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }
}
