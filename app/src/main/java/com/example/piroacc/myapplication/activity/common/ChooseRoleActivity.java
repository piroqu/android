package com.example.piroacc.myapplication.activity.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.piroacc.myapplication.activity.child.DzieckoMainActivity;
import com.example.piroacc.myapplication.activity.child.DzieckoRequestConnectionToParentActivity;
import com.example.piroacc.myapplication.activity.child.DzieckoRegistrationActivity;
import com.example.piroacc.myapplication.R;
import com.example.piroacc.myapplication.activity.parent.RodzicMapsActivity;
import com.example.piroacc.myapplication.activity.parent.RodzicRegistrationActivity;
import com.example.piroacc.myapplication.helper.DatabaseHelper;

public class ChooseRoleActivity extends AppCompatActivity {


    private Button btnRegisterRodzic;
    private Button btnRegisterChild;
    private EditText emailOrIdzChildUI;
    private EditText hasloUI;

    private String emailOrIdzChild;
    private String haslo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_role);
        findUIElements();

    }

    private void findUIElements() {
        btnRegisterRodzic = (Button) findViewById(R.id.btnParent);
        btnRegisterChild = (Button) findViewById(R.id.btnChild);
        emailOrIdzChildUI = (EditText) findViewById(R.id.editTxtEmailOrIdChild);
        hasloUI = (EditText) findViewById(R.id.editTextPassword);
    }

    private void initializeUIELementsValues() {
        emailOrIdzChild = emailOrIdzChildUI.getText().toString();
        haslo = hasloUI.getText().toString();
    }


    public void goToParentReigstrationPage(View v) {
        initializeUIELementsValues();
        if (DatabaseHelper.getInstance(this).getParents().size() == 0) {
            Intent i = new Intent(this, RodzicRegistrationActivity.class);
            i.putExtra("email",emailOrIdzChild);
            i.putExtra("haslo",haslo);
            startActivity(i);
        } else {
            Intent i = new Intent(this, RodzicMapsActivity.class);
            startActivity(i);
        }
    }

    public void goToChildRegistrationPage(View v) {
        if (DatabaseHelper.getInstance(this).getChilds().size() == 0) {
            Intent i = new Intent(this, DzieckoRegistrationActivity.class);
            startActivity(i);
        } else {
            Intent i = new Intent(this, DzieckoMainActivity.class);
            startActivity(i);
        }
    }

}
