package org.example.mvc.interfaces;

import java.io.Serializable;

public interface GenericModel <T> extends GenericViewModel<T>{
    T create(T data);
    T update(int id,T data);
    T delete(int id);
}
