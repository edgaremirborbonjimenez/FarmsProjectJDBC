package org.example.utils.marshallers;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.Animal;
import org.example.domain.jaxb.Animals;
import org.example.interfaces.IMarsheller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class AnimalMarshaller implements IMarsheller<Animals> {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void marshall(Animals data) throws IOException, JAXBException {
        Properties properties = new Properties();
        try (FileReader input = new FileReader("src/main/resources/env.properties")){
            properties.load(input);
        }catch (FileNotFoundException e){
            logger.error(e.getMessage());
        }
        JAXBContext context = JAXBContext.newInstance(Animals.class);
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
        mar.marshal(data,new File(properties.getProperty("xml.jaxb.animal")));
    }

    @Override
    public Animals unmarshall()throws IOException, JAXBException {
        Properties properties = new Properties();
        try (FileReader input = new FileReader("src/main/resources/env.properties")){
            properties.load(input);
        }catch (FileNotFoundException e){
            logger.error(e.getMessage());
        }
        JAXBContext context = JAXBContext.newInstance(Animals.class);
        return (Animals) context.createUnmarshaller().unmarshal(new File(properties.getProperty("xml.jaxb.animal")));
    }

    @Override
    public void setDataSource(String uri) {

    }
}
