package com.example.piroacc.myapplication.activity.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.piroacc.myapplication.R;
import com.example.piroacc.myapplication.activity.child.ChildMainActivity;
import com.example.piroacc.myapplication.activity.parent.ParentMainActivity;
import com.example.piroacc.myapplication.async.common.Registration;
import com.example.piroacc.myapplication.model.dto.request.UserDataResponse;

import java.util.concurrent.ExecutionException;

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

    private UserDataResponse userData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        findUIElements();
        initializeSpinner();
    }

    public void register(View view){
        getValuesFromUI();
        try {
            userData=new Registration().execute(email,password,name,phoneNumber,role).get();
            if(role.equals("parent")){
                goToParentActivity();
            }else if(role.equals("child")){
                goToChildActivity();
            }else if(userData.getRole().equals("wrong")){
                Toast.makeText(getApplicationContext(), "Sorry but we are not able to create your account," +
                                "probably account with email address already exists",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    private void goToChildActivity(){
        Intent i = new Intent(this, ChildMainActivity.class);
        i.putExtra("user data" ,userData );
        startActivity(i);
    }
    private void goToParentActivity(){
        Intent i = new Intent(this, ParentMainActivity.class);
        i.putExtra("user data" ,userData );
        startActivity(i);
    }

    private void getValuesFromUI(){
        email=editTextEmail.getText().toString();
        name=editTextName.getText().toString();
        password=editTextPassword.getText().toString();
        phoneNumber=editTextPhoneNumber.getText().toString();
        role= spinnerRole.getSelectedItem().toString().toLowerCase();
    }

    private void findUIElements(){
        editTextEmail = (EditText)findViewById(R.id.register_email);
        editTextName = (EditText)findViewById(R.id.register_name);
        editTextPassword = (EditText)findViewById(R.id.register_password);
        editTextPhoneNumber = (EditText)findViewById(R.id.register_phone_number);
        spinnerRole = (Spinner) findViewById(R.id.register_spinner_role);
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
