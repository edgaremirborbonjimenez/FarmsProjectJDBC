package org.example.utils.saxhandlers;

import org.example.domain.FarmSupplyProductInventory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.LinkedList;
import java.util.List;

public class FarmSupplyProductInventoryHandler extends DefaultHandler {
    private List<FarmSupplyProductInventory> farmSupplyProductInventoryList = null;
    private FarmSupplyProductInventory farmSupplyProductInventory = null;
    private StringBuilder stringData = null;

    private boolean bAmount = false;
    private boolean bFarmId = false;
    private boolean bProductId = false;

    private int latestIndex = 0;


    public List<FarmSupplyProductInventory> getFarmSupplyProductInventoryList() {
        return farmSupplyProductInventoryList;
    }

    public int getLatestIndex() {
        return latestIndex;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equalsIgnoreCase("FarmSupplyProductInventory")){
            farmSupplyProductInventory = new FarmSupplyProductInventory();
            String id = attributes.getValue("id");
            farmSupplyProductInventory.setId(Integer.parseInt(id));
            if(farmSupplyProductInventoryList == null){
                farmSupplyProductInventoryList = new LinkedList<>();
            }
        }else if(qName.equalsIgnoreCase("amount")){
            bAmount = true;
        }else if(qName.equalsIgnoreCase("farmId")){
            bFarmId = true;
        }else if(qName.equalsIgnoreCase("productId")){
            bProductId = true;
        }

        stringData = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if(bAmount){
            farmSupplyProductInventory.setAmount(Integer.parseInt(stringData.toString()));
            bAmount = false;
        } else if(bFarmId){
            farmSupplyProductInventory.setFarm_id(Integer.parseInt(stringData.toString()));
            bFarmId = false;
        } else if(bProductId){
            farmSupplyProductInventory.setProduct_id(Integer.parseInt(stringData.toString()));
            bProductId = false;
        }

        if(qName.equalsIgnoreCase("FarmSupplyProductInventory")){
            farmSupplyProductInventoryList.add(farmSupplyProductInventory);
        }
    }

    @Override
    public void startDocument() throws SAXException {
        farmSupplyProductInventoryList = null;
    }

    @Override
    public void endDocument() throws SAXException {
        latestIndex = farmSupplyProductInventoryList.size()+1;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        stringData.append(ch,start,length);
    }
}

