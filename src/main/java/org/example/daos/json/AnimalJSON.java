package org.example.daos.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.domain.Animal;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnimalJSON implements IDAO<Animal> {

    String dataSource;
    ObjectMapper objectMapper;

    public AnimalJSON(String dataSource){
        this.dataSource = dataSource;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(SerializationFeature.INDENT_OUTPUT,true);
    }

    @Override
    public Animal insert(Animal data) throws Exception {
        FileInputStream file = new FileInputStream(dataSource);
        List<Animal> list;
        if (file.available()!=0) {
            Animal[] arr = this.objectMapper.readValue(file,Animal[].class);
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
    public int updateById(int id, Animal data) throws Exception {
        int[] updated = {-1};
        FileInputStream file = new FileInputStream(dataSource);
        List<Animal> list;
        if (file.available()!=0) {
            Animal[] arr = this.objectMapper.readValue(file,Animal[].class);
            list = new ArrayList<>(Arrays.stream(arr).toList());
        }else{
            return updated[0];
        }
        list.forEach(animal -> {
            if(animal.getId() == id){
                animal.setName(data.getName());
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
        List<Animal> list;
        if (file.available()!=0) {
            Animal[] arr = this.objectMapper.readValue(file,Animal[].class);
            list = new ArrayList<>(Arrays.stream(arr).toList());
        }else{
            return deleted[0];
        }
        List<Animal> filtered = list.stream().filter(animal -> {
            if(animal.getId() == id){
                deleted[0] = 1;
                return false;
            }
            return true;
        }).toList();
        objectMapper.writeValue(new File(dataSource),filtered);
        return deleted[0];
    }

    @Override
    public List<Animal> findAll() throws Exception {
        FileInputStream file = new FileInputStream(dataSource);
        List<Animal> list;
        if (file.available()!=0) {
            Animal[] arr = this.objectMapper.readValue(file,Animal[].class);
            list = new ArrayList<>(Arrays.stream(arr).toList());
        }else{
            list = new ArrayList<>();
        }
        return list;
    }

    @Override
    public Animal findById(int id) throws Exception {
        FileInputStream file = new FileInputStream(dataSource);
        List<Animal> list;
        if (file.available()!=0) {
            Animal[] arr = this.objectMapper.readValue(file,Animal[].class);
            list = new ArrayList<>(Arrays.stream(arr).toList());
        }else{
            return null;
        }
        for(Animal animal:list){
            if (animal.getId() == id) {
                return animal;
            }
        }
        return null;
    }

    @Override
    public void setPoolConnection(IConnection<Connection> poolConnection) {

    }
}
