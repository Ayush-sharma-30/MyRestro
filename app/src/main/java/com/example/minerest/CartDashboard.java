package com.example.minerest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartDashboard extends AppCompatActivity implements PaymentResultListener {

    RecyclerView recview;
    TextView rateView;
    Button checkout_button;
    int sum=0;
    String token;
    List<MenuItemsTable> products;
    LoadingDialog loadingDialog=new LoadingDialog(CartDashboard.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getSupportActionBar().hide();

        Bundle bundle = getIntent().getExtras();
        token=bundle.getString("token");

        rateView=findViewById(R.id.rateview);
        getcartdata();


        checkout_button=findViewById(R.id.checkout_button);
        checkout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingDialog.startLoadingDialog();
                int samount =sum;
                int amount=Math.round(samount*100);

                Checkout checkout = new Checkout();
                checkout.setKeyID("rzp_test_7I8TlVOhwrNGWQ");
                checkout.setImage(R.drawable.logo);
                JSONObject object=new JSONObject();
                try {
                    object.put("name","Food Overflow");
                    object.put("Description","Food order payment");
                    object.put("amount",amount);
                    loadingDialog.dismissDialog();
                    checkout.open(CartDashboard.this,object);
                } catch (JSONException e) {
                    loadingDialog.dismissDialog();
                    e.printStackTrace();
                }
            }
        });
    }
    public void getcartdata()
    {
        loadingDialog.startLoadingDialog();
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "cart_db").allowMainThreadQueries().build();
        MenuItemsDao productDao = db.MenuItemsDao();

        recview=findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        products=productDao.getallproduct();

        CartAdapter adapter=new CartAdapter(products, rateView);
        recview.setAdapter(adapter);

        int i;
        for(i=0;i< products.size();i++)
            sum= (int) (sum+(products.get(i).getPrice()*products.get(i).getQnt()));

        rateView.setText("Total Amount : INR "+sum);
        loadingDialog.dismissDialog();
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(CartDashboard.this, "Order Successfull. Transaction No."+s, Toast.LENGTH_SHORT).show();
        processorder(products,token);
    }

    public void processorder(List<MenuItemsTable> products, String token) {
        loadingDialog.startLoadingDialog();
        CreateOrderDTO createOrderDTO=new CreateOrderDTO();
        ArrayList<FooditemDTO> fooditemDTOList = new ArrayList<>();
        for(int i=0;i<products.size();i++){
            FooditemDTO fooditemDTO=new FooditemDTO(products.get(i).getPname(),Integer.toString(products.get(i).getQnt()));
            fooditemDTOList.add(fooditemDTO);

        }
        createOrderDTO.setFooditem(fooditemDTOList);
        createOrderDTO.setPrice(sum);

        Call<FooditemDTO> call=apicontroller
                                .getInstance()
                                .getapi()
                                .getorders(token, createOrderDTO);

        call.enqueue(new Callback<FooditemDTO>() {
            @Override
            public void onResponse(Call<FooditemDTO> call, Response<FooditemDTO> response) {
                Toast.makeText(CartDashboard.this,"Your order has been placed!",Toast.LENGTH_SHORT).show();
                loadingDialog.dismissDialog();
                startActivity(new Intent(CartDashboard.this,MenuDashboard.class));
            }

            @Override
            public void onFailure(Call<FooditemDTO> call, Throwable t) {
                loadingDialog.dismissDialog();
                Toast.makeText(CartDashboard.this, "Oops, please try again!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onPaymentError(int i, String s) {
        loadingDialog.dismissDialog();
        Toast.makeText(CartDashboard.this,"Payment could not be processed.",Toast.LENGTH_SHORT).show();
    }
}