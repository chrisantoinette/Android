package com.chris.parkingandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity1stpage extends AppCompatActivity {

    Button button,button1 ;
    public class Listener  implements View.OnClickListener  {
        @Override
        public void onClick(View var1) {
            Log.i("--parking new class-" , "button clicked !!!!!!!");
            launchNextPage();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.intro_activity);
        button =(Button) findViewById(R.id.bBike);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bikeIntent = new Intent(MainActivity1stpage.this,ParkingInfoActivity.class);
                bikeIntent.putExtra("type","Bike");
                startActivity(bikeIntent);

            }
        });


    button1=(Button) findViewById(R.id.bCar);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bikeIntent = new Intent(MainActivity1stpage.this,ParkingInfoActivity.class);
                bikeIntent.putExtra("type","Car");
                startActivity(bikeIntent);

            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button findParking = (Button) findViewById(R.id.findParking);
        Listener listenButton = new Listener();
        findParking.setOnClickListener (listenButton);
    }
    public void launchNextPage ()
    {
        Intent intent = new Intent(this, ParkingInfoActivity.class) ;
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity1stpage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
