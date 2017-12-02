package com.chris.parkingandroidapp;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by narendrabidari on 12/1/17.
 */

public class Background {
    private final Activity context;

    public Background(Activity context) {
        this.context = context;

    }
    private static final String TAG = "MyActivity";

    ArrayList<ContactsAdapter> arrayList = new ArrayList<>();
    String json_url = "http://192.168.64.2/Andriod/includes/ListCar.php";

    public ArrayList<ContactsAdapter> getList(final String type) {
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
                                // setting values
                                adapter.setLotName(jsonObject.getString("LotName"));
                                adapter.setAddress(jsonObject.getString("Address"));
                                //adapter.setType(jsonObject.getString("Type"));

                                arrayList.add(adapter);
                                System.out.println("heelo");

                                Log.d(TAG,response.toString());
                                // setting values


                                // testing
                                //  String name = jsonObject.getString("LotName");
                                //System.out.println(i+ "\t" + name);
                                // testing

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

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
}

