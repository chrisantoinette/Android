package com.chris.parkingandroidapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

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

public class Availability extends AppCompatActivity {


    private TextView tLot,tAddress,tBit;
    private Context context;
    private String j = "";
    private int count=0;
    private static final String TAG = "MyActivity";

    static ArrayList<ContactsAdapter> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availability);

        context = getApplicationContext();

       // tSetLot = (TextView) findViewById(R.id.tAddress);
        tLot = (TextView) findViewById(R.id.tLot);
        tAddress=(TextView) findViewById(R.id.tAddress) ;
        tBit =(TextView) findViewById(R.id.tBit);


        Intent intent1 = getIntent();
        Bundle b = intent1.getExtras();

        if (b != null) {
            j = (String) b.get("keyName");
          //  tSetLot.setText(j);
        }




        String json_url = "http://192.168.64.2/Andriod/includes/BitCheck.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                json_url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        System.out.println("response " + response);
                        for (int i = 0; i < response.length(); i++) {// Get current json object
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                // new object
                                ContactsAdapter adapter = new ContactsAdapter(jsonObject);

                                // setting values
                                adapter.setLotName(jsonObject.getString("LotName"));
                                adapter.setAddress(jsonObject.getString("Address"));
                                adapter.setBit(jsonObject.getString("Bit"));
                               // adapter.setBit(jsonObject.getString("LotID"));

                               tLot.setText(adapter.getLotName());
                                tAddress.setText(adapter.getAddress());

                                int bit=Integer.parseInt(adapter.getBit());


                                if(bit==1){
                                    count++;
                                }

                                arrayList.add(adapter);
                              //  Toast.makeText(context, adapter.getLotName(), Toast.LENGTH_SHORT).show();
                                System.out.println(adapter.getLotName());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        tBit.setText(String.valueOf(count));
                        Log.d(TAG, response.toString());

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        // Toast.makeText(context,"Error..",Toast.LENGTH_SHORT).show();
                        System.out.println("Hello");
                        error.printStackTrace();
                    }

                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                System.out.print(j);
                //Toast.makeText(context,j,Toast.LENGTH_SHORT).show();
                params.put("lot", j);
                return params;
            }
        };


        MySingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);
    }

}
