package com.example.piroacc.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.piroacc.myapplication.helper.DatabaseHelper;

public class ChooseRoleActivity extends AppCompatActivity {


    private Button btnRegisterRodzic;
    private Button btnRegisterChild;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_role);
        btnRegisterRodzic = (Button) findViewById(R.id.btnParent);
        btnRegisterChild = (Button) findViewById(R.id.btnChild);
    }

    public void goToParentReigstrationPage(View v) {
        if(DatabaseHelper.getInstance(this).getParents().size()==0) {
            Intent i = new Intent(this, RodzicRegistrationActivity.class);
            startActivity(i);
        }else{
            Intent i = new Intent(this, RodzicMapsActivity.class);
            startActivity(i);
        }
    }
    public void goToChildRegistrationPage(View v) {
        Intent i = new Intent(this, DzieckoRegistrationActivity.class);
        startActivity(i);
    }

}
