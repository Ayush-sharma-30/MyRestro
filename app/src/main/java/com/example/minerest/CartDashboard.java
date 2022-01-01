package com.example.minerest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CartDashboard extends AppCompatActivity {

    RecyclerView rcvCart;
    CartAdapter cartAdapter;
    List<CartItems> cartItemsList;
    Button placeorderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        rcvCart = findViewById(R.id.recViewCart);
        placeorderBtn = findViewById(R.id.orderbtnCart);

        cartItemsList=new ArrayList<>();
        datalistCart();

        cartAdapter=new CartAdapter(cartItemsList);
        rcvCart.setAdapter(cartAdapter);

        LinearLayoutManager llm = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rcvCart.setLayoutManager(llm);

    }

    private void datalistCart() {
        cartItemsList.add(new CartItems("Biryani","230","2","460",R.drawable.biryani));
        cartItemsList.add(new CartItems("Biryani","230","2","460",R.drawable.biryani));
        cartItemsList.add(new CartItems("Biryani","230","2","460",R.drawable.biryani));
        cartItemsList.add(new CartItems("Biryani","230","2","460",R.drawable.biryani));
        cartItemsList.add(new CartItems("Biryani","230","2","460",R.drawable.biryani));
        cartItemsList.add(new CartItems("Biryani","230","2","460",R.drawable.biryani));
        cartItemsList.add(new CartItems("Biryani","230","2","460",R.drawable.biryani));
        cartItemsList.add(new CartItems("Biryani","230","2","460",R.drawable.biryani));


    }
}