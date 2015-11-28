package com.example.piroacc.myapplication.rest.child;

import android.os.AsyncTask;
import android.util.Log;

import com.example.piroacc.myapplication.PozycjaList;
import com.example.piroacc.myapplication.model.Pozycja;
import com.example.piroacc.myapplication.model.dto.response.DzieckoMDTOResponse;
import com.example.piroacc.myapplication.resources.Constant;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

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
import java.util.Arrays;
import java.util.List;

/**
 * Created by PiroACC on 2015-11-28.
 */
public class SynchronizeDataPositions extends AsyncTask <Pozycja, Void, Void>{

    private final static String SYNCHRONIZE_DATA_POSITION = "praca/rest/child/synchronize/";

    private static final String LOG_SYNCHRONIZE = "Synchronize data";

    public List<Pozycja> postByRestTemplate(Pozycja [] positions){
        // The connection URL
        String tempUserId = "1";
        String url = Constant.HOST_ADDRES + SYNCHRONIZE_DATA_POSITION +tempUserId;
// Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();
// Add the String message converter
//        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
// Make the HTTP GET request, marshaling the response to a String
        Log.d(LOG_SYNCHRONIZE, "SENDS : " + positions);
        ResponseEntity<Pozycja[]> response = restTemplate.postForEntity(url, positions, Pozycja[].class);
        List<Pozycja> responseAsList = Arrays.asList(response.getBody());
        for(Pozycja tmp : responseAsList){
            Log.d(LOG_SYNCHRONIZE, "RESULT : " +tmp.toString() );
        }
        return null;
    }

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
        postByRestTemplate(params);
        return null;
    }
}
