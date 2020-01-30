package com.example.password_protected_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    String Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //load the password
        SharedPreferences settings= getSharedPreferences("PREFS",0);
        Password = settings.getString("Password","");

        Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(Password.equals(" "))
                {
                    //if there is no password
                    Intent intent= new Intent(getApplicationContext(),CreatPasswordActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    //if there is a password
                    Intent intent= new Intent(getApplicationContext(),EnterPasswordActivity.class);
                    startActivity(intent);
                    finish();

                }


            }
        }, 2000);




    }
}
