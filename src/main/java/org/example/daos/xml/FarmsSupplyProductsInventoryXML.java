package org.example.daos.xml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.FarmSupplyProductBought;
import org.example.domain.FarmSupplyProductInventory;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;
import org.example.utils.saxhandlers.FarmSupplyProductInventoryHandler;
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

public class FarmsSupplyProductsInventoryXML implements IDAO<FarmSupplyProductInventory>{

private static final Logger logger = LogManager.getLogger();

    FarmSupplyProductInventoryHandler handler;
String xmlPathName;

public FarmsSupplyProductsInventoryXML(String xmlPathName, FarmSupplyProductInventoryHandler handler){
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
public FarmSupplyProductInventory insert(FarmSupplyProductInventory data) throws Exception {
    File xmlFile = new File(xmlPathName);
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse(xmlFile);

    Element farmSupplyProductInventory = doc.createElement("farmSupplyProductInventory");
//    int newId = handler.getLatestIndex();
    NodeList nodeList = doc.getElementsByTagName("farmSupplyProductInventory");
    Element lastNode = (Element) nodeList.item(nodeList.getLength()-1);
    int newId = Integer.parseInt(lastNode.getAttribute("id"))+1;
    farmSupplyProductInventory.setAttribute("id",Integer.toString(newId));

    Element amount = doc.createElement("amount");
    amount.appendChild(doc.createTextNode(data.getAmount().toString()));

    farmSupplyProductInventory.appendChild(amount);

    Element farmId = doc.createElement("farmId");
    farmId.appendChild(doc.createTextNode(data.getFarm_id().toString()));

    farmSupplyProductInventory.appendChild(farmId);

    Element productId = doc.createElement("productId");
    productId.appendChild(doc.createTextNode(data.getProduct_id().toString()));

    farmSupplyProductInventory.appendChild(productId);

    doc.getDocumentElement().appendChild(farmSupplyProductInventory);

    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    DOMSource source = new DOMSource(doc);
    StreamResult result = new StreamResult(xmlFile);
    transformer.transform(source,result);

    logger.info("New FarmSupplyProductInventory was created successfully");
    return findById(newId);
}


@Override
public int updateById(int id, FarmSupplyProductInventory data) throws Exception {
    File xmlFile = new File(xmlPathName);
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse(xmlFile);

    NodeList farmSupplyProductInventoryList = doc.getElementsByTagName("farmSupplyProductInventory");

    int updated = 0;

    for(int i = 0; i < farmSupplyProductInventoryList.getLength(); i++){
        Node farmSupplyProductInventory = farmSupplyProductInventoryList.item(i);
        Element farmSupplyProductInventoryElement = (Element) farmSupplyProductInventory;
        if(farmSupplyProductInventoryElement.getAttribute("id").equalsIgnoreCase(Integer.toString(id))){
            if(data.getAmount() != null && data.getAmount() > 0){
                NodeList amountList = farmSupplyProductInventoryElement.getElementsByTagName("amount");
                if(amountList.getLength()>0){
                    Element amountElement = (Element) amountList.item(0);
                    amountElement.setTextContent(data.getAmount().toString());
                }
            }
            if(data.getFarm_id() != null && data.getFarm_id() > 0){
                NodeList farmIdtList = farmSupplyProductInventoryElement.getElementsByTagName("farmId");
                if(farmIdtList.getLength()>0){
                    Element farmIdElement = (Element) farmIdtList.item(0);
                    farmIdElement.setTextContent(data.getFarm_id().toString());
                }
            }
            if(data.getProduct_id() != null && data.getProduct_id() > 0){
                NodeList productIdList = farmSupplyProductInventoryElement.getElementsByTagName("productId");
                if(productIdList.getLength()>0){
                    Element productIdElement = (Element) productIdList.item(0);
                    productIdElement.setTextContent(data.getProduct_id().toString());
                }
            }
            updated = 1;
            break;
        }
    }
    if(updated == 0){
        logger.info("FarmSupplyProductInventory with id "+id+" not found");
        return updated;
    }

    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    DOMSource source = new DOMSource(doc);
    StreamResult result = new StreamResult(xmlFile);
    transformer.transform(source,result);
    logger.info("FarmSupplyProductInventory with id {} successfully updated", id);
    return updated;
}

@Override
public int deleteById(Integer id) throws Exception {
    File xmlFile = new File(xmlPathName);
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse(xmlFile);

    NodeList farmSupplyProductInventoryList = doc.getElementsByTagName("farmSupplyProductInventory");

    int deleted = 0;

    for(int i = 0; i < farmSupplyProductInventoryList.getLength(); i++){
        Node farmSupplyProductInventory = farmSupplyProductInventoryList.item(i);
        Element farmSupplyProductInventoryElement = (Element) farmSupplyProductInventory;
        if(farmSupplyProductInventoryElement.getAttribute("id").equalsIgnoreCase(Integer.toString(id))){
            farmSupplyProductInventory.getParentNode().removeChild(farmSupplyProductInventory);
            deleted = 1;
            break;
        }
    }
    if(deleted == 0){
        logger.info("FarmSupplyProductInventory with id "+id+" not found");
        return deleted;
    }

    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    DOMSource source = new DOMSource(doc);
    StreamResult result = new StreamResult(xmlFile);
    transformer.transform(source,result);
    logger.info("FarmSupplyProductInventory with id {} successfully deleted", id);
    return deleted;
}

@Override
public List<FarmSupplyProductInventory> findAll() throws Exception {
    loadParser();
    return handler.getFarmSupplyProductInventoryList();
}

@Override
public FarmSupplyProductInventory findById(int id) throws Exception {
    loadParser();
    List<FarmSupplyProductInventory> farmSupplyProductInventorysFiltered = handler.getFarmSupplyProductInventoryList().stream().filter(f->f.getId() == id).toList();
    if(farmSupplyProductInventorysFiltered.isEmpty()){
        return null;
    }
    return farmSupplyProductInventorysFiltered.getFirst();
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

