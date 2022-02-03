package com.example.minerest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.MyviewHolder> {

    List<dashboard_response_model> dashData;
    Context context;

    public DashboardAdapter(List<dashboard_response_model> dashData, Context context) {
        this.dashData = dashData;
        this.context=context;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.menu_singlerow,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.itemnameTv.setText(dashData.get(position).getName());
        holder.itemtypeTv.setText(dashData.get(position).getType());

        if(dashData.get(position).getType().equals("veg"))
        holder.itemImg.setImageResource(R.drawable.vegmenuitem);
        else
            holder.itemImg.setImageResource(R.drawable.nonvegmenuitem);


      //  Picasso.get().load(dashData.get(position).getUrl()).placeholder(R.drawable.ic_launcher_background).fit().into(holder.itemImg);

        if(dashData.get(position).getType().equals("veg")){
            holder.itemtypeTv.setTextColor(Color.GREEN);
            holder.itemTypeImg.setImageResource(R.drawable.leaf);
        }else {
            holder.itemtypeTv.setTextColor(Color.RED);
            holder.itemTypeImg.setImageResource(R.drawable.leaf_red);
        }

        holder.rating.setText(dashData.get(position).getRating());
        holder.priceTv.setText("₹"+dashData.get(position).getPrice());

        holder.addCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDatabase db= Room.databaseBuilder(context,AppDatabase.class,"cart_db").allowMainThreadQueries().build();
                MenuItemsDao productDao=db.MenuItemsDao();
                Boolean check=productDao.is_exist(dashData.get(position).getId());
                if(check==false)
                {
                    int pid=dashData.get(position).getId();
                    String pname=dashData.get(position).getName();
                    int price=dashData.get(position).getPrice();
                    int qnt=1;
                    String type = dashData.get(position).getType();
                    productDao.insertrecord(new MenuItemsTable(pid,pname,type,price,qnt));
                    Toast.makeText(context,"Added to cart!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(context,"Product already in cart!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dashData == null ? 0 : dashData.size();
    }

    class MyviewHolder extends RecyclerView.ViewHolder{
        
        ImageView itemImg,itemTypeImg;
        TextView itemnameTv, itemtypeTv,priceTv,rating;
        Button addCartBtn;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            itemImg = itemView.findViewById(R.id.itemImg);

            addCartBtn=itemView.findViewById(R.id.dashCartBtn);

            itemnameTv = itemView.findViewById(R.id.row_nametv);
            itemtypeTv = itemView.findViewById(R.id.typeDesc);
            priceTv = itemView.findViewById(R.id.priceDash);
            itemTypeImg=itemView.findViewById(R.id.itemTypeImg);
            rating=itemView.findViewById(R.id.dashRating);
        }
    }
}
