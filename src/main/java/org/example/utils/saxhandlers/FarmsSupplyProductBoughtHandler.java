package org.example.utils.saxhandlers;

import org.example.domain.FarmSupplyProductBought;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class FarmsSupplyProductBoughtHandler extends DefaultHandler {
    private List<FarmSupplyProductBought> farmSupplyProductBoughtList = null;
    private FarmSupplyProductBought farmSupplyProductBought = null;
    private StringBuilder stringData = null;

    private boolean bAmount = false;
    private boolean bTotal = false;
    private boolean bPurchaseDate = false;
    private boolean bFarmId = false;
    private boolean bProductId = false;

    private int latestIndex = 0;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    public List<FarmSupplyProductBought> getFarmSupplyProductBoughtList() {
        return farmSupplyProductBoughtList;
    }

    public int getLatestIndex() {
        return latestIndex;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equalsIgnoreCase("FarmSupplyProductBought")){
            farmSupplyProductBought = new FarmSupplyProductBought();
            String id = attributes.getValue("id");
            farmSupplyProductBought.setId(Integer.parseInt(id));
            if(farmSupplyProductBoughtList == null){
                farmSupplyProductBoughtList = new LinkedList<>();
            }
        }else if(qName.equalsIgnoreCase("amount")){
            bAmount = true;
        }else if(qName.equalsIgnoreCase("total")){
            bTotal = true;
        }else if(qName.equalsIgnoreCase("purchaseDate")){
            bPurchaseDate = true;
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
            farmSupplyProductBought.setAmount(Integer.parseInt(stringData.toString()));
            bAmount = false;
        }else if(bTotal){
            farmSupplyProductBought.setTotal(Double.parseDouble(stringData.toString()));
            bTotal = false;
        }else if(bPurchaseDate){
            try {
                farmSupplyProductBought.setPurchaseDate(new Date(dateFormat.parse(stringData.toString()).getTime()));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            bPurchaseDate = false;
        } else if(bFarmId){
            farmSupplyProductBought.setFarm_id(Integer.parseInt(stringData.toString()));
            bFarmId = false;
        } else if(bProductId){
            farmSupplyProductBought.setProduct_id(Integer.parseInt(stringData.toString()));
            bProductId = false;
        }

        if(qName.equalsIgnoreCase("FarmSupplyProductBought")){
            farmSupplyProductBoughtList.add(farmSupplyProductBought);
        }
    }

    @Override
    public void startDocument() throws SAXException {
        farmSupplyProductBoughtList = null;
    }

    @Override
    public void endDocument() throws SAXException {
        latestIndex = farmSupplyProductBoughtList.size()+1;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        stringData.append(ch,start,length);
    }
}

