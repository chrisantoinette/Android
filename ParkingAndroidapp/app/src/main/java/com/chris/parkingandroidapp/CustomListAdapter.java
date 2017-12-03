package com.chris.parkingandroidapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chrisantoinette on 11/11/17.
 */

public class CustomListAdapter extends ArrayAdapter {
    //to reference the Activity
    private final Activity context;
    private List<String> parkingAddresses_ = new ArrayList<String>();
    private double[] parkingDistances_;
    private List<String> parkingNames_= new ArrayList<String>();

    public List<String> getParkingNames_() {
        return parkingNames_;
    }

    public void setParkingNames_(List<String> parkingNames_) {
        this.parkingNames_ = parkingNames_;
    }

    public List<String> getParkingAddresses_() {
        return parkingAddresses_;
    }

    public void setParkingAddresses_(List<String> parkingAddresses_) {
        this.parkingAddresses_ = parkingAddresses_;
    }

    public double[] getParkingDistances_() {
        return parkingDistances_;
    }

    public void setParkingDistances_(double[] parkingDistances) {
        parkingDistances_ = parkingDistances;
    }

    public CustomListAdapter(Activity context, List<String> parkingNames,
                             List<String> parkingAddresses, double[] parkingDistances)
    {
        super(context, R.layout.listview_row , parkingNames.toArray(new String[0]));

        this.context=context;
        //this.imageIDarray = imageIDArrayParam;
        this.parkingNames_ = parkingNames;
        this.parkingAddresses_ = parkingAddresses;
        this.parkingDistances_ = parkingDistances;
    }

    @Override
    public int getCount() {
        return this.parkingNames_.isEmpty() ? 0 : this.parkingNames_.size();
    }
    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listview_row, null,true);

        //this code gets references to objects in the listview_row.xml file
        TextView nameTextField = (TextView) rowView.findViewById(R.id.nameTextViewID);
        TextView infoTextField = (TextView) rowView.findViewById(R.id.infoTextViewID);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageViewID);
        TextView distanceField = (TextView) rowView.findViewById(R.id.distanceId);
        //this code sets the values of the objects to values from the arrays
        nameTextField.setText(parkingNames_.get(position));
        infoTextField.setText(parkingAddresses_.get(position));
        //imageView.setImageResource(imageIDarray[position]);
        distanceField.setText(parkingDistances_[position]+"m");
        return rowView;

    };
}
