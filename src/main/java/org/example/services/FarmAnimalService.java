package org.example.services;

import org.example.domain.FarmAnimal;
import org.example.interfaces.IDAO;
import org.example.interfaces.IService;

import java.util.List;

public class FarmAnimalService implements IService<FarmAnimal> {
    IDAO<FarmAnimal> farmAnimalIDAO;
    public FarmAnimalService(IDAO<FarmAnimal> farmAnimalIDAO){
        this.farmAnimalIDAO = farmAnimalIDAO;
    }
    @Override
    public FarmAnimal insert(FarmAnimal data) throws Exception {
        return farmAnimalIDAO.insert(data);
    }

    @Override
    public int updateById(int id, FarmAnimal data) throws Exception {
        return farmAnimalIDAO.updateById(id,data);
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        return farmAnimalIDAO.deleteById(id);
    }

    @Override
    public List<FarmAnimal> findAll() throws Exception {
        return farmAnimalIDAO.findAll();
    }

    @Override
    public FarmAnimal findById(int id) throws Exception {
        return farmAnimalIDAO.findById(id);
    }
}
