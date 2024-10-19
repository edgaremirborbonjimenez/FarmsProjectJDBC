package org.example.utils.saxhandlers;

import org.example.domain.Animal;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.LinkedList;
import java.util.List;

public class AnimalsHandler extends DefaultHandler {

    private List<Animal> animalList = null;
    private Animal animal = null;
    private StringBuilder stringData = null;

    private boolean bName = false;
    private int latestIndex = 0;

    public List<Animal> getAnimalList() {
        return animalList;
    }

    public int getLatestIndex() {
        return latestIndex;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equalsIgnoreCase("Animal")){
            animal = new Animal();
            String id = attributes.getValue("id");
            animal.setId(Integer.parseInt(id));
            if(animalList == null){
                animalList = new LinkedList<>();
            }
        }else if(qName.equalsIgnoreCase("name")){
            bName = true;
        }

        stringData = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if(bName){
            animal.setName(stringData.toString());
            bName = false;
        }

        if(qName.equalsIgnoreCase("Animal")){
            animalList.add(animal);
        }
    }

    @Override
    public void startDocument() throws SAXException {
        animalList = null;
    }

    @Override
    public void endDocument() throws SAXException {
        latestIndex = animalList.getLast().getId()+1;
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
