package org.example.daos.jaxb;

import org.example.domain.StoreProductBought;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;

import java.sql.Connection;
import java.util.List;

public class StoresProductsBoughtJAXB implements IDAO<StoreProductBought> {
    @Override
    public StoreProductBought insert(StoreProductBought data) throws Exception {
        return null;
    }

    @Override
    public int updateById(int id, StoreProductBought data) throws Exception {
        return 0;
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        return 0;
    }

    @Override
    public List<StoreProductBought> findAll() throws Exception {
        return List.of();
    }

    @Override
    public StoreProductBought findById(int id) throws Exception {
        return null;
    }

    @Override
    public void setPoolConnection(IConnection<Connection> poolConnection) {

    }
}
