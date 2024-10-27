package org.example.daos.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.domain.Animal;
import org.example.domain.Farm;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FarmJSON implements IDAO<Farm> {
    ObjectMapper objectMapper;
    String dataSource;

    public FarmJSON(String dataSource){
        this.dataSource = dataSource;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(SerializationFeature.INDENT_OUTPUT,true);
    }

    @Override
    public Farm insert(Farm data) throws Exception {
        FileInputStream file = new FileInputStream(dataSource);
        List<Farm> list;
        if (file.available()!=0) {
            Farm[] arr = this.objectMapper.readValue(file,Farm[].class);
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
    public int updateById(int id, Farm data) throws Exception {
        int[] updated = {-1};
        FileInputStream file = new FileInputStream(dataSource);
        List<Farm> list;
        if (file.available()!=0) {
            Farm[] arr = this.objectMapper.readValue(file,Farm[].class);
            list = new ArrayList<>(Arrays.stream(arr).toList());
        }else{
            return updated[0];
        }
        list.forEach(f -> {
            if(f.getId() == id){
                f.setName(data.getName());
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
        List<Farm> list;
        if (file.available()!=0) {
            Farm[] arr = this.objectMapper.readValue(file,Farm[].class);
            list = new ArrayList<>(Arrays.stream(arr).toList());
        }else{
            return deleted[0];
        }
        List<Farm> filtered = list.stream().filter(f -> {
            if(f.getId() == id){
                deleted[0] = 1;
                return false;
            }
            return true;
        }).toList();
        objectMapper.writeValue(new File(dataSource),filtered);
        return deleted[0];
    }

    @Override
    public List<Farm> findAll() throws Exception {
        FileInputStream file = new FileInputStream(dataSource);
        List<Farm> list;
        if (file.available()!=0) {
            Farm[] arr = this.objectMapper.readValue(file,Farm[].class);
            list = new ArrayList<>(Arrays.stream(arr).toList());
        }else{
            list = new ArrayList<>();
        }
        return list;
    }

    @Override
    public Farm findById(int id) throws Exception {
        FileInputStream file = new FileInputStream(dataSource);
        List<Farm> list;
        if (file.available()!=0) {
            Farm[] arr = this.objectMapper.readValue(file,Farm[].class);
            list = new ArrayList<>(Arrays.stream(arr).toList());
        }else{
            return null;
        }
        for(Farm f:list){
            if (f.getId() == id) {
                return f;
            }
        }
        return null;
    }

    @Override
    public void setPoolConnection(IConnection<Connection> poolConnection) {

    }
}
