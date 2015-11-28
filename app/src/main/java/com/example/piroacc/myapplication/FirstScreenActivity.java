package com.example.piroacc.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.piroacc.myapplication.helper.DatabaseHelper;
import com.example.piroacc.myapplication.model.Uzytkownik;

import java.util.List;

public class FirstScreenActivity extends AppCompatActivity {

    public void goToParentReigstrationPage(View v){
        Intent i = new Intent(this,RodzicRegistrationActivity.class);
        startActivity(i);
    }

    public void goToChildRegistrationPage(View v){
        Intent i = new Intent(this,DzieckoRegistrationActivity.class);
        startActivity(i);
    }

    Button parentRegistration;
    Button dzieckoRegistration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        parentRegistration = (Button) findViewById(R.id.btnRodzic);
        dzieckoRegistration = (Button) findViewById(R.id.btnDziecko);
        List<Uzytkownik> users = DatabaseHelper.getInstance(this).getUsers();
        Log.d("DATABASE SIZE : ", String.valueOf(users.size()));
 /*       Log.d("DATABASE CREATOR", "z FirstScreenActivity");
        DatabaseHelper db = new DatabaseHelper(this);
        Log.d("DATABASE CREATOR", "CREATED");

        Uzytkownik user = new Uzytkownik();
        user.setEmail("werwe@qwee.pl");
        user.setImie("Imje");
        user.setHaslo("123chusd");
        Log.d("DATABASE CREATOR", "USER BEFORE");
        db.insertUzytkownik(user);
        Log.d("DATABASE CREATOR", "USER INSERTED!");
        List<Uzytkownik> users = db.getUsers();
        Log.d("DATABASE SIZE : " , String.valueOf(users.size()));
        for(Uzytkownik tempUser: users){
            Log.d("USER", tempUser.getImie());
        }
*/
    }
}
