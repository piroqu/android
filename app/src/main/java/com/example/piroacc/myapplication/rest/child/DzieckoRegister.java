package com.example.piroacc.myapplication.rest.child;

import android.os.AsyncTask;
import android.util.Log;

import com.example.piroacc.myapplication.helper.DateParser;
import com.example.piroacc.myapplication.model.dto.DzieckoMDTO;
import com.example.piroacc.myapplication.resources.Constant;
import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by PiroACC on 2015-11-27.
 */
public class DzieckoRegister extends AsyncTask<String, Void, String> {

    public Reader post(String dzieckoAsString) throws IOException {
        URL url = null;

        url = new URL(Constant.HOST_ADDRES + "praca/rest/child/register");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setRequestProperty("Content-Type", "application/json");


        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        writer.write(String.valueOf(dzieckoAsString));
        writer.flush();
        writer.close();
        os.close();
        InputStream in = conn.getInputStream();
        return new InputStreamReader(in);
    }

    public String createDzieckoMDTO(String imie, String haslo) {
        DzieckoMDTO dzieckoMDTO = new DzieckoMDTO();
        dzieckoMDTO.setDataUtworzenia(DateParser.getCurrentParsedDateAsString());
        dzieckoMDTO.setImie(imie);
        dzieckoMDTO.setStatus(true);
        dzieckoMDTO.setHaslo(haslo);
        Gson gson = new Gson();
        String dzieckoMDTOasString = gson.toJson(dzieckoMDTO);
        Log.d("DzieckoRegister", "GSON PARSER:" + dzieckoMDTOasString);
        return dzieckoMDTOasString;
    }

    @Override
    protected String doInBackground(String... params) {
        String dzieckoMDTOAsString = createDzieckoMDTO(params[0],params[1]);
        try {
            post(dzieckoMDTOAsString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
