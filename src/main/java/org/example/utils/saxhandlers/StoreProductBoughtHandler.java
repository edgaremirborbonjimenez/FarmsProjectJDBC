package org.example.utils.saxhandlers;

import org.example.domain.StoreProductBought;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class StoreProductBoughtHandler extends DefaultHandler {
    private List<StoreProductBought> storeProductBoughtList = null;
    private StoreProductBought storeProductBought = null;
    private StringBuilder stringData = null;

    private boolean bAmount = false;
    private boolean bTotal = false;
    private boolean bProductId = false;
    private boolean bStoreId = false;

    private int latestIndex = 0;


    public List<StoreProductBought> getStoreProductBoughtList() {
        return storeProductBoughtList;
    }

    public int getLatestIndex() {
        return latestIndex;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equalsIgnoreCase("StoreProductBought")){
            storeProductBought = new StoreProductBought();
            String id = attributes.getValue("id");
            storeProductBought.setId(Integer.parseInt(id));
            if(storeProductBoughtList == null){
                storeProductBoughtList = new LinkedList<>();
            }
        }else if(qName.equalsIgnoreCase("amount")){
            bAmount = true;
        }else if(qName.equalsIgnoreCase("total")){
            bTotal = true;
        }else if(qName.equalsIgnoreCase("productId")){
            bProductId = true;
        }else if(qName.equalsIgnoreCase("storeId")){
            bStoreId = true;
        }

        stringData = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if(bAmount){
            storeProductBought.setAmount(Integer.parseInt(stringData.toString()));
            bAmount = false;
        }else if(bTotal){
            storeProductBought.setTotal(Double.parseDouble(stringData.toString()));
            bTotal = false;
        }else if(bProductId){
            storeProductBought.setProduct_id(Integer.parseInt(stringData.toString()));
            bProductId = false;
        }else if(bStoreId){
            storeProductBought.setStore_id(Integer.parseInt(stringData.toString()));
            bStoreId = false;
        }

        if(qName.equalsIgnoreCase("StoreProductBought")){
            storeProductBoughtList.add(storeProductBought);
        }
    }

    @Override
    public void startDocument() throws SAXException {
        storeProductBoughtList = null;
    }

    @Override
    public void endDocument() throws SAXException {
        latestIndex = storeProductBoughtList.size()+1;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        stringData.append(ch,start,length);
    }
}
