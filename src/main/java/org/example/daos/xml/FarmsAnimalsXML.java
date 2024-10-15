package org.example.daos.xml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.Animal;
import org.example.domain.FarmAnimal;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;
import org.example.utils.saxhandlers.AnimalsHandler;
import org.example.utils.saxhandlers.FarmsAnimalsHandler;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.sql.Connection;
import java.util.List;

public class FarmsAnimalsXML implements IDAO<FarmAnimal> {

    private static final Logger logger = LogManager.getLogger();

    FarmsAnimalsHandler handler;
    String xmlPathName;

    public FarmsAnimalsXML(String xmlPathName,FarmsAnimalsHandler handler){
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
    public FarmAnimal insert(FarmAnimal data) throws Exception {
        File xmlFile = new File(xmlPathName);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        Element newFarmAnimal = doc.createElement("farmAnimal");
        int newId = handler.getLatestIndex();
        newFarmAnimal.setAttribute("id",Integer.toString(newId));

        Element amount = doc.createElement("amount");
        amount.appendChild(doc.createTextNode(data.getAmount().toString()));

        newFarmAnimal.appendChild(amount);

        Element farmId = doc.createElement("farmId");
        farmId.appendChild(doc.createTextNode(data.getFarm_id().toString()));

        newFarmAnimal.appendChild(farmId);

        Element animalId = doc.createElement("animalId");
        animalId.appendChild(doc.createTextNode(data.getAnimal_id().toString()));

        newFarmAnimal.appendChild(animalId);

        doc.getDocumentElement().appendChild(newFarmAnimal);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(xmlFile);
        transformer.transform(source,result);

        logger.info("New FarmAnimal was created successfully");
        return findById(newId);
    }


    @Override
    public int updateById(int id, FarmAnimal data) throws Exception {
        File xmlFile = new File(xmlPathName);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        NodeList farmAnimalsList = doc.getElementsByTagName("farmAnimal");

        int updated = 0;

        for(int i = 0; i < farmAnimalsList.getLength(); i++){
            Node farmAnimal = farmAnimalsList.item(i);
            Element farmAnimalElement = (Element) farmAnimal;
            if(farmAnimalElement.getAttribute("id").equalsIgnoreCase(Integer.toString(id))){
                if(data.getAmount() != null && data.getAmount() > 0){
                    NodeList amountList = farmAnimalElement.getElementsByTagName("amount");
                    if(amountList.getLength()>0){
                        Element amountElement = (Element) amountList.item(0);
                        amountElement.setTextContent(data.getAmount().toString());
                    }
                }
                updated = 1;
                break;
            }
        }
        if(updated == 0){
            logger.info("FarmAnimal with id "+id+" not found");
            return updated;
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(xmlFile);
        transformer.transform(source,result);
        logger.info("FarmAnimal with id {} successfully updated", id);
        return updated;
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        File xmlFile = new File(xmlPathName);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        NodeList farmAnimalsList = doc.getElementsByTagName("farmAnimal");

        int deleted = 0;

        for(int i = 0; i < farmAnimalsList.getLength(); i++){
            Node farmAnimal = farmAnimalsList.item(i);
            Element farmAnimalElement = (Element) farmAnimal;
            if(farmAnimalElement.getAttribute("id").equalsIgnoreCase(Integer.toString(id))){
                farmAnimal.getParentNode().removeChild(farmAnimal);
                deleted = 1;
                break;
            }
        }
        if(deleted == 0){
            logger.info("FarmAnimal with id "+id+" not found");
            return deleted;
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(xmlFile);
        transformer.transform(source,result);
        logger.info("FarmAnimal with id {} successfully deleted", id);
        return deleted;
    }

    @Override
    public List<FarmAnimal> findAll() throws Exception {
        loadParser();
        return handler.getFarmAnimalList();
    }

    @Override
    public FarmAnimal findById(int id) throws Exception {
        loadParser();
        List<FarmAnimal> farmAnimalsFiltered = handler.getFarmAnimalList().stream().filter(farmAnimal->farmAnimal.getId() == id).toList();
        if(farmAnimalsFiltered.isEmpty()){
            return null;
        }
        return farmAnimalsFiltered.getFirst();
    }

    @Override
    public void setPoolConnection(IConnection<Connection> poolConnection) {
        System.out.println("There is no implementation here");
    }

    private void loadParser()throws Exception{
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        saxParser.parse(new File(xmlPathName),handler);
    }
}

