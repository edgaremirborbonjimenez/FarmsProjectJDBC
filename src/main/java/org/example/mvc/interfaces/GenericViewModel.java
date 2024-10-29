package org.example.mvc.interfaces;

import java.util.List;

public interface GenericViewModel <T>{
    List<T> findAll();
    T findById(int id);
}
