package org.example.services;

import org.example.domain.StoreProductBought;
import org.example.interfaces.IDAO;
import org.example.interfaces.IService;

import java.util.List;

public class StoreProductBoughtService implements IService<StoreProductBought> {
    IDAO<StoreProductBought> storeProductBoughtIDAO;
    public StoreProductBoughtService(IDAO<StoreProductBought> storeProductBoughtIDAO){
        this.storeProductBoughtIDAO = storeProductBoughtIDAO;
    }
    @Override
    public StoreProductBought insert(StoreProductBought data) throws Exception {
        return this.storeProductBoughtIDAO.insert(data);
    }

    @Override
    public int updateById(int id, StoreProductBought data) throws Exception {
        return storeProductBoughtIDAO.updateById(id,data);
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        return storeProductBoughtIDAO.deleteById(id);
    }

    @Override
    public List<StoreProductBought> findAll() throws Exception {
        return storeProductBoughtIDAO.findAll();
    }

    @Override
    public StoreProductBought findById(int id) throws Exception {
        return storeProductBoughtIDAO.findById(id);
    }
}
