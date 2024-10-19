package org.example.daos.jaxb;

import org.example.domain.Animal;
import org.example.domain.jaxb.Animals;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;
import org.example.interfaces.IMarsheller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnimalJAXB implements IDAO<Animal> {

    IMarsheller<Animals> animalsIMarsheller;

    public AnimalJAXB(IMarsheller<Animals> animalsIMarsheller){
        this.animalsIMarsheller = animalsIMarsheller;
    }
    @Override
    public Animal insert(Animal data) throws Exception {
        Animals sourceData = animalsIMarsheller.unmarshall();
        if(sourceData == null){
            sourceData = new Animals();
        }
        Animal[] arr = sourceData.getAnimals();
        List<Animal> list = new ArrayList<>(Arrays.stream(arr).toList());
        int index = 1;
        if(!list.isEmpty()){
            index = list.getLast().getId()+1;
        }
        data.setId(index);
        list.add(data);
        sourceData.setAnimals(list.toArray(new Animal[0]));
        animalsIMarsheller.marshall(sourceData);
        return data;
    }

    //Solve problem with xmlEmpty
    @Override
    public int updateById(int id, Animal data) throws Exception {
        int[] updated = {-1};
        Animals sourceData = animalsIMarsheller.unmarshall();
        if(sourceData ==null){

            return updated[0];
        }
        Animal[] arr = sourceData.getAnimals();
        List<Animal> list = new ArrayList<>(Arrays.stream(arr).toList());
        list.forEach(animal -> {
            if(animal.getId() == id){
                animal.setName(data.getName());
                updated[0] = 1;
            }
        });
        sourceData.setAnimals(list.toArray(new Animal[0]));
        animalsIMarsheller.marshall(sourceData);
        return updated[0];
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        int[] deleted = {-1};
        Animals sourceData = animalsIMarsheller.unmarshall();
        Animal[] arr = sourceData.getAnimals();
        List<Animal> list = new ArrayList<>(Arrays.stream(arr).toList());
        List<Animal> filtered = list.stream().filter(animal -> {
            if(animal.getId() != id){
                deleted[0] = 0;
                return false;
            }
            return true;
        }).toList();
        sourceData.setAnimals(filtered.toArray(new Animal[0]));
        animalsIMarsheller.marshall(sourceData);
        return deleted[0];
    }

    @Override
    public List<Animal> findAll() throws Exception {
        Animals sourceData = animalsIMarsheller.unmarshall();
        Animal[] arr = sourceData.getAnimals();
        return new ArrayList<>(Arrays.stream(arr).toList());
    }

    @Override
    public Animal findById(int id) throws Exception {
        Animals sourceData = animalsIMarsheller.unmarshall();
        Animal[] arr = sourceData.getAnimals();
        return (Animal) Arrays.stream(arr).filter(animal -> animal.getId() == id).toArray()[0];
    }

    @Override
    public void setPoolConnection(IConnection<Connection> poolConnection) {

    }
}
