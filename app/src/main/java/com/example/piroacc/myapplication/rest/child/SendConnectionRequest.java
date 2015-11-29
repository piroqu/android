package com.example.piroacc.myapplication.rest.child;

import android.os.AsyncTask;

import com.example.piroacc.myapplication.model.dto.request.DzieckoMDTORequest;
import com.example.piroacc.myapplication.model.dto.response.DzieckoMDTOResponse;
import com.example.piroacc.myapplication.resources.Constant;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;


public class SendConnectionRequest extends AsyncTask<Object, Void, Void> {

    private static final String CONNECT_PATH = "praca/rest/child/connect/";

    public void postByRestTemplate(String parentId,DzieckoMDTORequest request) {
        String url = Constant.HOST_ADDRES + CONNECT_PATH + parentId;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        DzieckoMDTOResponse response = restTemplate.postForObject(url, request, DzieckoMDTOResponse.class);
    }

    @Override
    protected Void doInBackground(Object... params) {
        String parentId = (String) params[0];
        DzieckoMDTORequest dzieckoMDTORequest = (DzieckoMDTORequest) params[1];
        postByRestTemplate(parentId,dzieckoMDTORequest);
        return null;
    }
}
