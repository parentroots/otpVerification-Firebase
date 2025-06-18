package com.example.otpverificationfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

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

            if (edNumber.getText().toString().trim().isEmpty()){

                edNumber.setError("Please Enter a number");

            }else {


                String sendOtpNumber=edNumber.getText().toString().trim();


               PhoneAuthProvider.getInstance().verifyPhoneNumber("+88" + sendOtpNumber, 120, TimeUnit.SECONDS, this
                       , new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                           @Override
                           public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                           }

                           @Override
                           public void onVerificationFailed(@NonNull FirebaseException e) {
                               Toast.makeText(SendOtpActivity.this, "Error Is"+e.getMessage(), Toast.LENGTH_SHORT).show();

                           }

                           @Override
                           public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                               Intent intent=new Intent(SendOtpActivity.this, OtpVerifyActivity.class);
                               intent.putExtra("sendOtpNumber",sendOtpNumber);
                               intent.putExtra("verificationId",verificationId);
                               startActivity(intent);

                           }
                       }


               );






                //animation for open a  new activity
                overridePendingTransition(R.anim.activity_open_animation, R.anim.activity_open_animation);
            }








        });


    }




    private  void variableFinder(){
        sendOtpBtn=findViewById(R.id.sendOtpBtn);
        edNumber=findViewById(R.id.edNumber);

    }


}