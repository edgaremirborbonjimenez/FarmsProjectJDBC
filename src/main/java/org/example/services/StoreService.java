package org.example.services;

import org.example.domain.Store;
import org.example.interfaces.IDAO;
import org.example.interfaces.IService;

import java.util.List;

public class StoreService implements IService<Store> {
    IDAO<Store> storeIDAO;
    public StoreService(IDAO<Store> storeIDAO){
        this.storeIDAO = storeIDAO;
    }
    @Override
    public Store insert(Store data) throws Exception {
        return storeIDAO.insert(data);
    }

    @Override
    public int updateById(int id, Store data) throws Exception {
        return storeIDAO.updateById(id,data);
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        return storeIDAO.deleteById(id);
    }

    @Override
    public List<Store> findAll() throws Exception {
        return storeIDAO.findAll();
    }

    @Override
    public Store findById(int id) throws Exception {
        return storeIDAO.findById(id);
    }
}
