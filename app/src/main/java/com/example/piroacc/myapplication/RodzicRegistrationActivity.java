package com.example.piroacc.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.piroacc.myapplication.rest.parent.RodzicRegister;

public class RodzicRegistrationActivity extends AppCompatActivity {

    public static final String logInfo = "registerButton";

    public void registerButton(View v){
        EditText imieUI =(EditText) findViewById(R.id.txtImie);
        String name = imieUI.getText().toString();
        Log.d(logInfo, "name : " + name);
        EditText emailUI =(EditText) findViewById(R.id.txtEmail);
        String email = emailUI.getText().toString();
        Log.d(logInfo, "email : " + email);
        EditText numerTelefonuUI =(EditText) findViewById(R.id.txtNumerTelefonu);
        String numerTelefonu = numerTelefonuUI.getText().toString();
        Log.d(logInfo, "email : " + numerTelefonu);
        EditText hasloUI =(EditText) findViewById(R.id.txtHaslo);
        String haslo = hasloUI.getText().toString();
        Log.d(logInfo, "email : " + haslo);
        Toast.makeText(getApplicationContext(), "Wysylam request", Toast.LENGTH_SHORT).show();
        String[] userData = {name,email,numerTelefonu,haslo};
        new RodzicRegister().execute(userData);   // dodac jakiegos boola ktory zostanie zwrocony z async taska
        // if(asyncDone){goToParnetRegistrationPage}
        goToParentReigstrationPage(v);
    }

    private void goToParentReigstrationPage(View v){
        Intent i = new Intent(this,RodzicMapsActivity.class);
        startActivity(i);
    }

    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rodzic_registration);
        b1 = (Button) findViewById(R.id.btnRegister);
    }
}
