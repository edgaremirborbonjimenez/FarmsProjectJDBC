package org.example.utils.myBatis.mappers;

import org.apache.ibatis.annotations.*;
import org.example.domain.Animal;

import java.util.List;

@Mapper
public interface AnimalMapper extends GenericMapper<Animal>{

    @Override
    @Insert("Insert into animals(name) values (#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer insert(Animal animal);

    @Override
    @Update("Update animals set name = #{animal.name} where id =#{id}")
    Integer updateById(@Param("id") int id,@Param("animal") Animal animal);

    @Override
    @Delete("Delete from animals where id = #{id}")
    Integer deleteById(int id);

    @Override
    @Results({
            @Result(column = "id", property = "id",id = true),
            @Result(column = "name",property = "name")
    })
    @Select("Select id,name from animals")
    List<Animal> findAll();

    @Results({
            @Result(column = "id", property = "id",id = true),
            @Result(column = "name",property = "name")
    })
    @Override
    @Select("Select id,name from animals where id =#{id}")
    Animal findById(int id);

}
