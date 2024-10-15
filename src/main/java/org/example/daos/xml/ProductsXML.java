package org.example.daos.xml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.Animal;
import org.example.domain.Product;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;
import org.example.utils.saxhandlers.AnimalsHandler;
import org.example.utils.saxhandlers.ProducHandler;
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

public class ProductsXML implements IDAO<Product> {

    private static final Logger logger = LogManager.getLogger();

    ProducHandler handler;
    String xmlPathName;

    public ProductsXML(String xmlPathName,ProducHandler handler){
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
    public Product insert(Product data) throws Exception {
        File xmlFile = new File(xmlPathName);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        Element newProduct = doc.createElement("product");
        int newId = handler.getLatestIndex();
        newProduct.setAttribute("id",Integer.toString(newId));

        Element name = doc.createElement("name");
        name.appendChild(doc.createTextNode(data.getName()));

        newProduct.appendChild(name);

        Element price = doc.createElement("price");
        price.appendChild(doc.createTextNode(Double.toString(data.getPrice())));

        newProduct.appendChild(price);

        Element unitMeasurement = doc.createElement("unitMeasurement");
        unitMeasurement.appendChild(doc.createTextNode(data.getUnitMeasurement()));

        newProduct.appendChild(unitMeasurement);

        doc.getDocumentElement().appendChild(newProduct);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(xmlFile);
        transformer.transform(source,result);

        logger.info("New Product was created successfully");
        return findById(newId);
    }


    @Override
    public int updateById(int id, Product data) throws Exception {
        File xmlFile = new File(xmlPathName);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        NodeList productsList = doc.getElementsByTagName("product");

        int updated = 0;

        for(int i = 0; i < productsList.getLength(); i++){
            Node product = productsList.item(i);
            Element productElement = (Element) product;
            if(productElement.getAttribute("id").equalsIgnoreCase(Integer.toString(id))){
                if(data.getName() != null && !data.getName().isBlank()){
                    NodeList nameList = productElement.getElementsByTagName("name");
                    if(nameList.getLength()>0){
                        Element nameElement = (Element) nameList.item(0);
                        nameElement.setTextContent(data.getName());
                    }
                }
                if(data.getPrice() > 0){
                    NodeList priceList = productElement.getElementsByTagName("price");
                    if(priceList.getLength()>0){
                        Element priceElement = (Element) priceList.item(0);
                        priceElement.setTextContent(Double.toString(data.getPrice()));
                    }
                }
                if(data.getUnitMeasurement() != null && !data.getUnitMeasurement().isBlank()){
                    NodeList unitList = productElement.getElementsByTagName("unitMeasurement");
                    if(unitList.getLength()>0){
                        Element unitElement = (Element) unitList.item(0);
                        unitElement.setTextContent(data.getUnitMeasurement());
                    }
                }
                updated = 1;
                break;
            }
        }
        if(updated == 0){
            logger.info("Product with id "+id+" not found");
            return updated;
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(xmlFile);
        transformer.transform(source,result);
        logger.info("Product with id {} successfully updated", id);
        return updated;
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        File xmlFile = new File(xmlPathName);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        NodeList productsList = doc.getElementsByTagName("product");

        int deleted = 0;

        for(int i = 0; i < productsList.getLength(); i++){
            Node product = productsList.item(i);
            Element productElement = (Element) product;
            if(productElement.getAttribute("id").equalsIgnoreCase(Integer.toString(id))){
                product.getParentNode().removeChild(product);
                deleted = 1;
                break;
            }
        }
        if(deleted == 0){
            logger.info("Product with id "+id+" not found");
            return deleted;
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(xmlFile);
        transformer.transform(source,result);
        logger.info("Product with id {} successfully deleted", id);
        return deleted;
    }

    @Override
    public List<Product> findAll() throws Exception {
        loadParser();
        return handler.getProductList();
    }

    @Override
    public Product findById(int id) throws Exception {
        loadParser();
        List<Product> productsFiltered = handler.getProductList().stream().filter(product->product.getId() == id).toList();
        if(productsFiltered.isEmpty()){
            return null;
        }
        return productsFiltered.getFirst();
    }

    @Override
    public void setPoolConnection(IConnection<Connection> poolConnection) {
        System.out.println("There is no implementation here");
    }

    private void loadParser()throws Exception{
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("src/main/java/org/example/utils/xsd/product.xsd"));

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
