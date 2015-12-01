package com.example.piroacc.myapplication.async.parent;

import android.os.AsyncTask;
import android.util.Log;

import com.example.piroacc.myapplication.model.Pozycja;
import com.example.piroacc.myapplication.model.dto.response.parent.PositionForParentMDTOResponse;
import com.example.piroacc.myapplication.resources.Constant;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class ParentGetChildPositions extends AsyncTask<String, Void, List<PositionForParentMDTOResponse>> {

    private static final String CHILDS_POSITIONS = "praca/rest/parent/position/";

    private static final String LOG_GET_POSITIONS = "PARENT GET " + ParentGetChildPositions.class.getName();

    private String childId;
    private String fromDate;
    private String toDate;

    public List<PositionForParentMDTOResponse> getForPositions() {
        String url = Constant.HOST_ADDRES + CHILDS_POSITIONS + childId;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        Log.d(LOG_GET_POSITIONS, "SENDS : " + childId + " to url address : " + url);
        ResponseEntity<PositionForParentMDTOResponse[]> response = restTemplate.getForEntity(url, PositionForParentMDTOResponse[].class);
        List<PositionForParentMDTOResponse> responseAsList = Arrays.asList(response.getBody());
        for (PositionForParentMDTOResponse tmp : responseAsList) {
            Log.d(LOG_GET_POSITIONS, "RESULT : " + tmp.toString());
        }
        Log.d(LOG_GET_POSITIONS, "RESULT : " + response);
        return responseAsList;
    }

    public List<PositionForParentMDTOResponse> getForPositionsFromTo() {

        Log.d(LOG_GET_POSITIONS, "FROM URL : "+ fromDate);
        Log.d(LOG_GET_POSITIONS, "TO URL : "+ toDate);

        String url = Constant.HOST_ADDRES + CHILDS_POSITIONS + childId +"/" +fromDate +"/" +toDate;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        Log.d(LOG_GET_POSITIONS, "SENDS : " + childId + " to url address : " + url);
        ResponseEntity<PositionForParentMDTOResponse[]> response = restTemplate.getForEntity(url, PositionForParentMDTOResponse[].class);
        List<PositionForParentMDTOResponse> responseAsList = Arrays.asList(response.getBody());
        for (PositionForParentMDTOResponse tmp : responseAsList) {
            Log.d(LOG_GET_POSITIONS, "RESULT : " + tmp.toString());
        }
        Log.d(LOG_GET_POSITIONS, "RESULT : " + response);
        return responseAsList;
    }

    @Override
    protected List<PositionForParentMDTOResponse> doInBackground(String... params) {
        childId = params[0];
        if(params.length ==1)
        return getForPositions();
        else{
            fromDate = params[1];
            toDate = params[2];
            return getForPositionsFromTo();
        }
    }
}
