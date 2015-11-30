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
import com.example.piroacc.myapplication.model.Uzytkownik;
import com.example.piroacc.myapplication.model.dto.request.RodzicMDTORequest;
import com.example.piroacc.myapplication.rest.parent.RodzicLogin;

import java.util.concurrent.ExecutionException;

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
    }

}
