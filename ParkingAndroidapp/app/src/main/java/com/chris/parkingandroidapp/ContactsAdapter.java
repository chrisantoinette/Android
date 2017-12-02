package com.chris.parkingandroidapp;

import org.json.JSONObject;

/**
 * Created by narendrabidari on 12/1/17.
 */

public class ContactsAdapter {


    public ContactsAdapter(JSONObject jsonObject) {

        try {
            this.setLotName(jsonObject.getString("LotName"));
            this.setAddress(jsonObject.getString("Address"));
            this.setLatitude(jsonObject.getString("Latitude"));
            this.setLongitude(jsonObject.getString("Longitude"));



        } catch (Exception e) {
            System.out.println("Error while creating object" + e);
        }
    }


    private String LotName;
    //camelCase
    private String Address;

    private String Type;

    private String lotID;

    private String latitude;

    private String longitude;

    private String bit;


    public String getLotID() {
        return lotID;
    }

    public void setLotID(String lotID) {
        this.lotID = lotID;
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getBit() {
        return bit;
    }

    public void setBit(String bit) {
        this.bit = bit;
    }
}
