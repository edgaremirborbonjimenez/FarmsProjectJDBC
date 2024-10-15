package org.example.interfaces;

import java.util.List;

public interface IService <T>{
    T insert(T data) throws Exception;
    int updateById(int id,T data) throws Exception;
    int deleteById(Integer id) throws Exception;
    List<T> findAll()throws Exception;
    T findById(int id)throws Exception;
}
