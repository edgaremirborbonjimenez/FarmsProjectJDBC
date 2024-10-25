package org.example.utils.myBatis.mappers;

import org.apache.ibatis.annotations.*;
import org.example.domain.Animal;
import org.example.domain.Farm;

import java.util.List;

public interface FarmMapper extends GenericMapper<Farm> {
    @Override
    @Insert("Insert into farms(name,address,owner_id) values (#{name},#{address},#{owner_id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer insert(Farm farm);

    @Override
    @Update("Update farms set name = #{farm.name}, address = #{farm.address}, owner_id = #{farm.owner_id} where id =#{id}")
    Integer updateById(@Param("id") int id, @Param("farm") Farm farm);

    @Override
    @Delete("Delete from farms where id = #{id}")
    Integer deleteById(int id);

    @Override
    @Results({
            @Result(column = "id", property = "id",id = true),
            @Result(column = "name",property = "name"),
            @Result(column = "address",property = "address"),
            @Result(column = "owner_id",property = "owner_id"),
    })
    @Select("Select id,name,address,owner_id from farms")
    List<Farm> findAll();

    @Results({
            @Result(column = "id", property = "id",id = true),
            @Result(column = "name",property = "name"),
            @Result(column = "address",property = "address"),
            @Result(column = "owner_id",property = "owner_id")
    })
    @Override
    @Select("Select id,name,address,owner_id from farms where id =#{id}")
    Farm findById(int id);

}
