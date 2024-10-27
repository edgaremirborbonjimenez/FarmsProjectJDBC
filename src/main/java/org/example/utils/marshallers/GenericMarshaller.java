package org.example.utils.marshallers;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.jaxb.Animals;
import org.example.interfaces.IMarsheller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class GenericMarshaller <T> implements IMarsheller<T> {
    private static final Logger logger = LogManager.getLogger();
    String uriDataSource;
    Class<T> clazz; //Class type stored
    public GenericMarshaller(String uriDataSource,Class<T> clazz){
        this.uriDataSource = uriDataSource;
        this.clazz = clazz;
    }

    @Override
    public void marshall(T data) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(clazz);
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
        mar.marshal(data,new File(uriDataSource));
    }

    @Override
    public T unmarshall() throws JAXBException, IOException {
        File file = new File(uriDataSource);
        if (!file.exists() || file.length() == 0) {
            return null;
        }
        JAXBContext context = JAXBContext.newInstance(clazz);
        return clazz.cast(context.createUnmarshaller().unmarshal(file));
    }

    @Override
    public void setDataSource(String uriDataSource) {
        this.uriDataSource = uriDataSource;
    }
}
