package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.daos.jaxb.FarmsSupplyProductsBoughtJAXB;
import org.example.daos.mybatis.*;
import org.example.domain.*;
import org.example.interfaces.IDAO;
import org.example.utils.enums.UnitMeasurement;
import org.example.utils.myBatis.mappers.AnimalMapper;

import java.sql.Date;


public class Main {

    private static final Logger logger = LogManager.getLogger();


    public static void main(String[] args) throws Exception {
        IDAO< Animal> animalIDAO = new AnimalMyBatis();
//        System.out.println(animalIDAO.findById(1));

        Animal animal = new Animal();
        animal.setName("Goat");
//        System.out.println(animalIDAO.insert(animal));
        animal.setName("Cabra");
//        System.out.println(animalIDAO.updateById(20,animal));
//        System.out.println(animalIDAO.deleteById(12));
//        animalIDAO.findAll().forEach(System.out::println);
//        System.out.println(animalIDAO.findById(9));

        IDAO<Owner> ownerIDAO = new OwnerMyBatis();

        Owner owner = new Owner();
        owner.setFullName("Marco");
        owner.setPhone("654654654");
        owner.setEmail("marco@gmail.com");
//        System.out.println(ownerIDAO.insert(owner));
//        System.out.println();
//        System.out.println();
//        System.out.println(ownerIDAO.updateById(13,owner));
//        System.out.println(ownerIDAO.findById(1));
//        System.out.println(ownerIDAO.deleteById(13));
//        ownerIDAO.findAll().forEach(System.out::println);

        IDAO<Farm> farmIDAO = new FarmMyBatis();
        Farm farm = new Farm();
        farm.setName("Granjita");
        farm.setAddress("Rancho");
        farm.setOwner_id(1);
//        System.out.println(farmIDAO.insert(farm));
        farm.setAddress("Villa");
//        System.out.println(farmIDAO.updateById(12,farm));
//        System.out.println(farmIDAO.deleteById(12));
//        System.out.println(farmIDAO.findById(8));
//        farmIDAO.findAll().forEach(System.out::println);

        IDAO<Product> productIDAO = new ProductMyBatis();
        Product product = new Product();
        product.setName("Honey");
        product.setUnitMeasurement(UnitMeasurement.L.unit);
        product.setPrice(5);
//        System.out.println(productIDAO.insert(product));
//        System.out.println(productIDAO.updateById(6,product));
//        System.out.println(productIDAO.findById(6));
//        productIDAO.findAll().forEach(System.out::println);

        IDAO<Store> storeIDAO = new StoreMyBatis();
        Store store = new Store();
        store.setName("Tiendita");
        store.setAddress("Street 54");
//        System.out.println(storeIDAO.insert(store));
        store.setName("Tienditita");
//        System.out.println(storeIDAO.updateById(8,store));
//        System.out.println(storeIDAO.deleteById(8));
//        System.out.println(storeIDAO.findById(5));
//        storeIDAO.findAll().forEach(System.out::println);

        IDAO<FarmSupplyProductBought> farmSupplyDAO = new FarmSupplyProductBoughtMyBatis();
        FarmSupplyProductBought f = new FarmSupplyProductBought();
        f.setPurchaseDate(new Date(System.currentTimeMillis()));
        f.setTotal(50d);
        f.setAmount(10);
        f.setProduct_id(1);
        f.setFarm_id(1);
//        System.out.println(farmSupplyDAO.insert(f));

        f.setAmount(5);
        f.setTotal(25d);
//        farmSupplyDAO.updateById(7,f);
//        farmSupplyDAO.deleteById(7);
//        System.out.println(farmSupplyDAO.findById(4));
//        farmSupplyDAO.findAll().forEach(System.out::println);
    }

}