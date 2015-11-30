package com.example.piroacc.myapplication.activity.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.piroacc.myapplication.R;
import com.example.piroacc.myapplication.activity.child.DzieckoMainActivity;
import com.example.piroacc.myapplication.activity.parent.RodzicMapsActivity;
import com.example.piroacc.myapplication.helper.DatabaseHelper;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        DatabaseHelper.getInstance(this);
        goToCorrectPage();
    }


    private void goToCorrectPage(){
        if(checkIfUserAccountExists()){
            if(checkIfUserIsParent()){
                Intent i = new Intent(this, RodzicMapsActivity.class);
                startActivity(i);
            }
            else{
                Intent i = new Intent(this, DzieckoMainActivity.class);
                startActivity(i);
            }
        }else{
            Intent i = new Intent(this, ChooseRoleActivity.class);
            startActivity(i);
        }
    }

    private boolean checkIfUserAccountExists(){
        if (DatabaseHelper.getInstance(this).getParents().size() != 0 ||  DatabaseHelper.getInstance(this).getChilds().size() != 0){
            return true;
        }
        else return false;
    }

    private boolean checkIfUserIsParent() {
        if (DatabaseHelper.getInstance(this).getParents().size() == 1 ) {
            return true;
        } else return false;
    }
}
