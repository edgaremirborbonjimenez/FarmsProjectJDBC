package org.example.mvc.interfaces;

import org.example.interfaces.IDAO;

public interface ConfigurationModel<T> {
    void setDAO(IDAO<T> dao);
}
