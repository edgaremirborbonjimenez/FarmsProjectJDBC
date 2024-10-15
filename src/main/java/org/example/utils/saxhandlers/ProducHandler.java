package org.example.utils.saxhandlers;

import org.example.domain.Product;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.LinkedList;
import java.util.List;

public class ProducHandler extends DefaultHandler {

    private List<Product> productList = null;
    private Product product = null;
    private StringBuilder stringData = null;

    private boolean bName = false;
    private boolean bPrice = false;
    private boolean bUnitMeasurement = false;
    private int latestIndex = 0;

    public List<Product> getProductList() {
        return productList;
    }

    public int getLatestIndex() {
        return latestIndex;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equalsIgnoreCase("Product")){
            product = new Product();
            String id = attributes.getValue("id");
            product.setId(Integer.parseInt(id));
            if(productList == null){
                productList = new LinkedList<>();
            }
        }else if(qName.equalsIgnoreCase("name")){
            bName = true;
        }else if(qName.equalsIgnoreCase("price")){
            bPrice = true;
        }else if(qName.equalsIgnoreCase("unitMeasurement")){
            bUnitMeasurement = true;
        }

        stringData = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if(bName){
            product.setName(stringData.toString());
            bName = false;
        }else if(bPrice){
            product.setPrice(Double.parseDouble(stringData.toString()));
            bPrice = false;
        }else if(bUnitMeasurement){
            product.setUnitMeasurement(stringData.toString());
            bUnitMeasurement = false;
        }

        if(qName.equalsIgnoreCase("Product")){
            productList.add(product);
        }
    }

    @Override
    public void startDocument() throws SAXException {
        productList = null;
    }

    @Override
    public void endDocument() throws SAXException {
        latestIndex = productList.size()+1;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        stringData.append(ch,start,length);
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        System.out.println("Error: " + e.getMessage());
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        System.out.println("Fatal error: " + e.getMessage());
    }

    @Override
    public void warning(SAXParseException e) throws SAXException {
        System.out.println("Warning: " + e.getMessage());
    }
}
