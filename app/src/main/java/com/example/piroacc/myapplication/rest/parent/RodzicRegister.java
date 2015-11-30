package com.example.piroacc.myapplication.rest.parent;

import android.os.AsyncTask;
import android.util.Log;

import com.example.piroacc.myapplication.helper.DateParser;
import com.example.piroacc.myapplication.model.Uzytkownik;
import com.example.piroacc.myapplication.model.dto.request.RodzicMDTORequest;
import com.example.piroacc.myapplication.model.dto.response.RodzicMDTOResponse;
import com.example.piroacc.myapplication.resources.Constant;
import com.google.gson.Gson;


import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

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
 * Created by PiroACC on 2015-11-26.
 */
public class RodzicRegister extends AsyncTask<RodzicMDTORequest, Void, RodzicMDTOResponse> {

    private static final String REGISTER = "praca/rest/parent/register";
    private static final String LOG_PARENT_REGISTER = "Parent Register ";

    public Reader post(String rodzicAsString) throws IOException {
        URL url = null;
        url = new URL(Constant.HOST_ADDRES+"praca/rest/parent/register");
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

    public RodzicMDTOResponse postByRestTemplate(RodzicMDTORequest rodzicMDTORequest) {
        String url = Constant.HOST_ADDRES + REGISTER;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        Log.d(LOG_PARENT_REGISTER, "SENDS : " + rodzicMDTORequest);
        RodzicMDTOResponse response = restTemplate.postForObject(url, rodzicMDTORequest, RodzicMDTOResponse.class);
        Log.d(LOG_PARENT_REGISTER, "RESULT : " + response);
        return response;
    }

    @Override
    protected RodzicMDTOResponse doInBackground(RodzicMDTORequest... params) {
        return postByRestTemplate(params[0]);
    }

/*    @Override
    protected String doInBackground(String[] params) {
        String rodzicAsString =createRodzicMDTO(params[0],params[1],params[2],params[3]);
        try {
            post(rodzicAsString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }*/
}
