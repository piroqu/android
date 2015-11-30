package com.example.piroacc.myapplication.activity.child;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.piroacc.myapplication.R;
import com.example.piroacc.myapplication.model.Uzytkownik;
import com.example.piroacc.myapplication.model.dto.request.DzieckoMDTORequest;
import com.example.piroacc.myapplication.rest.child.SendConnectionRequest;

public class DzieckoRequestConnectionToParentActivity extends AppCompatActivity {

    public static final String DEBUG_LOG_REQUEST = "Dziecko sendRequest : ";

    private Button btnConnectionRequest;
    private EditText txtParentId;
    private String parentId;
    private Uzytkownik currentChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dziecko_request_to_parent);
        findUIElements();
    }

    public void sendRequest(View v){
        getUIElementsValues();
        DzieckoMDTORequest dzieckoMDTORequest = createRequest(currentChild);
        new SendConnectionRequest().execute(parentId,dzieckoMDTORequest);
    }

    public DzieckoMDTORequest createRequest(Uzytkownik user){
        return new DzieckoMDTORequest(user);
    }

    private void findUIElements(){
        btnConnectionRequest = (Button) findViewById(R.id.btnRegisterParent);
        txtParentId= (EditText) findViewById(R.id.txtParentId);
    }

    public void getUIElementsValues(){
        parentId= txtParentId.getText().toString();
        Log.d(DEBUG_LOG_REQUEST, "parentIdUI : " + parentId);
    }
}
