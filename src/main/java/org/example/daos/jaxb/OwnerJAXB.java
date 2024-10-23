package org.example.daos.jaxb;

import org.example.domain.Animal;
import org.example.domain.Owner;
import org.example.domain.jaxb.Animals;
import org.example.domain.jaxb.Owners;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;
import org.example.interfaces.IMarsheller;
import org.example.utils.marshallers.GenericMarshaller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OwnerJAXB implements IDAO<Owner> {

    IMarsheller<Owners> ownersIMarsheller;

    public OwnerJAXB(IMarsheller<Owners> ownersIMarsheller){
        this.ownersIMarsheller = ownersIMarsheller;
    }

    @Override
    public Owner insert(Owner data) throws Exception {
        Owners sourceData = ownersIMarsheller.unmarshall();
        if(sourceData == null){
            sourceData = new Owners();
        }
        Owner[] arr = sourceData.getOwners();
        List<Owner> list = new ArrayList<>(Arrays.stream(arr).toList());
        int index = 1;
        if(!list.isEmpty()){
            index = list.getLast().getId()+1;
        }
        data.setId(index);
        list.add(data);
        sourceData.setOwners(list.toArray(new Owner[0]));
        ownersIMarsheller.marshall(sourceData);
        return data;
    }

    @Override
    public int updateById(int id, Owner data) throws Exception {
        int[] updated = {-1};
        Owners sourceData = ownersIMarsheller.unmarshall();
        if(sourceData ==null){
            return updated[0];
        }
        Owner[] arr = sourceData.getOwners();
        List<Owner> list = new ArrayList<>(Arrays.stream(arr).toList());
        list.forEach(owner -> {
            if(owner.getId() == id){
                owner.setFullName(data.getFullName());
                owner.setPhone(data.getPhone());
                owner.setEmail(data.getEmail());
                updated[0] = 1;
            }
        });
        sourceData.setOwners(list.toArray(new Owner[0]));
        ownersIMarsheller.marshall(sourceData);
        return updated[0];
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        int[] deleted = {-1};
        Owners sourceData = ownersIMarsheller.unmarshall();
        if(sourceData ==null){
            return deleted[0];
        }
        Owner[] arr = sourceData.getOwners();
        List<Owner> list = new ArrayList<>(Arrays.stream(arr).toList());
        List<Owner> filtered = list.stream().filter(owner -> {
            if(owner.getId() == id){
                deleted[0] = 1;
                return false;
            }
            return true;
        }).toList();
        sourceData.setOwners(filtered.toArray(new Owner[0]));
        ownersIMarsheller.marshall(sourceData);
        return deleted[0];
    }

    @Override
    public List<Owner> findAll() throws Exception {
        Owners sourceData = ownersIMarsheller.unmarshall();
        if(sourceData ==null){
            return null;
        }
        Owner[] arr = sourceData.getOwners();
        return new ArrayList<>(Arrays.stream(arr).toList());
    }

    @Override
    public Owner findById(int id) throws Exception {
        Owners sourceData = ownersIMarsheller.unmarshall();
        if(sourceData ==null){
            return null;
        }
        Owner[] arr = sourceData.getOwners();

        for (Owner owner : arr) {
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
