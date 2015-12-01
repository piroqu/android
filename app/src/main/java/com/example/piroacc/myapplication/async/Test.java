package com.example.piroacc.myapplication.async;

import android.os.AsyncTask;
import android.util.Log;

import com.example.piroacc.myapplication.model.Dziecko;
import com.example.piroacc.myapplication.resources.Constant;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by PiroACC on 2015-11-28.
 */
public class Test extends AsyncTask {
    public void testGET() {
// The connection URL
        String url = Constant.HOST_ADDRES + "praca/rest/parent";

// Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

// Add the String message converter
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

// Make the HTTP GET request, marshaling the response to a String
        String result = restTemplate.getForObject(url, String.class, "Android");
        Log.d("REST TEST ", "RESULT : " + result);
    }

    public void testPOST() {
// The connection URL
        String url = Constant.HOST_ADDRES + "praca/rest/parent/test";

// Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

// Add the String message converter
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

// Make the HTTP GET request, marshaling the response to a String
        Dziecko test = new Dziecko();
        test.setId(1);
        test.setImie("Zeofil");
        String request = "test request";
        String result = restTemplate.postForObject(url, request, String.class);

        Log.d("REST TEST ", "RESULT : " + result);
    }




    @Override
    protected Object doInBackground(Object[] params) {
        testPOST();
        return null;
    }
}
