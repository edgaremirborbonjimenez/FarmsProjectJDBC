package org.example.daos.jaxb;

import org.example.domain.FarmSupplyProductInventory;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;

import java.sql.Connection;
import java.util.List;

public class FarmsSupplyProductsInventoryJAXB implements IDAO<FarmSupplyProductInventory> {
    @Override
    public FarmSupplyProductInventory insert(FarmSupplyProductInventory data) throws Exception {
        return null;
    }

    @Override
    public int updateById(int id, FarmSupplyProductInventory data) throws Exception {
        return 0;
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        return 0;
    }

    @Override
    public List<FarmSupplyProductInventory> findAll() throws Exception {
        return List.of();
    }

    @Override
    public FarmSupplyProductInventory findById(int id) throws Exception {
        return null;
    }

    @Override
    public void setPoolConnection(IConnection<Connection> poolConnection) {

    }
}
