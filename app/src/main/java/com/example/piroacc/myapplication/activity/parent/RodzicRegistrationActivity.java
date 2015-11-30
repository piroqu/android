package com.example.piroacc.myapplication.activity.parent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.piroacc.myapplication.R;
import com.example.piroacc.myapplication.helper.DateParser;
import com.example.piroacc.myapplication.model.Uzytkownik;
import com.example.piroacc.myapplication.model.dto.request.RodzicMDTORequest;
import com.example.piroacc.myapplication.model.dto.response.RodzicMDTOResponse;
import com.example.piroacc.myapplication.rest.parent.RodzicRegister;

import java.util.concurrent.ExecutionException;

public class RodzicRegistrationActivity extends AppCompatActivity {

    public static final String LOG_UI_INFO = "REGISTRATION PARENT UI DATA ";

    private static final String LOG_PARENT_REGISTRATION = "PARENT REGISTRATION ";
    private Button b1;
    private EditText emailTextView;
    private EditText imieUI;
    private EditText emailUI;
    private EditText numerTelefonuUI;
    private EditText hasloUI;

    private String email;
    private String numerTelefonu;
    private String haslo;
    private String imie;

    private Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rodzic_registration);
            findUIElements();
            getUIElementsValues();
    }
    public void registerButton(View v) {
        Toast.makeText(getApplicationContext(), "Wysylam request", Toast.LENGTH_SHORT).show();
        RodzicMDTORequest rodzicMDTORequest = createRegistrationRequestBasedOnUIValues();
        try {
            RodzicMDTOResponse rodzicMDTOResponse = new RodzicRegister().execute(rodzicMDTORequest).get();
            rodzicMDTORequest.setRodzicId(rodzicMDTOResponse.getParentId());
            Uzytkownik user = createUser(rodzicMDTORequest);
            goToMap();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public RodzicMDTORequest createRegistrationRequestBasedOnUIValues() {
        getUIElementsValues();
        RodzicMDTORequest rodzicMDTORequest = new RodzicMDTORequest();
        rodzicMDTORequest.setEmail(email);
        rodzicMDTORequest.setHaslo(haslo);
        rodzicMDTORequest.setNumerTelefonu(numerTelefonu);
        rodzicMDTORequest.setImie(imie);
        rodzicMDTORequest.setDataUtworzenia(DateParser.getCurrentParsedDateAsString());
        rodzicMDTORequest.setStatus(true);
        return rodzicMDTORequest;
    }

    public Uzytkownik createUser(RodzicMDTORequest rodzicMDTORequest) {
        Uzytkownik user = new Uzytkownik();
        user.setId(rodzicMDTORequest.getRodzicId());
        user.setImie(rodzicMDTORequest.getImie());
        user.setHaslo(rodzicMDTORequest.getHaslo());
        user.setEmail(rodzicMDTORequest.getEmail());
        user.setNumerTelefonu(rodzicMDTORequest.getNumerTelefonu());
        user.setDataUtworzenia(DateParser.getCurrentParsedDateAsString());
        return user;
    }

    private void goToMap() {
        Intent i = new Intent(this, RodzicMapsActivity.class);
        startActivity(i);
    }
    private void findUIElements() {
        b1 = (Button) findViewById(R.id.btnRegisterParent);
        imieUI = (EditText) findViewById(R.id.txtImie);
        emailUI = (EditText) findViewById(R.id.txtEmail);
        numerTelefonuUI = (EditText) findViewById(R.id.txtNumerTelefonu);
        hasloUI = (EditText) findViewById(R.id.txtHaslo);
        emailTextView = (EditText) findViewById(R.id.txtEmail);
        bundle = getIntent().getExtras();
        emailUI.setText(bundle.getString("email"));
        hasloUI.setText(bundle.getString("haslo"));
    }

    private void getUIElementsValues() {

        imie = imieUI.getText().toString();
        Log.d(LOG_UI_INFO, "imie : " + imie);
        email = emailUI.getText().toString();
        Log.d(LOG_UI_INFO, "email : " + email);
        numerTelefonu = numerTelefonuUI.getText().toString();
        Log.d(LOG_UI_INFO, "numerTelefonu : " + numerTelefonu);
        haslo = hasloUI.getText().toString();
        Log.d(LOG_UI_INFO, "haslo : " + haslo);
    }
}
