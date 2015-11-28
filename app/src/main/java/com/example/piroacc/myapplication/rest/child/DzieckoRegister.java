package com.example.piroacc.myapplication.rest.child;

import android.os.AsyncTask;
import android.util.Log;

import com.example.piroacc.myapplication.helper.DateParser;
import com.example.piroacc.myapplication.model.dto.request.DzieckoMDTO;
import com.example.piroacc.myapplication.model.dto.response.DzieckoMDTOResponse;
import com.example.piroacc.myapplication.resources.Constant;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by PiroACC on 2015-11-27.
 */
public class DzieckoRegister extends AsyncTask<String, Void, DzieckoMDTOResponse> {

    private static final String REGISTER = "praca/rest/child/register";

    private static final String LOG_CHILD_REGISTER = "CHILD REGISTER: ";

    public DzieckoMDTOResponse post(String dzieckoAsString) throws IOException {
        URL url = null;
        DzieckoMDTOResponse dzieckoMDTOResponse = new DzieckoMDTOResponse();
        url = new URL(Constant.HOST_ADDRES + "praca/rest/child/register");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();  //TODO dodac sprawdzanie polaczenia
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
        if (checkResponseMessage(conn.getResponseMessage())) ;
        {
            InputStream in = conn.getInputStream();
            JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
            Gson gson = new Gson();
            dzieckoMDTOResponse = gson.fromJson(reader, DzieckoMDTOResponse.class);
            Log.d("Dziecko register: :", dzieckoMDTOResponse.toString());
        }
        return dzieckoMDTOResponse;
    }

    public DzieckoMDTOResponse postByRestTemplate(DzieckoMDTO dzieckoMDTO) {
// The connection URL
        String url = Constant.HOST_ADDRES + REGISTER;
// Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();
// Add the String message converter
//        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
// Make the HTTP GET request, marshaling the response to a String
        Log.d(LOG_CHILD_REGISTER, "SENDS : " + dzieckoMDTO);
        DzieckoMDTOResponse response = restTemplate.postForObject(url, dzieckoMDTO, DzieckoMDTOResponse.class);
        Log.d(LOG_CHILD_REGISTER, "RESULT : " + response);
        return response;
    }


    private boolean checkResponseMessage(String response) {
        Log.d("Dziecko register: :", response);
        if (response.equals("OK")) return true;
        else return false;
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

    public DzieckoMDTO createDziecko(String imie, String haslo) {
        DzieckoMDTO dzieckoMDTO = new DzieckoMDTO();
        dzieckoMDTO.setDataUtworzenia(DateParser.getCurrentParsedDateAsString());
        dzieckoMDTO.setImie(imie);
        dzieckoMDTO.setStatus(true);
        dzieckoMDTO.setHaslo(haslo);
//        Gson gson = new Gson();
        Log.d("DzieckoRegister", "CREATED DzieckoMDTO : " + dzieckoMDTO);
        return dzieckoMDTO;
    }

    @Override
    protected DzieckoMDTOResponse doInBackground(String... params) {
        DzieckoMDTO dzieckoMDTO = createDziecko(params[0],params[1]);
        return postByRestTemplate(dzieckoMDTO);
    }

 /*   @Override
    protected DzieckoMDTOResponse doInBackground(String... params) {
        String dzieckoMDTOAsString = createDzieckoMDTO(params[0], params[1]);
        try {
            DzieckoMDTOResponse response = post(dzieckoMDTOAsString);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }*/
}
