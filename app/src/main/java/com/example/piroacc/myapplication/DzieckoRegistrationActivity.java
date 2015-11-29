package com.example.piroacc.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.piroacc.myapplication.helper.DatabaseHelper;
import com.example.piroacc.myapplication.helper.DateParser;
import com.example.piroacc.myapplication.model.Dziecko;
import com.example.piroacc.myapplication.model.Uzytkownik;
import com.example.piroacc.myapplication.model.dto.request.DzieckoMDTORequest;
import com.example.piroacc.myapplication.model.dto.request.RodzicMDTORequest;
import com.example.piroacc.myapplication.model.dto.response.DzieckoMDTOResponse;
import com.example.piroacc.myapplication.rest.child.DzieckoRegister;

import java.util.concurrent.ExecutionException;

public class DzieckoRegistrationActivity extends AppCompatActivity {

    public static final String DEBUG_LOG_UI = "DZIECKO UI ";
    public static Integer DZIECKO_ID;

    private Button b1;
    private EditText imieUI;
    private EditText hasloUI;

    private String imie;
    private String haslo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dziecko_registration);
        DatabaseHelper.getInstance(this).getChilds();
        findUIElements();
        getUIElementsValues();
    }

    public void registerButton(View v){
        Toast.makeText(getApplicationContext(), "Wysylam request", Toast.LENGTH_SHORT).show();
        DzieckoMDTORequest dzieckoMDTORequest =createRegistrationRequestBasedOnUIValues();
        DzieckoMDTOResponse dzieckoMDTOResponse = null;
        try {
            dzieckoMDTOResponse = new DzieckoRegister().execute(dzieckoMDTORequest).get();
            dzieckoMDTORequest.setDzieckoId(dzieckoMDTOResponse.getDzieckoId());
            Uzytkownik user = createUser(dzieckoMDTORequest);
            DatabaseHelper.getInstance(this).insertUzytkownikDziecko(user);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    private DzieckoMDTORequest createRegistrationRequestBasedOnUIValues() {
        getUIElementsValues();
        DzieckoMDTORequest dzieckoMDTORequest = new DzieckoMDTORequest();
        dzieckoMDTORequest.setImie(imie);
        dzieckoMDTORequest.setHaslo(haslo);
        dzieckoMDTORequest.setStatus(true);
        return dzieckoMDTORequest;
    }

    public Uzytkownik createUser(DzieckoMDTORequest dzieckoMDTORequest) {
        Uzytkownik user = new Uzytkownik();
        user.setId(dzieckoMDTORequest.getDzieckoId());
        user.setImie(dzieckoMDTORequest.getImie());
        user.setHaslo(dzieckoMDTORequest.getHaslo());
        user.setDataUtworzenia(DateParser.getCurrentParsedDateAsString());
        return user;
    }
    private void goToLocationChildActivity(View v){
        Intent i = new Intent(this,DzieckoLocationActivity.class);
        startActivity(i);
    }

    private void findUIElements(){
        b1= (Button) findViewById(R.id.btnRegisterParent);
        imieUI =(EditText) findViewById(R.id.txtImie);
        hasloUI =(EditText) findViewById(R.id.txtPassword);

    }

    public void getUIElementsValues(){
        imie = imieUI.getText().toString();
        Log.d(DEBUG_LOG_UI, "imie : " + imie);
        haslo = hasloUI.getText().toString();
        Log.d(DEBUG_LOG_UI, "haslo : " + haslo);
    }


}
