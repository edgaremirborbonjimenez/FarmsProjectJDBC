package org.example.daos.jaxb;

import org.example.domain.Animal;
import org.example.domain.Store;
import org.example.domain.jaxb.Animals;
import org.example.domain.jaxb.Stores;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;
import org.example.interfaces.IMarsheller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoreJAXB implements IDAO<Store> {

    IMarsheller<Stores> storesIMarsheller;

    public StoreJAXB(IMarsheller<Stores> storesIMarsheller){
        this.storesIMarsheller = storesIMarsheller;
    }

    @Override
    public Store insert(Store data) throws Exception {
        Stores sourceData = storesIMarsheller.unmarshall();
        if(sourceData == null){
            sourceData = new Stores();
        }
        Store[] arr = sourceData.getStores();
        List<Store> list = new ArrayList<>(Arrays.stream(arr).toList());
        int index = 1;
        if(!list.isEmpty()){
            index = list.getLast().getId()+1;
        }
        data.setId(index);
        list.add(data);
        sourceData.setStores(list.toArray(new Store[0]));
        storesIMarsheller.marshall(sourceData);
        return data;
    }

    @Override
    public int updateById(int id, Store data) throws Exception {
        int[] updated = {-1};
        Stores sourceData = storesIMarsheller.unmarshall();
        if(sourceData ==null){
            return updated[0];
        }
        Store[] arr = sourceData.getStores();
        List<Store> list = new ArrayList<>(Arrays.stream(arr).toList());
        list.forEach(store -> {
            if(store.getId() == id){
                store.setName(data.getName());
                store.setAddress(data.getAddress());
                updated[0] = 1;
            }
        });
        sourceData.setStores(list.toArray(new Store[0]));
        storesIMarsheller.marshall(sourceData);
        return updated[0];
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        int[] deleted = {-1};
        Stores sourceData = storesIMarsheller.unmarshall();
        if(sourceData ==null){
            return deleted[0];
        }
        Store[] arr = sourceData.getStores();
        List<Store> list = new ArrayList<>(Arrays.stream(arr).toList());
        List<Store> filtered = list.stream().filter(store -> {
            if(store.getId() == id){
                deleted[0] = 1;
                return false;
            }
            return true;
        }).toList();
        sourceData.setStores(filtered.toArray(new Store[0]));
        storesIMarsheller.marshall(sourceData);
        return deleted[0];
    }

    @Override
    public List<Store> findAll() throws Exception {
        Stores sourceData = storesIMarsheller.unmarshall();
        if(sourceData ==null){
            return null;
        }
        Store[] arr = sourceData.getStores();
        return new ArrayList<>(Arrays.stream(arr).toList());
    }

    @Override
    public Store findById(int id) throws Exception {
        Stores sourceData = storesIMarsheller.unmarshall();
        if(sourceData ==null){
            return null;
        }
        Store[] arr = sourceData.getStores();

        for (Store store : arr) {
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
