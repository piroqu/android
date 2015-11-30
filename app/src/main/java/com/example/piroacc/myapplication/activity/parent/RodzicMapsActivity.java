package com.example.piroacc.myapplication.activity.parent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.piroacc.myapplication.R;
import com.example.piroacc.myapplication.helper.DatabaseHelper;
import com.example.piroacc.myapplication.model.Pozycja;
import com.example.piroacc.myapplication.model.Uzytkownik;
import com.example.piroacc.myapplication.model.dto.request.RodzicMDTORequest;
import com.example.piroacc.myapplication.model.dto.response.KolejkaRodzicMDTOResponse;
import com.example.piroacc.myapplication.rest.parent.RodzicCheckRequests;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class RodzicMapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private static final String DEBUG_LOG = "RODZIC MAPS ";

    private ArrayList<Pozycja> childsPozytions;

    private Uzytkownik currentParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rodzic_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        currentParent= DatabaseHelper.getInstance(this).getCurrentRodzic();
//        childsPozytions =  this.getIntent().getParcelableArrayListExtra("childenLocalizations");
//        Log.d(DEBUG_LOG, "CHILDRENS POSITIONS : " + childsPozytions.size());
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
        switch (item.getItemId()) {
            case R.id.parentChildrens:
                Toast.makeText(getBaseContext(), "You selected moje dzieci", Toast.LENGTH_SHORT).show();
                break;
            case R.id.parentSynchronize:
                Toast.makeText(getBaseContext(), "You selected snychronize", Toast.LENGTH_SHORT).show();
                RodzicMDTORequest request = new RodzicMDTORequest(currentParent);
                try {
                    List<KolejkaRodzicMDTOResponse> requests =new RodzicCheckRequests().execute(request).get();
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
