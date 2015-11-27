package com.example.piroacc.myapplication.rest.child;

import android.os.AsyncTask;
import android.util.Log;

import com.example.piroacc.myapplication.model.DzieckoMDTO;
import com.example.piroacc.myapplication.model.RodzicDzieckoMDTO;
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

public class SendConnectionRequest extends AsyncTask<Integer, Void, String> {
    @Override
    protected String doInBackground(Integer... params) {
        String request = createRodzicDzieckoMDTO(params[0],params[1]);
        try {
            post(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Reader post(String dzieckoAsString) throws IOException {
        URL url = null;

        url = new URL(Constant.HOST_ADDRES + "praca/rest/child/connect");
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


    public String createRodzicDzieckoMDTO(Integer idDziecko, Integer idRodzic) {
        RodzicDzieckoMDTO rodzicDzieckoMDTO = new RodzicDzieckoMDTO();
        rodzicDzieckoMDTO.setDzieckoId(idDziecko);
        rodzicDzieckoMDTO.setRodzicId(idRodzic);
        Gson gson = new Gson();
        String rodzicDzieckoMDTOasString = gson.toJson(rodzicDzieckoMDTO);
        Log.d("RodzicDzieckoRegister", "GSON PARSER:" + rodzicDzieckoMDTOasString);
        return rodzicDzieckoMDTOasString;
    }
}
