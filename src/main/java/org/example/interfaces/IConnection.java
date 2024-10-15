package org.example.interfaces;

import org.example.exceptions.ConnectionException;

import java.sql.Connection;

public interface IConnection<T>{
    T getConnectionFromPool()throws ConnectionException;
    void releaseConnection(T connection) throws ConnectionException;
    void startPool()throws ConnectionException;
    void setPoolSize(int size)throws ConnectionException;
}
