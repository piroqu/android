package com.example.piroacc.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstScreenActivity extends AppCompatActivity {

    public void goToParentReigstrationPage(View v){
        Intent i = new Intent(this,RodzicRegistrationActivity.class);
        startActivity(i);
    }

    Button parentRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        parentRegistration = (Button) findViewById(R.id.btnRodzic);

    }
}
