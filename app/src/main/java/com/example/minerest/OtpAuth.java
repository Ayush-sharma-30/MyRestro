package com.example.minerest;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpAuth extends AppCompatActivity {
    EditText submitotptv;
    Button submitotptbn;
    String phoneNumber;
    String otpid;
    FirebaseAuth mAuth;
    String username;
    String useremail;
    String userpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_auth);

        Bundle bundle = getIntent().getExtras();

        username=bundle.getString("name");
        useremail=bundle.getString("email");
        userpass=bundle.getString("pass");
        phoneNumber = bundle.getString("mobile");

        submitotptv = findViewById(R.id.submitotptv);
        submitotptbn = findViewById(R.id.submitotpbtn);

        mAuth=FirebaseAuth.getInstance();
        initiateotp();

        submitotptbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(submitotptv.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "Blank field cannot be processed!", Toast.LENGTH_LONG).show();
                else if(submitotptv.getText().toString().length()!=6)
                    Toast.makeText(getApplicationContext(), "Invalid OTP!", Toast.LENGTH_SHORT).show();
                else{
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(otpid,submitotptv.getText().toString());
                    signInWithPhoneAuthCredential(credential);

                }
            }
        });
    }

    private void initiateotp() {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                otpid=s;
                            }

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                signInWithPhoneAuthCredential(phoneAuthCredential);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(getApplicationContext(),"Oops, something went wrong!", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(OtpAuth.this,SignUp.class));
                                finish();
                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            getregister(username,useremail,userpass,phoneNumber);
                            }
                        else {
                            Toast.makeText(getApplicationContext(), "Oops, there was some error while signup!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void getregister(String name,String email,String pass,String number){
                String uname=name;
                String uemail=email;
                String upass=pass;
                String unumber=number;
        Call<signup_response_model> call=apicontroller.getInstance()
                                         .getapi()
                                        .getregister(uname,unumber,uemail,upass);
        call.enqueue(new Callback<signup_response_model>() {
            @Override
            public void onResponse(Call<signup_response_model> call, Response<signup_response_model> response) {
//                System.out.println("#############");
//                System.out.println(response);
//                System.out.println(response);
//                System.out.println(response.body());
                signup_response_model obj = response.body();
                String result = obj.getMessage();
                if(result.equals("true")) {
                    Toast.makeText(OtpAuth.this,"You are registered!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(OtpAuth.this, MainActivity.class));
                    finish();
                }else if(result.equals("false")){
                    Toast.makeText(OtpAuth.this,"Email/Phone number already exists!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(OtpAuth.this, MainActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<signup_response_model> call, Throwable t) {
                Toast.makeText(OtpAuth.this,"Oops, there was some error while registering!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(OtpAuth.this,SignUp.class));
                    finish();
            }
        });
    }
}