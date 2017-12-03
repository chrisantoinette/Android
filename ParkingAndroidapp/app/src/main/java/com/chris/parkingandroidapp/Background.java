package com.chris.parkingandroidapp;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by narendrabidari on 12/1/17.
 */

public class Background {
    private final Activity context;

    ParkingDataProvider mDataProvider;

    public Background(Activity context) {
        this.context = context;

    }
    private static final String TAG = "MyActivity";

    ArrayList<ContactsAdapter> arrayList = new ArrayList<>();
    String json_url = "http://18.220.178.61/Andriod/includes/ListCar.php";

    public ArrayList<ContactsAdapter> getList(final String type, final RecyclerView recyclerView, final  GoogleMap googleMap) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                json_url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        System.out.println("response " + response);
                        for (int i = 0; i < response.length(); i++) {
                            // Get current json object
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                // new object
                                ContactsAdapter adapter = new ContactsAdapter(jsonObject);
                                arrayList.add(adapter);
                                Log.d(TAG,response.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        RecyclerView.Adapter recylerAdapter = new RecyclerAdapter(arrayList);
                        if(recyclerView!=null)
                        recyclerView.setAdapter(recylerAdapter);
                        if(googleMap!=null){
                            plotParkingLocations(arrayList,googleMap);
                        }

//
//
//                        if(recylerAdapter !=null)
//                        recylerAdapter.notifyDataSetChanged();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Error..", Toast.LENGTH_SHORT).show();
                        System.out.println("Heelo");
                        error.printStackTrace();
                    }

                })


        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                System.out.print(type);
                //Toast.makeText(context,j,Toast.LENGTH_SHORT).show();
                params.put("type", type);
                return params;
            }
        };

        MySingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);
        return arrayList;
    }

    private void plotParkingLocations(ArrayList<ContactsAdapter> contactsAdapterArrayList,GoogleMap googleMap) {

        List<ParkingData> allLocations = new ArrayList<ParkingData>();
        for(ContactsAdapter data : contactsAdapterArrayList){
            LatLng parkingSpot2 = new LatLng(Double.parseDouble(data.getLatitude()),Double.parseDouble(data.getLongitude()) );
            ParkingData pData = new ParkingData(data.getLotName(), data.getAddress(), parkingSpot2);
            allLocations.add(pData);
        }

        for(int i = 0; i < allLocations.size(); i++) {
            googleMap.addMarker(new MarkerOptions().position(allLocations.get(i).getParkingSpotLocation()).title("Parking Spot: "+ i));
        }
    }





}

