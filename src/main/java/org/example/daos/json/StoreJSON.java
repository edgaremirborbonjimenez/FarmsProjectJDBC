package org.example.daos.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.domain.Animal;
import org.example.domain.Store;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoreJSON implements IDAO<Store> {

    String dataSource;
    ObjectMapper objectMapper;

    public StoreJSON(String dataSource){
        this.dataSource = dataSource;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(SerializationFeature.INDENT_OUTPUT,true);
    }

    @Override
    public Store insert(Store data) throws Exception {
        FileInputStream file = new FileInputStream(dataSource);
        List<Store> list;
        if (file.available()!=0) {
            Store[] arr = this.objectMapper.readValue(file,Store[].class);
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
    public int updateById(int id, Store data) throws Exception {
        int[] updated = {-1};
        FileInputStream file = new FileInputStream(dataSource);
        List<Store> list;
        if (file.available()!=0) {
            Store[] arr = this.objectMapper.readValue(file,Store[].class);
            list = new ArrayList<>(Arrays.stream(arr).toList());
        }else{
            return updated[0];
        }
        list.forEach(store -> {
            if(store.getId() == id){
                store.setName(data.getName());
                store.setAddress(data.getAddress());
                updated[0] = 1;
            }
        });
        objectMapper.writeValue(new File(dataSource),list);
        return updated[0];    }

    @Override
    public int deleteById(Integer id) throws Exception {
        int[] deleted = {-1};
        FileInputStream file = new FileInputStream(dataSource);
        List<Store> list;
        if (file.available()!=0) {
            Store[] arr = this.objectMapper.readValue(file,Store[].class);
            list = new ArrayList<>(Arrays.stream(arr).toList());
        }else{
            return deleted[0];
        }
        List<Store> filtered = list.stream().filter(store -> {
            if(store.getId() == id){
                deleted[0] = 1;
                return false;
            }
            return true;
        }).toList();
        objectMapper.writeValue(new File(dataSource),filtered);
        return deleted[0];
    }

    @Override
    public List<Store> findAll() throws Exception {
        FileInputStream file = new FileInputStream(dataSource);
        List<Store> list;
        if (file.available()!=0) {
            Store[] arr = this.objectMapper.readValue(file,Store[].class);
            list = new ArrayList<>(Arrays.stream(arr).toList());
        }else{
            list = new ArrayList<>();
        }
        return list;
    }

    @Override
    public Store findById(int id) throws Exception {
        FileInputStream file = new FileInputStream(dataSource);
        List<Store> list;
        if (file.available()!=0) {
            Store[] arr = this.objectMapper.readValue(file,Store[].class);
            list = new ArrayList<>(Arrays.stream(arr).toList());
        }else{
            return null;
        }
        for(Store store:list){
            if (store.getId() == id) {
                return store;
            }
        }
        return null;
    }

    @Override
    public void setPoolConnection(IConnection<Connection> poolConnection) {

    }
}
