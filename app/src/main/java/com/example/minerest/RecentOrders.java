package com.example.minerest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecentOrders extends AppCompatActivity {

    RecyclerView recviewRO;
    Button backDash;
    String token;
    List<OrderField> orderFields;
    LoadingDialog loadingDialog=new LoadingDialog(RecentOrders.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_orders);

        Bundle bundle = getIntent().getExtras();
        token=bundle.getString("token");


        recviewRO = findViewById(R.id.recViewRO);
        backDash = findViewById(R.id.revbtnRO);

        processdata(token);

        LinearLayoutManager llm = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recviewRO.setLayoutManager(llm);


        backDash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingDialog.startLoadingDialog();
                startActivity(new Intent(RecentOrders.this,MenuDashboard.class));
                loadingDialog.dismissDialog();
                finish();
            }
        });


    }

    public void processdata(String token) {

        loadingDialog.startLoadingDialog();
         Call<RO_response_model> call = apicontroller.getInstance().getapi().getorderData(token);
         call.enqueue(new Callback<RO_response_model>() {
             @Override
             public void onResponse(Call<RO_response_model> call, Response<RO_response_model> response) {

                 RO_response_model ro_response_model = response.body();
                 orderFields = new ArrayList<>(Arrays.asList(ro_response_model.getOrderFields()));

                 RecentOrderAdapter recentOrderAdapter = new RecentOrderAdapter(orderFields,RecentOrders.this);
                 recviewRO.setAdapter(recentOrderAdapter);
                 recentOrderAdapter.notifyDataSetChanged();
                 loadingDialog.dismissDialog();
             }

             @Override
             public void onFailure(Call<RO_response_model> call, Throwable t) {
                 loadingDialog.dismissDialog();
             }
         });
    }
}