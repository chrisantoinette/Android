package com.chris.parkingandroidapp;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * Created by chrisantoinette on 11/8/17.
 * What is the use of this Data Provider Class?
 * When the user changes his location, he will use the current information(latLng) to query the
 * Database / WEbServer to get parking spots near him. This class is used to interface with the
 * remote servers, query the location using zipcode and return all the parking spot data.
 */

public class ParkingDataProvider {
    private Context mContext;
    // Lookup parking locations in this zip code.
    private HashMap<String, List<ParkingData>> mLocationLookup;

    public ParkingDataProvider(Context thisContext) {
        mContext = thisContext;
        constructMockParkingLocations();
    }

    public void constructMockParkingLocations () {
        mLocationLookup = new HashMap<String, List<ParkingData>>();
        // Parking Spot - East
        LatLng parkingSpot = new LatLng(37.333148, -121.882753 );
        String address ="Lot 11/13 San Jose, CA 95112";
        String name = ("East Parking");
        ParkingData pData = new ParkingData(name, address, parkingSpot);

        // Parking Spot - South
        LatLng parkingSpot2 = new LatLng(37.337758, -121.879406 );
        String address2 ="Lot 4  San Jose, CA 95112";
        String name2 = ("South Parking");
        ParkingData pData2 = new ParkingData(name2, address2, parkingSpot2);


        List<ParkingData> allLocations = new ArrayList<ParkingData>();
        allLocations.add(pData);
        allLocations.add(pData2);

        // College location is our current location
        LatLng currentLocation = new LatLng(37.337011, -121.881613);
        // Use college lat/long to get Zipcode and save that.. to store the above mentioned ParkingSpot
        mLocationLookup.put(getZipCode(currentLocation), allLocations);
    }
    // Below are two functions to get Zip-Code from the current location / LatLng
    public String getZipCode(LatLng currentLocation) {
        String zipCode = "95112";
        Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(currentLocation.latitude, currentLocation.longitude, 1);
            zipCode = addresses.get(0).getPostalCode();
        }
        catch (Exception e) {
        }
        return zipCode;
    }

    public String getZipCode(Location currentLocation) {
        String zipCode = "95112";
        Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(currentLocation.getLatitude(), currentLocation.getLongitude(), 1);
            zipCode = addresses.get(0).getPostalCode();
        }
        catch (Exception e) {
        }
        return zipCode;
    }

    // This function returns of the whole parking data class.
    public List<ParkingData> getParkingData(Location currentLocation) {
        if(mLocationLookup.containsKey(getZipCode(currentLocation))) {
            return mLocationLookup.get(getZipCode(currentLocation));
        }
        List<ParkingData> parkingEmptyList = new ArrayList<ParkingData>();
        return parkingEmptyList;
    }

    // This function returns only the parking location / latlng.
    public List<LatLng> getParkingLocations(Location currentLocation) {
        List<LatLng> allLocations = new ArrayList<LatLng>();
        if(mLocationLookup.containsKey(getZipCode(currentLocation))) {
            List<ParkingData> result = mLocationLookup.get(getZipCode(currentLocation));
            for(int i = 0; i < result.size(); ++i) {
                allLocations.add(result.get(i).getParkingSpotLocation());
            }
        }
        return allLocations;
    }

    // This function returns only the parking address in String format.
    public List<String> getParkingAddresses(Location currentLocation) {
        List<String> results = new ArrayList<String>();
        if(mLocationLookup.containsKey(getZipCode(currentLocation))) {
            List<ParkingData> result = mLocationLookup.get(getZipCode(currentLocation));
            for(int i = 0; i < result.size(); ++i) {
                results.add(result.get(i).getParkingAddress());
            }
        }
        return  results;
    }

    // This function returns only the parking spot names in String format.
    public List<String> getParkingLocationNames(Location currentLocation)
    {
        List<String> results = new ArrayList<String>();
        if(mLocationLookup.containsKey(getZipCode(currentLocation))) {
            List<ParkingData> result = mLocationLookup.get(getZipCode(currentLocation));
            for(int i = 0; i < result.size(); ++i) {
                results.add(result.get(i).getParkingName());
            }
        }
        return  results;
    }
}

