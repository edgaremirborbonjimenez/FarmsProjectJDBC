package org.example.utils.myBatis.mappers;

import org.apache.ibatis.annotations.*;
import org.example.domain.Owner;

import java.util.List;

public interface OwnerMapper extends GenericMapper<Owner> {

    @Override
    @Insert("Insert into owners(full_name,phone,email) values (#{owner.fullName},#{owner.phone},#{owner.email})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer insert(@Param("owner") Owner data);

    @Override
    @Update("Update owners set full_name = #{owner.fullName},phone = #{owner.phone},email = #{owner.email} where id =#{id}")
    Integer updateById(@Param("id") int id, @Param("owner") Owner data);

    @Override
    @Delete("Delete from owners where id = #{id}")
    Integer deleteById(int id);

    @Override
    @Results({
            @Result(column = "id", property = "id",id = true),
            @Result(column = "full_name",property = "fullName"),
            @Result(column = "phone",property = "phone"),
            @Result(column = "email",property = "email")
    })
    @Select("Select  id,full_name,phone,email from owners")
    List<Owner> findAll();

    @Override
    @Results({
            @Result(column = "id", property = "id",id = true),
            @Result(column = "full_name",property = "fullName"),
            @Result(column = "phone",property = "phone"),
            @Result(column = "email",property = "email")
    })
    @Select("Select id,full_name,phone,email from owners where id =#{id}")
    Owner findById(int id);
}
