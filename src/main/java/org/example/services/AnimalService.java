package org.example.services;

import org.example.domain.Animal;
import org.example.interfaces.IDAO;
import org.example.interfaces.IService;

import java.util.List;

public class AnimalService implements IService<Animal> {

    IDAO<Animal> animalIDAO;

    public AnimalService(IDAO<Animal> animalIDAO){
        this.animalIDAO = animalIDAO;
    }

    @Override
    public Animal insert(Animal data) throws Exception {
        return animalIDAO.insert(data);
    }

    @Override
    public int updateById(int id, Animal data) throws Exception {
        return animalIDAO.updateById(id,data);
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        return animalIDAO.deleteById(id);
    }

    @Override
    public List<Animal> findAll() throws Exception {
        return animalIDAO.findAll();
    }

    @Override
    public Animal findById(int id) throws Exception {
        return animalIDAO.findById(id);
    }
}
