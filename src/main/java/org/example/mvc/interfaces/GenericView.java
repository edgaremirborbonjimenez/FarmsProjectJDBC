package org.example.mvc.interfaces;

public interface GenericView <T>{
    void showCreated(T data);
    void showUpdated(T data);
    void showDeleted(T data);
    void showAll();
    void showById();
    void showMenu();
}
