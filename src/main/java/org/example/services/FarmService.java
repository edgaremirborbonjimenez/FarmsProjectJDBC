package org.example.services;

import org.example.domain.Farm;
import org.example.interfaces.IDAO;
import org.example.interfaces.IService;

import java.util.List;

public class FarmService implements IService<Farm> {

    IDAO<Farm> farmIDAO;

    public FarmService(IDAO<Farm> farmIDAO){
        this.farmIDAO = farmIDAO;
    }

    @Override
    public Farm insert(Farm data) throws Exception {
        return farmIDAO.insert(data);
    }

    @Override
    public int updateById(int id, Farm data) throws Exception {
        return farmIDAO.updateById(id,data);
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        return farmIDAO.deleteById(id);
    }

    @Override
    public List<Farm> findAll() throws Exception {
        return farmIDAO.findAll();
    }

    @Override
    public Farm findById(int id) throws Exception {
        return farmIDAO.findById(id);
    }
}
