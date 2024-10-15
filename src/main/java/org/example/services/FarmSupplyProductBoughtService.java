package org.example.services;

import org.example.domain.FarmSupplyProductBought;
import org.example.interfaces.IDAO;
import org.example.interfaces.IService;

import java.util.List;

public class FarmSupplyProductBoughtService implements IService<FarmSupplyProductBought> {

    IDAO<FarmSupplyProductBought> farmSupplyProductBoughtIDAO;

    public FarmSupplyProductBoughtService(IDAO<FarmSupplyProductBought> farmSupplyProductBoughtIDAO){
        this.farmSupplyProductBoughtIDAO = farmSupplyProductBoughtIDAO;
    }
    @Override
    public FarmSupplyProductBought insert(FarmSupplyProductBought data) throws Exception {
        return farmSupplyProductBoughtIDAO.insert(data);
    }

    @Override
    public int updateById(int id, FarmSupplyProductBought data) throws Exception {
        return farmSupplyProductBoughtIDAO.updateById(id,data);
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        return farmSupplyProductBoughtIDAO.deleteById(id);
    }

    @Override
    public List<FarmSupplyProductBought> findAll() throws Exception {
        return farmSupplyProductBoughtIDAO.findAll();
    }

    @Override
    public FarmSupplyProductBought findById(int id) throws Exception {
        return farmSupplyProductBoughtIDAO.findById(id);
    }
}
