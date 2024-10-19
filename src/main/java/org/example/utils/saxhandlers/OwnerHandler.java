package org.example.utils.saxhandlers;

import org.example.domain.Owner;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.LinkedList;
import java.util.List;

public class OwnerHandler extends DefaultHandler {

    private List<Owner> ownerList = null;
    private Owner owner = null;
    private StringBuilder stringData = null;

    private boolean bFullName = false;
    private boolean bPhone = false;
    private boolean bEmail = false;
    private int latestIndex = 0;

    public List<Owner> getOwnerList() {
        return ownerList;
    }

    public int getLatestIndex() {
        return latestIndex;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equalsIgnoreCase("Owner")){
            owner = new Owner();
            String id = attributes.getValue("id");
            owner.setId(Integer.parseInt(id));
            if(ownerList == null){
                ownerList = new LinkedList<>();
            }
        }else if(qName.equalsIgnoreCase("fullName")){
            bFullName = true;
        }else if(qName.equalsIgnoreCase("phone")){
            bPhone = true;
        }else if(qName.equalsIgnoreCase("email")){
            bEmail = true;
        }

        stringData = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if(bFullName){
            owner.setFullName(stringData.toString());
            bFullName = false;
        }else if(bPhone){
            owner.setPhone(stringData.toString());
            bPhone = false;
        }else if(bEmail){
            owner.setEmail(stringData.toString());
            bEmail = false;
        }

        if(qName.equalsIgnoreCase("Owner")){
            ownerList.add(owner);
        }
    }

    @Override
    public void startDocument() throws SAXException {
        ownerList = null;
    }

    @Override
    public void endDocument() throws SAXException {
        latestIndex = ownerList.getLast().getId()+1;
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
