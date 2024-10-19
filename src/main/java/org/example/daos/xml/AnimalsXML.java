package org.example.daos.xml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.Animal;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;
import org.example.utils.saxhandlers.AnimalsHandler;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.sql.Connection;
import java.util.List;

public class AnimalsXML implements IDAO<Animal> {

    private static final Logger logger = LogManager.getLogger();

    AnimalsHandler handler;
    String xmlPathName;

    public AnimalsXML(String xmlPathName,AnimalsHandler handler){
        this.xmlPathName = xmlPathName;
        this.handler = handler;
    }

    public void setXmlPathName(String xmlPathName) {
        this.xmlPathName = xmlPathName;
    }

    public String getXmlPathName() {
        return xmlPathName;
    }

    @Override
    public Animal insert(Animal data) throws Exception {
        File xmlFile = new File(xmlPathName);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        Element newAnimal = doc.createElement("animal");
//        int newId = handler.getLatestIndex();
        NodeList nodeList = doc.getElementsByTagName("animal");
        Element lastNode = (Element) nodeList.item(nodeList.getLength()-1);
        int newId = Integer.parseInt(lastNode.getAttribute("id"))+1;
        newAnimal.setAttribute("id",Integer.toString(newId));

        Element name = doc.createElement("name");
        name.appendChild(doc.createTextNode(data.getName()));

        newAnimal.appendChild(name);
        doc.getDocumentElement().appendChild(newAnimal);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(xmlFile);
        transformer.transform(source,result);

        logger.info("New Animal was created successfully");
        return findById(newId);
    }


    @Override
    public int updateById(int id, Animal data) throws Exception {
        File xmlFile = new File(xmlPathName);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        NodeList animalsList = doc.getElementsByTagName("animal");

        int updated = 0;

        for(int i = 0; i < animalsList.getLength(); i++){
            Node animal = animalsList.item(i);
            Element animalElement = (Element) animal;
            if(animalElement.getAttribute("id").equalsIgnoreCase(Integer.toString(id))){
                if(data.getName() != null && !data.getName().isBlank()){
                    NodeList nameList = animalElement.getElementsByTagName("name");
                    if(nameList.getLength()>0){
                        Element nameElement = (Element) nameList.item(0);
                        nameElement.setTextContent(data.getName());
                    }
                }
                updated = 1;
                break;
            }
        }
        if(updated == 0){
            logger.info("Animal with id "+id+" not found");
            return updated;
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(xmlFile);
        transformer.transform(source,result);
        logger.info("Animal with id {} successfully updated", id);
        return updated;
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        File xmlFile = new File(xmlPathName);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        NodeList animalsList = doc.getElementsByTagName("animal");

        int deleted = 0;

        for(int i = 0; i < animalsList.getLength(); i++){
            Node animal = animalsList.item(i);
            Element animalElement = (Element) animal;
            if(animalElement.getAttribute("id").equalsIgnoreCase(Integer.toString(id))){
                animal.getParentNode().removeChild(animal);
                deleted = 1;
                break;
            }
        }
        if(deleted == 0){
            logger.info("Animal with id "+id+" not found");
            return deleted;
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(xmlFile);
        transformer.transform(source,result);
        logger.info("Animal with id {} successfully deleted", id);
        return deleted;
    }

    @Override
    public List<Animal> findAll() throws Exception {
        loadParser();
        return handler.getAnimalList();
    }

    @Override
    public Animal findById(int id) throws Exception {
        loadParser();
        List<Animal> animalsFiltered = handler.getAnimalList().stream().filter(animal->animal.getId() == id).toList();
        if(animalsFiltered.isEmpty()){
            return null;
        }
        return animalsFiltered.getFirst();
    }

    @Override
    public void setPoolConnection(IConnection<Connection> poolConnection) {
        System.out.println("There is no implementation here");
    }

    private void loadParser()throws Exception{
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("src/main/java/org/example/utils/xsd/animal.xsd"));

//        String schemaLang = "http://www.w3.org/2001/XMLSchema"; // Option 2 Validator
//        SchemaFactory schemaFactory = SchemaFactory.newInstance(schemaLang); // Option 2 Validator
//        Schema schema = schemaFactory.newSchema(new File("src/main/java/org/example/utils/xsd/animal.xsd")); // Option 2 Validator


        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        saxParserFactory.setNamespaceAware(true);
        saxParserFactory.setSchema(schema);
        SAXParser saxParser = saxParserFactory.newSAXParser();
//        XMLReader xmlReader = saxParser.getXMLReader();// Option 2 Validator
//        xmlReader.parse(new InputSource(xmlPathName));// Option 2 Validator
        saxParser.parse(new File(xmlPathName),handler);
    }
}
