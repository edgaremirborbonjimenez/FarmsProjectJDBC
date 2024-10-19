package org.example.daos.jaxb;

import org.example.domain.Owner;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;

import java.sql.Connection;
import java.util.List;

public class OwnerJAXB implements IDAO<Owner> {
    @Override
    public Owner insert(Owner data) throws Exception {
        return null;
    }

    @Override
    public int updateById(int id, Owner data) throws Exception {
        return 0;
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        return 0;
    }

    @Override
    public List<Owner> findAll() throws Exception {
        return List.of();
    }

    @Override
    public Owner findById(int id) throws Exception {
        return null;
    }

    @Override
    public void setPoolConnection(IConnection<Connection> poolConnection) {

    }
}
