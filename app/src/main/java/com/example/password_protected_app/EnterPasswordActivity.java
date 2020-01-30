package com.example.password_protected_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnterPasswordActivity extends AppCompatActivity  {
    EditText editText;
    Button button;
    String Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_password);

        //load the password
        SharedPreferences settings= getSharedPreferences("PREFS",0);
        Password = settings.getString("Password","");

        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.Enter_Buttom);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();

                if(text.equals(Password)){
                    //enter the app
                    Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{

                    Toast.makeText(EnterPasswordActivity.this, "Wrong password!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),CreatPasswordActivity.class);
                    startActivity(intent);
                    finish();

                }
            }
        });
    }



}
