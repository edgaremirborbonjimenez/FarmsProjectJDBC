package org.example.daos.xml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.FarmSupplyProductBought;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;
import org.example.utils.saxhandlers.FarmsSupplyProductBoughtHandler;
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

public class FarmsSupplyProductsBoughtXML implements IDAO<FarmSupplyProductBought> {

    private static final Logger logger = LogManager.getLogger();

    FarmsSupplyProductBoughtHandler handler;
    String xmlPathName;

    public FarmsSupplyProductsBoughtXML(String xmlPathName,FarmsSupplyProductBoughtHandler handler){
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
    public FarmSupplyProductBought insert(FarmSupplyProductBought data) throws Exception {
        File xmlFile = new File(xmlPathName);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        Element newFarmSupplyProductBought = doc.createElement("farmSupplyProductBought");
//        int newId = handler.getLatestIndex();
        NodeList nodeList = doc.getElementsByTagName("farmSupplyProductBought");
        Element lastNode = (Element) nodeList.item(nodeList.getLength()-1);
        int newId = Integer.parseInt(lastNode.getAttribute("id"))+1;
        newFarmSupplyProductBought.setAttribute("id",Integer.toString(newId));

        Element amount = doc.createElement("amount");
        amount.appendChild(doc.createTextNode(data.getAmount().toString()));

        newFarmSupplyProductBought.appendChild(amount);

        Element total = doc.createElement("total");
        total.appendChild(doc.createTextNode(data.getTotal().toString()));

        newFarmSupplyProductBought.appendChild(total);

        Element purchaseDate = doc.createElement("purchaseDate");
        purchaseDate.appendChild(doc.createTextNode(data.getPurchaseDate().toString()));

        newFarmSupplyProductBought.appendChild(purchaseDate);

        Element farmId = doc.createElement("farmId");
        farmId.appendChild(doc.createTextNode(data.getFarm_id().toString()));

        newFarmSupplyProductBought.appendChild(farmId);

        Element productId = doc.createElement("productId");
        productId.appendChild(doc.createTextNode(data.getProduct_id().toString()));

        newFarmSupplyProductBought.appendChild(productId);

        doc.getDocumentElement().appendChild(newFarmSupplyProductBought);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(xmlFile);
        transformer.transform(source,result);

        logger.info("New FarmSupplyProductBought was created successfully");
        return findById(newId);
    }


    @Override
    public int updateById(int id, FarmSupplyProductBought data) throws Exception {
        File xmlFile = new File(xmlPathName);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        NodeList farmSupplyProductBoughtsList = doc.getElementsByTagName("farmSupplyProductBought");

        int updated = 0;

        for(int i = 0; i < farmSupplyProductBoughtsList.getLength(); i++){
            Node farmSupplyProductBought = farmSupplyProductBoughtsList.item(i);
            Element farmSupplyProductBoughtElement = (Element) farmSupplyProductBought;
            if(farmSupplyProductBoughtElement.getAttribute("id").equalsIgnoreCase(Integer.toString(id))){
                if(data.getAmount() != null && data.getAmount() > 0){
                    NodeList amountList = farmSupplyProductBoughtElement.getElementsByTagName("amount");
                    if(amountList.getLength()>0){
                        Element amountElement = (Element) amountList.item(0);
                        amountElement.setTextContent(data.getAmount().toString());
                    }
                }
                if(data.getTotal() != null && data.getTotal() > 0){
                    NodeList totalList = farmSupplyProductBoughtElement.getElementsByTagName("total");
                    if(totalList.getLength()>0){
                        Element totalElement = (Element) totalList.item(0);
                        totalElement.setTextContent(data.getTotal().toString());
                    }
                }
                if(data.getPurchaseDate() != null){
                    NodeList purchaseDateList = farmSupplyProductBoughtElement.getElementsByTagName("purchaseDate");
                    if(purchaseDateList.getLength()>0){
                        Element purchaseDateElement = (Element) purchaseDateList.item(0);
                        purchaseDateElement.setTextContent(data.getPurchaseDate().toString());
                    }
                }
                if(data.getFarm_id() != null && data.getFarm_id() > 0){
                    NodeList farmIdtList = farmSupplyProductBoughtElement.getElementsByTagName("farmId");
                    if(farmIdtList.getLength()>0){
                        Element farmIdElement = (Element) farmIdtList.item(0);
                        farmIdElement.setTextContent(data.getFarm_id().toString());
                    }
                }
                if(data.getProduct_id() != null && data.getProduct_id() > 0){
                    NodeList productIdList = farmSupplyProductBoughtElement.getElementsByTagName("productId");
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
            logger.info("FarmSupplyProductBought with id "+id+" not found");
            return updated;
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(xmlFile);
        transformer.transform(source,result);
        logger.info("FarmSupplyProductBought with id {} successfully updated", id);
        return updated;
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        File xmlFile = new File(xmlPathName);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        NodeList farmSupplyProductBoughtsList = doc.getElementsByTagName("farmSupplyProductBought");

        int deleted = 0;

        for(int i = 0; i < farmSupplyProductBoughtsList.getLength(); i++){
            Node farmSupplyProductBought = farmSupplyProductBoughtsList.item(i);
            Element farmSupplyProductBoughtElement = (Element) farmSupplyProductBought;
            if(farmSupplyProductBoughtElement.getAttribute("id").equalsIgnoreCase(Integer.toString(id))){
                farmSupplyProductBought.getParentNode().removeChild(farmSupplyProductBought);
                deleted = 1;
                break;
            }
        }
        if(deleted == 0){
            logger.info("FarmSupplyProductBought with id "+id+" not found");
            return deleted;
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(xmlFile);
        transformer.transform(source,result);
        logger.info("FarmSupplyProductBought with id {} successfully deleted", id);
        return deleted;
    }

    @Override
    public List<FarmSupplyProductBought> findAll() throws Exception {
        loadParser();
        return handler.getFarmSupplyProductBoughtList();
    }

    @Override
    public FarmSupplyProductBought findById(int id) throws Exception {
        loadParser();
        List<FarmSupplyProductBought> farmSupplyProductBoughtsFiltered = handler.getFarmSupplyProductBoughtList().stream().filter(farmSupplyProductBought->farmSupplyProductBought.getId() == id).toList();
        if(farmSupplyProductBoughtsFiltered.isEmpty()){
            return null;
        }
        return farmSupplyProductBoughtsFiltered.getFirst();
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

