package com.chris.parkingandroidapp;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chrisantoinette on 11/8/17.
 */

public class DBhelper {
    private Map<LatLng, List<ParkingData>> mLocationLookup;

    public DBhelper() {
        constructMockParkingLocations();
    }

    public void constructMockParkingLocations () {
        LatLng parkingSpot = new LatLng(37.333148, -121.882753 );
        String address ="Lot 11/13 San Jose, CA 95112";
        String name = ("East Parking");
        ParkingData pData = new ParkingData(name, address, parkingSpot);
        List<ParkingData> allLocations = new ArrayList<ParkingData>();
        allLocations.add(pData);

        // College location is our current location
        LatLng currentLocation = new LatLng(37.337011, 121.881613);
        mLocationLookup.put(currentLocation, allLocations);
    }

    public List<ParkingData> getParkingData(Location currentLocation) {
        if(mLocationLookup.containsKey(currentLocation)) {
            return mLocationLookup.get(currentLocation);
        }
        List<ParkingData> parkingEmptyList = new ArrayList<ParkingData>();
        return parkingEmptyList;
    }

    public List<LatLng> getParkingLocations(Location currentLocation) {
        List<LatLng> allLocations = new ArrayList<LatLng>();
        if(mLocationLookup.containsKey(currentLocation)) {
            List<ParkingData> result = mLocationLookup.get(currentLocation);
            for(int i = 0; i < result.size(); ++i) {
                allLocations.add(result.get(i).getParkingSpotLocation());
            }
        }
        return allLocations;
    }

    public List<String> getParkingAddresses(Location currentLocation)
    {
        List<String> results = new ArrayList<String>();
        if(mLocationLookup.containsKey(currentLocation)) {
            List<ParkingData> result = mLocationLookup.get(currentLocation);
            for(int i = 0; i < result.size(); ++i) {
                results.add(result.get(i).getParkingAddress());
            }
        }
        return  results;
    }

    public List<String> getParkingLocationNames(Location currentLocation)
    {
        List<String> results = new ArrayList<String>();
        if(mLocationLookup.containsKey(currentLocation)) {
            List<ParkingData> result = mLocationLookup.get(currentLocation);
            for(int i = 0; i < result.size(); ++i) {
                results.add(result.get(i).getParkingName());
            }
        }
        return  results;
    }
}