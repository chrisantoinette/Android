package com.chris.parkingandroidapp;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by narendrabidari on 12/1/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    ArrayList<ContactsAdapter> arrayList=new ArrayList<>();

    public RecyclerAdapter(ArrayList<ContactsAdapter> arrayList){
        this.arrayList=arrayList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)  {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rowactivity,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.LotName.setText(arrayList.get(position).getLotName());
        holder.Address.setText(arrayList.get(position).getAddress());


    }

    @Override
    public int getItemCount() {


        return arrayList.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView LotName,Address,Availability;


        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            LotName=(TextView) itemView.findViewById(R.id.tx_name);
            Address=(TextView) itemView.findViewById(R.id.tx_address);
            //Availability=(TextView) itemView.findViewById(R.id.distanceId);

        }

        @Override
        public void onClick(View view){
            // Toast.makeText(view.getContext(), "position = " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
            // Toast.makeText(view.getContext(),(arrayList.get(getAdapterPosition()).getLotName()),Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(view.getContext(),Availability.class);
            String selectLotname=arrayList.get(getAdapterPosition()).getLotName();
            intent.putExtra("keyName",selectLotname);
            view.getContext().startActivity(intent);


        }

    }
}
