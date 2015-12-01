package com.example.piroacc.myapplication.async.parent;

import android.os.AsyncTask;
import android.util.Log;

import com.example.piroacc.myapplication.model.dto.request.RodzicMDTORequest;
import com.example.piroacc.myapplication.resources.Constant;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by PiroACC on 2015-11-30.
 */
public class RodzicLogin extends AsyncTask<String, Void, RodzicMDTORequest> {

    private static final String LOGIN = "praca/rest/parent/login/";
    private static final String LOG_PARENT_LOGIN = "PARENT LOGIN ";


    public RodzicMDTORequest postByRestTemplate(String email, String haslo) {
        String url = Constant.HOST_ADDRES + LOGIN + email + "/" + haslo;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        Log.d(LOG_PARENT_LOGIN, "SENDS : " + url);
        RodzicMDTORequest response = restTemplate.getForObject(url, RodzicMDTORequest.class);
        Log.d(LOG_PARENT_LOGIN, "RESULT : " + response);
        return response;
    }

    @Override
    protected RodzicMDTORequest doInBackground(String... params) {
        String email = params[0];
        String haslo = params[1];
        return postByRestTemplate(email, haslo);
    }
}
