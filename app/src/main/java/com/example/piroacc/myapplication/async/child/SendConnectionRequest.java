package com.example.piroacc.myapplication.async.child;

import android.os.AsyncTask;
import android.widget.Toast;

import com.example.piroacc.myapplication.model.dto.request.DzieckoMDTORequest;
import com.example.piroacc.myapplication.model.dto.response.DzieckoMDTOResponse;
import com.example.piroacc.myapplication.model.dto.response.child.ConnectionMDTOResponse;
import com.example.piroacc.myapplication.resources.Constant;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;


public class SendConnectionRequest extends AsyncTask<Object, Void, ConnectionMDTOResponse> {

    private Integer childId;
    private String parentEmail;

    private static final String CONNECT_PATH = "praca/rest/child/connect/";

    public ConnectionMDTOResponse postByRestTemplate() {
        String url = Constant.HOST_ADDRES + CONNECT_PATH + childId+"/"+parentEmail;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        ConnectionMDTOResponse response = restTemplate.postForObject(url, null, ConnectionMDTOResponse.class);
        return response;
    }

    @Override
    protected ConnectionMDTOResponse doInBackground(Object... params) {
        childId = (Integer) params[0];
        parentEmail = (String) params[1];
        return postByRestTemplate();
    }
}
