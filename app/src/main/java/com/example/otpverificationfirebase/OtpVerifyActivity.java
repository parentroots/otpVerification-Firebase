package com.example.otpverificationfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.mukeshsolanki.OtpView;

public class OtpVerifyActivity extends AppCompatActivity {

    TextView number;
    AppCompatButton verifyOtpBtn;
    OtpView otpView;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_otp_verify);

        number=findViewById(R.id.number);
        verifyOtpBtn=findViewById(R.id.verifyOtpBtn);
        otpView=findViewById(R.id.otp_view);


        Intent intent=getIntent();
        String intentNumber=intent.getStringExtra("sendOtpNumber");
        String verificationId=intent.getStringExtra("verificationId");
        number.setText(intentNumber);


        verifyOtpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (otpView.getText().toString().isEmpty()){

                    otpView.setError("Please Enter OTP");
                }else {


                    String otpNumber=otpView.getText().toString().trim();

                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, otpNumber);

                    // Firebase এ sign-in করা
                    signInWithCredential(credential);

                }


            }
        });



    }//onCreate method ends here

    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // OTP verification successful
                        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();

                        // এবার চাইলে Dashboard / Home Activity-তে পাঠাতে পারিস
                        Intent intent = new Intent(OtpVerifyActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(this, "Verification Failed", Toast.LENGTH_SHORT).show();
                    }
                });


    }



}