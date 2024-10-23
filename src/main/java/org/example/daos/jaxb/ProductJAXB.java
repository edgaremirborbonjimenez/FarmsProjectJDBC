package org.example.daos.jaxb;

import org.example.domain.Animal;
import org.example.domain.Product;
import org.example.domain.jaxb.Animals;
import org.example.domain.jaxb.Products;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;
import org.example.interfaces.IMarsheller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductJAXB implements IDAO<Product> {

    IMarsheller<Products> productsIMarsheller;

    public ProductJAXB(IMarsheller<Products> productsIMarsheller){
        this.productsIMarsheller = productsIMarsheller;
    }

    @Override
    public Product insert(Product data) throws Exception {
        Products sourceData = productsIMarsheller.unmarshall();
        if(sourceData == null){
            sourceData = new Products();
        }
        Product[] arr = sourceData.getProducts();
        List<Product> list = new ArrayList<>(Arrays.stream(arr).toList());
        int index = 1;
        if(!list.isEmpty()){
            index = list.getLast().getId()+1;
        }
        data.setId(index);
        list.add(data);
        sourceData.setProducts(list.toArray(new Product[0]));
        productsIMarsheller.marshall(sourceData);
        return data;
    }

    @Override
    public int updateById(int id, Product data) throws Exception {
        int[] updated = {-1};
        Products sourceData = productsIMarsheller.unmarshall();
        if(sourceData ==null){
            return updated[0];
        }
        Product[] arr = sourceData.getProducts();
        List<Product> list = new ArrayList<>(Arrays.stream(arr).toList());
        list.forEach(product -> {
            if(product.getId() == id){
                product.setName(data.getName());
                product.setPrice(data.getPrice());
                product.setUnitMeasurement(data.getUnitMeasurement());
                updated[0] = 1;
            }
        });
        sourceData.setProducts(list.toArray(new Product[0]));
        productsIMarsheller.marshall(sourceData);
        return updated[0];
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        int[] deleted = {-1};
        Products sourceData = productsIMarsheller.unmarshall();
        if(sourceData ==null){
            return deleted[0];
        }
        Product[] arr = sourceData.getProducts();
        List<Product> list = new ArrayList<>(Arrays.stream(arr).toList());
        List<Product> filtered = list.stream().filter(product -> {
            if(product.getId() == id){
                deleted[0] = 1;
                return false;
            }
            return true;
        }).toList();
        sourceData.setProducts(filtered.toArray(new Product[0]));
        productsIMarsheller.marshall(sourceData);
        return deleted[0];
    }

    @Override
    public List<Product> findAll() throws Exception {
        Products sourceData = productsIMarsheller.unmarshall();
        if(sourceData ==null){
            return null;
        }
        Product[] arr = sourceData.getProducts();
        return new ArrayList<>(Arrays.stream(arr).toList());
    }

    @Override
    public Product findById(int id) throws Exception {
        Products sourceData = productsIMarsheller.unmarshall();
        if(sourceData ==null){
            return null;
        }
        Product[] arr = sourceData.getProducts();

        for (Product product : arr) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void setPoolConnection(IConnection<Connection> poolConnection) {

    }
}
