package org.example.daos.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.domain.Animal;
import org.example.domain.Product;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductJSON implements IDAO<Product> {

    String dataSource;
    ObjectMapper objectMapper;

    public ProductJSON(String dataSource){
        this.dataSource = dataSource;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(SerializationFeature.INDENT_OUTPUT,true);
    }

    @Override
    public Product insert(Product data) throws Exception {
        FileInputStream file = new FileInputStream(dataSource);
        List<Product> list;
        if (file.available()!=0) {
            Product[] arr = this.objectMapper.readValue(file,Product[].class);
            list = new ArrayList<>(Arrays.stream(arr).toList());
        }else{
            list = new ArrayList<>();
        }
        int index = 1;
        if(!list.isEmpty()){
            index = list.getLast().getId()+1;
        }
        data.setId(index);
        list.add(data);
        objectMapper.writeValue(new File(dataSource),list);
        return data;
    }

    @Override
    public int updateById(int id, Product data) throws Exception {
        int[] updated = {-1};
        FileInputStream file = new FileInputStream(dataSource);
        List<Product> list;
        if (file.available()!=0) {
            Product[] arr = this.objectMapper.readValue(file,Product[].class);
            list = new ArrayList<>(Arrays.stream(arr).toList());
        }else{
            return updated[0];
        }
        list.forEach(product -> {
            if(product.getId() == id){
                product.setName(data.getName());
                product.setPrice(data.getPrice());
                product.setUnitMeasurement(data.getUnitMeasurement());
                updated[0] = 1;
            }
        });
        objectMapper.writeValue(new File(dataSource),list);
        return updated[0];
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        int[] deleted = {-1};
        FileInputStream file = new FileInputStream(dataSource);
        List<Product> list;
        if (file.available()!=0) {
            Product[] arr = this.objectMapper.readValue(file,Product[].class);
            list = new ArrayList<>(Arrays.stream(arr).toList());
        }else{
            return deleted[0];
        }
        List<Product> filtered = list.stream().filter(product -> {
            if(product.getId() == id){
                deleted[0] = 1;
                return false;
            }
            return true;
        }).toList();
        objectMapper.writeValue(new File(dataSource),filtered);
        return deleted[0];
    }

    @Override
    public List<Product> findAll() throws Exception {
        FileInputStream file = new FileInputStream(dataSource);
        List<Product> list;
        if (file.available()!=0) {
            Product[] arr = this.objectMapper.readValue(file,Product[].class);
            list = new ArrayList<>(Arrays.stream(arr).toList());
        }else{
            list = new ArrayList<>();
        }
        return list;
    }

    @Override
    public Product findById(int id) throws Exception {
        FileInputStream file = new FileInputStream(dataSource);
        List<Product> list;
        if (file.available()!=0) {
            Product[] arr = this.objectMapper.readValue(file,Product[].class);
            list = new ArrayList<>(Arrays.stream(arr).toList());
        }else{
            return null;
        }
        for(Product product:list){
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
