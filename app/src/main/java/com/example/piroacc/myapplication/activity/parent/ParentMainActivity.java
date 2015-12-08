package com.example.piroacc.myapplication.activity.parent;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.piroacc.myapplication.R;
import com.example.piroacc.myapplication.async.parent.ParentGetChildPositions;
import com.example.piroacc.myapplication.async.parent.ParentGetChildrens;
import com.example.piroacc.myapplication.helper.DateParser;
import com.example.piroacc.myapplication.model.Position;
import com.example.piroacc.myapplication.model.Uzytkownik;
import com.example.piroacc.myapplication.model.dto.request.RodzicMDTORequest;
import com.example.piroacc.myapplication.model.dto.request.UserDataResponse;
import com.example.piroacc.myapplication.model.dto.response.parent.ParentChildMDTOResponse;
import com.example.piroacc.myapplication.model.dto.response.parent.PositionForParentMDTOResponse;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ParentMainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private static final String DEBUG_LOG = "RODZIC MAPS ";

    private List<Position> childsPozytions = new ArrayList<>();

    private Uzytkownik currentParent;

    private UserDataResponse userData;

    private List<ParentChildMDTOResponse> parentChildrens;


    private Spinner childrensSpinner;

    public TextView txtViewTimeFrom;
    public TextView textViewDateFrom;
    public TextView textViewTimeTo;
    public TextView textViewDateTo;

    public int fromHour;
    public int fromMinutes;
    public int toHour;
    public int toMinutes;
    public int fromYear;
    public int fromDay;
    public int fromMonth;
    public int toYear;
    public int toDay;
    public int toMonth;

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

    public void showPositionForSelectedChildren(View view) {
        Log.d("fromHour", String.valueOf(fromHour));
        Log.d("fromMinutes", String.valueOf(fromMinutes));
        Log.d("toHour", String.valueOf(toHour));
        Log.d("toMinutes", String.valueOf(toMinutes));
        Log.d("fromYear", String.valueOf(fromYear));
        Log.d("fromDay", String.valueOf(fromDay));
        Log.d("fromMonth", String.valueOf(fromMonth));
        Log.d("toYear", String.valueOf(toYear));
        Log.d("toDay", String.valueOf(toDay));
        Log.d("toMonth", String.valueOf(toMonth));
        ParentChildMDTOResponse selectedChildren = (ParentChildMDTOResponse) childrensSpinner.getSelectedItem();
        try {
            fromMonth++;// TODO temp rozwiazanie, miesiace liczone od 0
            toMonth++;
            String fromDate = fromYear + "-" + fromMonth + "-" + fromDay + " " + fromHour + ":" + fromMinutes + ":00";
            String toDate = toYear + "-" + toMonth + "-" + toDay + " " + toHour + ":" + toMinutes + ":00";
            fromMonth=0;
            toMonth=0;
            List<PositionForParentMDTOResponse> childrenPositions = new ParentGetChildPositions().
                    execute(String.valueOf(selectedChildren.getChildId()), fromDate, toDate).get();
            for (PositionForParentMDTOResponse temp : childrenPositions) {
                LatLng point = new LatLng(temp.getLatitude(), temp.getLongitude());
                String formattedDate = DateParser.parseDateForMapFormatDisplay(temp.getCreationDate());
                mMap.addMarker(new MarkerOptions().position(point).title(formattedDate));
                childsPozytions.add(new Position(temp));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void findUIElements() {
        txtViewTimeFrom =(TextView) findViewById(R.id.txtViewTime);
        textViewDateFrom =(TextView) findViewById(R.id.txtViewFromDay);
        textViewTimeTo =(TextView) findViewById(R.id.textViewTimeTo);
        textViewDateTo =(TextView) findViewById(R.id.textViewDateTo);

        childrensSpinner = (Spinner) findViewById(R.id.spinnerChildrens);
    }

    private void addChildrensToSpinner() {
        Spinner mySpinner = (Spinner) findViewById(R.id.spinnerChildrens);

// Setting a Custom Adapter to the Spinner
        mySpinner.setAdapter(new MyAdapter(ParentMainActivity.this, R.layout.custom,
                parentChildrens.toArray()));
        /*ArrayAdapter<ParentChildMDTOResponse> adapter = new ArrayAdapter<ParentChildMDTOResponse>(this,
                android.R.layout.simple_spinner_item, parentChildrens);
        childrensSpinner.setAdapter(adapter);*/
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
            case R.id.action_paint_polygon:
                drawPolylineOnMap();
        }
        return super.onOptionsItemSelected(item);
    }

    private void drawPolylineOnMap() {
        PolylineOptions rectOptions = new PolylineOptions();
        for (Position tempPos : childsPozytions) {
            rectOptions.add(new LatLng(tempPos.getLatitude(), tempPos.getLongitude()));
        }
        Polyline polyline = mMap.addPolyline(rectOptions);
    }

    public void showTimePickerFrom(View v) {
        DialogFragment newFragment = new TimePickerFrom();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void showTimePickerTo(View v) {
        DialogFragment newFragment = new TimePickerTo();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public class TimePickerFrom extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {


        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);


            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            fromHour = hourOfDay;
            fromMinutes = minute;
            String preparedText = hourOfDay + ":" + minute;
            txtViewTimeFrom.setText(preparedText);
            // Do something with the time chosen by the user
        }
    }

    public class TimePickerTo extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {


        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);


            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            toHour = hourOfDay;
            toMinutes = minute;
            String preparedText = hourOfDay + ":" + minute;
            textViewTimeTo.setText(preparedText);
            // Do something with the time chosen by the user
        }
    }

    public void showDatePickerTo(View v) {
        DialogFragment newFragment = new DatePickerTo();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showDatePickerFrom(View v) {
        DialogFragment newFragment = new DatePickerFrom();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public class DatePickerFrom extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            fromYear = year;
            fromMonth = month;
            month++;
            fromDay = day;
            String preparedDate = day+"/"+month+ "/"+year;
            textViewDateFrom.setText(preparedDate);
            // Do something with the date chosen by the user
        }
    }

    public class DatePickerTo extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            toYear = year;
            toMonth = month;
            month++;
            toDay = day;
            String preparedDate = day+"/"+month+ "/"+year;
            textViewDateTo.setText(preparedDate);
            // Do something with the date chosen by the user
        }
    }

    // Creating an Adapter Class
    public class MyAdapter extends ArrayAdapter {

        public ParentChildMDTOResponse langs[];

        public MyAdapter(Context context, int textViewResourceId,
                         Object[] objects) {
            super(context, textViewResourceId, objects);
            langs = (ParentChildMDTOResponse[]) objects;
        }

        public View getCustomView(int position, View convertView,
                                  ViewGroup parent) {

// Inflating the layout for the custom Spinner
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.custom, parent, false);

// Declaring and Typecasting the textview in the inflated layout
            TextView tvLanguage = (TextView) layout
                    .findViewById(R.id.tvLanguage);

// Setting the text using the array
            tvLanguage.setText( langs[position].toString());

// Setting the color of the text
            tvLanguage.setTextColor(Color.rgb(75, 180, 225));


// Setting Special atrributes for 1st element
            if (position == 0) {
// Setting the size of the text
                tvLanguage.setTextSize(20f);
// Setting the text Color
                tvLanguage.setTextColor(Color.BLACK);

            }

            return layout;
        }

        // It gets a View that displays in the drop down popup the data at the specified position
        @Override
        public View getDropDownView(int position, View convertView,
                                    ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        // It gets a View that displays the data at the specified position
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }
    }

}
