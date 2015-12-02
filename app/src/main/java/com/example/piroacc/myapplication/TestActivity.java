package com.example.piroacc.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {
    // Declaring and typecasting a Spinner
    // Declaring the String Array with the Text Data for the Spinners
    String[] Languages = {"Select a Language", "C# Language", "HTML Language",
            "XML Language", "PHP Language"};
    // Declaring the Integer Array with resourse Id's of Images for the Spinners

    // Setting a Custom Adapter to the Spinner
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        // Declaring and typecasting a Spinner
        Spinner mySpinner = (Spinner) findViewById(R.id.test_spinner);

// Setting a Custom Adapter to the Spinner
        mySpinner.setAdapter(new MyAdapter(TestActivity.this, R.layout.custom,
                Languages));
    }

    // Creating an Adapter Class
    public class MyAdapter extends ArrayAdapter {

        public String langs[];

        public MyAdapter(Context context, int textViewResourceId,
                         String[] objects) {
            super(context, textViewResourceId, objects);
            langs = objects;
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
            tvLanguage.setText(langs[position]);

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
