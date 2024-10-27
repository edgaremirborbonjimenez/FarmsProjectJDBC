package org.example.utils.myBatis.mappers;

import org.apache.ibatis.annotations.*;
import org.example.domain.Product;

import java.util.List;

public interface ProductMapper extends GenericMapper<Product> {
    @Override
    @Insert("Insert into products(name,price,unit_measurement) values (#{product.name},#{product.price},#{product.unitMeasurement})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer insert(@Param("product") Product data);

    @Override
    @Update("Update products set name = #{product.name}, price = #{product.price}, unit_measurement = #{product.unitMeasurement} where id =#{id}")
    Integer updateById(@Param("id") int id, @Param("product") Product data);

    @Override
    @Delete("Delete from products where id = #{id}")
    Integer deleteById(int id);

    @Override
    @Results({
            @Result(column = "id", property = "id",id = true),
            @Result(column = "name",property = "name"),
            @Result(column = "unit_measurement",property = "unitMeasurement"),
            @Result(column = "price", property = "price")
    })
    @Select("Select id,name,price,unit_measurement from products")
    List<Product> findAll();

    @Override
    @Results({
            @Result(column = "id", property = "id",id = true),
            @Result(column = "name",property = "name"),
            @Result(column = "unit_measurement",property = "unitMeasurement"),
            @Result(column = "price", property = "price")
    })
    @Select("Select id,name,price,unit_measurement from products where id =#{id}")
    Product findById(int id);
}
