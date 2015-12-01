package com.example.piroacc.myapplication.async.parent;

import android.os.AsyncTask;
import android.util.Log;

import com.example.piroacc.myapplication.model.dto.request.RodzicMDTORequest;
import com.example.piroacc.myapplication.model.dto.response.parent.ParentChildMDTOResponse;
import com.example.piroacc.myapplication.resources.Constant;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by PiroACC on 2015-12-01.
 */
public class ParentGetChildrens extends AsyncTask<Integer,Void, List<ParentChildMDTOResponse>> {

    Integer parentId;
    private static final String PATH_CHILDRENS = "praca/rest/parent/childrens/";
    private static final String LOG_PARENT_LOGIN = "PARENT GET CHILDRENS";

    @Override
    protected List<ParentChildMDTOResponse> doInBackground(Integer... params) {
        parentId=params[0];
        return getForChildrens();
    }

    public List<ParentChildMDTOResponse> getForChildrens() {
        String url = Constant.HOST_ADDRES + PATH_CHILDRENS+parentId;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        Log.d(LOG_PARENT_LOGIN, "GET TO : " + url);
        ResponseEntity<ParentChildMDTOResponse[]> responseEntity = restTemplate.getForEntity(url, ParentChildMDTOResponse[].class);
        for(ParentChildMDTOResponse temp: responseEntity.getBody()){
            Log.d(LOG_PARENT_LOGIN, "GET DATA RECIEVED : " + temp);
        }
        return Arrays.asList(responseEntity.getBody());
    }

}
