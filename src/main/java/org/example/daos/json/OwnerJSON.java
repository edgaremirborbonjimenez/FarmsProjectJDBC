package org.example.daos.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.domain.Animal;
import org.example.domain.Owner;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OwnerJSON implements IDAO<Owner> {

    String dataSource;
    ObjectMapper objectMapper;

    public OwnerJSON(String dataSource){
        this.dataSource = dataSource;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(SerializationFeature.INDENT_OUTPUT,true);
    }

    @Override
    public Owner insert(Owner data) throws Exception {
        FileInputStream file = new FileInputStream(dataSource);
        List<Owner> list;
        if (file.available()!=0) {
            Owner[] arr = this.objectMapper.readValue(file,Owner[].class);
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
    public int updateById(int id, Owner data) throws Exception {
        int[] updated = {-1};
        FileInputStream file = new FileInputStream(dataSource);
        List<Owner> list;
        if (file.available()!=0) {
            Owner[] arr = this.objectMapper.readValue(file,Owner[].class);
            list = new ArrayList<>(Arrays.stream(arr).toList());
        }else{
            return updated[0];
        }
        list.forEach(owner -> {
            if(owner.getId() == id){
                owner.setFullName(data.getFullName());
                owner.setPhone(data.getPhone());
                owner.setEmail(data.getEmail());
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
        List<Owner> list;
        if (file.available()!=0) {
            Owner[] arr = this.objectMapper.readValue(file,Owner[].class);
            list = new ArrayList<>(Arrays.stream(arr).toList());
        }else{
            return deleted[0];
        }
        List<Owner> filtered = list.stream().filter(owner -> {
            if(owner.getId() == id){
                deleted[0] = 1;
                return false;
            }
            return true;
        }).toList();
        objectMapper.writeValue(new File(dataSource),filtered);
        return deleted[0];
    }

    @Override
    public List<Owner> findAll() throws Exception {
        FileInputStream file = new FileInputStream(dataSource);
        List<Owner> list;
        if (file.available()!=0) {
            Owner[] arr = this.objectMapper.readValue(file,Owner[].class);
            list = new ArrayList<>(Arrays.stream(arr).toList());
        }else{
            list = new ArrayList<>();
        }
        return list;
    }

    @Override
    public Owner findById(int id) throws Exception {
        FileInputStream file = new FileInputStream(dataSource);
        List<Owner> list;
        if (file.available()!=0) {
            Owner[] arr = this.objectMapper.readValue(file,Owner[].class);
            list = new ArrayList<>(Arrays.stream(arr).toList());
        }else{
            return null;
        }
        for(Owner owner:list){
            if (owner.getId() == id) {
                return owner;
            }
        }
        return null;
    }

    @Override
    public void setPoolConnection(IConnection<Connection> poolConnection) {

    }
}
