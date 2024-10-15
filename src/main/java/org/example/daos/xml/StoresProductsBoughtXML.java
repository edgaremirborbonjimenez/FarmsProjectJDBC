package org.example.daos.xml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.FarmSupplyProductBought;
import org.example.domain.StoreProductBought;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;
import org.example.utils.saxhandlers.FarmsSupplyProductBoughtHandler;
import org.example.utils.saxhandlers.StoreProductBoughtHandler;
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

public class StoresProductsBoughtXML implements IDAO<StoreProductBought> {

    private static final Logger logger = LogManager.getLogger();

    StoreProductBoughtHandler handler;
    String xmlPathName;

    public StoresProductsBoughtXML(String xmlPathName,StoreProductBoughtHandler handler){
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
    public StoreProductBought insert(StoreProductBought data) throws Exception {
        File xmlFile = new File(xmlPathName);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        Element storeProductBought = doc.createElement("storeProductBought");
        int newId = handler.getLatestIndex();
        storeProductBought.setAttribute("id",Integer.toString(newId));

        Element amount = doc.createElement("amount");
        amount.appendChild(doc.createTextNode(data.getAmount().toString()));

        storeProductBought.appendChild(amount);

        Element total = doc.createElement("total");
        total.appendChild(doc.createTextNode(data.getTotal().toString()));

        storeProductBought.appendChild(total);

        Element productId = doc.createElement("productId");
        productId.appendChild(doc.createTextNode(data.getProduct_id().toString()));

        storeProductBought.appendChild(productId);

        Element storeId = doc.createElement("storeId");
        storeId.appendChild(doc.createTextNode(data.getStore_id().toString()));

        storeProductBought.appendChild(storeId);

        doc.getDocumentElement().appendChild(storeProductBought);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(xmlFile);
        transformer.transform(source,result);

        logger.info("New StoreProductBought was created successfully");
        return findById(newId);
    }


    @Override
    public int updateById(int id, StoreProductBought data) throws Exception {
        File xmlFile = new File(xmlPathName);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        NodeList storeProductBoughtList = doc.getElementsByTagName("storeProductBought");

        int updated = 0;

        for(int i = 0; i < storeProductBoughtList.getLength(); i++){
            Node storeProductBought = storeProductBoughtList.item(i);
            Element storeProductBoughtElement = (Element) storeProductBought;
            if(storeProductBoughtElement.getAttribute("id").equalsIgnoreCase(Integer.toString(id))){
                if(data.getAmount() != null && data.getAmount() > 0){
                    NodeList amountList = storeProductBoughtElement.getElementsByTagName("amount");
                    if(amountList.getLength()>0){
                        Element amountElement = (Element) amountList.item(0);
                        amountElement.setTextContent(data.getAmount().toString());
                    }
                }
                if(data.getTotal() != null && data.getTotal() > 0){
                    NodeList totalList = storeProductBoughtElement.getElementsByTagName("total");
                    if(totalList.getLength()>0){
                        Element totalElement = (Element) totalList.item(0);
                        totalElement.setTextContent(data.getTotal().toString());
                    }
                }
                if(data.getProduct_id() != null && data.getProduct_id() > 0){
                    NodeList productIdList = storeProductBoughtElement.getElementsByTagName("productId");
                    if(productIdList.getLength()>0){
                        Element productIdElement = (Element) productIdList.item(0);
                        productIdElement.setTextContent(data.getProduct_id().toString());
                    }
                }
                if(data.getStore_id() != null && data.getStore_id() > 0){
                    NodeList farmIdtList = storeProductBoughtElement.getElementsByTagName("storeId");
                    if(farmIdtList.getLength()>0){
                        Element farmIdElement = (Element) farmIdtList.item(0);
                        farmIdElement.setTextContent(data.getStore_id().toString());
                    }
                }
                updated = 1;
                break;
            }
        }
        if(updated == 0){
            logger.info("StoreProductBought with id "+id+" not found");
            return updated;
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(xmlFile);
        transformer.transform(source,result);
        logger.info("StoreProductBought with id {} successfully updated", id);
        return updated;
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        File xmlFile = new File(xmlPathName);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        NodeList storeProductBoughtList = doc.getElementsByTagName("storeProductBought");

        int deleted = 0;

        for(int i = 0; i < storeProductBoughtList.getLength(); i++){
            Node storeProductBought = storeProductBoughtList.item(i);
            Element farmSupplyProductBoughtElement = (Element) storeProductBought;
            if(farmSupplyProductBoughtElement.getAttribute("id").equalsIgnoreCase(Integer.toString(id))){
                storeProductBought.getParentNode().removeChild(storeProductBought);
                deleted = 1;
                break;
            }
        }
        if(deleted == 0){
            logger.info("StoreProductBought with id "+id+" not found");
            return deleted;
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(xmlFile);
        transformer.transform(source,result);
        logger.info("StoreProductBought with id {} successfully deleted", id);
        return deleted;
    }

    @Override
    public List<StoreProductBought> findAll() throws Exception {
        loadParser();
        return handler.getStoreProductBoughtList();
    }

    @Override
    public StoreProductBought findById(int id) throws Exception {
        loadParser();
        List<StoreProductBought> storeProductBoughtFiltered = handler.getStoreProductBoughtList().stream().filter(s->s.getId() == id).toList();
        if(storeProductBoughtFiltered.isEmpty()){
            return null;
        }
        return storeProductBoughtFiltered.getFirst();
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

