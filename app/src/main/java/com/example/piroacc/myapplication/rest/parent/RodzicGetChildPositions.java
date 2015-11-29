package com.example.piroacc.myapplication.rest.parent;

import android.os.AsyncTask;
import android.util.Log;

import com.example.piroacc.myapplication.model.Pozycja;
import com.example.piroacc.myapplication.model.dto.request.RodzicMDTORequest;
import com.example.piroacc.myapplication.model.dto.response.RodzicMDTOResponse;
import com.example.piroacc.myapplication.resources.Constant;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class RodzicGetChildPositions extends AsyncTask<Integer, Void, List<Pozycja>> {

    private static final String CHILDS_POSITIONS = "praca/rest/parent/position/";

    private static final String LOG_PARENT_GET_CHILDS_POSITIONS = "PARENT GET CHILD POSITIONS ";

    public List<Pozycja> postByRestTemplate(Integer childId) {
        String url = Constant.HOST_ADDRES + CHILDS_POSITIONS + childId;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        Log.d(LOG_PARENT_GET_CHILDS_POSITIONS, "SENDS : " + childId + " to url address : " + url);
        ResponseEntity<Pozycja[]> response = restTemplate.postForEntity(url, null, Pozycja[].class);
        List<Pozycja> responseAsList = Arrays.asList(response.getBody());
        for (Pozycja tmp : responseAsList) {
            Log.d(LOG_PARENT_GET_CHILDS_POSITIONS, "RESULT : " + tmp.toString());
        }
        Log.d(LOG_PARENT_GET_CHILDS_POSITIONS, "RESULT : " + response);

        return responseAsList;
    }

    @Override
    protected List<Pozycja> doInBackground(Integer... params) {
        return postByRestTemplate(params[0]);
    }
}
