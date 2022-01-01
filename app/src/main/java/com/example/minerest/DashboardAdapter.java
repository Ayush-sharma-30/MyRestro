package com.example.minerest;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.MyviewHolder> {

    List<dashboard_response_model> dashData;

    public DashboardAdapter(List<dashboard_response_model> dashData) {
        this.dashData = dashData;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.menu_singlerow,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder,int position) {

        String quant= holder.quantityTv.getText().toString();
        final int[] quant_int = {Integer.parseInt(quant)};


        holder.itemnameTv.setText(dashData.get(position).getName());
        holder.itemtypeTv.setText(dashData.get(position).getType());

        //TODO: Image drive load
       // Glide.with(holder.itemImg.getContext()).load(""+dashData.get(position).getUrl()).into(holder.itemImg);

        if(dashData.get(position).getType().equals("veg")){
            holder.itemtypeTv.setTextColor(Color.GREEN);
            holder.itemTypeImg.setImageResource(R.drawable.leaf);
        }else {
            holder.itemtypeTv.setTextColor(Color.RED);
            holder.itemTypeImg.setImageResource(R.drawable.leaf_red);
        }

        holder.rating.setText(dashData.get(position).getRating());
        holder.itemImg.setImageResource(R.drawable.biryani);
        holder.priceTv.setText("₹"+dashData.get(position).getPrice());

        holder.decImg.setImageResource(R.drawable.minus);

        holder.incImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quant_int[0]++;
                if(quant_int[0]<0)
                    quant_int[0]=0;
                holder.quantityTv.setText(String.valueOf(quant_int[0]));
                Log.e("quantity", String.valueOf(quant_int[0]));

            }
        });
        System.out.println(quant_int[0]);
        holder.decImg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                quant_int[0]--;
                if(quant_int[0]<0)
                    quant_int[0]=0;
                holder.quantityTv.setText(String.valueOf(quant_int[0]));
                Log.e("quantity", String.valueOf(quant_int[0]));
            }
        });
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dashData == null ? 0 : dashData.size();
    }

    class MyviewHolder extends RecyclerView.ViewHolder{
        
        ImageView itemImg,incImg,decImg,itemTypeImg;
        TextView itemnameTv, itemtypeTv, quantityTv,priceTv,rating;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            itemImg = itemView.findViewById(R.id.itemImg);
            incImg = itemView.findViewById(R.id.incBtn);
            decImg = itemView.findViewById(R.id.decBtn);

            itemnameTv = itemView.findViewById(R.id.row_nametv);
            itemtypeTv = itemView.findViewById(R.id.typeDesc);
            quantityTv = itemView.findViewById(R.id.cartTv);
            priceTv = itemView.findViewById(R.id.priceDash);
            itemTypeImg=itemView.findViewById(R.id.itemTypeImg);
            rating=itemView.findViewById(R.id.dashRating);
        }
    }
}
