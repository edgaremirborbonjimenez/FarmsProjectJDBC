package org.example.services;

import org.example.domain.Product;
import org.example.interfaces.IDAO;
import org.example.interfaces.IService;

import java.util.List;

public class ProductService implements IService<Product> {
    IDAO<Product> productIDAO;

    public ProductService(IDAO<Product> productIDAO){
        this.productIDAO = productIDAO;
    }

    @Override
    public Product insert(Product data) throws Exception {
        return this.productIDAO.insert(data);
    }

    @Override
    public int updateById(int id, Product data) throws Exception {
        return this.productIDAO.updateById(id,data);
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        return this.productIDAO.deleteById(id);
    }

    @Override
    public List<Product> findAll() throws Exception {
        return this.productIDAO.findAll();
    }

    @Override
    public Product findById(int id) throws Exception {
        return this.productIDAO.findById(id);
    }
}
