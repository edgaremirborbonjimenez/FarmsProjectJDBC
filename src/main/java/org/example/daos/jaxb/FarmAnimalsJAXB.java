package org.example.daos.jaxb;

import org.example.domain.FarmAnimal;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;

import java.sql.Connection;
import java.util.List;

public class FarmAnimalsJAXB implements IDAO<FarmAnimal> {
    @Override
    public FarmAnimal insert(FarmAnimal data) throws Exception {
        return null;
    }

    @Override
    public int updateById(int id, FarmAnimal data) throws Exception {
        return 0;
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        return 0;
    }

    @Override
    public List<FarmAnimal> findAll() throws Exception {
        return List.of();
    }

    @Override
    public FarmAnimal findById(int id) throws Exception {
        return null;
    }

    @Override
    public void setPoolConnection(IConnection<Connection> poolConnection) {

    }
}
