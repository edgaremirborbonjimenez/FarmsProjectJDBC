package org.example.daos.jaxb;

import org.example.domain.FarmSupplyProductBought;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;

import java.sql.Connection;
import java.util.List;

public class FarmsSupplyProductsBoughtJAXB implements IDAO<FarmSupplyProductBought> {
    @Override
    public FarmSupplyProductBought insert(FarmSupplyProductBought data) throws Exception {
        return null;
    }

    @Override
    public int updateById(int id, FarmSupplyProductBought data) throws Exception {
        return 0;
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        return 0;
    }

    @Override
    public List<FarmSupplyProductBought> findAll() throws Exception {
        return List.of();
    }

    @Override
    public FarmSupplyProductBought findById(int id) throws Exception {
        return null;
    }

    @Override
    public void setPoolConnection(IConnection<Connection> poolConnection) {

    }
}
