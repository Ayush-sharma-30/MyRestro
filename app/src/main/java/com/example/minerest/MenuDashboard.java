package com.example.minerest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MenuDashboard extends AppCompatActivity {
    RecyclerView rcv;
    DashboardAdapter dashboardAdapter;
    List<MenuItems> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_dashboard);


        rcv = findViewById(R.id.recView);

        data = new ArrayList<>();
        datalist();

        dashboardAdapter = new DashboardAdapter(data);
        rcv.setAdapter(dashboardAdapter);


        LinearLayoutManager llm = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rcv.setLayoutManager(llm);
    }

    private void datalist(){
        data.add(new MenuItems("Biryani","A perfect blend of Indian Spices","Veg",R.drawable.biryani,R.drawable.leaf,0));
        data.add(new MenuItems("Biryani","A perfect blend of Indian Spices","Veg",R.drawable.biryani,R.drawable.leaf,0));
        data.add(new MenuItems("Biryani","A perfect blend of Indian Spices","Veg",R.drawable.biryani,R.drawable.leaf,0));
        data.add(new MenuItems("Biryani","A perfect blend of Indian Spices","Veg",R.drawable.biryani,R.drawable.leaf,0));
        data.add(new MenuItems("Biryani","A perfect blend of Indian Spices","Veg",R.drawable.biryani,R.drawable.leaf,0));
        data.add(new MenuItems("Biryani","A perfect blend of Indian Spices","Veg",R.drawable.biryani,R.drawable.leaf,0));
        data.add(new MenuItems("Biryani","A perfect blend of Indian Spices","Veg",R.drawable.biryani,R.drawable.leaf,0));
        data.add(new MenuItems("Biryani","A perfect blend of Indian Spices","Veg",R.drawable.biryani,R.drawable.leaf,0));
        data.add(new MenuItems("Biryani","A perfect blend of Indian Spices","Veg",R.drawable.biryani,R.drawable.leaf,0));

    }
}