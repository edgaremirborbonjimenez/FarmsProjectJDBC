package org.example.utils.saxhandlers;

import org.example.domain.Farm;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.LinkedList;
import java.util.List;

public class FarmHandler extends DefaultHandler {

    private List<Farm> farmList = null;
    private Farm farm = null;
    private StringBuilder stringData = null;

    private boolean bName = false;
    private boolean bAddress = false;
    private boolean bOwnerId = false;
    private int latestIndex = 0;

    public List<Farm> getFarmList() {
        return farmList;
    }

    public int getLatestIndex() {
        return latestIndex;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equalsIgnoreCase("Farm")){
            farm = new Farm();
            String id = attributes.getValue("id");
            farm.setId(Integer.parseInt(id));
            if(farmList == null){
                farmList = new LinkedList<>();
            }
        }else if(qName.equalsIgnoreCase("name")){
            bName = true;
        }else if(qName.equalsIgnoreCase("address")){
            bAddress = true;
        }else if(qName.equalsIgnoreCase("ownerId")){
            bOwnerId = true;
        }

        stringData = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if(bName){
            farm.setName(stringData.toString());
            bName = false;
        }else if(bAddress){
            farm.setAddress(stringData.toString());
            bAddress = false;
        }else if(bOwnerId){
            farm.setOwner_id(Integer.parseInt(stringData.toString()));
            bOwnerId = false;
        }

        if(qName.equalsIgnoreCase("Farm")){
            farmList.add(farm);
        }

    }

    @Override
    public void startDocument() throws SAXException {
        farmList = null;
    }

    @Override
    public void endDocument() throws SAXException {
        latestIndex = farmList.size()+1;
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
