package org.example.services;

import org.example.interfaces.IDAO;
import org.example.interfaces.IService;

import java.util.List;

public class FarmService implements IService<FarmService> {

    IDAO<FarmService> farmServiceIDAO;

    public FarmService(IDAO<FarmService> farmServiceIDAO){
        this.farmServiceIDAO = farmServiceIDAO;
    }

    @Override
    public FarmService insert(FarmService data) throws Exception {
        return farmServiceIDAO.insert(data);
    }

    @Override
    public int updateById(int id, FarmService data) throws Exception {
        return farmServiceIDAO.updateById(id,data);
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        return farmServiceIDAO.deleteById(id);
    }

    @Override
    public List<FarmService> findAll() throws Exception {
        return farmServiceIDAO.findAll();
    }

    @Override
    public FarmService findById(int id) throws Exception {
        return farmServiceIDAO.findById(id);
    }
}
