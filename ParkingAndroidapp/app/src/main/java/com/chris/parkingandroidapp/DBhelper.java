package com.chris.parkingandroidapp;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by chrisantoinette on 11/8/17.
 */

public class DBhelper {
    public ArrayList<LatLng> findParkingSpaces(Location currentLocation)
    {
        ArrayList<LatLng> parkingSpaces = new ArrayList<LatLng>();
        LatLng parkingSpot = new LatLng(37.333148, -121.882753 );
        parkingSpaces.add(parkingSpot);
        return parkingSpaces;
    }
}