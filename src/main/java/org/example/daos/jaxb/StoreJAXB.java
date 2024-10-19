package org.example.daos.jaxb;

import org.example.domain.Store;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;

import java.sql.Connection;
import java.util.List;

public class StoreJAXB implements IDAO<Store> {
    @Override
    public Store insert(Store data) throws Exception {
        return null;
    }

    @Override
    public int updateById(int id, Store data) throws Exception {
        return 0;
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        return 0;
    }

    @Override
    public List<Store> findAll() throws Exception {
        return List.of();
    }

    @Override
    public Store findById(int id) throws Exception {
        return null;
    }

    @Override
    public void setPoolConnection(IConnection<Connection> poolConnection) {

    }
}
