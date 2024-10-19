package org.example.daos.xml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.Animal;
import org.example.domain.Owner;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;
import org.example.utils.saxhandlers.OwnerHandler;
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

public class OwnerXML implements IDAO<Owner> {
    private static final Logger logger = LogManager.getLogger();

    OwnerHandler handler;
    String xmlPathName;

    public OwnerXML(String xmlPathName, OwnerHandler handler){
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
    public Owner insert(Owner data) throws Exception {
        File xmlFile = new File(xmlPathName);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        Element newOwner = doc.createElement("owner");
//        int newId = handler.getLatestIndex();
        NodeList nodeList = doc.getElementsByTagName("owner");
        Element lastNode = (Element) nodeList.item(nodeList.getLength()-1);
        int newId = Integer.parseInt(lastNode.getAttribute("id"))+1;
        newOwner.setAttribute("id",Integer.toString(newId));

        Element fullName = doc.createElement("fullName");
        fullName.appendChild(doc.createTextNode(data.getFullName()));

        newOwner.appendChild(fullName);

        Element phone = doc.createElement("phone");
        phone.appendChild(doc.createTextNode(data.getPhone()));

        newOwner.appendChild(phone);

        Element email = doc.createElement("email");
        email.appendChild(doc.createTextNode(data.getEmail()));

        newOwner.appendChild(email);

        doc.getDocumentElement().appendChild(newOwner);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(xmlFile);
        transformer.transform(source,result);

        logger.info("New newOwner was created successfully");
        return findById(newId);
    }

    @Override
    public int updateById(int id, Owner data) throws Exception {
        File xmlFile = new File(xmlPathName);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        NodeList ownerList = doc.getElementsByTagName("owner");

        int updated = 0;

        for(int i = 0; i < ownerList.getLength(); i++){
            Node owner = ownerList.item(i);
            Element ownerElement = (Element) owner;
            if(ownerElement.getAttribute("id").equalsIgnoreCase(Integer.toString(id))){
                if(data.getFullName() != null && !data.getFullName().isBlank()){
                    NodeList fullNameList = ownerElement.getElementsByTagName("fullName");
                    if(fullNameList.getLength()>0){
                        Element fullNameElement = (Element) fullNameList.item(0);
                        fullNameElement.setTextContent(data.getFullName());
                    }
                }
                if(data.getPhone() != null && !data.getPhone().isBlank()){
                    NodeList phoneList = ownerElement.getElementsByTagName("phone");
                    if(phoneList.getLength()>0){
                        Element phoneElement = (Element) phoneList.item(0);
                        phoneElement.setTextContent(data.getPhone());
                    }
                }
                if(data.getEmail() != null && !data.getEmail().isBlank()){
                    NodeList emailList = ownerElement.getElementsByTagName("email");
                    if(emailList.getLength()>0){
                        Element emailElement = (Element) emailList.item(0);
                        emailElement.setTextContent(data.getEmail());
                    }
                }
                updated = 1;
                break;
            }
        }
        if(updated == 0){
            logger.info("Owner with id "+id+" not found");
            return updated;
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(xmlFile);
        transformer.transform(source,result);
        logger.info("Owner with id {} successfully updated", id);
        return updated;
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        File xmlFile = new File(xmlPathName);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        NodeList ownersList = doc.getElementsByTagName("owner");

        int deleted = 0;

        for(int i = 0; i < ownersList.getLength(); i++){
            Node owner = ownersList.item(i);
            Element ownerElement = (Element) owner;
            if(ownerElement.getAttribute("id").equalsIgnoreCase(Integer.toString(id))){
                owner.getParentNode().removeChild(owner);
                deleted = 1;
                break;
            }
        }
        if(deleted == 0){
            logger.info("Owner with id "+id+" not found");
            return deleted;
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(xmlFile);
        transformer.transform(source,result);
        logger.info("Owner with id {} successfully deleted", id);
        return deleted;
    }

    @Override
    public List<Owner> findAll() throws Exception {
        loadParser();
        return handler.getOwnerList();
    }

    @Override
    public Owner findById(int id) throws Exception {
        loadParser();
        List<Owner> ownersFiltered = handler.getOwnerList().stream().filter(owner->owner.getId() == id).toList();
        if(ownersFiltered.isEmpty()){
            return null;
        }
        return ownersFiltered.getFirst();
    }

    @Override
    public void setPoolConnection(IConnection<Connection> poolConnection) {
        System.out.println("There is no implementation");
    }

    private void loadParser()throws Exception{
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("src/main/java/org/example/utils/xsd/owner.xsd"));

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
