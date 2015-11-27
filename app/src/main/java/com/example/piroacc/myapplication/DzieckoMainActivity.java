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
import com.example.piroacc.myapplication.rest.child.SendConnectionRequest;

public class DzieckoMainActivity extends AppCompatActivity {

    public static final String logInfo = "Dziecko sendRequest:";

    public void sendRequest(View v){
        EditText parentIdUI =(EditText) findViewById(R.id.txtParentId);
        String parentIdasString = parentIdUI.getText().toString();
        Integer parentId = Integer.valueOf(parentIdasString);
        Integer [] userData = {1,parentId}; //TODO zmienic na uzywana z bazy danych
        Log.d(logInfo, "parentId : " + parentId);
        new SendConnectionRequest().execute(userData);   // dodac jakiegos boola ktory zostanie zwrocony z async taska
    }
    private Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dziecko_screen);
        b1 = (Button) findViewById(R.id.btnRegister);
    }

}
