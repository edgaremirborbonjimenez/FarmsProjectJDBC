package org.example.mvc.interfaces;

public interface GenericController <T>{
    void create(T data);
    void update(int id,T data);
    void delete(int id);
}
