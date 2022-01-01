package com.example.minerest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.mycartviewHolder>{

    List<CartItems> cartItemsList;

    public CartAdapter(List<CartItems> cartItemsList) {
        this.cartItemsList=cartItemsList;
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
        holder.itemimgCart.setImageResource(cartItemsList.get(position).getItemimgCart());
        holder.itempriceCart.setText(cartItemsList.get(position).getItemPriceCart());
        holder.itemQtyCart.setText(cartItemsList.get(position).getItemQtyCart());
        holder.itemnameCart.setText(cartItemsList.get(position).getItemNameCart());
        holder.totalpriceCart.setText(cartItemsList.get(position).getTotalpriceCart());
    }

    @Override
    public int getItemCount() {
        return cartItemsList.size();
    }

    class mycartviewHolder extends RecyclerView.ViewHolder{

        ImageView itemimgCart;
        TextView itempriceCart, itemQtyCart, totalpriceCart,itemnameCart;

        public mycartviewHolder(@NonNull View itemView) {
            super(itemView);
            itemimgCart=itemView.findViewById(R.id.itemImgCart);
            itempriceCart = itemView.findViewById(R.id.priceinCart);
            itemQtyCart = itemView.findViewById(R.id.qtyCart);
            totalpriceCart = itemView.findViewById(R.id.totalamtCart);
            itemnameCart = itemView.findViewById(R.id.row_nametvCart);
        }
    }
}
