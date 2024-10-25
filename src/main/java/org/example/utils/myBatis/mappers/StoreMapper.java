package org.example.utils.myBatis.mappers;

import org.apache.ibatis.annotations.*;
import org.example.domain.Store;

import java.util.List;

public interface StoreMapper extends GenericMapper<Store> {

    @Override
    @Insert("Insert into stores(name,address) values (#{name},#{address})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer insert(Store data);

    @Override
    @Update("Update stores set name = #{store.name}, address = #{store.address} where id = #{id}")
    Integer updateById(@Param("id") int id, @Param("store") Store data);

    @Override
    @Delete("Delete from stores where id = #{id}")
    Integer deleteById(int id);

    @Override
    @Results({
            @Result(column = "id", property = "id",id = true),
            @Result(column = "name",property = "name"),
            @Result(column = "address",property = "address")
    })
    @Select("Select id,name,address from stores where id = #{id}")
    Store findById(int id);

    @Override
    @Results({
            @Result(column = "id", property = "id",id = true),
            @Result(column = "name",property = "name"),
            @Result(column = "address",property = "address")
    })
    @Select("Select id,name,address from stores")
    List<Store> findAll();
}
