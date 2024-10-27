package org.example.utils.myBatis.mappers;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.domain.Animal;

import java.util.List;

public interface GenericMapper <T>{
    Integer insert(T data);
    Integer updateById(int id, T data);
    Integer deleteById(int id);
    List<T> findAll();
    T findById(int id);
}
