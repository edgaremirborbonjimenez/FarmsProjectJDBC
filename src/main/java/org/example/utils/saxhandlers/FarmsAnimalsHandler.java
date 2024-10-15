package org.example.utils.saxhandlers;

import org.example.domain.FarmAnimal;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.LinkedList;
import java.util.List;

public class FarmsAnimalsHandler extends DefaultHandler {

    private List<FarmAnimal> farmAnimalList = null;
    private FarmAnimal farmAnimal = null;
    private StringBuilder stringData = null;

    private boolean bAmount = false;
    private boolean bFarmId = false;
    private boolean bAnimalId = false;
    private int latestIndex = 0;

    public List<FarmAnimal> getFarmAnimalList() {
        return farmAnimalList;
    }

    public int getLatestIndex() {
        return latestIndex;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equalsIgnoreCase("FarmAnimal")){
            farmAnimal = new FarmAnimal();
            String id = attributes.getValue("id");
            farmAnimal.setId(Integer.parseInt(id));
            if(farmAnimalList == null){
                farmAnimalList = new LinkedList<>();
            }
        }else if(qName.equalsIgnoreCase("amount")){
            bAmount = true;
        }else if(qName.equalsIgnoreCase("farmId")){
            bFarmId = true;
        }else if(qName.equalsIgnoreCase("animalId")){
            bAnimalId = true;
        }

        stringData = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if(bAmount){
            farmAnimal.setAmount(Integer.parseInt(stringData.toString()));
            bAmount = false;
        }else if(bFarmId){
            farmAnimal.setFarm_id(Integer.parseInt(stringData.toString()));
            bFarmId = false;
        }else if(bAnimalId){
            farmAnimal.setAnimal_id(Integer.parseInt(stringData.toString()));
            bAnimalId = false;
        }

        if(qName.equalsIgnoreCase("FarmAnimal")){
            farmAnimalList.add(farmAnimal);
        }
    }

    @Override
    public void startDocument() throws SAXException {
        farmAnimalList = null;
    }

    @Override
    public void endDocument() throws SAXException {
        latestIndex = farmAnimalList.size()+1;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        stringData.append(ch,start,length);
    }
}
