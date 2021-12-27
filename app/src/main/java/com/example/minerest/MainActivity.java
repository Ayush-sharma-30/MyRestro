package com.example.minerest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText username, password;
    TextView CreateNewAcc;
    Button loginBtn;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username=findViewById(R.id.etemail);
        password=findViewById(R.id.mypass);
        CreateNewAcc = findViewById(R.id.createnewac);
        loginBtn = findViewById(R.id.btnlogin);

        CreateNewAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUpIntent = new Intent(MainActivity.this,SignUp.class);
                startActivity(signUpIntent);
                finish();
            }
        });

        verifyuserexistence();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().trim().equalsIgnoreCase("")) {
                    username.setError("Enter username");
                }
                if (password.getText().toString().trim().equalsIgnoreCase("")) {
                    password.setError("Enter password");
                } else {
                    processlogin(username.getText().toString(), password.getText().toString());
                }
            }
        });
    }

    public void processlogin(String username, String password) {

        Call<login_response_model> call=apicontroller.getInstance()
                                        .getapi()
                                        .getlogin(username, password);
        call.enqueue(new Callback<login_response_model>() {
            @Override
            public void onResponse(Call<login_response_model> call, Response<login_response_model> response) {
                login_response_model obj= response.body();
                String result = obj.getMessage();
                if(result.equals("true")){
                    SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("username",username);
                    editor.putString("password",password);
                    editor.commit();
                    editor.apply();
                    startActivity(new Intent(MainActivity.this,MenuDashboard.class));
                    finish();
                }
                if(result.equals("Error logging in")){
                    Toast.makeText(MainActivity.this,"Something went wrong! Please try again.",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<login_response_model> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong! Try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void verifyuserexistence(){
        SharedPreferences sp=getSharedPreferences("credentials",MODE_PRIVATE);
        if(sp.contains("username")){
            startActivity(new Intent(getApplicationContext(),MenuDashboard.class));
        }
    }
}
