package com.example.piroacc.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseRoleActivity extends AppCompatActivity {


    private Button btnRegisterRodzic;
    private Button btnRegisterChild;
    private Bundle bundle;
    private String recievedEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_role);
        btnRegisterRodzic = (Button) findViewById(R.id.btnParent);
        btnRegisterChild = (Button) findViewById(R.id.btnChild);
        bundle = getIntent().getExtras();
        recievedEmail = bundle.getString("email");

    }

    public void goToParentReigstrationPage(View v) {
        Intent i = new Intent(this, RodzicRegistrationActivity.class);
        i.putExtra("email", recievedEmail);
        startActivity(i);
    }

    public void goToChildRegistrationPage(View v) {
        Intent i = new Intent(this, DzieckoRegistrationActivity.class);
        startActivity(i);
    }

}
