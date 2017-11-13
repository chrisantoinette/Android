package com.chris.parkingandroidapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by chrisantoinette on 10/30/17.
 */

public class fragmentList extends Fragment {
    String[] nameArray = {"Octopus","Pig","Sheep","Rabbit","Snake","Spider" };

    String[] infoArray = {
            "8 tentacled monster",
            "Delicious in rolls",
            "Great for jumpers",
            "Nice in a stew",
            "Great for shoes",
            "Scary."
    };
    ListView mListView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_parking_list, container, false);
        CustomListAdapter parkingDataAdapter = new CustomListAdapter(getActivity(), nameArray, infoArray);
        mListView = (ListView) getActivity().findViewById(R.id.listviewID);
        mListView.setAdapter(parkingDataAdapter);
        return rootView;

    }
}
