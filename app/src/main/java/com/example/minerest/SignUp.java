package com.example.minerest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hbb20.CountryCodePicker;

public class SignUp extends AppCompatActivity {
    Button getotpbtn;
    EditText phoneNum,name,email,pass;
    CountryCodePicker ccp;
    LoadingDialog loadingDialog=new LoadingDialog(SignUp.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        phoneNum = findViewById(R.id.editMobile);
        name=findViewById(R.id.editName);
        email=findViewById(R.id.editEmail);
        pass=findViewById(R.id.editPass);

        ccp=findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(phoneNum);

        getotpbtn = findViewById(R.id.getotptbn);

        getotpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().trim().equalsIgnoreCase("")) {
                    name.setError("Enter Name");
                }
                if (email.getText().toString().trim().equalsIgnoreCase("")) {
                    email.setError("Enter email");
                }
                if (pass.getText().toString().trim().equalsIgnoreCase("")) {
                    pass.setError("Enter password");
                }
                if (phoneNum.getText().toString().trim().equalsIgnoreCase("")) {
                    phoneNum.setError("Enter number");}
                if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
                        email.setError("Please enter a valid email address!");
                    }
                if(phoneNum.getText().toString().trim().length() < 10) {
                    phoneNum.setError("Please enter a valid phone number.");
                } else if(name.getText().toString().trim().length() > 0 && email.getText().toString().trim().length() > 0 && pass.getText().toString().trim().length() > 0 && phoneNum.getText().toString().trim().length() == 11) {
                    loadingDialog.startLoadingDialog();
                    Intent otpIntent = new Intent(SignUp.this, OtpAuth.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("mobile", ccp.getFullNumberWithPlus().replace(" ", ""));
                        bundle.putString("name", name.getText().toString());
                        bundle.putString("email", email.getText().toString());
                        bundle.putString("pass", pass.getText().toString());
                        otpIntent.putExtras(bundle);
                        startActivity(otpIntent);
                        loadingDialog.dismissDialog();
                    }
                }
        });
    }
}