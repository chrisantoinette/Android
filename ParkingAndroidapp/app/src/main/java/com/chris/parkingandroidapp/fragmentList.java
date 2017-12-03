package com.chris.parkingandroidapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by chrisantoinette on 10/30/17.
 */

public class fragmentList extends Fragment {

    ListView mListView;
    ParkingDataProvider mDataProvider;
    Context thiscontext;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<ContactsAdapter> arrayList = new ArrayList<>();
    // ParkingDataProvider mDataProvider;
    private static final String TAG = "FragmentList";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_parking_list, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        Background background = new Background(getActivity());
        Bundle args = getArguments();

        String type = null;
        if (args != null) {
            type = (String) args.get("type");
        }else{
            Log.e(TAG,"ERROR while fetching value");
        }

        arrayList = background.getList(type, recyclerView,null);

        return rootView;


















        // College location is our current location
//        Location currentLocation = new Location("gps");
//        currentLocation.setLatitude(37.337011);
//        currentLocation.setLongitude(-121.881613);
//
//        CustomListAdapter parkingDataAdapter = new CustomListAdapter(getActivity(),
//                mDataProvider.getParkingLocationNames(currentLocation),
//                mDataProvider.getParkingAddresses(currentLocation),
//                mDataProvider.getParkingDistance(currentLocation));
//        mListView = (ListView) rootView.findViewById(R.id.listviewID);
//        mListView.setAdapter(parkingDataAdapter);



    }
}
