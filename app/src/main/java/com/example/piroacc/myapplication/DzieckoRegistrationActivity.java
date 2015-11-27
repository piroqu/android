package com.example.piroacc.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.piroacc.myapplication.rest.child.DzieckoRegister;

public class DzieckoRegistrationActivity extends AppCompatActivity {

    public static final String logInfo = "Dziecko registerButton:";


    public void registerButton(View v){
        EditText imieUI =(EditText) findViewById(R.id.txtImie);
        String name = imieUI.getText().toString();
        Log.d(logInfo, "name : " + name);
        EditText emailUI =(EditText) findViewById(R.id.txtPassword);
        String email = emailUI.getText().toString();
        Log.d(logInfo, "email : " + email);
        Toast.makeText(getApplicationContext(), "Wysylam request", Toast.LENGTH_SHORT).show();
        String[] userData = {name,email};
        new DzieckoRegister().execute(userData);   // dodac jakiegos boola ktory zostanie zwrocony z async taska
        // if(asyncDone){goToParnetRegistrationPage}
        gotToChildParentActivity(v);
    }

    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dziecko_registration);
        b1= (Button) findViewById(R.id.btnRegister);
    }

    private void gotToChildParentActivity(View v){
        Intent i = new Intent(this,DzieckoMainActivity.class);
        startActivity(i);
    }
}
