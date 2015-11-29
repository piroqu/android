package com.example.piroacc.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.piroacc.myapplication.helper.DatabaseHelper;
import com.example.piroacc.myapplication.model.Dziecko;
import com.example.piroacc.myapplication.model.dto.response.DzieckoMDTOResponse;
import com.example.piroacc.myapplication.rest.child.DzieckoRegister;

import java.util.concurrent.ExecutionException;

public class DzieckoRegistrationActivity extends AppCompatActivity {

    public static final String logInfo = "Dziecko registerButton:";
    public static Integer DZIECKO_ID;


    public void registerButton(View v){
        EditText imieUI =(EditText) findViewById(R.id.txtImie);
        String name = imieUI.getText().toString();
        Log.d(logInfo, "name : " + name);
        EditText emailUI =(EditText) findViewById(R.id.txtPassword);
        String email = emailUI.getText().toString();
        Log.d(logInfo, "email : " + email);
        Toast.makeText(getApplicationContext(), "Wysylam request", Toast.LENGTH_SHORT).show();
        String[] userData = {name,email};
        DzieckoMDTOResponse responseBody = null;
        try {
            responseBody = new DzieckoRegister().execute(userData).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (responseBody!= null){
            Dziecko dziecko = new Dziecko(responseBody.getDzieckoId(),name);
            DZIECKO_ID = dziecko.getId();
            DatabaseHelper.getInstance(this).insertDziecko(dziecko);
            goToLocationChildActivity(v);
        }
        else {
            Toast.makeText(getApplicationContext(),
                    "Ops ! Cos nie dziala, serwer? Aplikacja? - nie wyslalem requesta !", Toast.LENGTH_LONG)
                    .show();
        }
    }

    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dziecko_registration);
        b1= (Button) findViewById(R.id.btnRegisterParent);
    }

    private void goToLocationChildActivity(View v){
        Intent i = new Intent(this,DzieckoLocationActivity.class);
        startActivity(i);
    }
}
