package com.example.piroacc.myapplication;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.piroacc.myapplication.helper.DatabaseHelper;
import com.example.piroacc.myapplication.helper.DateParser;
import com.example.piroacc.myapplication.model.Pozycja;
import com.example.piroacc.myapplication.model.Uzytkownik;
import com.example.piroacc.myapplication.rest.child.SynchronizeDataPositions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.List;

public class DzieckoLocationActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

    // LogCat tag
    private static final String TAG = MainActivity.class.getSimpleName();

    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;

    private Location mLastLocation;

    // Google client to interact with Google API
    private GoogleApiClient mGoogleApiClient;

    // boolean flag to toggle periodic location updates
    private boolean mRequestingLocationUpdates = false;

    private LocationRequest mLocationRequest;

    // Location updates intervals in sec
    private static int UPDATE_INTERVAL = 5000; // 5 sec
    private static int FATEST_INTERVAL = 2000; // 2 sec
    private static int DISPLACEMENT = 2; // 2 meters

    private TextView txtProvider;
    private TextView txtLat;
    private TextView txtLong;
    private Button btnRefresh;
    private Button btnSynchronzie;

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dziecko_location);
        txtProvider = (TextView) findViewById(R.id.txtProvider);
        txtLat = (TextView) findViewById(R.id.txtLatitude);
        txtLong = (TextView) findViewById(R.id.txtLongitude);
        btnRefresh = (Button) findViewById(R.id.btnRefresh);
        btnSynchronzie= (Button) findViewById(R.id.btnSynchronize);
        db=new DatabaseHelper(this);
               Log.d("DATABASE CREATOR", "z FirstScreenActivity");
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
        // First we need to check availability of play services
        if (checkPlayServices()) {
            // Building the GoogleApi client
            buildGoogleApiClient();
            createLocationRequest();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Resuming the periodic location updates
        if (mGoogleApiClient.isConnected() && mRequestingLocationUpdates) {
            startLocationUpdates();
        }
    }

    public void refresh(View view) {
        Log.d("REFRESH", "REFRESH PRESSED");
        displayLocation();
        startLocationUpdates();
    }
    public void synchronize(View view){
        Log.d("SYNC", "SYNC PRESSED");
        List<Pozycja> positionsToSync = DatabaseHelper.getInstance(this).getPositionsToSync();
        Pozycja[] stockArr = new Pozycja[positionsToSync.size()];
        stockArr = positionsToSync.toArray(stockArr);
        new SynchronizeDataPositions().execute(stockArr);
    }

    /**
     * Method to display the location on UI
     */
    private void displayLocation() {
        mLastLocation = LocationServices.FusedLocationApi
                .getLastLocation(mGoogleApiClient);

        if (mLastLocation != null) {
            double latitude = mLastLocation.getLatitude();
            double longitude = mLastLocation.getLongitude();

            Log.d("Localization:", "LAT: " + latitude);
            Log.d("Localization:", "LONG: " + longitude);
            txtLat.setText("" + latitude);
            txtLong.setText("" + longitude);
        } else {
            txtLat.setText("FAILED");
            txtLong.setText("FAILED");
        }
    }

    /**
     * Creating google api client object
     */
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
    }

    /**
     * Method to verify google play services on the device
     */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "This device is not supported.", Toast.LENGTH_LONG)
                        .show();
                finish();
            }
            return false;
        }
        return true;
    }

    /**
     * Method to toggle periodic location updates
     */
    private void togglePeriodicLocationUpdates() {
        if (!mRequestingLocationUpdates) {
            // Changing the button text

            mRequestingLocationUpdates = true;

            // Starting the location updates
            startLocationUpdates();

            Log.d(TAG, "Periodic location updates started!");

        } else {
            // Changing the button text

            mRequestingLocationUpdates = false;

            // Stopping the location updates
            stopLocationUpdates();

            Log.d(TAG, "Periodic location updates stopped!");
        }
    }

    /**
     * Starting the location updates
     */
    protected void startLocationUpdates() {
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);

    }
    /**
     * Stopping location updates
     */
    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this);
    }
    /**
     * Creating location request object
     */
    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FATEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
       // mLocationRequest.setSmallestDisplacement(DISPLACEMENT); // 10 meters
    }



    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    @Override
    public void onConnected(Bundle bundle) {
        displayLocation();
        if (mRequestingLocationUpdates) {
            startLocationUpdates();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = "
                + connectionResult.getErrorCode());
    }

    @Override
    public void onLocationChanged(Location location) {
        // Assign the new location
        mLastLocation = location;

        Log.d("LOCATION", " CHANGED ");
        Toast.makeText(getApplicationContext(), "Location changed!",
                Toast.LENGTH_SHORT).show();
        Log.d("LOCATION", " INSERT TO DATABASE ");
        Log.d("LOCATION", "POZYCJE przed insert : " + db.getPositions());
        insertPozycja(location.getLongitude(), location.getLatitude());
        Log.d("LOCATION", "POZYCJE po insert: " + db.getPositions());

        // Displaying the new location on UI
        displayLocation();
    }

    private void insertPozycja(double longitude,double latitude){
        DatabaseHelper db = new DatabaseHelper(this);
        db.insertPozycja(createPozycja(longitude, latitude));
    }

    private Pozycja createPozycja(double longitude, double latitude){
        Pozycja position= new Pozycja();
        position.setCzyZsynchronizowano(false);
        position.setData(DateParser.getCurrentParsedDateAsString());
        position.setDlugoscGeograficzna(longitude);
        position.setSzerokoscGeograficzna(latitude);
        position.setFkDzieckoId(DatabaseHelper.getInstance(this).getCurrentChildId());
        position.setFkDzieckoId(1);
        return position;
    }
}
