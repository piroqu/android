package com.example.piroacc.myapplication.activity.parent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.piroacc.myapplication.R;
import com.example.piroacc.myapplication.async.parent.ParentGetChildrens;
import com.example.piroacc.myapplication.model.Dziecko;
import com.example.piroacc.myapplication.model.Pozycja;
import com.example.piroacc.myapplication.model.Uzytkownik;
import com.example.piroacc.myapplication.model.dto.request.RodzicMDTORequest;
import com.example.piroacc.myapplication.model.dto.request.UserDataResponse;
import com.example.piroacc.myapplication.model.dto.response.KolejkaRodzicMDTOResponse;
import com.example.piroacc.myapplication.async.parent.RodzicCheckRequests;
import com.example.piroacc.myapplication.async.parent.RodzicGetChildPositions;
import com.example.piroacc.myapplication.model.dto.response.parent.ParentChildMDTOResponse;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ParentMainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private static final String DEBUG_LOG = "RODZIC MAPS ";

    private ArrayList<Pozycja> childsPozytions;

    private Uzytkownik currentParent;

    private UserDataResponse userData;

    private List<ParentChildMDTOResponse> parentChildrens;

    private Spinner childrensSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rodzic_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        findUIElements();
        getUserData();
        initializeParentChildrens();
    }

    private void showPositionForSelectedChildren() {
        ParentChildMDTOResponse selectedChildren = (ParentChildMDTOResponse) childrensSpinner.getSelectedItem();
    }

    private void findUIElements() {
        childrensSpinner = (Spinner) findViewById(R.id.spinnerChildrens);
    }

    private void addChildrensToSpinner() {
        ArrayAdapter<ParentChildMDTOResponse> adapter = new ArrayAdapter<ParentChildMDTOResponse>(this,
                android.R.layout.simple_spinner_item, parentChildrens);
        childrensSpinner.setAdapter(adapter);
    }

    private void initializeParentChildrens() {
        try {
            parentChildrens = new ParentGetChildrens().execute(userData.getId()).get();
            addChildrensToSpinner();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void getUserData() {
        Intent i = getIntent();
        userData = (UserDataResponse) i.getParcelableExtra("user data");
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
/*        for (Pozycja temp : childsPozytions) {
            LatLng point = new LatLng(temp.getSzerokoscGeograficzna(), temp.getDlugoscGeograficzna());
            mMap.addMarker(new MarkerOptions().position(point).title("Marker"));
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        RodzicMDTORequest request = null;

        switch (item.getItemId()) {
            case R.id.parentChildrens:
                Toast.makeText(getBaseContext(), "You selected moje dzieci", Toast.LENGTH_SHORT).show();
                break;
            case R.id.parentSynchronize:
                Toast.makeText(getBaseContext(), "You selected snychronize", Toast.LENGTH_SHORT).show();
                request = new RodzicMDTORequest(currentParent);
                try {
                    List<KolejkaRodzicMDTOResponse> requests = new RodzicCheckRequests().execute(request).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                break;
            /**
             * co tu bedzie?
             * pobierz liste wszystkich dzieci - dodaj je do lokalnej bazy
             * wybierz pierwszy element
             * pobierz jego pozycje z serwera
             * wyswietl je
             */
            case R.id.parentTest:
                request = new RodzicMDTORequest(currentParent);
            /*    for(Uzytkownik child : childs){
                    DatabaseHelper.getInstance(this).addChildrenForParent(child);
                }*/
//                Uzytkownik selectedChild = childs.get(0);
                try {
                    List<Pozycja> childrenPositionsFromServer = new RodzicGetChildPositions().execute(1).get();
//                    List<Pozycja> selectedChildrenPositions = DatabaseHelper.getInstance(this).getChildsPosition(String.valueOf(selectedChild.getId()));
                    for (Pozycja temp : childrenPositionsFromServer) {
                        LatLng point = new LatLng(temp.getSzerokoscGeograficzna(), temp.getDlugoscGeograficzna());
                        mMap.addMarker(new MarkerOptions().position(point).title("Marker"));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.parentSynchronizeTest:
                request = new RodzicMDTORequest(currentParent);
                try {
                    List<KolejkaRodzicMDTOResponse> requests = new RodzicCheckRequests().execute(request).get();
                    for (KolejkaRodzicMDTOResponse tmp : requests) {
                        Dziecko dziecko = new Dziecko(tmp);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                break;


        }
        return super.onOptionsItemSelected(item);
    }

/*    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        try {
            List<Pozycja> positions = new RodzicGetChildPositions().execute(51).get();
            Log.d("POZYCJE POBRANE : ", String.valueOf(positions.size()));
            for (Pozycja temp : positions) {
                LatLng point = new LatLng(temp.getSzerokoscGeograficzna(), temp.getDlugoscGeograficzna());
                mMap.addMarker(new MarkerOptions().position(point).title("Marker"));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }*/
}
