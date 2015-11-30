package com.example.piroacc.myapplication.activity.parent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.piroacc.myapplication.R;
import com.example.piroacc.myapplication.model.dto.request.RodzicMDTORequest;
import com.example.piroacc.myapplication.model.dto.response.KolejkaRodzicMDTOResponse;
import com.example.piroacc.myapplication.rest.parent.RodzicCheckRequests;

import java.util.List;

public class RodzicSynchronizeActivity extends AppCompatActivity {

    private KolejkaRodzicMDTOResponse[] arraySpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rodzic_synchronize);
        KolejkaRodzicMDTOResponse test = new KolejkaRodzicMDTOResponse();

        RodzicMDTORequest request =null;
//        List<KolejkaRodzicMDTOResponse> requests =new RodzicCheckRequests().execute(request).get();

        this.arraySpinner = new KolejkaRodzicMDTOResponse[] {
                test
        };
        Spinner s = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<KolejkaRodzicMDTOResponse> adapter = new ArrayAdapter<KolejkaRodzicMDTOResponse>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        s.setAdapter(adapter);
    }
}
