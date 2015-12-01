package com.example.piroacc.myapplication.activity.common;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.piroacc.myapplication.R;
import com.example.piroacc.myapplication.async.common.Registration;

public class RegistrationActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextName;
    private EditText editTextPassword;
    private EditText editTextPhoneNumber;
    private Spinner spinnerRole;
    private String[] arraySpinner = {"Parent", "Child"};

    private String email;
    private String name;
    private String password;
    private String phoneNumber;
    private String role;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        findUIElements();
        initializeSpinner();
    }

    public void register(View view){
        getValuesFromUI();
        new Registration().execute(email,name,password,phoneNumber,role);
    }

    private void getValuesFromUI(){
        email=editTextEmail.getText().toString();
        name=editTextName.getText().toString();
        password=editTextPassword.getText().toString();
        phoneNumber=editTextPhoneNumber.getText().toString();
        role= spinnerRole.getSelectedItem().toString();
    }

    private void findUIElements(){
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextName = (EditText)findViewById(R.id.editTextName);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        editTextPhoneNumber = (EditText)findViewById(R.id.editTextPhoneNumber);
        spinnerRole = (Spinner) findViewById(R.id.spinnerRole);
        Bundle b = getIntent().getExtras();
        String recieviedEmail = b.getString("email");
        if(!recieviedEmail.equals("")){
            editTextEmail.setText(recieviedEmail);
        }
    }

    private void initializeSpinner(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        spinnerRole.setAdapter(adapter);
    }
}
