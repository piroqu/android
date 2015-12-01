package com.example.piroacc.myapplication.async.child;

import android.os.AsyncTask;
import android.util.Log;

import com.example.piroacc.myapplication.model.dto.request.DzieckoMDTORequest;
import com.example.piroacc.myapplication.model.dto.response.DzieckoMDTOResponse;
import com.example.piroacc.myapplication.resources.Constant;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by PiroACC on 2015-11-27.
 */
public class DzieckoRegister extends AsyncTask<DzieckoMDTORequest, Void, DzieckoMDTOResponse> {

    private static final String REGISTER = "praca/rest/child/register";

    private static final String LOG_CHILD_REGISTER = "CHILD REGISTER: ";

    public DzieckoMDTOResponse postByRestTemplate(DzieckoMDTORequest dzieckoMDTORequest) {
// The connection URL
        String url = Constant.HOST_ADDRES + REGISTER;
// Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();
// Add the String message converter
//        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
// Make the HTTP GET request, marshaling the response to a String
        Log.d(LOG_CHILD_REGISTER, "SENDS : " + dzieckoMDTORequest);
        DzieckoMDTOResponse response = restTemplate.postForObject(url, dzieckoMDTORequest, DzieckoMDTOResponse.class);
        Log.d(LOG_CHILD_REGISTER, "RESULT : " + response);
        return response;
    }


    private boolean checkResponseMessage(String response) {
        Log.d("Dziecko register: :", response);
        if (response.equals("OK")) return true;
        else return false;
    }


    @Override
    protected DzieckoMDTOResponse doInBackground(DzieckoMDTORequest... params) {
        return postByRestTemplate(params[0]);
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
