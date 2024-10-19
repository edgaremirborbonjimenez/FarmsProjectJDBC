package org.example.daos.jaxb;

import org.example.domain.Product;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;

import java.sql.Connection;
import java.util.List;

public class ProductJAXB implements IDAO<Product> {
    @Override
    public Product insert(Product data) throws Exception {
        return null;
    }

    @Override
    public int updateById(int id, Product data) throws Exception {
        return 0;
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        return 0;
    }

    @Override
    public List<Product> findAll() throws Exception {
        return List.of();
    }

    @Override
    public Product findById(int id) throws Exception {
        return null;
    }

    @Override
    public void setPoolConnection(IConnection<Connection> poolConnection) {

    }
}
