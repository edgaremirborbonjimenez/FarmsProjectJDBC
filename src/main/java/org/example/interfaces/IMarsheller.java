package org.example.interfaces;

import jakarta.xml.bind.JAXBException;

import java.io.IOException;

public interface IMarsheller<T>{
    void marshall(T data) throws JAXBException, IOException;
    T unmarshall()throws JAXBException, IOException;
    void setDataSource(String uri);
}
