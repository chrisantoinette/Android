package com.chris.parkingandroidapp;

import org.json.JSONObject;

/**
 * Created by narendrabidari on 12/1/17.
 */

public class ContactsAdapter {

    private String LotName;
    //camelCase
    private String Address;

    private String Type;

    private String Bit;

    private String lotID;

    public String getLotID() {
        return lotID;
    }

    public void setLotID(String lotID) {
        this.lotID = lotID;
    }

    public String getBit() {
        return Bit;
    }

    public void setBit(String bit) {
        Bit = bit;
    }

    public ContactsAdapter(JSONObject jsonObject) {


        try {
            this.setLotName(jsonObject.getString("LotName"));

            this.setAddress(jsonObject.getString("Address"));
        } catch (Exception e) {
            System.out.println("Error while creating object" + e);
        }
    }


    public String getAddress() {
        return Address;
    }

    public String getType() {
        return Type;
    }


    public void setLotName(String lotName) {
        LotName = lotName;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getLotName() {
        return LotName;
    }
}
