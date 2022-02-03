package com.example.minerest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecentOrderAdapter extends RecyclerView.Adapter<RecentOrderAdapter.MyROviewHolder> {

    List<OrderField> ROdata;
    Context context;

    public RecentOrderAdapter(List<OrderField> ROdata, Context context) {
        this.ROdata = ROdata;
        this.context = context;
    }

    @NonNull
    @Override
    public MyROviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_recentorder_single_row,parent,false);
        return new MyROviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyROviewHolder holder, int position) {

 //       holder.ROitems.setText(ROdata.get(position).getROName().get(position).getName()+"(Qty:"+ROdata.get(position).getROName().get(position).getQuantity()+")\n");

        String temp="";
        for(Fooditems item:ROdata.get(position).getFooditems()){
            temp+=item.getName()+"(Qty:"+item.getQuantity()+")\n";
        }
        holder.ROitems.setText(temp);

        holder.ROid.setText(ROdata.get(position).getId());
        holder.ROprice.setText("₹"+ROdata.get(position).getTotalPrice());
//        holder.ROitems.setText(ROdata.get(position).getFooditems());

    }

    @Override
    public int getItemCount() { return ROdata == null ? 0 : ROdata.size(); }

    class MyROviewHolder extends RecyclerView.ViewHolder{
        TextView ROid,ROprice,ROitems;

        public MyROviewHolder(@NonNull View itemView) {
            super(itemView);
            ROid=itemView.findViewById(R.id.roId);
            ROprice=itemView.findViewById(R.id.roPrice);
            ROitems=itemView.findViewById(R.id.roItems);
        }
    }

}
