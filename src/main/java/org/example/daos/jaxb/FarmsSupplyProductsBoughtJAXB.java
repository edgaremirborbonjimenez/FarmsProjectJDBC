package org.example.daos.jaxb;

import org.example.domain.Animal;
import org.example.domain.FarmSupplyProductBought;
import org.example.domain.jaxb.Animals;
import org.example.domain.jaxb.FarmSuppliesProductBought;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;
import org.example.interfaces.IMarsheller;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FarmsSupplyProductsBoughtJAXB implements IDAO<FarmSupplyProductBought> {

    IMarsheller<FarmSuppliesProductBought> farmsuppliesB;

    public FarmsSupplyProductsBoughtJAXB(IMarsheller<FarmSuppliesProductBought> farmsuppliesB){
        this.farmsuppliesB = farmsuppliesB;
    }

    @Override
    public FarmSupplyProductBought insert(FarmSupplyProductBought data) throws Exception {
        FarmSuppliesProductBought sourceData = farmsuppliesB.unmarshall();
        if(sourceData == null){
            sourceData = new FarmSuppliesProductBought();
        }
        FarmSupplyProductBought[] arr = sourceData.getFarmSuppliesProductsBought();
        List<FarmSupplyProductBought> list = new ArrayList<>(Arrays.stream(arr).toList());
        int index = 1;
        if(!list.isEmpty()){
            index = list.getLast().getId()+1;
        }
        data.setId(index);
        data.setPurchaseDate(new Date(System.currentTimeMillis()));
        list.add(data);
        sourceData.setFarmSuppliesProductsBought(list.toArray(new FarmSupplyProductBought[0]));
        farmsuppliesB.marshall(sourceData);
        return data;
    }

    @Override
    public int updateById(int id, FarmSupplyProductBought data) throws Exception {
        int[] updated = {-1};
        FarmSuppliesProductBought sourceData = farmsuppliesB.unmarshall();
        if(sourceData ==null){
            return updated[0];
        }
        FarmSupplyProductBought[] arr = sourceData.getFarmSuppliesProductsBought();
        List<FarmSupplyProductBought> list = new ArrayList<>(Arrays.stream(arr).toList());
        list.forEach(f -> {
            if(f.getId() == id){
                f.setProduct_id(data.getProduct_id());
                f.setFarm_id(data.getFarm_id());
                f.setAmount(data.getAmount());
                f.setTotal(data.getTotal());
                updated[0] = 1;
            }
        });
        sourceData.setFarmSuppliesProductsBought(list.toArray(new FarmSupplyProductBought[0]));
        farmsuppliesB.marshall(sourceData);
        return updated[0];
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        int[] deleted = {-1};
        FarmSuppliesProductBought sourceData = farmsuppliesB.unmarshall();
        if(sourceData ==null){
            return deleted[0];
        }
        FarmSupplyProductBought[] arr = sourceData.getFarmSuppliesProductsBought();
        List<FarmSupplyProductBought> list = new ArrayList<>(Arrays.stream(arr).toList());
        List<FarmSupplyProductBought> filtered = list.stream().filter(f -> {
            if(Objects.equals(f.getId(), id)){
                deleted[0] = 1;
                return false;
            }
            return true;
        }).toList();
        sourceData.setFarmSuppliesProductsBought(filtered.toArray(new FarmSupplyProductBought[0]));
        farmsuppliesB.marshall(sourceData);
        return deleted[0];
    }

    @Override
    public List<FarmSupplyProductBought> findAll() throws Exception {
        FarmSuppliesProductBought sourceData = farmsuppliesB.unmarshall();
        if(sourceData ==null){
            return null;
        }
        FarmSupplyProductBought[] arr = sourceData.getFarmSuppliesProductsBought();
        return new ArrayList<>(Arrays.stream(arr).toList());
    }

    @Override
    public FarmSupplyProductBought findById(int id) throws Exception {
        FarmSuppliesProductBought sourceData = farmsuppliesB.unmarshall();
        if(sourceData ==null){
            return null;
        }
        FarmSupplyProductBought[] arr = sourceData.getFarmSuppliesProductsBought();

        for (FarmSupplyProductBought f : arr) {
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
