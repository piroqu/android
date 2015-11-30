package com.example.piroacc.myapplication.activity.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.piroacc.myapplication.R;
import com.example.piroacc.myapplication.activity.child.DzieckoMainActivity;
import com.example.piroacc.myapplication.activity.parent.RodzicMapsActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }
}
