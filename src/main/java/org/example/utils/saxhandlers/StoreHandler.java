package org.example.utils.saxhandlers;

import org.example.domain.Store;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.LinkedList;
import java.util.List;

public class StoreHandler extends DefaultHandler {

    private List<Store> storeList = null;
    private Store store = null;
    private StringBuilder stringData = null;

    private boolean bName = false;
    private boolean bAddress = false;
    private int latestIndex = 0;

    public List<Store> getStoreList() {
        return storeList;
    }

    public int getLatestIndex() {
        return latestIndex;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equalsIgnoreCase("Store")){
            store = new Store();
            String id = attributes.getValue("id");
            store.setId(Integer.parseInt(id));
            if(storeList == null){
                storeList = new LinkedList<>();
            }
        }else if(qName.equalsIgnoreCase("name")){
            bName = true;
        }else if(qName.equalsIgnoreCase("address")){
            bAddress = true;
        }

        stringData = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if(bName){
            store.setName(stringData.toString());
            bName = false;
        }else if(bAddress){
            store.setAddress(stringData.toString());
            bAddress = false;
        }

        if(qName.equalsIgnoreCase("Store")){
            storeList.add(store);
        }
    }

    @Override
    public void startDocument() throws SAXException {
        storeList = null;
    }

    @Override
    public void endDocument() throws SAXException {
        latestIndex = storeList.getLast().getId()+1;
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
