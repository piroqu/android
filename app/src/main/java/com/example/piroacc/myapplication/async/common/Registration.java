package com.example.piroacc.myapplication.async.common;

import android.os.AsyncTask;
import android.util.Log;

import com.example.piroacc.myapplication.model.dto.request.UserDataResponse;
import com.example.piroacc.myapplication.model.dto.response.parent.PositionForParentMDTOResponse;
import com.example.piroacc.myapplication.resources.Constant;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
public class Registration extends AsyncTask<String, Void, UserDataResponse> {
    private static final String USER_REGISTRATION_PATH = "praca/rest/common/register/";
    private static final String LOG_GET_POSITIONS = "USER REGISTRATION ";
    private String userEmail;
    private String userPassword;
    private String name;
    private String phoneNumber;
    private String role;
    @Override
    protected UserDataResponse doInBackground(String... params) {
        userEmail = params[0];
        userPassword = params[1];
        name = params[2];
        phoneNumber = params[3];
        role = params[4];
        return postRegister();
    }

    public UserDataResponse postRegister() {
        String url = Constant.HOST_ADDRES + USER_REGISTRATION_PATH + userEmail
                + "/" + userPassword + "/" + name + "/" + phoneNumber + "/" +
                role;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        Log.d(LOG_GET_POSITIONS, "SENDS : " + USER_REGISTRATION_PATH);
        UserDataResponse response = restTemplate.postForObject(url, null, UserDataResponse.class);
        return response;
    }
}
