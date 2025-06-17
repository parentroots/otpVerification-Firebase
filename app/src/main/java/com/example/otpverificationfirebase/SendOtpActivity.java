package com.example.otpverificationfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SendOtpActivity extends AppCompatActivity {

    AppCompatButton sendOtpBtn;
    EditText edNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_send_otp);

        variableFinder();



        sendOtpBtn.setOnClickListener(v -> {

            String sendOtpNumber=edNumber.getText().toString().trim();


            Intent intent=new Intent(SendOtpActivity.this, OtpVerifyActivity.class);
            intent.putExtra("sendOtpNumber",sendOtpNumber);
            startActivity(intent);

        });



    }

    private  void variableFinder(){
        sendOtpBtn=findViewById(R.id.sendOtpBtn);
        edNumber=findViewById(R.id.edNumber);

    }


}