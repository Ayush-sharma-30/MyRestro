package com.example.minerest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.mycartviewHolder>{

    List<MenuItemsTable> products;
    TextView rateview;

    public CartAdapter(List<MenuItemsTable> products, TextView rateview) {
        this.products = products;
        this.rateview= rateview;
    }


    @NonNull
    @Override
    public mycartviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cart_singlerow,parent,false);
        return new mycartviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull mycartviewHolder holder, int position) {

        if(products.get(position).getType().equals("veg")){
            holder.type.setImageResource(R.drawable.leaf);
        }else{
            holder.type.setImageResource(R.drawable.leaf_red);
        }
        holder.recpname.setText(products.get(position).getPname());
        holder.recpprice.setText("₹"+String.valueOf(products.get(position).getPrice()));
        holder.recqnt.setText(String.valueOf(products.get(position).getQnt()));

        holder.delbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDatabase db = Room.databaseBuilder(holder.recpname.getContext(),
                        AppDatabase.class, "cart_db").allowMainThreadQueries().build();
                MenuItemsDao productDao = db.MenuItemsDao();

                productDao.deleteById(products.get(position).getPid());
                products.remove(position);
                notifyItemRemoved(position);
                updateprice();
            }
        });

        holder.incr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qnt=products.get(position).getQnt();
                qnt++;
                products.get(position).setQnt(qnt);
                notifyDataSetChanged();
                updateprice();
            }
        });

        holder.decr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qnt=products.get(position).getQnt();
                qnt--;
                products.get(position).setQnt(qnt);
                notifyDataSetChanged();
                updateprice();
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class mycartviewHolder extends RecyclerView.ViewHolder{

        TextView recpname,recqnt, recpprice;
        ImageButton delbtn;
        ImageView incr,decr,type;

        public mycartviewHolder(@NonNull View itemView) {
            super(itemView);
            type=itemView.findViewById(R.id.itemtypecartImg);
            recpname=itemView.findViewById(R.id.row_nametvCart);
            recpprice=itemView.findViewById(R.id.row_pricetextCart);
            recqnt=itemView.findViewById(R.id.recqnt);
            delbtn=itemView.findViewById(R.id.delbtn);

            incr=itemView.findViewById(R.id.incbtn);
            decr=itemView.findViewById(R.id.decbtn);
        }
    }

    public int updateprice()
    {
        int sum=0,i;
        for(i=0;i< products.size();i++)
            sum= (int) (sum+(products.get(i).getPrice()*products.get(i).getQnt()));

        rateview.setText("Total Amount : INR "+sum);
        return sum;
    }
}
