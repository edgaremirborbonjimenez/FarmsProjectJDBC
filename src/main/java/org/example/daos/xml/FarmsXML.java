package org.example.daos.xml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.Farm;
import org.example.domain.Owner;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;
import org.example.utils.saxhandlers.FarmHandler;
import org.example.utils.saxhandlers.OwnerHandler;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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

public class FarmsXML implements IDAO<Farm> {
    private static final Logger logger = LogManager.getLogger();

    FarmHandler handler;
    String xmlPathName;

    public FarmsXML(String xmlPathName, FarmHandler handler){
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
    public Farm insert(Farm data) throws Exception {
        File xmlFile = new File(xmlPathName);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        Element newFarm = doc.createElement("farm");
//        int newId = handler.getLatestIndex();
        NodeList nodeList = doc.getElementsByTagName("farm");
        Element lastNode = (Element) nodeList.item(nodeList.getLength()-1);
        int newId = Integer.parseInt(lastNode.getAttribute("id"))+1;
        newFarm.setAttribute("id",Integer.toString(newId));

        Element name = doc.createElement("name");
        name.appendChild(doc.createTextNode(data.getName()));

        newFarm.appendChild(name);

        Element address = doc.createElement("address");
        address.appendChild(doc.createTextNode(data.getAddress()));

        newFarm.appendChild(address);

        Element ownerId = doc.createElement("ownerId");
        ownerId.appendChild(doc.createTextNode(Integer.toString(data.getOwner_id())));

        newFarm.appendChild(ownerId);

        doc.getDocumentElement().appendChild(newFarm);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(xmlFile);
        transformer.transform(source,result);

        logger.info("New Farm was created successfully");
        return findById(newId);
    }

    @Override
    public int updateById(int id, Farm data) throws Exception {
        File xmlFile = new File(xmlPathName);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        NodeList farmList = doc.getElementsByTagName("farm");

        int updated = 0;

        for(int i = 0; i < farmList.getLength(); i++){
            Node farm = farmList.item(i);
            Element farmElement = (Element) farm;
            if(farmElement.getAttribute("id").equalsIgnoreCase(Integer.toString(id))){
                if(data.getName() != null && !data.getName().isBlank()){
                    NodeList nameList = farmElement.getElementsByTagName("name");
                    if(nameList.getLength()>0){
                        Element nameElement = (Element) nameList.item(0);
                        nameElement.setTextContent(data.getName());
                    }
                }
                if(data.getAddress() != null && !data.getAddress().isBlank()){
                    NodeList addressList = farmElement.getElementsByTagName("address");
                    if(addressList.getLength()>0){
                        Element addressElement = (Element) addressList.item(0);
                        addressElement.setTextContent(data.getAddress());
                    }
                }
                if(data.getOwner_id() > 0){
                    NodeList ownerIdList = farmElement.getElementsByTagName("ownerId");
                    if(ownerIdList.getLength()>0){
                        Element ownerIdElement = (Element) ownerIdList.item(0);
                        ownerIdElement.setTextContent(Integer.toString(data.getOwner_id()));
                    }
                }
                updated = 1;
                break;
            }
        }
        if(updated == 0){
            logger.info("Farm with id "+id+" not found");
            return updated;
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(xmlFile);
        transformer.transform(source,result);
        logger.info("Farm with id {} successfully updated", id);
        return updated;
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        File xmlFile = new File(xmlPathName);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        NodeList farmsList = doc.getElementsByTagName("farm");

        int deleted = 0;

        for(int i = 0; i < farmsList.getLength(); i++){
            Node farm = farmsList.item(i);
            Element farmElement = (Element) farm;
            if(farmElement.getAttribute("id").equalsIgnoreCase(Integer.toString(id))){
                farm.getParentNode().removeChild(farm);
                deleted = 1;
                break;
            }
        }
        if(deleted == 0){
            logger.info("Farm with id "+id+" not found");
            return deleted;
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(xmlFile);
        transformer.transform(source,result);
        logger.info("Farm with id {} successfully deleted", id);
        return deleted;
    }

    @Override
    public List<Farm> findAll() throws Exception {
        loadParser();
        return handler.getFarmList();
    }

    @Override
    public Farm findById(int id) throws Exception {
        loadParser();
        List<Farm> farmsFiltered = handler.getFarmList().stream().filter(farm->farm.getId() == id).toList();
        if(farmsFiltered.isEmpty()){
            return null;
        }
        return farmsFiltered.getFirst();
    }

    @Override
    public void setPoolConnection(IConnection<Connection> poolConnection) {
        System.out.println("There is no implementation");
    }

    private void loadParser()throws Exception{
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("src/main/java/org/example/utils/xsd/farm.xsd"));

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

