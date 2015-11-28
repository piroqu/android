package com.example.piroacc.myapplication.rest.child;

import android.os.AsyncTask;
import android.util.Log;

import com.example.piroacc.myapplication.model.Pozycja;
import com.example.piroacc.myapplication.resources.Constant;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PiroACC on 2015-11-28.
 */
public class SynchronizeDataPositions extends AsyncTask <Pozycja, Void, Void>{

    private final static String SYNCHRONIZE_DATA_POSITION = "praca/rest/child/synchronize/";

    private static final String LOG_SYNCHRONIZE = "Synchronize data";

    public void post(Pozycja[] positions) throws IOException {
        Log.d(LOG_SYNCHRONIZE, "in POST");
        URL url = null;
        url = new URL(Constant.HOST_ADDRES + SYNCHRONIZE_DATA_POSITION);
        Log.d(LOG_SYNCHRONIZE, url.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();  //TODO dodac sprawdzanie polaczenia
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setRequestProperty("Content-Type", "application/json");
        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        Gson gson = new Gson();
        String positionsAsString = gson.toJson(positions);
        Log.d(LOG_SYNCHRONIZE, "PARSED DATA: "+ positionsAsString);
        writer.write(positionsAsString);
        writer.flush();
        writer.close();
        os.close();
        Log.d(LOG_SYNCHRONIZE, "REPOSNE: " + conn.getResponseMessage());
        InputStream in = conn.getInputStream();
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        Type listType = new TypeToken<ArrayList<Pozycja>>() {
        }.getType();
        List<Pozycja> response  = gson.fromJson(reader, listType) ;
        Log.d(LOG_SYNCHRONIZE, "REPONSE BODY:" + response.toString());

    }

    @Override
    protected Void doInBackground(Pozycja... params) {
        try {
            post(params);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
