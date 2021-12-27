package com.example.minerest;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.MyviewHolder> {

   public final List<MenuItems> data;

    public DashboardAdapter(List<MenuItems> data) {
        this.data = data;
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


        holder.itemnameTv.setText(data.get(position).getItemName());
        holder.itemdescTv.setText(data.get(position).getItemDesc());
        holder.itemtypeTv.setText(data.get(position).getItemType());


        holder.itemImg.setImageResource(data.get(position).getItemImg());
        holder.itemtypeImg.setImageResource(data.get(position).getItemtypeImg());
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
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyviewHolder extends RecyclerView.ViewHolder{
        
        ImageView itemImg, itemtypeImg,incImg,decImg;
        TextView itemnameTv, itemdescTv, itemtypeTv, quantityTv;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            itemImg = itemView.findViewById(R.id.itemImg);
            itemtypeImg = itemView.findViewById(R.id.typeImg);
            incImg = itemView.findViewById(R.id.incBtn);
            decImg = itemView.findViewById(R.id.decBtn);

            itemnameTv = itemView.findViewById(R.id.row_nametv);
            itemdescTv = itemView.findViewById(R.id.row_desctv);
            itemtypeTv = itemView.findViewById(R.id.typeDesc);
            quantityTv = itemView.findViewById(R.id.cartTv);
        }
    }
}
