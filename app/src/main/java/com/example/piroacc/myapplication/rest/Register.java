package com.example.piroacc.myapplication.rest;

import android.os.AsyncTask;
import android.util.Log;

import com.example.piroacc.myapplication.fetchers.objects.PokemonGson;
import com.example.piroacc.myapplication.model.RodzicMDTO;
import com.google.gson.Gson;



import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Calendar;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by PiroACC on 2015-11-26.
 */
public class Register extends AsyncTask<String, Void, String> {

    public Reader post(String rodzicAsString) throws IOException {
        URL url = null;
        url = new URL("http://192.168.0.10:8080/praca/rest/parent/register");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setRequestProperty("Content-Type", "application/json");


        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        writer.write(String.valueOf(rodzicAsString));
        writer.flush();
        writer.close();
        os.close();
        InputStream in = conn.getInputStream();
        return new InputStreamReader(in);
    }

    public String createRodzicMDTO(){
        RodzicMDTO rodzicMDTO = new RodzicMDTO();
        rodzicMDTO.setDataUtworzenia("2015-11-24");
        rodzicMDTO.setEmail("zuisuper@android.pl");
        rodzicMDTO.setImie("android");
        rodzicMDTO.setStatus(true);
        rodzicMDTO.setHaslo("pass");
        rodzicMDTO.setNumerTelefonu("1234567890");
        Gson gson = new Gson();
        String rodzicMDTOString = gson.toJson(rodzicMDTO);
        Log.d("Register", "GSON PARSER:" + rodzicMDTOString);
        return rodzicMDTOString;
    }

    public String createRodzicMDTO(String imie,String email,String numerTelefonu,
                                   String haslo){
        RodzicMDTO rodzicMDTO = new RodzicMDTO();
        rodzicMDTO.setDataUtworzenia("2015-11-24");
        rodzicMDTO.setEmail(email);
        rodzicMDTO.setImie(imie);
        rodzicMDTO.setStatus(true);
        rodzicMDTO.setHaslo(haslo);
        rodzicMDTO.setNumerTelefonu(numerTelefonu);
        Gson gson = new Gson();
        String rodzicMDTOString = gson.toJson(rodzicMDTO);
        Log.d("Register", "GSON PARSER:" + rodzicMDTOString);
        return rodzicMDTOString;
    }

    @Override
    protected String doInBackground(String[] params) {
        String rodzicAsString =createRodzicMDTO(params[0],params[1],params[2],params[3]);
        try {
            post(rodzicAsString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
