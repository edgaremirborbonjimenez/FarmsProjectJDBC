package org.example.services;

import org.example.domain.FarmSupplyProductInventory;
import org.example.interfaces.IDAO;
import org.example.interfaces.IService;

import java.util.List;

public class FarmSupplyProductInventoryService implements IService<FarmSupplyProductInventory> {

    IDAO<FarmSupplyProductInventory> farmSupplyProductInventoryIDAO;

    public FarmSupplyProductInventoryService(IDAO<FarmSupplyProductInventory> farmSupplyProductInventoryIDAO){
        this.farmSupplyProductInventoryIDAO = farmSupplyProductInventoryIDAO;
    }
    @Override
    public FarmSupplyProductInventory insert(FarmSupplyProductInventory data) throws Exception {
        return farmSupplyProductInventoryIDAO.insert(data);
    }

    @Override
    public int updateById(int id, FarmSupplyProductInventory data) throws Exception {
        return farmSupplyProductInventoryIDAO.updateById(id,data);
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        return farmSupplyProductInventoryIDAO.deleteById(id);
    }

    @Override
    public List<FarmSupplyProductInventory> findAll() throws Exception {
        return farmSupplyProductInventoryIDAO.findAll();
    }

    @Override
    public FarmSupplyProductInventory findById(int id) throws Exception {
        return farmSupplyProductInventoryIDAO.findById(id);
    }
}
