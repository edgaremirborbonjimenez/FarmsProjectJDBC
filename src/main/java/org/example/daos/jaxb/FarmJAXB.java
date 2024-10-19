package org.example.daos.jaxb;

import org.example.domain.Animal;
import org.example.domain.Farm;
import org.example.domain.jaxb.Animals;
import org.example.domain.jaxb.Farms;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;
import org.example.interfaces.IMarsheller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FarmJAXB implements IDAO<Farm> {
    IMarsheller<Farms> farmsIMarsheller;

    public FarmJAXB(IMarsheller<Farms> farmsIMarsheller){
        this.farmsIMarsheller = farmsIMarsheller;
    }

    @Override
    public Farm insert(Farm data) throws Exception {
        Farms sourceData = farmsIMarsheller.unmarshall();
        Farm[] arr = sourceData.getFarms();
        List<Farm> list = new ArrayList<>(Arrays.stream(arr).toList());
        int index = list.getLast().getId()+1;
        data.setId(index);
        list.add(data);
        sourceData.setFarms(list.toArray(new Farm[0]));
        farmsIMarsheller.marshall(sourceData);
        return data;
    }

    @Override
    public int updateById(int id, Farm data) throws Exception {
        int[] updated = {-1};
        Farms sourceData = farmsIMarsheller.unmarshall();
        Farm[] arr = sourceData.getFarms();
        List<Farm> list = new ArrayList<>(Arrays.stream(arr).toList());
        list.forEach(farm -> {
            if(farm.getId() == id){
                farm.setName(data.getName());
                farm.setAddress(data.getAddress());
                farm.setOwner_id(data.getOwner_id());
                updated[0] = 1;
            }
        });
        sourceData.setFarms(list.toArray(new Farm[0]));
        farmsIMarsheller.marshall(sourceData);
        return updated[0];
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        int[] deleted = {-1};
        Farms sourceData = farmsIMarsheller.unmarshall();
        Farm[] arr = sourceData.getFarms();
        List<Farm> list = new ArrayList<>(Arrays.stream(arr).toList());
        List<Farm> filtered = list.stream().filter(farm -> {
            if(farm.getId() != id){
                deleted[0] = 0;
                return false;
            }
            return true;
        }).toList();
        sourceData.setFarms(filtered.toArray(new Farm[0]));
        farmsIMarsheller.marshall(sourceData);
        return deleted[0];
    }

    @Override
    public List<Farm> findAll() throws Exception {
        Farms sourceData = farmsIMarsheller.unmarshall();
        Farm[] arr = sourceData.getFarms();
        return new ArrayList<>(Arrays.stream(arr).toList());
    }

    @Override
    public Farm findById(int id) throws Exception {
        Farms sourceData = farmsIMarsheller.unmarshall();
        Farm[] arr = sourceData.getFarms();
        return (Farm) Arrays.stream(arr).filter(farm -> farm.getId() == id).toArray()[0];
    }

    @Override
    public void setPoolConnection(IConnection<Connection> poolConnection) {

    }
}
