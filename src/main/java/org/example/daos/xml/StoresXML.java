package org.example.daos.xml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.Store;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;
import org.example.utils.saxhandlers.StoreHandler;
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

public class StoresXML implements IDAO<Store> {

    private static final Logger logger = LogManager.getLogger();

    StoreHandler handler;
    String xmlPathName;

    public StoresXML(String xmlPathName,StoreHandler handler){
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
    public Store insert(Store data) throws Exception {
        File xmlFile = new File(xmlPathName);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        Element newStore = doc.createElement("store");
//        int newId = handler.getLatestIndex();
        NodeList nodeList = doc.getElementsByTagName("store");
        Element lastNode = (Element) nodeList.item(nodeList.getLength()-1);
        int newId = Integer.parseInt(lastNode.getAttribute("id"))+1;
        newStore.setAttribute("id",Integer.toString(newId));

        Element name = doc.createElement("name");
        name.appendChild(doc.createTextNode(data.getName()));

        newStore.appendChild(name);

        Element address = doc.createElement("address");
        address.appendChild(doc.createTextNode(data.getAddress()));

        newStore.appendChild(address);


        doc.getDocumentElement().appendChild(newStore);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(xmlFile);
        transformer.transform(source,result);

        logger.info("New Store was created successfully");
        return findById(newId);
    }


    @Override
    public int updateById(int id, Store data) throws Exception {
        File xmlFile = new File(xmlPathName);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        NodeList storesList = doc.getElementsByTagName("store");

        int updated = 0;

        for(int i = 0; i < storesList.getLength(); i++){
            Node store = storesList.item(i);
            Element storeElement = (Element) store;
            if(storeElement.getAttribute("id").equalsIgnoreCase(Integer.toString(id))){
                if(data.getName() != null && !data.getName().isBlank()){
                    NodeList nameList = storeElement.getElementsByTagName("name");
                    if(nameList.getLength()>0){
                        Element nameElement = (Element) nameList.item(0);
                        nameElement.setTextContent(data.getName());
                    }
                }
                if(data.getAddress() != null && !data.getAddress().isBlank()){
                    NodeList addressList = storeElement.getElementsByTagName("address");
                    if(addressList.getLength()>0){
                        Element addressElement = (Element) addressList.item(0);
                        addressElement.setTextContent(data.getAddress());
                    }
                }
                updated = 1;
                break;
            }
        }
        if(updated == 0){
            logger.info("Store with id "+id+" not found");
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

        NodeList storesList = doc.getElementsByTagName("store");

        int deleted = 0;

        for(int i = 0; i < storesList.getLength(); i++){
            Node store = storesList.item(i);
            Element storeElement = (Element) store;
            if(storeElement.getAttribute("id").equalsIgnoreCase(Integer.toString(id))){
                store.getParentNode().removeChild(store);
                deleted = 1;
                break;
            }
        }
        if(deleted == 0){
            logger.info("Store with id "+id+" not found");
            return deleted;
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(xmlFile);
        transformer.transform(source,result);
        logger.info("Store with id {} successfully deleted", id);
        return deleted;
    }

    @Override
    public List<Store> findAll() throws Exception {
        loadParser();
        return handler.getStoreList();
    }

    @Override
    public Store findById(int id) throws Exception {
        loadParser();
        List<Store> storesFiltered = handler.getStoreList().stream().filter(store->store.getId() == id).toList();
        if(storesFiltered.isEmpty()){
            return null;
        }
        return storesFiltered.getFirst();
    }

    @Override
    public void setPoolConnection(IConnection<Connection> poolConnection) {
        System.out.println("There is no implementation here");
    }

    private void loadParser()throws Exception{
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("src/main/java/org/example/utils/xsd/store.xsd"));

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

