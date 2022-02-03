package com.example.minerest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuDashboard extends AppCompatActivity {
    RecyclerView rcv;
    Button cartRevBtn;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_dashboard);

        Bundle bundle = getIntent().getExtras();
        token=bundle.getString("token");

        rcv = findViewById(R.id.recView);

        processdata(token);

        LinearLayoutManager llm = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rcv.setLayoutManager(llm);


        cartRevBtn = findViewById(R.id.revbtndash);

        cartRevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cartIntent = new Intent(MenuDashboard.this, CartDashboard.class);
                Bundle cartBundle= new Bundle();
                cartBundle.putString("token",token);
                cartIntent.putExtras(cartBundle);
                startActivity(cartIntent);
            }
        });

    }

    public void processdata(String token) {
        Call<List<dashboard_response_model>> call = apicontroller
                                                    .getInstance()
                                                    .getapi()
                                                    .getdata(token);
        call.enqueue(new Callback<List<dashboard_response_model>>() {
            @Override
            public void onResponse(Call<List<dashboard_response_model>> call, Response<List<dashboard_response_model>> response) {
                List<dashboard_response_model> dashData= response.body();

                DashboardAdapter dashboardAdapter = new DashboardAdapter(dashData,MenuDashboard.this);
                rcv.setAdapter(dashboardAdapter);
                dashboardAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<dashboard_response_model>> call, Throwable t) {
                Toast.makeText(MenuDashboard.this, "There was some error fetching data. Please try again!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                SharedPreferences preferences =getSharedPreferences("credentials",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();

                Intent logoutintent = new Intent(MenuDashboard.this,MainActivity.class);
                startActivity(logoutintent);
                finish();
                break;

            case R.id.cart_intent_option:
                startActivity(new Intent(MenuDashboard.this,CartDashboard.class));
                break;

            case R.id.bill_intent_option:
                Intent roIntent = new Intent(MenuDashboard.this, RecentOrders.class);
                Bundle roBundle= new Bundle();
                roBundle.putString("token",token);
                roIntent.putExtras(roBundle);
                startActivity(roIntent);
                break;

            case R.id.contactus_intent_option:
                Toast.makeText(MenuDashboard.this,"Contact us option selected",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.dashboard_option_menu,menu);
        return true;
    }

}