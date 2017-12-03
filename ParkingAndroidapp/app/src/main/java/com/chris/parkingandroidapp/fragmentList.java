package com.chris.parkingandroidapp;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by chrisantoinette on 10/30/17.
 */

public class fragmentList extends Fragment {

    ListView mListView;
    private filterOptions mOptions;
    ParkingDataProvider mDataProvider;
    private android.support.design.widget.FloatingActionButton mFloatButtonCar, mFloatButtonBike;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mOptions = SharedPrefManager.getInstance(getActivity()).getOptions();
        final View rootView = inflater.inflate(R.layout.fragment_parking_list, container, false);

        // College location is our current location
        final Location currentLocation = new Location("gps");
        currentLocation.setLatitude(37.337011);
        currentLocation.setLongitude(-121.881613);

        mDataProvider = new ParkingDataProvider(getActivity(), new ParkingDataProvider.ActivityCallBack() {
            @Override
            public void onSuccess() {
                // Means, we have received data from server.
                refreshList(currentLocation, mOptions);
            }

            @Override
            public void onFail(String msg) {

            }
        });
        CustomListAdapter parkingDataAdapter = new CustomListAdapter(getActivity(),
                mDataProvider.getParkingLocationNames(currentLocation, mOptions),
                mDataProvider.getParkingAddresses(currentLocation, mOptions),
                mDataProvider.getParkingDistance(currentLocation, mOptions));
        mListView = (ListView) rootView.findViewById(R.id.listviewID);
        mListView.setAdapter(parkingDataAdapter);

        mFloatButtonCar = (android.support.design.widget.FloatingActionButton) rootView.findViewById(R.id.floatCarButtonList);
        mFloatButtonCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOptions = filterOptions.kCar;
                SharedPrefManager.getInstance(getActivity()).setOptions(mOptions);
                refreshList(currentLocation, mOptions);
            }
        });

        mFloatButtonBike = (android.support.design.widget.FloatingActionButton) rootView.findViewById(R.id.floatBikeButtonList);
        mFloatButtonBike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOptions = filterOptions.kBike;
                SharedPrefManager.getInstance(getActivity()).setOptions(mOptions);
                refreshList(currentLocation, mOptions);
            }
        });

        return rootView;
    }

    void refreshList(Location currentLocation, filterOptions options) {
        // Call this function everytime you have new data.
        Log.i("LIST", "Arriving here...");
        mOptions = options;
        CustomListAdapter adapter = (CustomListAdapter) mListView.getAdapter();
        adapter.clear();
        adapter.setParkingAddresses_(mDataProvider.getParkingAddresses(currentLocation, mOptions));
        adapter.setParkingNames_(mDataProvider.getParkingLocationNames(currentLocation, mOptions));
        adapter.setParkingDistances_(mDataProvider.getParkingDistance(currentLocation, mOptions));
        adapter.notifyDataSetChanged();
    }
}
