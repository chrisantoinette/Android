package com.chris.parkingandroidapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import static android.content.Context.LOCATION_SERVICE;


/**
 * Created on 11/6/17.
 */

public class MapViewFragment extends Fragment  implements LocationListener{

    MapView mMapView;
    private Fragment mFragment;
    private GoogleMap googleMap;
    private String TAG = MapViewFragment.class.getName();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_fragment_parking_map, container, false);

        mMapView = (MapView) rootView.findViewById(R.id.parkingmap);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button
                googleMap.setMyLocationEnabled(true);
                showMap();

//                // For dropping a marker at a point on the Map
//                LatLng sydney = new LatLng(-34, 151);
//                googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker Title").snippet("Marker Description"));
//
//                // For zooming automatically to the location of the marker
//                CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
//                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    public void showMap() {

        // Enable Zoom
        googleMap.getUiSettings().setZoomGesturesEnabled(true);

        //set Map TYPE
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //enable Current location Button
        googleMap.setMyLocationEnabled(true);

        LocationManager mLocationManager = (LocationManager)getActivity().getApplicationContext().getSystemService(LOCATION_SERVICE);
        List<String> providers = mLocationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            Location l = mLocationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                bestLocation = l;
            }
        }
        if (bestLocation != null) {
            onLocationChanged(bestLocation);
        }
        //locationManager.requestLocationUpdates(bestProvider, 2000, 0, onLocationChanged);
        //locationManager.requestLocationUpdates(bestProvider, 2000, 0, getActivity().getApplicationContext());
    }
    // Location Changed
    @Override
    public void onLocationChanged(Location location) {

        Marker marker = null;
        double latitude = location.getLatitude();
        double longitude =location.getLongitude();

        LatLng loc = new LatLng(latitude, longitude);

        if (marker!=null){
            marker.remove();
        }

        marker =  googleMap.addMarker(new MarkerOptions().position(loc).title("Parking Spot"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 16.0f));

        // For zooming automatically to the location of the marker
        //CameraPosition cameraPosition = new CameraPosition.Builder().target(loc).zoom(12).build();
        //googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }
//    @Override
//    public void onProviderDisabled(String provider) {
//
//        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//        startActivity(intent);
//        Toast.makeText(getActivity().getBaseContext(), "Gps is turned off!!",
//                Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onProviderEnabled(String provider) {
//
//        Toast.makeText(getActivity().getBaseContext(), "Gps is turned on!! ",
//                Toast.LENGTH_SHORT).show();
//    }
}
